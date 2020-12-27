/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reactive.scenarios;

import io.smallrye.common.annotation.Blocking;
import java.math.BigDecimal;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author paul
 */
@ApplicationScoped
@Path("/")
public class RestResource {

    @Inject
    RestSubResource restSubResource;


    /*
    NOTE: The name parameter is not bound in the sub resource when using reactive mode
    */
    @Path("sub-resource/{name}")
    public RestSubResource getSubResource(@PathParam("name") String name) {
        return restSubResource;
    }

    /*
    NOTE: In reactive mode this method will cause the build will fail
    Build step io.quarkus.resteasy.reactive.server.deployment.ResteasyReactiveProcessor#setupEndpoints threw an exception: java.lang.RuntimeException: java.lang.RuntimeException: 
    Failed to process method reactive.scenarios.RestResource#java.lang.String doesNotBuildInReactiveMode(java.util.List<reactive.scenarios.SomeClass> inList)
     */
    
    @POST
    @Path("/listofsomeclass")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String doesNotBuildInReactiveMode(List<SomeClass> inList) {
        return "OK";
    }

    /*
    NOTE: In reactive mode Quarkus will throw an error when booting
    Caused by: java.lang.ClassNotFoundException: java.math.BigDecimal$quarkusrestparamConverter$
     */

    
    @GET
    @Path("/bigdecimal/{x}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public BigDecimal doesNotBootInReactiveMode(@PathParam("x") BigDecimal x) {
        return x;
    }
    
    
    /*
    NOTE: This returns a string with escaped JSON in reactive mode
    */
    @POST
    @Path("/stringarray")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> returnsStringInReactiveMode(List<String> test) {
        return test;
    }

    @GET
    @Blocking
    @Path("/dummypath")
    @Consumes(MediaType.WILDCARD)
    @Produces(MediaType.WILDCARD)
    public String dummy() {
        return "OK";
    }

}
