package de.rabea.controller;

import de.rabea.Controller;
import de.rabea.request.HttpRequest;
import de.rabea.request.HttpVerb;
import de.rabea.response.HttpResponse;
import de.rabea.response.OptionsResponseHeader;

import java.util.HashMap;
import java.util.Map;

import static de.rabea.request.HttpVerb.GET;
import static de.rabea.request.HttpVerb.OPTIONS;
import static de.rabea.response.StatusLine.OK;

public class MethodOptions2Controller extends Controller {

    Map<HttpVerb, HttpResponse> responsesForMethods = new HashMap<>();
    HttpRequest request;

    public MethodOptions2Controller() {
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
        responsesForMethods.put(OPTIONS, responseForOptions());
        return responsesForMethods;
    }

    private HttpResponse responseForOptions() {
        return new HttpResponse(OK, new OptionsResponseHeader(responsesForMethods));
    }
}
