/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reactive.scenarios;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author paul
 */
@ApplicationScoped
public class RestSubResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String doesNotWorkInReactiveMode5(@PathParam("name") String name) {
        /*
        NOTE: In reactive mode, name is not bound and ends up being null. Seems to be due to this being a sub resource
         */
        return name;
    }

}
