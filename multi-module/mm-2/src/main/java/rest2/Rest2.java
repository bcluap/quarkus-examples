/*
 * Copyright (C) Jini Guru Technologies (Mauritius) Limited - Company No. : 154309 - All Rights Reserved Unauthorized copying of
 * this file or any of its contents, via any medium is strictly prohibited Proprietary and confidential
 */
package rest2;

import io.quarkus.arc.Unremovable;
import io.smallrye.common.annotation.RunOnVirtualThread;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
@Path("/get2")
@RunOnVirtualThread
//@Transactional
public class Rest2 {

    private static final Logger log = LoggerFactory.getLogger(Rest2.class);


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @RunOnVirtualThread
    public String get2() throws ClassNotFoundException {
        log.warn("Thread is virtual [{}]", Thread.currentThread().isVirtual());
        return "aaa";
    }

}
