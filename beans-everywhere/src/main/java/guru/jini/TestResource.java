package guru.jini;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("test1")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class TestResource {
    
    @Inject
    AsBean1 asbean;
    
    @GET
    @Path("/go")
    @Produces(MediaType.TEXT_PLAIN)
    public String go(@QueryParam("beanCalls") int calls) {
        asbean.go(calls);
        return "Hello world";
    }
    
    
}
