package de.rabea.response;

import de.rabea.ContentStorage;
import de.rabea.Controller;
import de.rabea.response.head.StatusLine;

public class DeleteResponse extends ResponseCreator {

    private StatusLine statusLine;
    private final ContentStorage contentStorage;
    private final Controller controller;

    public DeleteResponse(StatusLine statusLine, ContentStorage contentStorage, Controller controller) {
        this.statusLine = statusLine;
        this.contentStorage = contentStorage;
        this.controller = controller;
    }

    @Override
    public HttpResponse create(String body) {
        if (contentStorage.hasContentFor(controller)) {
            contentStorage.deleteContentFor(controller);
        }
        return new HttpResponse(statusLine);
    }
}
