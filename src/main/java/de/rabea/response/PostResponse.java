package de.rabea.response;

import de.rabea.ContentStorage;
import de.rabea.Controller;
import de.rabea.response.head.StatusLine;

public class PostResponse extends ResponseCreator {

    private StatusLine statusLine;
    private ContentStorage contentStorage;
    private Controller controller;

    public PostResponse(StatusLine statusLine, ContentStorage contentStorage, Controller controller) {
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
