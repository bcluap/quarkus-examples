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
import java.io.IOException;
import java.time.ZonedDateTime;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author paul
 */
@ApplicationScoped
@Path("/")
public class RestResource {

    private static final Logger log = LoggerFactory.getLogger(RestResource.class);
    @Inject
    ObjectMapper jacksonMapper;
    
    @PostConstruct
    void init() {
        log.info("Configuring Jackson mapper [{}]", jacksonMapper);
        SimpleModule module = new SimpleModule();
        module.addSerializer(ZonedDateTime.class, new JsonSerializer<ZonedDateTime>() {
            @Override
            public void serialize(ZonedDateTime t, JsonGenerator jg, SerializerProvider sp) throws IOException {
                jg.writeString("aaa");
            }
            
        });
        jacksonMapper.registerModule(module);
    }
    
    @GET
    @Path("dt")
    @Produces(MediaType.APPLICATION_JSON)
    public MyData get() {
        return new MyData();
    }

    

}
