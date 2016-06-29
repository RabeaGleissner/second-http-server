package de.rabea.controller;

import de.rabea.ContentStorage;
import de.rabea.Controller;
import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;
import de.rabea.response.head.OptionsResponseHeader;

import static de.rabea.request.HttpVerb.*;
import static de.rabea.response.head.StatusLine.OK;

public class MethodOptionsController extends Controller {
    private final ContentStorage contentStorage;

    public MethodOptionsController(ContentStorage contentStorage) {
        this.contentStorage = contentStorage;
    }

    @Override
    public HttpResponse doGet(HttpRequest request) {
        return new HttpResponse(OK);
    }

    @Override
    public HttpResponse doHead(HttpRequest request) {
        return new HttpResponse(OK);
    }

    @Override
    public HttpResponse doPost(HttpRequest request) {
        contentStorage.store(request.body().getBytes());
        return new HttpResponse(OK);
    }

    @Override
    public HttpResponse doOptions(HttpRequest request) {
        return new HttpResponse(OK, new OptionsResponseHeader(GET, HEAD, POST, OPTIONS, PUT));
    }

    @Override
    public HttpResponse doPut(HttpRequest request) {
        contentStorage.store(request.body().getBytes());
        return new HttpResponse(OK);
    }

}
