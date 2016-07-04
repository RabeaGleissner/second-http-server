package de.rabea.controller;

import de.rabea.ContentStorage;
import de.rabea.request.Directory;
import de.rabea.request.HttpRequest;
import de.rabea.request.RequestLine;
import de.rabea.response.HttpResponse;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static de.rabea.request.HttpVerb.GET;
import static de.rabea.request.HttpVerb.PATCH;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class AssetControllerTest {
    private String pathToFolder;
    private File file1;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Before
    public void setup() throws IOException {
        file1 = temporaryFolder.newFile("file1");
        writeContentTo(file1, "default content");
        pathToFolder = temporaryFolder.getRoot().getAbsolutePath();
    }

    @Test
    public void returnsResponse() {
        AssetController controller = new AssetController(new Directory(pathToFolder), new ContentStorage());
        HttpResponse response = controller.dispatch(new HttpRequest(GET, "/file1"));
        assertEquals("HTTP/1.1 200 OK\n\ndefault content", response.asString());
    }

    @Test
    public void returns204ResponseForPatchRequest() {
        ContentStorage storage = new ContentStorage();
        AssetController controller = new AssetController(new Directory(pathToFolder), storage);
        assertEquals("HTTP/1.1 204 No Content\n\n", controller.dispatch(new HttpRequest(PATCH, "/file1")).asString());
    }

    @Test
    public void updatesFileContentForPatchRequest() {
        Directory directory = new Directory(pathToFolder);
        AssetController controller = new AssetController(directory, new ContentStorage());
        Map<String, String> headers = new HashMap<>();
        headers.put("If-Match", "dc50a0d27dda2eee9f65644cd7e4c9cf11de8bec");

        controller.dispatch(new HttpRequest(new RequestLine("PATCH /file1 HTTP/1.1"), headers, "patched content"));

        assertArrayEquals("patched content".getBytes(), directory.fileContent("/file1"));
    }

    @Test
    public void doesNotUpdateFileContentForPatchIfEtagIsIncorrect() {
        Directory directory = new Directory(pathToFolder);
        AssetController controller = new AssetController(directory, new ContentStorage());
        Map<String, String> headers = new HashMap<>();
        headers.put("If-Match", "notTheRightEtag");

        controller.dispatch(new HttpRequest(new RequestLine("PATCH /file1 HTTP/1.1"), headers, "patched content"));

        assertArrayEquals("default content".getBytes(), directory.fileContent("/file1"));
    }

    @Test
    public void returns206PartialContentResponseIfHeaderContainsRange() {
        Directory directory = new Directory(pathToFolder);
        AssetController controller = new AssetController(directory, new ContentStorage());
        Map<String, String> headers = new HashMap<>();
        headers.put("Range", "bytes=0-4");

        HttpResponse response = controller.dispatch(new HttpRequest(new RequestLine("GET /file1 HTTP/1.1"), headers, ""));
        assertEquals("HTTP/1.1 206 Partial Content\n\ndefau", response.asString());
    }

    private void writeContentTo(File file, String content) throws IOException {
        FileWriter writer = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        bufferedWriter.write(content);
        bufferedWriter.close();
    }
}