/* Copyright (C) Jini Guru Technologies (Mauritius) Limited - Company No. : 154309  - All Rights Reserved
 * Unauthorized copying of this file or any of its contents, via any medium is strictly prohibited
 * Proprietary and confidential
 */
package reactive.scenarios;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import javax.annotation.Priority;
import javax.interceptor.Interceptor;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClientConfig;
import org.asynchttpclient.Dsl;
import org.asynchttpclient.Request;
import org.asynchttpclient.RequestBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author paul
 */
@Provider
@Priority(Interceptor.Priority.LIBRARY_AFTER + 150)
public class SuspendingTestFilter implements ContainerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(SuspendingTestFilter.class);
    private static Method suspendMethod;
    private static AsyncHttpClient asyncclient;

    static {
        init();
    }

    static void init() {

        DefaultAsyncHttpClientConfig.Builder clientBuilder = Dsl.config()
                .setMaxRequestRetry(0)
                .setIoThreadsCount(2);

        asyncclient = Dsl.asyncHttpClient(clientBuilder);
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        log.warn("In filter");
        try {
            if (suspendMethod == null) {
                suspendMethod = requestContext.getClass().getMethod("suspend");
            }
            suspendMethod.invoke(requestContext);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        MultivaluedMap<String, String> proxyHeaders = makeProxyHeaders(requestContext);

        try {
        doHttpMethodForProxying(requestContext.getMethod(), "https://jsonplaceholder.typicode.com/posts",
                new LoggingInputStream(requestContext.getEntityStream()),
                requestContext.getUriInfo().getQueryParameters(),
                proxyHeaders,
                Duration.ofMillis(10000),
                requestContext.getLength())
                .thenAccept((Response response) -> {
                    requestContext.abortWith(response);
                })
                .exceptionally((Throwable e) -> {
                    log.warn("Error", e);
                    requestContext.abortWith(Response.status(400).type(MediaType.TEXT_PLAIN).entity(e.toString()).build());
                    return null;
                });
        } catch (Exception e) {
            log.warn("Error", e);
        }
        log.warn("Out filter");
    }

    public CompletionStage<Response> doHttpMethodForProxying(String httpMethod, String url, InputStream requestData,
            MultivaluedMap<String, String> queryParams, MultivaluedMap<String, String> headers, Duration socketTimeout,
            int contentLength) throws IOException {

        
        log.warn("In doHttpMethodForProxying");
        int timeoutMs = (int) socketTimeout.toMillis();
        // DOnt follow redirects as the proxy itself
        RequestBuilder requestBuilder = new RequestBuilder(httpMethod)
                .setReadTimeout(timeoutMs)
                .setFollowRedirect(false)
                .setRequestTimeout(timeoutMs).setUrl(url);
        if (queryParams != null) {
            for (Map.Entry<String, List<String>> entry : queryParams.entrySet()) {
                requestBuilder.addQueryParam(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }
        if (headers == null) {
            headers = new MultivaluedHashMap<>(4, 1);
        }

        requestBuilder.setBody(requestData);

        for (Map.Entry<String, List<String>> header : headers.entrySet()) {
            for (String object : header.getValue()) {
                requestBuilder.addHeader(header.getKey(), object);
            }
        }

        Request request = requestBuilder.build();
        log.warn("Passing to async http client");
        CompletableFuture<org.asynchttpclient.Response> cfAsyncHttpResponse = asyncclient
                .executeRequest(request)
                .toCompletableFuture();

        return cfAsyncHttpResponse
                .thenApply((org.asynchttpclient.Response response) -> {
                    return Response.status(response.getStatusCode()).entity(response.getResponseBodyAsStream()).build();
                });

    }
    
     private MultivaluedMap<String, String> makeProxyHeaders(ContainerRequestContext requestContext) {

        requestContext.getHeaders().remove("connection");
        requestContext.getHeaders().remove("host");
        requestContext.getHeaders().remove("transfer-encoding");
        // This line causes the issue on CR1
        requestContext.getHeaders().remove("content-length");
        requestContext.getHeaders().remove("expect");
        requestContext.getHeaders().remove("origin");
        requestContext.getHeaders().remove("referer");
        requestContext.getHeaders().remove("sec-ch-ua");
        requestContext.getHeaders().remove("sec-ch-ua-mobile");
        requestContext.getHeaders().remove("sec-fetch-dest");
        requestContext.getHeaders().remove("sec-fetch-mode");
        requestContext.getHeaders().remove("sec-fetch-site");
        requestContext.getHeaders().remove("authority");
        
        return requestContext.getHeaders();
    }
     

}
