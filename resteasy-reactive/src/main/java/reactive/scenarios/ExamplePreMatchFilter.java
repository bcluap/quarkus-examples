/* Copyright (C) Jini Guru Technologies (Mauritius) Limited - Company No. : 154309  - All Rights Reserved
 * Unauthorized copying of this file or any of its contents, via any medium is strictly prohibited
 * Proprietary and confidential
 */
package reactive.scenarios;

import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author paul
 */
@Provider
@PreMatching
public class ExamplePreMatchFilter implements ContainerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(ExamplePreMatchFilter.class);

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        if (requestContext.getHeaderString("prematchresponse") != null) {
            /*
            
            // If an abort is done here with more than 30463 bytes and the PostMatchFilter implements aroundWriteTo then we get this error.
            
            Caused by: java.io.IOException: Failed to write
	at org.jboss.resteasy.reactive.server.vertx.ResteasyReactiveOutputStream.write(ResteasyReactiveOutputStream.java:106)
	at org.jboss.resteasy.reactive.server.vertx.ResteasyReactiveOutputStream.writeBlocking(ResteasyReactiveOutputStream.java:223)
	at org.jboss.resteasy.reactive.server.vertx.ResteasyReactiveOutputStream.close(ResteasyReactiveOutputStream.java:276)
	... 77 more
            Caused by: java.lang.UnsupportedOperationException: direct buffer
	at io.netty.buffer.PooledUnsafeDirectByteBuf.array(PooledUnsafeDirectByteBuf.java:226)
	at org.jboss.resteasy.reactive.server.vertx.ResteasyReactiveOutputStream.write(ResteasyReactiveOutputStream.java:90)
	... 79 more
            
            Anything less than 30464 is fine
             */
            requestContext.abortWith(Response.ok().type(MediaType.APPLICATION_OCTET_STREAM).entity(new byte[30464]).build());
        }
    }

}
