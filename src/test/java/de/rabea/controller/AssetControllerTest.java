package de.rabea.controller;

import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;
import org.junit.Test;

import static de.rabea.request.HttpVerb.GET;
import static org.junit.Assert.assertEquals;

public class AssetControllerTest {

    @Test
    public void returnsResponse() {
        HttpRequest httpRequest = new HttpRequest(GET, "/");
        AssetController controller = new AssetController();
        HttpResponse response = controller.getResponse(httpRequest);
        assertEquals("HTTP/1.1 200 OK\n", response.asString());
    }
}