package de.rabea.controller;

import de.rabea.Controller;
import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;

import static de.rabea.response.head.StatusLine.OK;

public class RootController extends Controller {

    @Override
    public HttpResponse doGet(HttpRequest request) {
        return new HttpResponse(OK);
    }

    @Override
    public HttpResponse doHead(HttpRequest request) {
        return new HttpResponse(OK);
    }
}
