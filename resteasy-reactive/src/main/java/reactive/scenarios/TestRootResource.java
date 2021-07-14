package reactive.scenarios;

import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import java.util.UUID;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
@Blocking
@Transactional
@Path("/test")
public class TestRootResource {

    private static final Logger log = LoggerFactory.getLogger(TestRootResource.class);

    @Inject
    VertxMutinyHttpClient client;
    @Inject
    EntityManager em;

    @Path("/works")
    @GET
    public String getWorks() {
        try {
            em.createNativeQuery("INSERT into demo_table values (?)").setParameter(1, UUID.randomUUID().toString()).executeUpdate();
            Uni<String> uni = client.doHttpMethod("GET", "http://localhost:8000/test/myself", null);
            String res = uni.await().indefinitely();
            return res;
        } catch (Exception e) {
            log.warn("Error: ", e);
            throw new RuntimeException(e);
        }
    }
    
    @Path("/fails")
    @GET
    public String getFails() {
        try {
            em.createNativeQuery("INSERT into demo_table values (?)").setParameter(1, UUID.randomUUID().toString()).executeUpdate();
            Uni<String> uni = client.doHttpMethod("GET", "http://localhost:8000/test/myself", null);
            String res = uni.subscribeAsCompletionStage().get();
            return res;
        } catch (Exception e) {
            log.warn("Error: ", e);
            throw new RuntimeException(e);
        }

    }
    
    @Path("/myself")
    @GET
    public String getMyself() {
        return "X";
    }

}
