package de.rabea.controller;

import de.rabea.Controller;
import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;

import static de.rabea.response.head.StatusLine.TEAPOT;

public class CoffeeController implements Controller {

    @Override
    public HttpResponse getResponse(HttpRequest httpRequest) {
        return new HttpResponse(TEAPOT, "I'm a teapot".getBytes());
    }
}
