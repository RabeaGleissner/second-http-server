package de.rabea.controller;

import de.rabea.Controller;
import de.rabea.communication.ResourceReader;
import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;

import static de.rabea.response.head.StatusLine.OK;

public class StyleSheetController extends Controller {

    private ResourceReader resourceReader;

    public StyleSheetController(ResourceReader resourceReader) {
        this.resourceReader = resourceReader;
    }

    @Override
    public HttpResponse doGet(HttpRequest request) {
        String fileName = request.requestLine().uri().substring(1);
        byte[] responseBody = resourceReader.read(fileName);
        return new HttpResponse(OK, new ContentTypeHeader("text/css"), responseBody);
    }
}
