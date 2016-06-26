package de.rabea.controller;

import org.junit.Test;

import static org.junit.Assert.*;

public class NotFoundControllerTest {

    @Test
    public void returnsNotFoundResponse() {
        FakeHttpRequest request = new FakeHttpRequest("bogus request");
        NotFoundController controller = new NotFoundController();
        assertEquals("HTTP/1.1 404 Not Found", controller.getResponse(request).asString());
    }
}