package de.rabea.controller;

import de.rabea.Controller2;
import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;
import de.rabea.response.creator.GetResponseCreator;
import de.rabea.response.creator.HeadResponseCreator;

import static de.rabea.response.head.StatusLine.OK;

public class RootController extends Controller2 {

    @Override
    public HttpResponse doGet(HttpRequest request) {
        return new GetResponseCreator(OK).create(request.body().getBytes());
    }

    @Override
    public HttpResponse doHead(HttpRequest request) {
        return new HeadResponseCreator(OK).create(request.body().getBytes());
    }
}
