package de.rabea.controller;

import de.rabea.ContentStorage;
import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;

import static de.rabea.request.HttpVerb.GET;
import static org.junit.Assert.assertEquals;

public class AssetControllerTest {
    private String pathToFolder;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Before
    public void setup() throws IOException {
        temporaryFolder.newFile("file1");
        pathToFolder = temporaryFolder.getRoot().getAbsolutePath();
    }

    @Test
    public void returnsResponse() {
        HttpRequest httpRequest = new HttpRequest(GET, "/file1");
        AssetController controller = new AssetController(pathToFolder, new ContentStorage());
        HttpResponse response = controller.getResponse(httpRequest);
        assertEquals("HTTP/1.1 200 OK\n\n", response.asString());
    }
}