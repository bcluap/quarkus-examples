/* Copyright (C) Jini Guru Technologies (Mauritius) Limited - Company No. : 154309  - All Rights Reserved
 * Unauthorized copying of this file or any of its contents, via any medium is strictly prohibited
 * Proprietary and confidential
 */
package reactive.scenarios;

import java.io.IOException;
import java.util.List;
import javax.annotation.Priority;
import javax.interceptor.Interceptor;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author paul
 */
@Provider
@Priority(Interceptor.Priority.PLATFORM_BEFORE + 100)
public class ExamplePostMatchFilter implements ContainerRequestFilter, ContainerResponseFilter {

    private static final Logger log = LoggerFactory.getLogger(ExamplePostMatchFilter.class);

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        /*
        NOTE: In reactive mode you will see that calls to sub resources go through this point twice
        java.lang.IllegalStateException: Why has this request been through the filter twice when we are in reactive mode using sub resource? -- /sub-resource/abc
        */
        if (requestContext.getProperty("been.here") != null) {
            throw new IllegalStateException("Why has this request been through the filter twice when we are in reactive mode using sub resource? -- " + requestContext.getUriInfo().getPath());
        }

        requestContext.setProperty("been.here", Boolean.TRUE);

    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        /*
        NOTE: In reactive mode an NPE will be thrown
        java.lang.NullPointerException: Cannot invoke "org.jboss.resteasy.reactive.server.mapping.RuntimeResource.getClassPath()" because "this.target" is null
        */
        List<Object> resources = requestContext.getUriInfo().getMatchedResources();
    }


}
