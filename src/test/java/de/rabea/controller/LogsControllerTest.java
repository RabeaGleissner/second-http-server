package de.rabea.controller;

import de.rabea.Logger;
import de.rabea.MultiLogger;
import de.rabea.request.HttpRequest;
import de.rabea.request.RequestLine;
import de.rabea.response.HttpResponse;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static de.rabea.request.HttpVerb.GET;
import static org.junit.Assert.assertEquals;

public class LogsControllerTest {

    @Test
    public void returnsResponseWithLogsIfAuthorised() {
        Logger logger = new MultiLogger();
        logger.log("GET /somewhere HTTP/1.1");
        LogsController controller = new LogsController(logger);
        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Authorization", "Basic YWRtaW46aHVudGVyMg==");

        HttpResponse response = controller.dispatch(new HttpRequest(new RequestLine("GET /logs HTTP/1.1"), requestHeaders, ""));

        assertEquals("HTTP/1.1 200 OK\n\nGET /somewhere HTTP/1.1", response.asString());
    }

    @Test
    public void returnsAuthenticateResponseIfNotAuthorised() {
        LogsController controller = new LogsController(new MultiLogger());
        HttpResponse response = controller.dispatch(new HttpRequest(GET, "/logs"));
        assertEquals("HTTP/1.1 401 Unauthorized\nWWW-Authenticate: Basic realm=\"Logger\"\n", response.asString());
    }
}