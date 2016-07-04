package de.rabea.controller;

import de.rabea.Controller;
import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;
import de.rabea.response.head.RedirectResponseHeader;

import static de.rabea.response.head.StatusLine.REDIRECT;

public class RedirectController extends Controller {

    @Override
    public HttpResponse doGet(HttpRequest request) {
        return new HttpResponse(REDIRECT, new RedirectResponseHeader("http://localhost:5000/"));
    }
}