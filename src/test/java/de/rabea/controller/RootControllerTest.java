package de.rabea.controller;

import de.rabea.request.HttpRequest;
import org.junit.Test;

import static de.rabea.request.HttpVerb.*;
import static org.junit.Assert.assertEquals;

public class RootControllerTest {

    @Test
    public void returnsMethodNotAllowedIfMethodIsNotImplemented() {
        HttpRequest request = new HttpRequest(PUT, "/");
        RootController controller = new RootController();
        assertEquals("HTTP/1.1 405 Method Not Allowed\n\n", controller.getResponse(request).asString());
    }

    @Test
    public void returns200ResponseForGetRequest() {
        HttpRequest httpRequest = new HttpRequest(GET, "/");
        RootController controller = new RootController();
        assertEquals("HTTP/1.1 200 OK\n\n", controller.getResponse(httpRequest).asString());
    }

    @Test
    public void returns200ResponseForHeadRequest() {
        HttpRequest httpRequest = new HttpRequest(HEAD, "/");
        RootController controller = new RootController();
        assertEquals("HTTP/1.1 200 OK\n\n", controller.getResponse(httpRequest).asString());
    }

}