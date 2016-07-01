package de.rabea.controller;

import de.rabea.Controller;
import de.rabea.LinkGenerator;
import de.rabea.request.Directory;
import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;

import static de.rabea.response.head.StatusLine.OK;

public class RootController extends Controller {

    private Directory directory;

    public RootController(Directory directory) {

        this.directory = directory;
    }

    @Override
    public HttpResponse doGet(HttpRequest request) {
        return new HttpResponse(OK, LinkGenerator.generate(directory.filesWithRelativePaths()).getBytes());
    }

    @Override
    public HttpResponse doHead(HttpRequest request) {
        return okResponse();
    }
}
