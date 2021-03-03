/* Copyright (C) Jini Guru Technologies (Mauritius) Limited - Company No. : 154309  - All Rights Reserved
 * Unauthorized copying of this file or any of its contents, via any medium is strictly prohibited
 * Proprietary and confidential
 */
package reactive.scenarios;

import java.io.IOException;
import javax.annotation.Priority;
import javax.interceptor.Interceptor;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;

/**
 *
 * @author paul
 */
@Provider
@Priority(Interceptor.Priority.APPLICATION)
public class MyFilter implements ReaderInterceptor {


    @Override
    public Object aroundReadFrom(ReaderInterceptorContext ric) throws IOException, WebApplicationException {
        return ric.proceed();
    }

}
