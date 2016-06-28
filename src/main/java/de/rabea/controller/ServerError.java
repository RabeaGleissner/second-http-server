package de.rabea.controller;

import de.rabea.Controller;
import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;

import static de.rabea.response.head.StatusLine.SERVER_ERROR;

public class ServerError implements Controller {

    @Override
    public HttpResponse getResponse(HttpRequest request) {
        return new HttpResponse(SERVER_ERROR);
    }
}
