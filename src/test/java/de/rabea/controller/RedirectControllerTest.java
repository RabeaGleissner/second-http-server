package de.rabea.controller;

import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;
import org.junit.Test;

import static de.rabea.request.HttpVerb.GET;
import static org.junit.Assert.assertEquals;

public class RedirectControllerTest {

    @Test
    public void createsResponseForRedirect() {
        RedirectController redirectController = new RedirectController();
        HttpResponse response = redirectController.dispatch(new HttpRequest(GET, "/redirect"));
        assertEquals("HTTP/1.1 302 Found\nLocation: http://localhost:5000/\n", response.asString());
    }
}