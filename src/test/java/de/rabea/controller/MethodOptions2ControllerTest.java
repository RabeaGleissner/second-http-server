package de.rabea.controller;

import de.rabea.request.HttpRequest;
import de.rabea.request.HttpVerb;
import org.junit.Test;

import static de.rabea.request.HttpVerb.OPTIONS;
import static org.junit.Assert.assertEquals;

public class MethodOptions2ControllerTest {

    @Test
    public void returnsResponseForOptionsRequest() {
        MethodOptions2Controller controller = new MethodOptions2Controller();
        String response = controller.dispatch(new HttpRequest(OPTIONS, "/method_options")).asString();
        assertEquals("HTTP/1.1 200 OK\nAllow: GET,OPTIONS\n", response);
    }

    @Test
    public void returnsResponseForGetRequest() {
        MethodOptions2Controller controller = new MethodOptions2Controller();
        String response = controller.dispatch(new HttpRequest(HttpVerb.GET, "/method_options")).asString();
        assertEquals("HTTP/1.1 200 OK\n\n", response);
    }
}