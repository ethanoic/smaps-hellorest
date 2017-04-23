package com.hellorest.app;
import org.glassfish.jersey.server.ResourceConfig;

public class HelloRESTApplication extends ResourceConfig {
    public HelloRESTApplication() {
        // Define the package which contains the service classes.
        packages("com.hellorest.api");
        register(MyJacksonFeature.class);
    }
    
}