package reactive.scenarios;

import io.smallrye.common.annotation.Blocking;
import io.smallrye.common.constraint.Assert;
import io.smallrye.mutiny.Multi;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
@Path("/test")
public class TestResource {

    private static final Logger log = LoggerFactory.getLogger(TestResource.class);

    @GET
    @Blocking
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Multi<Long> test() {
        Stream<Long> stream = new Random().longs(1000000).boxed();
        stream = stream.peek((s) -> {
            log.warn("Here");
            if (!Thread.currentThread().getName().contains("executor")) {
                throw new IllegalStateException("I am on thread " + Thread.currentThread().getName());
            }
        });
        return Multi.createFrom().items(stream);

    }

}
