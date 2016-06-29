package de.rabea.controller;

import de.rabea.ContentStorage;
import de.rabea.Controller;
import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;

import static de.rabea.response.head.StatusLine.OK;

public class FormController extends Controller {

    private final ContentStorage contentStorage;

    public FormController(ContentStorage contentStorage) {
        this.contentStorage = contentStorage;
    }

    @Override
    public HttpResponse doPut(HttpRequest request) {
        contentStorage.store(request.body().getBytes());
        return okResponse();
    }

    @Override
    public HttpResponse doPost(HttpRequest request) {
        contentStorage.store(request.body().getBytes());
        return okResponse();
    }

    @Override
    public HttpResponse doGet(HttpRequest request) {
        return new HttpResponse(OK, contentStorage.content());
    }

    @Override
    public HttpResponse doDelete(HttpRequest request) {
        contentStorage.deleteContentFor();
        return okResponse();
    }
}
