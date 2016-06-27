package de.rabea.response;

import de.rabea.ContentStorage;
import de.rabea.Controller;
import de.rabea.response.head.StatusLine;

public class GetResponse extends ResponseCreator {

    private StatusLine statusLine;
    private final ContentStorage contentStorage;
    private final Controller controller;

    public GetResponse(StatusLine statusLine, ContentStorage contentStorage, Controller controller) {
        this.statusLine = statusLine;
        this.contentStorage = contentStorage;
        this.controller = controller;
    }

    @Override
    public HttpResponse create(String body) {
        if (contentStorage.hasContentFor(controller)) {
            return new HttpResponse(statusLine, contentStorage.contentFor(controller));
        } else {
            return new HttpResponse(statusLine);
        }
    }
}
