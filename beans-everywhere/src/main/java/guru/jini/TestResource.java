package guru.jini;

import io.smallrye.common.annotation.RunOnVirtualThread;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("test1")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
@RunOnVirtualThread
public class TestResource {
    
    private static final Logger log = LoggerFactory.getLogger(TestResource.class);
    
    @Inject
    AsBean1 asbean;
    
    @GET
    @Path("/go")
    @Produces(MediaType.TEXT_PLAIN)
    public String go(@QueryParam("beanCalls") int calls) {
        asbean.go(calls);
        log.warn("Thread is virtual [{}]", Thread.currentThread().isVirtual());
        return "Hello world";
    }
    
    
}
