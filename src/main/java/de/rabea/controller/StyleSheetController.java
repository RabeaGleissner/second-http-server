package de.rabea.controller;

import de.rabea.Controller;
import de.rabea.communication.FileReader;
import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;

import java.io.File;
import java.net.URL;

import static de.rabea.response.head.StatusLine.OK;

public class StyleSheetController extends Controller {

    @Override
    public HttpResponse doGet(HttpRequest request) {
        URL url = getClass().getResource(request.requestLine().uri());
        String file = new File(url.getPath()).getAbsolutePath();
        return new HttpResponse(OK, new ContentTypeHeader("text/css"), FileReader.read(file));
    }
}
