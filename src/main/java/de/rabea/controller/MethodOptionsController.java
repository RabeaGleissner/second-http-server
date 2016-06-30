package de.rabea.controller;

import de.rabea.Controller;
import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;
import de.rabea.response.head.OptionsResponseHeader;

import static de.rabea.request.HttpVerb.*;
import static de.rabea.response.head.StatusLine.OK;

public class MethodOptionsController extends Controller {

    @Override
    public HttpResponse doGet(HttpRequest request) {
        return okResponse();
    }

    @Override
    public HttpResponse doHead(HttpRequest request) {
        return okResponse();
    }

    @Override
    public HttpResponse doPost(HttpRequest request) {
        return okResponse();
    }

    @Override
    public HttpResponse doOptions(HttpRequest request) {
        return new HttpResponse(OK, new OptionsResponseHeader(GET, HEAD, POST, OPTIONS, PUT));
    }

    @Override
    public HttpResponse doPut(HttpRequest request) {
        return okResponse();
    }

}
