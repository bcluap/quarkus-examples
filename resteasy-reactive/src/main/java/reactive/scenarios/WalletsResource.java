package reactive.scenarios;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class WalletsResource {

    private static final Logger log = LoggerFactory.getLogger(WalletsResource.class);
    
    @Inject
    WalletResource walletResource;

    @Path("{walletId}")
    public WalletResource getWalletResource(@PathParam("tenantId") long tenantId, @PathParam("walletId") long walletId) {
        log.info("We are getting wallet with id [{}] under tenant [{}]", walletId, tenantId);
        return walletResource;
    }


}
