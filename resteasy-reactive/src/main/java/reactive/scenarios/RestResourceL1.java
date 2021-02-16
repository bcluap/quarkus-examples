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
public class RestResourceL1 {

    private static final Logger log = LoggerFactory.getLogger(RestResourceL1.class);
    
    @Inject
    RestResourceL2 l2;
    
    @Path("/sr/{id1}")
    public RestResourceL2 get(@PathParam("id1") long id1) {
        return l2;
    }

    

}
