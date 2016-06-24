package de.rabea.controller;

import de.rabea.Controller;
import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;

import static de.rabea.request.HttpVerb.GET;

public class RootController implements Controller {

    private HttpRequest request;

    public RootController(HttpRequest request) {
        this.request = request;
    }

    public HttpResponse getResponse() {
        if (request.requestLine().method() == GET) {
            return ok200();
        } else {
            return methodNotAllowed();
        }
    }

    private HttpResponse methodNotAllowed() {
        return new HttpResponse("HTTP/1.1 405 Method Not Allowed\n");
    }

    private HttpResponse ok200() {
        return new HttpResponse("HTTP/1.1 200 OK\n");
    }
}
