package de.rabea.controller;

import de.rabea.Controller;
import de.rabea.Controller2;
import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;
import de.rabea.response.head.RedirectResponseHeader;

import static de.rabea.response.head.StatusLine.*;

public class RedirectController extends Controller2 implements Controller {

    @Override
    public HttpResponse getResponse(HttpRequest httpRequest) {
        throw new RuntimeException("not this one");
    }

    @Override
    public HttpResponse doGet(HttpRequest request) {
        return new HttpResponse(REDIRECT, new RedirectResponseHeader("http://localhost:5000/"));
    }
}