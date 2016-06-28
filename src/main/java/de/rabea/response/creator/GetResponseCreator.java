package de.rabea.response.creator;

import de.rabea.ContentStorage;
import de.rabea.Controller;
import de.rabea.response.HttpResponse;
import de.rabea.response.ResponseCreator;
import de.rabea.response.head.StatusLine;

public class GetResponseCreator implements ResponseCreator {

    private final StatusLine statusLine;
    private ContentStorage contentStorage;
    private Controller controller;
    private final boolean simpleResponse;

    public GetResponseCreator(StatusLine statusLine, ContentStorage contentStorage, Controller controller) {
        this.statusLine = statusLine;
        this.contentStorage = contentStorage;
        this.controller = controller;
        this.simpleResponse = false;
    }

    public GetResponseCreator(StatusLine statusLine) {
        this.statusLine = statusLine;
        this.simpleResponse = true;
    }

    @Override
    public HttpResponse create(String body) {
        if (simpleResponse) {
            return new HttpResponse(statusLine);
        } else {
            return responseWithBody();
        }
    }

    private HttpResponse responseWithBody() {
        if (contentStorage.hasContentFor(controller)) {
            return new HttpResponse(statusLine, contentStorage.contentFor(controller));
        } else {
            return new HttpResponse(statusLine);
        }
    }
}
