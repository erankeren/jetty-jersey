package com.github.erankeren;

/**
 * Hello world!
 *
 */
public class Main
{
    private static final Integer port = 9998;

    public static void main( String[] args ) throws Exception {

        JettyJersey jettyJersey = new JettyJersey(port);

        jettyJersey.startAndJoin();
    }
}
