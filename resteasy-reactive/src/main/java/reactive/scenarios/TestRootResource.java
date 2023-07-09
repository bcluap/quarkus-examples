package reactive.scenarios;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
@Transactional
@Path("/test")
public class TestRootResource {

    private static final Logger log = LoggerFactory.getLogger(TestRootResource.class);
    
    @GET
    public void timeoutNoBody() {
        log.warn("HERE");
    }


}
