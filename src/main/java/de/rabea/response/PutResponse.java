package de.rabea.response;

import de.rabea.ContentStorage;
import de.rabea.Controller;
import de.rabea.response.head.StatusLine;

public class PutResponse extends ResponseCreator {

    private StatusLine statusLine;
    private final ContentStorage contentStorage;
    private final Controller controller;

    public PutResponse(StatusLine statusLine, ContentStorage contentStorage, Controller controller) {
        this.statusLine = statusLine;
        this.contentStorage = contentStorage;
        this.controller = controller;
    }

    @Override
    public HttpResponse create(String body) {
        contentStorage.store(controller, body);
        return new HttpResponse(statusLine);
    }
}
