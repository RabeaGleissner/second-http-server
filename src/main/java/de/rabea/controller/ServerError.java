package de.rabea.controller;

import de.rabea.Controller;
import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;

public class ServerError implements Controller {

    @Override
    public HttpResponse getResponse(HttpRequest request) {
        return new HttpResponse("HTTP/1.1 500 Internal Server Error");
    }
}
