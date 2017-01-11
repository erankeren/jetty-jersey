package com.github.erankeren.jettyjersey;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.*;
import org.junit.rules.ExpectedException;

public class JettyJerseyUnitTest {

    private static JettyJersey jettyJersey;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @BeforeClass
    public static void before() throws Exception {
        jettyJersey = new JettyJersey(9998, new MyResourceConfig() {
        });

        jettyJersey.start();
    }

    @AfterClass
    public static void after() throws Exception {
        jettyJersey.stop();
    }

    @Test
    public void notFound() throws UnirestException {

        HttpResponse<String> response = Unirest.get("http://localhost:9998/notfound").asString();

        Assert.assertEquals(404, response.getStatus());
    }

    @Test
    public void ping() throws UnirestException {

        HttpResponse<String> response = Unirest.get("http://localhost:9998/ping").asString();

        String res = response.getBody().trim();

        Assert.assertEquals(200, response.getStatus());

        Assert.assertEquals("pong", res);
    }

}