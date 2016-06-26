package de.rabea.controller;

import de.rabea.FormController;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FormControllerTest {

    private String PUT_REQUEST;
    private String HEAD_REQUEST;

    @Before
    public void setup() {
        PUT_REQUEST = "PUT / HTTP/1.1\n";
        HEAD_REQUEST = "HEAD / HTTP/1.1\n";
    }

    @Test
    public void returns200ResponseForPutRequest() {
        FakeHttpRequest fakeHttpRequest = new FakeHttpRequest(PUT_REQUEST);
        FormController controller = new FormController();
        assertEquals("HTTP/1.1 200 OK\n", controller.getResponse(fakeHttpRequest).asString());
    }

    @Test
    public void returnsMethodNotAllowedForMethodThatIsNotImplemented() {
        FakeHttpRequest fakeHttpRequest = new FakeHttpRequest(HEAD_REQUEST);
        FormController controller = new FormController();
        assertEquals("HTTP/1.1 405 Method Not Allowed\n", controller.getResponse(fakeHttpRequest).asString());
    }
}