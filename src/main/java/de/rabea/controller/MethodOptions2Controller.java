package de.rabea.controller;

import de.rabea.Controller;
import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;

import static de.rabea.response.head.StatusLine.OK;

public class MethodOptions2Controller extends Controller {

    @Override
    public HttpResponse doGet(HttpRequest request) {
        return new HttpResponse(OK);
    }

    @Override
    public HttpResponse doOptions(HttpRequest request) {
        return new HttpResponse(OK, () -> "Allow: GET,OPTIONS");
    }
}
