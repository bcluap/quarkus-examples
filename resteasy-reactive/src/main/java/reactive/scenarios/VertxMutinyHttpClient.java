/*
 * Copyright (C) Jini Guru Technologies (Mauritius) Limited - Company No. : 154309 - All Rights Reserved Unauthorized copying of
 * this file or any of its contents, via any medium is strictly prohibited Proprietary and confidential
 */
package reactive.scenarios;

import io.quarkus.arc.Unremovable;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpVersion;
import io.vertx.core.http.RequestOptions;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.core.buffer.Buffer;
import io.vertx.mutiny.core.http.HttpClient;
import io.vertx.mutiny.core.http.HttpClientRequest;
import io.vertx.mutiny.core.http.HttpClientResponse;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of interface.
 *
 * @author Paul Carter-Brown
 */
@Singleton
@Unremovable
public class VertxMutinyHttpClient {

    private static final Logger log = LoggerFactory.getLogger(VertxMutinyHttpClient.class);
    private HttpClient httpClient;

    VertxMutinyHttpClient(io.vertx.core.Vertx vertx) {
        log.info("Using Vert.x Mutiny based Async Web Client");

        HttpClientOptions options = new HttpClientOptions();

        options
                .setSsl(true)
                .setKeepAlive(true)
                .setMaxPoolSize(1000)
                .setUseAlpn(true)
                .setHttp2MaxPoolSize(1000)
                .setHttp2MultiplexingLimit(20)
                .setAlpnVersions(Arrays.asList(HttpVersion.HTTP_2, HttpVersion.HTTP_1_1));
        httpClient = Vertx.newInstance(vertx).createHttpClient(options);
    }

    public Uni<String> doHttpMethod(String httpMethod, String url, String body) {

        log.debug("In doHttpMethod with VertX Mutiny HTTP client [{}]", url);

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.setTimeout(30000);
        requestOptions.setAbsoluteURI(url);
        requestOptions.setMethod(HttpMethod.valueOf(httpMethod));

        Uni<HttpClientRequest> requestUni = httpClient.request(requestOptions);

        Uni<HttpClientResponse> responseUni = requestUni.onItem().transformToUni((HttpClientRequest request) -> {
            log.debug("Sending request body");
            return body == null ? request.send() : request.send(body);
        });

        Uni<String> bufferedResult = responseUni.onItem().transformToUni((HttpClientResponse response) -> {
            log.debug("Got initial response headers from call to [{}]. Communicating with server using [{}]", url, response.version());
            return response.body().onItem().transform((Buffer b)-> {
                return b.toString();
            });
        });

        return bufferedResult;
    }


}
