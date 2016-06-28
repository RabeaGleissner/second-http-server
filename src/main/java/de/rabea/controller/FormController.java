package de.rabea.controller;

import de.rabea.ContentStorage;
import de.rabea.Controller;
import de.rabea.request.HttpRequest;
import de.rabea.request.HttpVerb;
import de.rabea.response.HttpResponse;
import de.rabea.response.ResponseCreator;
import de.rabea.response.creator.*;

import java.util.HashMap;
import java.util.Map;

import static de.rabea.request.HttpVerb.*;
import static de.rabea.response.head.StatusLine.OK;

public class FormController implements Controller {

    private Map<HttpVerb, ResponseCreator> responsesForMethods = new HashMap<>();
    private final ContentStorage contentStorage;

    public FormController(ContentStorage contentStorage) {
        this.contentStorage = contentStorage;
        this.responsesForMethods = registerResponses();
    }

    @Override
    public HttpResponse getResponse(HttpRequest request) {
        return responsesForMethods.getOrDefault(
                request.requestLine().method(),
                new NoMethodResponseCreator())
                .create(request.body().getBytes());
    }

    private Map<HttpVerb, ResponseCreator> registerResponses() {
        responsesForMethods.put(PUT, new PutResponseCreator(OK, contentStorage));
        responsesForMethods.put(GET, new GetResponseCreator(OK, contentStorage));
        responsesForMethods.put(POST, new PostResponseCreator(OK, contentStorage));
        responsesForMethods.put(DELETE, new DeleteResponseCreator(OK, contentStorage));
        return responsesForMethods;
    }
}
