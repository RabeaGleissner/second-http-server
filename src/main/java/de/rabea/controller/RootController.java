package de.rabea.controller;

import de.rabea.Controller;
import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;

import static de.rabea.request.HttpVerb.GET;
import static de.rabea.request.HttpVerb.HEAD;
import static de.rabea.response.StatusLine.NOT_ALLOWED;
import static de.rabea.response.StatusLine.OK;

public class RootController implements Controller {

    public HttpResponse getResponse(HttpRequest request) {
        if (request.requestLine().method() == GET) {
            return ok200();
        }

        if (request.requestLine().method() == HEAD) {
            return ok200();
        }
        return methodNotAllowed();
    }

    private HttpResponse methodNotAllowed() {
        return new HttpResponse(NOT_ALLOWED);
    }

    private HttpResponse ok200() {
        return new HttpResponse(OK);
    }
}
