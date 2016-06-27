package de.rabea.response;

import de.rabea.ContentStorage;
import de.rabea.Controller;
import de.rabea.response.head.StatusLine;

public class GetResponse extends ResponseCreator {

    private StatusLine statusLine;
    private ContentStorage contentStorage;
    private Controller controller;
    private boolean simpleResponse;

    public GetResponse(StatusLine statusLine, ContentStorage contentStorage, Controller controller) {
        this.statusLine = statusLine;
        this.contentStorage = contentStorage;
        this.controller = controller;
        this.simpleResponse = false;
    }

    public GetResponse(StatusLine statusLine) {
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
