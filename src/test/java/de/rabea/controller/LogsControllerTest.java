package de.rabea.controller;

import de.rabea.ServerConsole;
import de.rabea.logger.ConsoleLogger;
import de.rabea.logger.FileLogger;
import de.rabea.logger.Logger;
import de.rabea.logger.MultiLogger;
import de.rabea.request.HttpRequest;
import de.rabea.request.RequestLine;
import de.rabea.response.HttpResponse;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static de.rabea.request.HttpVerb.GET;
import static org.junit.Assert.assertEquals;

public class LogsControllerTest {

    private String pathToEmptyFile;
    private List<Logger> loggers;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void setup() throws IOException {
        File file = folder.newFile("log.txt");
        pathToEmptyFile = file.getAbsolutePath();
        loggers = new ArrayList<>(Arrays.asList(new FileLogger(pathToEmptyFile), new ConsoleLogger(new ServerConsole())));
    }

    @Test
    public void returnsResponseWithLogsIfAuthorised() {
        MultiLogger logger = new MultiLogger(loggers);
        logger.log("GET /somewhere HTTP/1.1");
        LogsController controller = new LogsController(logger);
        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Authorization", "Basic YWRtaW46aHVudGVyMg==");

        HttpResponse response = controller.dispatch(new HttpRequest(new RequestLine("GET /logs HTTP/1.1"), requestHeaders, ""));

        assertEquals("HTTP/1.1 200 OK\n\nGET /somewhere HTTP/1.1, ", response.asString());
    }

    @Test
    public void returnsAuthenticateResponseIfNotAuthorised() {
        LogsController controller = new LogsController(new MultiLogger(loggers));
        HttpResponse response = controller.dispatch(new HttpRequest(GET, "/logs"));
        assertEquals("HTTP/1.1 401 Unauthorized\nWWW-Authenticate: Basic realm=\"Logger\"\n", response.asString());
    }
}