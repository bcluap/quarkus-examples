/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reactive.scenarios;

import io.jaegertracing.Configuration;
import io.jaegertracing.spi.Sender;
import io.jaegertracing.spi.SenderFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author paul
 */
public class JaegerLoggingSenderFactory implements SenderFactory {

    private static final Logger log = LoggerFactory.getLogger(JaegerLoggingSenderFactory.class);

    @Override
    public Sender getSender(Configuration.SenderConfiguration conf) {
        log.info("Using JaegerLoggingSenderFactory");
        return new LoggingSender();
    }

    @Override
    public String getType() {
        return this.getClass().getName();
    }


}
