/*
 * Copyright (C) Jini Guru Technologies (Mauritius) Limited - Company No. : 154309 - All Rights Reserved Unauthorized copying of
 * this file or any of its contents, via any medium is strictly prohibited Proprietary and confidential
 */

package client2;

import guru.jini.services.mobilerecharge.acs.generated.VoucherSystem;
import guru.jini.services.mobilerecharge.acs.generated.VoucherSystemSoap;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.runtime.annotations.RegisterForReflection;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
@RegisterForReflection
public class Soap2 {

    private static final Logger log = LoggerFactory.getLogger(Soap2.class);

    
    void getSoap(@Observes StartupEvent ev) {
        try {
            log.info("Creating soap client 2");
            VoucherSystemSoap soapEP = new VoucherSystem().getVoucherSystemSoap();
            soapEP.login("", "");
            log.info("Created soap client 2");
        } catch (Exception e) {
            log.warn("Error:", e);
        }
    }

}
