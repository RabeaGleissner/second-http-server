package de.rabea.controller;

import de.rabea.Controller;
import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;

import static de.rabea.request.HttpVerb.GET;
import static de.rabea.request.HttpVerb.HEAD;

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
        return new HttpResponse("HTTP/1.1 405 Method Not Allowed\n");
    }

    private HttpResponse ok200() {
        return new HttpResponse("HTTP/1.1 200 OK\n");
    }
}
