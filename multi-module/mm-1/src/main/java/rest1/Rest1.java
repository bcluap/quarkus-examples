/*
 * Copyright (C) Jini Guru Technologies (Mauritius) Limited - Company No. : 154309 - All Rights Reserved Unauthorized copying of
 * this file or any of its contents, via any medium is strictly prohibited Proprietary and confidential
 */
package rest1;

import com.ukheshe.mathslibrary.MathsBean;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
@Path("/get1")
public class Rest1 {

    private static final Logger log = LoggerFactory.getLogger(Rest1.class);
    
    @Inject
    MathsBean mb;
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String get1() throws ClassNotFoundException {
        return String.valueOf(mb.add(1, 2));
    }

}
