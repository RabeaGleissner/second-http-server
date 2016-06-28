package de.rabea.response.creator;

import de.rabea.ContentStorage;
import de.rabea.Controller;
import de.rabea.response.HttpResponse;
import de.rabea.response.ResponseCreator;
import de.rabea.response.head.StatusLine;

public class DeleteResponseCreator implements ResponseCreator {

    private final StatusLine statusLine;
    private final ContentStorage contentStorage;
    private final Controller controller;

    public DeleteResponseCreator(StatusLine statusLine, ContentStorage contentStorage, Controller controller) {
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
