package de.rabea.controller;

import de.rabea.Controller;
import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;

public class TeaController extends Controller {

    @Override
    public HttpResponse doGet(HttpRequest request) {
        return okResponse();
    }
}