package com.github.erankeren.jettyjersey;

import org.apache.http.util.Args;
import org.eclipse.jetty.server.Server;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spi.Container;
import org.glassfish.jersey.server.spi.ContainerLifecycleListener;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class JettyJersey {

    private Server jetty;

    private ResourceConfig resourceConfig;

    private URI baseUri;

    private ServiceLocator serviceLocator;

    public static JettyJersey newInstance(String host, Integer port, String pkgs, AbstractBinder binder) {
        return new JettyJersey(host, port, pkgs, binder);
    }

    public static JettyJersey newLocalhostInstance(Integer port, String pkgs, AbstractBinder binder) {
        return new JettyJersey("localhost", port, pkgs, binder);
    }

    private JettyJersey(String host, Integer port, String pkgs, AbstractBinder binder) {
        Args.notEmpty(host, "host");
        Args.notEmpty(pkgs, "pkgs");
        Args.notNegative(port, "port");
        Args.notNull(binder, "binder");

        final String uri = "http://" + host + "/";

        baseUri = UriBuilder
                .fromUri(uri)
                .port(port)
                .build();

        this.resourceConfig = new ResourceConfig()
                .packages(pkgs)
                .register(binder)
                .register(JacksonMapperProvider.class)
                .register(new ContainerLifecycleListener() {

                    @Override
                    public void onStartup(Container container) {
                        serviceLocator = container.getApplicationHandler().getServiceLocator();
                    }

                    @Override
                    public void onReload(Container container) {
                        //
                    }

                    @Override
                    public void onShutdown(Container container) {
                        //
                    }
                });
    }

    public ResourceConfig getResourceConfig() {
        return resourceConfig;
    }

    public ServiceLocator serviceLocator() {
        return serviceLocator;
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
        if (jetty != null) {
            jetty.stop();
        }
    }

}