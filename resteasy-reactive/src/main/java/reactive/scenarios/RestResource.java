/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reactive.scenarios;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author paul
 */
@ApplicationScoped
@Path("/")
public class RestResource {

    private static final Logger log = LoggerFactory.getLogger(RestResource.class);
    
    
    @GET
    @Path("dt")
    @Produces(MediaType.APPLICATION_JSON)
    public MyData get() {
        return new MyData();
    }

    

}
