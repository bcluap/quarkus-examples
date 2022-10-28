/*
 * Copyright (C) Jini Guru Technologies (Mauritius) Limited - Company No. : 154309 - All Rights Reserved Unauthorized copying of
 * this file or any of its contents, via any medium is strictly prohibited Proprietary and confidential
 */
package reactive.scenarios;

import java.io.IOException;
import javax.annotation.Priority;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ContextInjectionProvider.
 *
 * @author Paul Carter-Brown
 */
@Provider
@PreMatching
@RequestScoped
@Priority(Priorities.HEADER_DECORATOR + 200)
public class RequestContextImpl implements ContainerRequestFilter, RequestContext {

    private static final Logger log = LoggerFactory.getLogger(RequestContextImpl.class);


    private ContainerRequestContext containerRequestContext;


    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        this.containerRequestContext = requestContext;
    }

}
