package de.rabea.controller;

import de.rabea.Controller;
import de.rabea.ParameterParser;
import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;

import static de.rabea.response.head.StatusLine.OK;

public class ParametersController extends Controller {

    @Override
    public HttpResponse doGet(HttpRequest request) {
        return new HttpResponse(OK, new ParameterParser().parse(request.requestLine().uri()).getBytes());
    }
}
