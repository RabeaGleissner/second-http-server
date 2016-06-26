package de.rabea.controller;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RootControllerTest {
    private String GET_REQUEST;
    private String PUT_REQUEST;
    private String HEAD_REQUEST;

    @Before
    public void setup() {
        GET_REQUEST = "GET / HTTP/1.1\n";
        HEAD_REQUEST = "HEAD / HTTP/1.1\n";
        PUT_REQUEST = "PUT / HTTP/1.1\n";
    }

    @Test
    public void returnsMethodNotAllowedIfMethodIsNotImplemented() {
        FakeHttpRequest fakeHttpRequest = new FakeHttpRequest(PUT_REQUEST);
        RootController controller = new RootController();
        assertEquals("HTTP/1.1 405 Method Not Allowed\n", controller.getResponse(fakeHttpRequest).asString());
    }

    @Test
    public void returns200ResponseForGetRequest() {
        FakeHttpRequest fakeHttpRequest = new FakeHttpRequest(GET_REQUEST);
        RootController controller = new RootController();
        assertEquals("HTTP/1.1 200 OK\n", controller.getResponse(fakeHttpRequest).asString());
    }

    @Test
    public void returns200ResponseForHeadRequest() {
        FakeHttpRequest fakeHttpRequest = new FakeHttpRequest(HEAD_REQUEST);
        RootController controller = new RootController();
        assertEquals("HTTP/1.1 200 OK\n", controller.getResponse(fakeHttpRequest).asString());
    }

}