package de.rabea.controller;

import de.rabea.request.HttpRequest;
import de.rabea.request.HttpVerb;
import de.rabea.response.HttpResponse;
import org.junit.Test;

import static org.junit.Assert.*;

public class RedirectControllerTest {

    @Test
    public void createsResponseForRedirect() {
        HttpRequest httpRequest = new HttpRequest(HttpVerb.GET, "/redirect");
        RedirectController redirectController = new RedirectController();
        HttpResponse response = redirectController.dispatch(httpRequest);
        assertEquals("HTTP/1.1 302 Found\nLocation: http://localhost:5000/\n", response.asString());
    }
}