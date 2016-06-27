package de.rabea.controller;

import de.rabea.Controller;
import de.rabea.request.HttpRequest;
import de.rabea.request.HttpVerb;
import de.rabea.response.HttpResponse;
import de.rabea.response.OptionsResponseHeader;

import java.util.HashMap;
import java.util.Map;

import static de.rabea.request.HttpVerb.*;
import static de.rabea.response.StatusLine.OK;

public class MethodOptionsController extends Controller {
    Map<HttpVerb, HttpResponse> responsesForMethods = new HashMap<>();
    HttpRequest request;

    public MethodOptionsController() {
        this.responsesForMethods = registerResponses();
        this.request = null;
    }

    @Override
    public HttpResponse getResponse(HttpRequest httpRequest) {
        this.request = httpRequest;
        return responsesForMethods.getOrDefault(request.requestLine().method(), methodNotAllowed());
    }

    private Map<HttpVerb, HttpResponse> registerResponses() {
        HttpResponse okResponse = ok200();
        responsesForMethods.put(GET, okResponse);
        responsesForMethods.put(PUT, okResponse);
        responsesForMethods.put(POST, okResponse);
        responsesForMethods.put(HEAD, okResponse);
        responsesForMethods.put(OPTIONS, responseForOptions());
        return responsesForMethods;
    }

    private HttpResponse responseForOptions() {
        return new HttpResponse(OK, new OptionsResponseHeader(responsesForMethods));
    }
}
