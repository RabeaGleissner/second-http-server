package de.rabea.controller;

import de.rabea.Controller;
import de.rabea.request.HttpRequest;
import de.rabea.request.HttpVerb;
import de.rabea.response.*;

import java.util.HashMap;
import java.util.Map;

import static de.rabea.request.HttpVerb.*;
import static de.rabea.request.HttpVerb.PUT;

public class FormController extends Controller {

    private Map<HttpVerb, ResponseCreator> responsesForMethods = new HashMap<>();

    public FormController() {
        this.responsesForMethods = registerResponses();
    }

    @Override
    public HttpResponse getResponse(HttpRequest request) {
        return responsesForMethods.getOrDefault(request.requestLine().method(),
                new GetResponse(StatusLine.NOT_ALLOWED)).create();
    }

    private Map<HttpVerb, ResponseCreator> registerResponses() {
        HttpResponse okResponse = ok200();
        responsesForMethods.put(PUT, new PutResponse(StatusLine.OK));
        responsesForMethods.put(POST, new PostResponse());
        return responsesForMethods;
    }
}
