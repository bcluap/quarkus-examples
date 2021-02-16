/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reactive.scenarios;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author paul
 */
@ApplicationScoped
public class LeavesResource {

    private static final Logger log = LoggerFactory.getLogger(LeavesResource.class);
    
    
    
    @GET
    @Path("{leafId}")
    @Produces(MediaType.TEXT_PLAIN)
    public String get(@PathParam("treeId") long treeId, @PathParam("branchId") long branchId, @PathParam("leafId") long leafId) {
        log.info("I see tree Id [{}] and branch Id [{}] and leaf id [{}]", treeId, branchId, leafId);
        return treeId + "." + branchId + "." + leafId;
    }

    

}
