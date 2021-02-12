/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reactive.scenarios;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.quarkus.jackson.ObjectMapperCustomizer;
import java.io.IOException;
import java.time.ZonedDateTime;
import javax.inject.Singleton;

/**
 *
 * @author paul
 */
@Singleton
public class ZMyCustomizer implements ObjectMapperCustomizer { // THIS ONE DOES NOT WORK
// public class AMyCustomizer implements ObjectMapperCustomizer { // THIS ONE WORKS
    
    @Override
    public int priority() {
        return MINIMUM_PRIORITY; //this is needed in order to ensure that your SimpleModule's Serializer will be applied last and thus override the one coming from the `JavaTimeModule`
    }
    
    @Override
    public void customize(ObjectMapper objectMapper) {
        SimpleModule module = new SimpleModule();
        module.addSerializer(ZonedDateTime.class, new JsonSerializer<ZonedDateTime>() {
            @Override
            public void serialize(ZonedDateTime t, JsonGenerator jg, SerializerProvider sp) throws IOException {
                jg.writeString("aaa");
            }
        });
        objectMapper.registerModule(module);
    }
}
