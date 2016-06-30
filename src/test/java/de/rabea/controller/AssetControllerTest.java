package de.rabea.controller;

import de.rabea.ContentStorage;
import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static de.rabea.request.HttpVerb.GET;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class AssetControllerTest {
    private String pathToFolder;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Before
    public void setup() throws IOException {
        pathToFolder = temporaryFolder.getRoot().getAbsolutePath();
        FileWriter writer = new FileWriter(temporaryFolder.newFile("file1").getAbsoluteFile());
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        bufferedWriter.write("Some content");
        bufferedWriter.close();
    }

    @Test
    public void returnsResponse() {
        AssetController controller = new AssetController(pathToFolder, new ContentStorage());
        HttpResponse response = controller.dispatch(new HttpRequest(GET, "/file1"));
        assertEquals("HTTP/1.1 200 OK\n\nSome content", response.asString());
    }

    @Test
    public void savesFileContentInContentStorage() {
        ContentStorage storage = new ContentStorage();
        AssetController controller = new AssetController(pathToFolder, storage);
        controller.dispatch(new HttpRequest(GET, "/file1"));
        assertArrayEquals("Some content".getBytes(), storage.content());
    }
}