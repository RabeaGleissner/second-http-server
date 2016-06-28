package de.rabea.controller;

import de.rabea.Controller;
import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;

import static de.rabea.response.head.StatusLine.OK;

public class TeaController implements Controller {

    @Override
    public HttpResponse getResponse(HttpRequest httpRequest) {
        return new HttpResponse(OK);
    }
}
