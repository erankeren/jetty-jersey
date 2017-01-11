package com.github.erankeren.jettyjersey;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

/**
 * An example how to use the jetty jersey class
 *
 */
public class Main
{
    private static final Integer port = 9998;

    public static void main( String[] args ) throws Exception {

        JettyJersey jettyJersey = new JettyJersey(port, new MyResourceConfig() {
        });

        //bind/inject goes here
        jettyJersey.getResourceConfig().register(new AbstractBinder() {

            @Override
            protected void configure() {
                //bind/inject goes here
            }
        });

        jettyJersey.startAndJoin();
    }
}
