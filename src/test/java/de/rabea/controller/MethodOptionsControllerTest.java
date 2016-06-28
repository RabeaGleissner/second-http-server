package de.rabea.controller;

import de.rabea.ContentStorage;
import de.rabea.request.HttpRequest;
import de.rabea.request.HttpVerb;
import org.junit.Test;

import static de.rabea.request.HttpVerb.OPTIONS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MethodOptionsControllerTest {

    @Test
    public void returnsResponseForOptionsRequest() {
        MethodOptionsController controller = new MethodOptionsController(new ContentStorage());
        String response = controller.dispatch(new HttpRequest(OPTIONS, "/method_options")).asString();
        assertTrue(response.contains("Allow: "));
        assertTrue(response.contains("GET"));
        assertTrue(response.contains("POST"));
        assertTrue(response.contains("PUT"));
        assertTrue(response.contains("HEAD"));
    }

    @Test
    public void returnsResponseForGetRequest() {
        MethodOptionsController controller = new MethodOptionsController(new ContentStorage());
        String response = controller.dispatch(new HttpRequest(HttpVerb.GET, "/method_options")).asString();
        assertEquals("HTTP/1.1 200 OK\n\n", response);
    }
}