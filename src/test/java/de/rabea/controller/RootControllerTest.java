package de.rabea.controller;

import de.rabea.request.Directory;
import de.rabea.request.HttpRequest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;

import static de.rabea.request.HttpVerb.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RootControllerTest {

    private String pathToFolder;

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    @Before
    public void setup() throws IOException {
        testFolder.newFile("file1");
        testFolder.newFile("file2");
        pathToFolder = testFolder.getRoot().getAbsolutePath();
    }
    @Test
    public void returnsMethodNotAllowedIfMethodIsNotImplemented() {
        HttpRequest request = new HttpRequest(PUT, "/");
        RootController controller = new RootController(new Directory(""));
        assertEquals("HTTP/1.1 405 Method Not Allowed\n\n", controller.dispatch(request).asString());
    }

    @Test
    public void returns200ResponseAndDirectoryLinksForGetRequest() {
        HttpRequest httpRequest = new HttpRequest(GET, "/");
        RootController controller = new RootController(new Directory(pathToFolder));
        String response = controller.dispatch(httpRequest).asString();
        assertTrue(response.contains("HTTP/1.1 200 OK\n\n"));
        assertTrue(response.contains("</a><a href="));
    }

    @Test
    public void returns200ResponseForHeadRequest() {
        HttpRequest httpRequest = new HttpRequest(HEAD, "/");
        RootController controller = new RootController(new Directory(""));
        assertEquals("HTTP/1.1 200 OK\n\n", controller.dispatch(httpRequest).asString());
    }
}