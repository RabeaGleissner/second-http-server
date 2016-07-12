package de.rabea.controller;

import de.rabea.communication.ResourceReader;
import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;
import org.junit.Test;

import static de.rabea.request.HttpVerb.GET;
import static org.junit.Assert.assertEquals;

public class StyleSheetControllerTest {

    @Test
    public void returnsResponseForRequestToStyleSheet() {
        StyleSheetController controller = new StyleSheetController(new ResourceReaderStub());
        HttpResponse response = controller.doGet(new HttpRequest(GET, "/styles.css"));
        assertEquals("HTTP/1.1 200 OK\n" +
                "Content-Type: text/css\n" +
                "styles.css", response.asString());
    }

    private class ResourceReaderStub extends ResourceReader {
        @Override
        public byte[] read(String fileName) {
            return fileName.getBytes();
        }
    }
}