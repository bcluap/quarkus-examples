package reactive.scenarios;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
@Path("/tenants")
public class TenantsResource {

    private static final Logger log = LoggerFactory.getLogger(TenantsResource.class);

    @Inject
    TenantResource tenantResource;
    @Context
    UriInfo info;

    @Path("{tenantId}")
    public TenantResource getTenantResource(@PathParam("tenantId") long tenantId) {
        log.info("We are in tenant [{}]", tenantId);
        return tenantResource;
    }
    
    
    
    @GET
    @Path("test")
    public String getTest() {
        return info.getRequestUri().toString();
    }

}
