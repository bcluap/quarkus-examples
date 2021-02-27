package reactive.scenarios;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class TenantResource {

    private static final Logger log = LoggerFactory.getLogger(TenantResource.class);


    @Inject
    WalletsResource walletsResource;


    @Path("wallets")
    public WalletsResource getWallets(@PathParam("tenantId") long tenantId ) {
        log.info("In get wallets resource for tenant [{}]", tenantId);
        return walletsResource;
    }


}
