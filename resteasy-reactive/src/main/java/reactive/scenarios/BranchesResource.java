/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reactive.scenarios;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
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
public class BranchesResource {

    private static final Logger log = LoggerFactory.getLogger(BranchesResource.class);
    
    @Inject
    LeavesResource leaves;
    
    @GET
    @Path("{branchId}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getBranch(@PathParam("treeId") long treeId, @PathParam("branchId") long branchId) {
        log.info("I see tree Id [{}] and branch Id [{}]", treeId, branchId);
        return treeId + "." + branchId;
    }
    
    @Path("{branchId}/leaves")
    public LeavesResource getLeavesOnBranch(@PathParam("treeId") long treeId, @PathParam("branchId") long branchId) {
        log.info("I see tree Id [{}] and branch Id [{}]", treeId, branchId);
        return leaves;
    }

    

}
