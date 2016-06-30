package de.rabea.controller;

import de.rabea.Controller;
import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;

import static de.rabea.response.head.StatusLine.TEAPOT;

public class CoffeeController extends Controller {

    @Override
    public HttpResponse doGet(HttpRequest request) {
        return new HttpResponse(TEAPOT, "I'm a teapot".getBytes());
    }
}
