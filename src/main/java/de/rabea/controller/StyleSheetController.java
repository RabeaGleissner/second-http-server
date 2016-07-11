package de.rabea.controller;

import de.rabea.Controller;
import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;
import org.apache.commons.io.IOUtils;

import java.io.*;

import static de.rabea.response.head.StatusLine.OK;

public class StyleSheetController extends Controller {

    @Override
    public HttpResponse doGet(HttpRequest request) {
        byte[] responseBody = readStylesheetContent();
        return new HttpResponse(OK, new ContentTypeHeader("text/css"), responseBody);
    }

    private byte[] readStylesheetContent() {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("styles.css");
        byte[] responseBody = new byte[0];
        try {
            responseBody = IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseBody;
    }
}
