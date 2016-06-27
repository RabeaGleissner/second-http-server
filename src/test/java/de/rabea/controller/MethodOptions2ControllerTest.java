package de.rabea.controller;

import de.rabea.request.HttpRequest;
import org.junit.Test;

import static de.rabea.request.HttpVerb.OPTIONS;
import static org.junit.Assert.assertTrue;

public class MethodOptions2ControllerTest {

    @Test
    public void returnsResponseForOptionsRequest() {
        MethodOptions2Controller controller = new MethodOptions2Controller();
        String response = controller.getResponse(new HttpRequest(OPTIONS, "/method_options")).asString();
        assertTrue(response.contains("Allow: "));
        assertTrue(response.contains("GET"));
        assertTrue(response.contains("OPTIONS"));
    }
}