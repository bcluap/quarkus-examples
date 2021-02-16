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
public class RestResourceL2 {

    private static final Logger log = LoggerFactory.getLogger(RestResourceL2.class);
    
    @Inject
    RestResourceL3 l3;
    
    @Path("l2/{id2}")
    @GET
    public String getA(@PathParam("id1") long id1, @PathParam("id2") long id2) {
        return  id1 + "." + id2;
    }
    
    @Path("{id2}")
    public RestResourceL3 getB(@PathParam("id2") long id2) {
        return l3;
    }

    

}
