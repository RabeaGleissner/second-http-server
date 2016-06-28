package de.rabea.response.creator;

import de.rabea.ContentStorage;
import de.rabea.Controller;
import de.rabea.response.HttpResponse;
import de.rabea.response.ResponseCreator;
import de.rabea.response.head.StatusLine;

public class PostResponseCreator implements ResponseCreator {

    private StatusLine statusLine;
    private ContentStorage contentStorage;
    private Controller controller;

    public PostResponseCreator(StatusLine statusLine, ContentStorage contentStorage, Controller controller) {
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
