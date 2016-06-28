package de.rabea.controller;

import de.rabea.ContentStorage;
import de.rabea.request.HttpRequest;
import org.junit.Test;

import static de.rabea.request.HttpVerb.HEAD;
import static de.rabea.request.HttpVerb.PUT;
import static org.junit.Assert.assertEquals;

public class FormControllerTest {

    @Test
    public void returns200ResponseForPutRequest() {
        HttpRequest httpRequest = new HttpRequest(PUT, "/");
        FormController controller = new FormController(new ContentStorage());
        assertEquals("HTTP/1.1 200 OK\n", controller.getResponse(httpRequest).asString());
    }

    @Test
    public void returnsMethodNotAllowedForMethodThatIsNotImplemented() {
        HttpRequest httpRequest = new HttpRequest(HEAD, "/");
        FormController controller = new FormController(new ContentStorage());
        assertEquals("HTTP/1.1 405 Method Not Allowed\n", controller.getResponse(httpRequest).asString());
    }
}