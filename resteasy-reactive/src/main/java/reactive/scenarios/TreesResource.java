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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author paul
 */
@ApplicationScoped
@Path("/")
public class TreesResource {

    private static final Logger log = LoggerFactory.getLogger(TreesResource.class);
    
    @Inject
    BranchesResource branches;
    
    @Path("/trees/{treeId}/branches")
    public BranchesResource getBranchesOnTree(@PathParam("treeId") long treeId) {
        log.info("I see tree Id [{}]", treeId);
        return branches;
    }

    

}
