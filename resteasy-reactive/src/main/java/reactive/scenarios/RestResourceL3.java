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
public class RestResourceL3 {

    private static final Logger log = LoggerFactory.getLogger(RestResourceL3.class);
    
    
    @Path("l3/{id3}")
    @GET
    public String getA(@PathParam("id1") long id1, @PathParam("id2") long id2, @PathParam("id3") long id3) {
        return id1 + "." + id2 + "." + id3;
    }
    
    @GET
    @Path("{id3}")
    @Produces(MediaType.TEXT_PLAIN)
    public String get(@PathParam("id1") long id1, @PathParam("id2") long id2, @PathParam("id3") long id3) {
        return id1 + "." + id2 + "." + id3;
    }

    

}
