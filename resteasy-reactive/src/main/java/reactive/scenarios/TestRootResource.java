package reactive.scenarios;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Scope;
import io.smallrye.common.vertx.VertxContext;
import io.smallrye.mutiny.Uni;
import io.vertx.core.Context;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.RequestOptions;
import io.vertx.mutiny.core.http.HttpClient;
import io.vertx.mutiny.core.http.HttpClientRequest;
import io.vertx.mutiny.core.http.HttpClientResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.ContextNotActiveException;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.control.ActivateRequestContext;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
@Transactional
@Path("/")
public class TestRootResource {

    private static final Logger log = LoggerFactory.getLogger(TestRootResource.class);

    @Inject
    Vertx vertx;
    @Inject
    Tracer tracer;

    @GET
    @Path("/doesnotwork")
    public String go1() throws InterruptedException {
        Thread t = new Thread(() -> {
            doVertxWebCall(false);
        });
        t.start();
        t.join();
        return "Hello World!";
    }

    @GET
    @Path("/doeswork")
    public String go2() throws InterruptedException {
        Thread t = new Thread(() -> {
            doVertxWebCall(true);
        });
        t.start();
        t.join();
        return "Hello World!";
    }

    @GET
    @Path("/doesworkaswell")
    public String go3() throws InterruptedException {
        doVertxWebCall(false);
        return "Hello World!";
    }

    public void doVertxWebCall(boolean runResponseInDuplicatedContext) {

        tracer.spanBuilder("Test").startSpan().makeCurrent();
        Span mySpan = Span.current();
        log.info("I have my own span with id [{}]", mySpan.getSpanContext().getSpanId());
        HttpClient httpClient = io.vertx.mutiny.core.Vertx.newInstance(vertx).createHttpClient();
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.setAbsoluteURI("https://www.google.com/");
        requestOptions.setMethod(HttpMethod.GET);
        requestOptions.addHeader("aaa", "bbb");
        Uni<HttpClientResponse> responseUni = httpClient
                .request(requestOptions)
                .onItem()
                .transformToUni((HttpClientRequest request) -> {
                    return request.send();
                });
        responseUni.onItem().invoke((HttpClientResponse response) -> {
            response.statusCode();
            log.info("Now processing response. I'm on eventloop thread inside a vertx context. My span is [{}]", Span.current());
            if (!VertxContext.isOnDuplicatedContext()) {
                log.info("I am not in a duplicated context");
                Context vertxContext = vertx.getOrCreateContext();
                if (vertxContext != null) {
                    log.info("But I am in a vertx Context [{}]", vertxContext);
                } else {
                    log.info("I am not in a vertx context");
                }
            } else {
                log.info("I am in a duplicated context");
            }
            log.info("Making the span from the request leg the current span. Using hack [{}]", runResponseInDuplicatedContext);

            if (runResponseInDuplicatedContext) {
                VertxContext.getOrCreateDuplicatedContext().runOnContext((x) -> {
                    makeRequestSpanCurrent(mySpan);
                });
            } else {
                makeRequestSpanCurrent(mySpan);
            }
        }).await().indefinitely();
    }

    public void makeRequestSpanCurrent(Span span) {
        
        log.info("Am I in a request context [{}]", amInRequestContext());
        try (Scope scope = span.makeCurrent()) {
            log.info("My span is [{}]", Span.current());
            if (!span.getSpanContext().getSpanId().equals(Span.current().getSpanContext().getSpanId())) {
                log.warn("This is wrong! The current span Id should be [{}] but is [{}] and I probably have no MDC data", span.getSpanContext().getSpanId(), Span.current().getSpanContext().getSpanId());
            } else {
                log.warn("This is correct! The current span id is [{}] and I probably have MDC data", span.getSpanContext().getSpanId());
            }
        }
    }
    
    public boolean amInRequestContext() {
        try {
            return CDI.current().getBeanManager().getContext(RequestScoped.class).isActive();
        } catch (ContextNotActiveException | IllegalArgumentException e) {
            return false;
        }
    }
}
