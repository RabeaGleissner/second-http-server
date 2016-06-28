package de.rabea.controller;

import de.rabea.Controller;
import de.rabea.request.HttpRequest;
import de.rabea.request.HttpVerb;
import de.rabea.response.HttpResponse;
import de.rabea.response.ResponseCreator;
import de.rabea.response.creator.GetResponseCreator;
import de.rabea.response.creator.NoMethodResponseCreator;

import java.util.HashMap;
import java.util.Map;

import static de.rabea.request.HttpVerb.GET;
import static de.rabea.response.head.StatusLine.OK;

public class AssetController implements Controller {

    private Map<HttpVerb, ResponseCreator> responsesForMethods = new HashMap<>();

    public AssetController() {
        this.responsesForMethods = registerResponses();
    }

    @Override
    public HttpResponse getResponse(HttpRequest httpRequest) {
        return responsesForMethods.getOrDefault(
                httpRequest.requestLine().method(),
                new NoMethodResponseCreator())
                .create(httpRequest.body());
    }

    private Map<HttpVerb, ResponseCreator> registerResponses() {
        responsesForMethods.put(GET, new GetResponseCreator(OK));
        return responsesForMethods;
    }
}