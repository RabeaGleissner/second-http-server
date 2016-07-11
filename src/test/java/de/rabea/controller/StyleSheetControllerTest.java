package de.rabea.controller;

import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;
import org.junit.Test;

import static de.rabea.request.HttpVerb.GET;
import static org.junit.Assert.assertTrue;

public class StyleSheetControllerTest {

    @Test
    public void returnsResponseForRequestToStyleSheet() {
        StyleSheetController controller = new StyleSheetController();
        HttpResponse responseObject = controller.doGet(new HttpRequest(GET, "/styles.css"));
        String response = responseObject.asString();
        assertTrue(response.contains("HTTP/1.1 200 OK\n" +
                "Content-Type: text/css\n" +
                "* {\n" +
                "  box-sizing: border-box;\n" +
                "}"));
    }
}