package de.rabea.controller;

import de.rabea.Controller;
import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;
import de.rabea.response.head.RedirectResponseHeader;

import static de.rabea.response.head.StatusLine.*;

public class RedirectController implements Controller {

    @Override
    public HttpResponse getResponse(HttpRequest httpRequest) {
        return new HttpResponse(REDIRECT, new RedirectResponseHeader("http://localhost:5000/"));
    }
}