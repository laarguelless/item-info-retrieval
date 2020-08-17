package org.laarguelless.resources;

import org.glassfish.jersey.server.ResourceConfig;


public class App extends ResourceConfig {

    public App(){
        packages("org.example.laarguelles.services");
    }
}
