package com.github.erankeren;

import com.github.erankeren.jettyjersey.JettyJersey;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

/**
 * Hello world!
 *
 */
public class Main
{
    private static final Integer port = 9998;

    public static void main( String[] args ) throws Exception {

        JettyJersey jettyJersey = new JettyJersey(port);

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
