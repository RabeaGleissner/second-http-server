package de.rabea.controller;

import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;
import org.junit.Test;

import static de.rabea.request.HttpVerb.GET;
import static org.junit.Assert.assertEquals;

public class StyleSheetControllerTest {

    @Test
    public void returnsResponseForRequestToStyleSheet() {
        StyleSheetControllerStub controller = new StyleSheetControllerStub();
        HttpResponse response = controller.doGet(new HttpRequest(GET, "/styles.css"));
        assertEquals("HTTP/1.1 200 OK\n" +
                "Content-Type: text/css\n" +
                "some styles", response.asString());
    }

    private class StyleSheetControllerStub extends StyleSheetController {

        @Override
        public byte[] readStylesheetContent() {
            return "some styles".getBytes();
        }
    }
}