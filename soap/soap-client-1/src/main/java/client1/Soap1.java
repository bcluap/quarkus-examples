/*
 * Copyright (C) Jini Guru Technologies (Mauritius) Limited - Company No. : 154309 - All Rights Reserved Unauthorized copying of
 * this file or any of its contents, via any medium is strictly prohibited Proprietary and confidential
 */

package client1;

import guru.jini.services.mobilerecharge.vodacom.generated.MobileRecharge;
import guru.jini.services.mobilerecharge.vodacom.generated.MobileRechargeSoap;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.runtime.annotations.RegisterForReflection;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
@RegisterForReflection
public class Soap1 {

    private static final Logger log = LoggerFactory.getLogger(Soap1.class);

    
    void getSoap(@Observes StartupEvent ev) {
        try {
            log.info("Creating soap client 1");
            MobileRechargeSoap soapEP = new MobileRecharge().getMobileRechargeSoap();
            soapEP.getNetwork("", "", "");
            log.info("Created soap client 1");
        } catch (Exception e) {
            log.warn("Error:", e);
        }
    }

}
