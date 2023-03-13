package com.github.grommitz;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api")
public class JerseyApp extends ResourceConfig {

    public JerseyApp() {
        super();
        packages(true, "com.github.grommitz");
    }

}