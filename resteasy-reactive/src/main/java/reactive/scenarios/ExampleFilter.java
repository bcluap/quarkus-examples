/* Copyright (C) Jini Guru Technologies (Mauritius) Limited - Company No. : 154309  - All Rights Reserved
 * Unauthorized copying of this file or any of its contents, via any medium is strictly prohibited
 * Proprietary and confidential
 */
package reactive.scenarios;

import java.io.IOException;
import javax.annotation.Priority;
import javax.interceptor.Interceptor;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author paul
 */
@Provider
@Priority(Interceptor.Priority.APPLICATION + 100)
public class ExampleFilter implements ContainerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(ExampleFilter.class);

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        log.warn("Has request body [{}], Length:  [{}], Inputstream Class [{}], Data [{}]",
                requestContext.hasEntity(), requestContext.getLength(), requestContext.getEntityStream().getClass(), requestContext.getEntityStream().readAllBytes());
    }
}
