package de.rabea.controller;

import de.rabea.request.HttpRequest;
import org.junit.Test;

import static de.rabea.request.HttpVerb.*;
import static de.rabea.request.HttpVerb.OPTIONS;
import static org.junit.Assert.assertEquals;

public class MethodOptionsControllerTest {

    @Test
    public void returnsResponseForGetRequest() {
        MethodOptionsController controller = new MethodOptionsController();
        String response = controller.dispatch(new HttpRequest(GET, "/method_options")).asString();
        assertEquals("HTTP/1.1 200 OK\n\n", response);
    }

    @Test
    public void returnsResponseForHeadRequest() {
        MethodOptionsController controller = new MethodOptionsController();
        String response = controller.dispatch(new HttpRequest(HEAD, "/method_options")).asString();
        assertEquals("HTTP/1.1 200 OK\n\n", response);
    }

    @Test
    public void returnsResponseForPostRequest() {
        MethodOptionsController controller = new MethodOptionsController();
        String response = controller.dispatch(new HttpRequest(POST, "/method_options")).asString();
        assertEquals("HTTP/1.1 200 OK\n\n", response);
    }

    @Test
    public void returnsResponseForOptionsRequest() {
        MethodOptionsController controller = new MethodOptionsController();
        String response = controller.dispatch(new HttpRequest(OPTIONS, "/method_options")).asString();
        assertEquals("HTTP/1.1 200 OK\nAllow: GET,HEAD,POST,OPTIONS,PUT\n", response);
    }

    @Test
    public void returnsResponseForPutRequest() {
        MethodOptionsController controller = new MethodOptionsController();
        String response = controller.dispatch(new HttpRequest(PUT, "/method_options")).asString();
        assertEquals("HTTP/1.1 200 OK\n\n", response);
    }
}