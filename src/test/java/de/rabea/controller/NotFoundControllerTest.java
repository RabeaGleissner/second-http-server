package de.rabea.controller;

import de.rabea.request.HttpRequest;
import org.junit.Test;

import static de.rabea.request.HttpVerb.GET;
import static org.junit.Assert.assertEquals;

public class NotFoundControllerTest {

    @Test
    public void returnsNotFoundResponse() {
        HttpRequest request = new HttpRequest(GET, "/non-existent-route");
        NotFoundController controller = new NotFoundController();
        assertEquals("HTTP/1.1 404 Not Found\n\n", controller.dispatch(request).asString());
    }
}