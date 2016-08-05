package com.github.erankeren;

import org.glassfish.jersey.server.ResourceConfig;

public class MyResourceConfig extends ResourceConfig {

    public MyResourceConfig() {

        // scan packages
        packages("com.github.erankeren");
    }
}
