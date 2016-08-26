package com.github.erankeren.jettyjersey;

import org.glassfish.jersey.server.ResourceConfig;

public class MyResourceConfig extends ResourceConfig {

    public MyResourceConfig() {

        // scan packages
        packages("com.github.erankeren");
    }
}
