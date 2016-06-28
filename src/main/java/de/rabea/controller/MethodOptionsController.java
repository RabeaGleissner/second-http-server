package de.rabea.controller;

import de.rabea.ContentStorage;
import de.rabea.Controller;
import de.rabea.Controller2;
import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;
import de.rabea.response.creator.PostResponseCreator;
import de.rabea.response.creator.PutResponseCreator;

import static de.rabea.response.head.StatusLine.OK;

public class MethodOptionsController extends Controller2 implements Controller {
    private final ContentStorage contentStorage;

    public MethodOptionsController(ContentStorage contentStorage) {
        this.contentStorage = contentStorage;
    }

    @Override
    public HttpResponse getResponse(HttpRequest request) {
        throw new RuntimeException("should not be called");
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
        return new PostResponseCreator(OK, contentStorage).create(request.body().getBytes());
    }

    @Override
    public HttpResponse doPut(HttpRequest request) {
        return new PutResponseCreator(OK, contentStorage).create(request.body().getBytes());
    }

    @Override
    public HttpResponse doOptions(HttpRequest request) {
        return new HttpResponse(OK, () -> "Allow: GET,POST,PUT,HEAD");
    }

}
