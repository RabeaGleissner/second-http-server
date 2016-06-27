package de.rabea;

import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;

import static de.rabea.response.head.StatusLine.NOT_ALLOWED;
import static de.rabea.response.head.StatusLine.OK;

public abstract class Controller {

    public HttpResponse ok200() {
        return new HttpResponse(OK);
    }

    public HttpResponse methodNotAllowed() {
        return new HttpResponse(NOT_ALLOWED);
    }

    public abstract HttpResponse getResponse(HttpRequest httpRequest);
}
