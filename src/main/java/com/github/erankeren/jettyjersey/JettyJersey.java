package com.github.erankeren.jettyjersey;

import org.apache.http.util.Args;
import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class JettyJersey {

    private Server jetty;

    private ResourceConfig resourceConfig;

    URI baseUri;


    public JettyJersey(Integer port, MyResourceConfig resourceConfig) {
        Args.notNegative(port, "port");
        Args.notNull(resourceConfig, "resourceConfig");

        baseUri = UriBuilder
                .fromUri("http://localhost/")
                .port(port)
                .build();

        this.resourceConfig = resourceConfig;
        this.resourceConfig.register(MyObjectMapperProvider.class);
    }

    public ResourceConfig getResourceConfig() {
        return resourceConfig;
    }

    public void start() throws Exception {
        doStart(false);
    }

    public void startAndJoin() throws Exception {
        doStart(true);
    }

    private void doStart(boolean join) throws Exception {
        jetty = JettyHttpContainerFactory.createServer(baseUri, resourceConfig);
        jetty.start();
        if (join) {
            jetty.join();
        }

    }

    public void stop() throws Exception {

        jetty.stop();
    }
}