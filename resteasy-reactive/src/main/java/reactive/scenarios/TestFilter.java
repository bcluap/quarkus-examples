/* Copyright (C) Jini Guru Technologies (Mauritius) Limited - Company No. : 154309  - All Rights Reserved
 * Unauthorized copying of this file or any of its contents, via any medium is strictly prohibited
 * Proprietary and confidential
 */
package reactive.scenarios;

import io.opentracing.Span;
import io.opentracing.Tracer;
import java.io.IOException;
import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.Interceptor;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author paul
 */
@Provider
@Priority(Interceptor.Priority.LIBRARY_AFTER + 200)
public class TestFilter implements ContainerRequestFilter, ContainerResponseFilter, ReaderInterceptor, WriterInterceptor {

    private static final Logger log = LoggerFactory.getLogger(TestFilter.class);

    @Inject
    Tracer tracer;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        requestContext.setProperty("span", tracer.activeSpan());
        tracer.activeSpan().setTag("filterInTestTag", "TestValue");
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        ((Span) requestContext.getProperty("span")).setTag("filterOutTestTag", "TestValue");
    }

    @Override
    public Object aroundReadFrom(ReaderInterceptorContext ric) throws IOException, WebApplicationException {
        ((Span) ric.getProperty("span")).setTag("aroundReadFromTestTag", "TestValue");
        return ric.proceed();
    }

    @Override
    public void aroundWriteTo(WriterInterceptorContext wic) throws IOException, WebApplicationException {
        // Sleep a bit to simulate processing a response and highlight that the span is closed by another thread around now
        try {
            Thread.sleep(100);
        } catch (Exception e) {
        }
        log.info("Writing tag in aroundWriteTo");
        ((Span) wic.getProperty("span")).setTag("aroundWriteToTestTag", "TestValue");
        wic.proceed();
    }

}
