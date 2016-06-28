package de.rabea.controller;

import de.rabea.Controller;
import de.rabea.Controller2;
import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;

import static de.rabea.response.head.StatusLine.OK;

public class TeaController extends Controller2 implements Controller {

    @Override
    public HttpResponse getResponse(HttpRequest httpRequest) {
        throw new RuntimeException("not this one");
    }

    @Override
    public HttpResponse doGet(HttpRequest request) {
        return new HttpResponse(OK);
    }
}
