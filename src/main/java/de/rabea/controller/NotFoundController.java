package de.rabea.controller;

import de.rabea.Controller;
import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;

import static de.rabea.response.StatusLine.NOT_FOUND;

public class NotFoundController implements Controller {

    @Override
    public HttpResponse getResponse(HttpRequest request) {
        return new HttpResponse(NOT_FOUND);
    }
}
