package de.rabea.controller;

import de.rabea.Controller2;
import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;

import static de.rabea.response.head.StatusLine.NOT_FOUND;

public class NotFoundController extends Controller2 {

    @Override
    public HttpResponse dispatch(HttpRequest httpRequest) {
        return new HttpResponse(NOT_FOUND);
    }
}
