package de.rabea.controller;

import de.rabea.Logger;
import de.rabea.request.HttpRequest;
import de.rabea.request.RequestLine;
import de.rabea.response.HttpResponse;
import org.junit.Test;

import static de.rabea.request.HttpVerb.GET;
import static org.junit.Assert.assertEquals;

public class LogsControllerTest {

    @Test
    public void returnsResponseWithLogs() {
        Logger logger = new Logger();
        logger.log(new RequestLine("GET /somewhere HTTP/1.1"));
        LogsController controller = new LogsController(logger);
        HttpResponse response = controller.dispatch(new HttpRequest(GET, "/logs"));
        assertEquals("HTTP/1.1 200 OK\n\nGET /somewhere HTTP/1.1", response.asString());
    }
}