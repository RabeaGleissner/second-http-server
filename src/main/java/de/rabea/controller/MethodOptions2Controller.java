package de.rabea.controller;

import de.rabea.Controller;
import de.rabea.request.HttpRequest;
import de.rabea.request.HttpVerb;
import de.rabea.response.HttpResponse;
import de.rabea.response.ResponseCreator;
import de.rabea.response.creator.GetResponseCreator;
import de.rabea.response.creator.NoMethodResponseCreator;
import de.rabea.response.creator.OptionsResponseCreator;
import de.rabea.response.head.OptionsResponseHeader;

import java.util.HashMap;
import java.util.Map;

import static de.rabea.request.HttpVerb.GET;
import static de.rabea.request.HttpVerb.OPTIONS;
import static de.rabea.response.head.StatusLine.OK;

public class MethodOptions2Controller implements Controller {

    private Map<HttpVerb, ResponseCreator> responsesForMethods = new HashMap<>();
    private HttpRequest request;

    public MethodOptions2Controller() {
        this.responsesForMethods = registerResponses();
        this.request = null;
    }

    @Override
    public HttpResponse getResponse(HttpRequest httpRequest) {
        this.request = httpRequest;
        return responsesForMethods.getOrDefault(
                request.requestLine().method(),
                new NoMethodResponseCreator())
                .create(httpRequest.body());
    }

    private Map<HttpVerb, ResponseCreator> registerResponses() {
        responsesForMethods.put(GET, new GetResponseCreator(OK));
        responsesForMethods.put(OPTIONS, new OptionsResponseCreator(OK, new OptionsResponseHeader(responsesForMethods)));
        return responsesForMethods;
    }
}