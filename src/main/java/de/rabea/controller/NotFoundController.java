package de.rabea.controller;

import de.rabea.Controller;
import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;

public class NotFoundController implements Controller {

    @Override
    public HttpResponse getResponse(HttpRequest request) {
        return new HttpResponse("HTTP/1.1 404 Not Found");
    }
}
