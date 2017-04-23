package com.hellorest.app;

import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
/*
 * This
 */
public class MyJacksonFeature implements Feature {

    private static final ObjectMapper mapper =
        new ObjectMapper(){{
        	
            //registerModule(new JodaModule());
            //registerModule(new GuavaModule());  
            // or whatever you want...
 			
            // We want ISO dates, not Unix timestamps!:
        	configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        }};
 
    private static final JacksonJaxbJsonProvider provider =
        new JacksonJaxbJsonProvider(){{
            setMapper(mapper);
        }};
 
    /** This method is what actually gets called,
        when your ResourceConfig registers a Feature. */
    @Override
    public boolean configure(FeatureContext context) {
        context.register(provider);
        return true;
    }
}