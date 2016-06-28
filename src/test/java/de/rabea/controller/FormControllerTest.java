package de.rabea.controller;

import de.rabea.ContentStorage;
import de.rabea.request.HttpRequest;
import org.junit.Test;

import static de.rabea.request.HttpVerb.*;
import static org.junit.Assert.assertEquals;

public class FormControllerTest {

    @Test
    public void returns200ResponseForPutRequest() {
        HttpRequest httpRequest = new HttpRequest(PUT, "/");
        FormController controller = new FormController(new ContentStorage());
        assertEquals("HTTP/1.1 200 OK\n\n", controller.dispatch(httpRequest).asString());
    }

    @Test
    public void returnsMethodNotAllowedForMethodThatIsNotImplemented() {
        HttpRequest httpRequest = new HttpRequest(HEAD, "/");
        FormController controller = new FormController(new ContentStorage());
        assertEquals("HTTP/1.1 405 Method Not Allowed\n\n", controller.dispatch(httpRequest).asString());
    }

    @Test
    public void returnsPostResponse() {
        HttpRequest httpRequest = new HttpRequest(POST, "/");
        FormController controller = new FormController(new ContentStorage());
        assertEquals("HTTP/1.1 200 OK\n\n", controller.dispatch(httpRequest).asString());
    }

    @Test
    public void returnsGetResponse() {
        HttpRequest httpRequest = new HttpRequest(GET, "/");
        FormController controller = new FormController(new ContentStorage());
        assertEquals("HTTP/1.1 200 OK\n\n", controller.dispatch(httpRequest).asString());
    }

    @Test
    public void returnsDeleteResponse() {
        HttpRequest httpRequest = new HttpRequest(DELETE, "/");
        FormController controller = new FormController(new ContentStorage());
        assertEquals("HTTP/1.1 200 OK\n\n", controller.dispatch(httpRequest).asString());
    }
}