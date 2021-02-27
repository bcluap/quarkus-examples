package reactive.scenarios;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class WalletResource {

    private static final Logger log = LoggerFactory.getLogger(WalletResource.class);
    
    @GET
    public String getWallet(@PathParam("tenantId") long tenantId, @PathParam("walletId") long walletId) {
        log.info("I am wallet with id [{}] under tenant [{}]", walletId, tenantId);
        return tenantId + "-" + walletId;
    }

}
