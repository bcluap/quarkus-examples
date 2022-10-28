package reactive.scenarios;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
@Path("/test")
public class TestRootResource {

    private static final Logger log = LoggerFactory.getLogger(TestRootResource.class);
    
    @Inject
    RequestContext context;

    @GET
    @Path("{param}")
    public String go(@PathParam("param") String param) {
        log.info("Param is [{}]", param);
        return param;
    }

}
