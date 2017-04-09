package com.github.erankeren.jettyjersey;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

/**
 * An example how to use the jetty jersey class
 */
public class Main {
    private static final Integer port = 9998;

    public static void main(String[] args) throws Exception {

        JettyJersey jettyJersey = JettyJersey.newLocalhostInstance(
                port,
                "com.github.erankeren",
                new AbstractBinder() {
                    @Override
                    protected void configure() {
                        //
                    }
                });

        jettyJersey.startAndJoin();
    }
}
