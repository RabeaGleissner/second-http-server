package de.rabea.controller;

import de.rabea.Controller;
import de.rabea.request.HttpRequest;
import de.rabea.request.HttpVerb;
import de.rabea.response.HttpResponse;

import java.util.HashMap;
import java.util.Map;

import static de.rabea.request.HttpVerb.GET;
import static de.rabea.request.HttpVerb.HEAD;
import static de.rabea.response.StatusLine.NOT_ALLOWED;
import static de.rabea.response.StatusLine.OK;

public class RootController implements Controller {

    Map<HttpVerb, HttpResponse> responsesForMethods = new HashMap<>();

    public RootController() {
        this.responsesForMethods = registerResponses();
    }

    @Override
    public HttpResponse getResponse(HttpRequest httpRequest) {
        return responsesForMethods.getOrDefault(httpRequest.requestLine().method(), methodNotAllowed());
    }

    private Map<HttpVerb, HttpResponse> registerResponses() {
        HttpResponse okResponse = ok200();
        responsesForMethods.put(GET, okResponse);
        responsesForMethods.put(HEAD, okResponse);
        return responsesForMethods;
    }

    private HttpResponse ok200() {
        return new HttpResponse(OK);
    }

    private HttpResponse methodNotAllowed() {
        return new HttpResponse(NOT_ALLOWED);
    }
}
