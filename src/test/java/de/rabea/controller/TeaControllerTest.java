package de.rabea.controller;

import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;
import org.junit.Test;

import static de.rabea.request.HttpVerb.GET;
import static org.junit.Assert.assertEquals;

public class TeaControllerTest {

    @Test
    public void returnResponse() {
        TeaController controller = new TeaController();
        HttpResponse response = controller.dispatch(new HttpRequest(GET, "/"));
        assertEquals("HTTP/1.1 200 OK\n\n", response.asString());
    }
}