package de.rabea.controller;

import de.rabea.Controller;
import de.rabea.request.HttpRequest;
import de.rabea.request.HttpVerb;
import de.rabea.response.HttpResponse;
import de.rabea.response.ResponseCreator;
import de.rabea.response.creator.GetResponseCreator;
import de.rabea.response.creator.HeadResponseCreator;

import java.util.HashMap;
import java.util.Map;

import static de.rabea.request.HttpVerb.GET;
import static de.rabea.request.HttpVerb.HEAD;
import static de.rabea.response.head.StatusLine.NOT_ALLOWED;
import static de.rabea.response.head.StatusLine.OK;

public class RootController extends Controller {

    private Map<HttpVerb, ResponseCreator> responsesForMethods = new HashMap<>();

    public RootController() {
        this.responsesForMethods = registerResponses();
    }

    @Override
    public HttpResponse getResponse(HttpRequest httpRequest) {
        return responsesForMethods.getOrDefault(httpRequest.requestLine().method(),
                new GetResponseCreator(NOT_ALLOWED)).create(httpRequest.body());
    }

    private Map<HttpVerb, ResponseCreator> registerResponses() {
        responsesForMethods.put(GET, new GetResponseCreator(OK));
        responsesForMethods.put(HEAD, new HeadResponseCreator(OK));
        return responsesForMethods;
    }
}
