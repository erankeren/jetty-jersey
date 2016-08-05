package com.github.erankeren;

import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class JettyJersey {

    private Server jetty;

    private final ResourceConfig resourceConfig = new MyResourceConfig();

    public JettyJersey(Integer port) {

        URI baseUri = UriBuilder
                .fromUri("http://localhost/")
                .port(port)
                .build();

        jetty = JettyHttpContainerFactory.createServer(baseUri, resourceConfig);
    }

    public void start() throws Exception {

        doStart(false);
    }

    public void startAndJoin() throws Exception {

        doStart(true);
    }

    private void doStart(boolean join) throws Exception {

        jetty.start();
        if (join) {
            jetty.join();
        }

    }

    public void stop() throws Exception {

        jetty.stop();
    }


}
