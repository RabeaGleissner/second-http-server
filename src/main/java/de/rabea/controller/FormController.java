package de.rabea.controller;

import de.rabea.ContentStorage;
import de.rabea.Controller;
import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;
import de.rabea.response.creator.DeleteResponseCreator;
import de.rabea.response.creator.GetResponseCreator;
import de.rabea.response.creator.PostResponseCreator;
import de.rabea.response.creator.PutResponseCreator;

import static de.rabea.response.head.StatusLine.OK;

public class FormController extends Controller {

    private final ContentStorage contentStorage;

    public FormController(ContentStorage contentStorage) {
        this.contentStorage = contentStorage;
    }

    @Override
    public HttpResponse doPut(HttpRequest request) {
        return new PutResponseCreator(OK, contentStorage).create(request.body().getBytes());
    }

    @Override
    public HttpResponse doPost(HttpRequest request) {
        return new PostResponseCreator(OK, contentStorage).create(request.body().getBytes());
    }

    @Override
    public HttpResponse doGet(HttpRequest request) {
        return new GetResponseCreator(OK, contentStorage).create(request.body().getBytes());
    }

    @Override
    public HttpResponse doDelete(HttpRequest request) {
        return new DeleteResponseCreator(OK, contentStorage).create(request.body().getBytes());
    }

}
