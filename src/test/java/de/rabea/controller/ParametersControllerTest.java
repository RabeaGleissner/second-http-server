package de.rabea.controller;

import de.rabea.request.HttpRequest;
import de.rabea.request.HttpVerb;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ParametersControllerTest {

    @Test
    public void returns200ResponseForGetRequest() {
        HttpRequest httpRequest = new HttpRequest(HttpVerb.GET, "/parameters");
        ParametersController controller = new ParametersController();
        String response = controller.dispatch(httpRequest).asString();
        assertTrue(response.contains("HTTP/1.1 200 OK\n\n"));
    }

    @Test
    public void returns200WithBodyForGetRequestToUrlWithParameters() {
        HttpRequest httpRequest = new HttpRequest(HttpVerb.GET, "/parameters?some=params");
        ParametersController controller = new ParametersController();
        String response = controller.dispatch(httpRequest).asString();
        assertEquals("HTTP/1.1 200 OK\n\nsome = params", response);
    }
}