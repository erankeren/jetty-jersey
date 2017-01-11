package com.github.erankeren.jettyjersey;

import org.glassfish.jersey.server.ResourceConfig;

public abstract class MyResourceConfig extends ResourceConfig {

    public MyResourceConfig() {

        packages("com.github.erankeren");
    }
}
