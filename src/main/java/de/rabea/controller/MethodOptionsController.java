package de.rabea.controller;

import de.rabea.ContentStorage;
import de.rabea.Controller;
import de.rabea.request.HttpRequest;
import de.rabea.request.HttpVerb;
import de.rabea.response.*;
import de.rabea.response.creator.*;
import de.rabea.response.head.OptionsResponseHeader;

import java.util.HashMap;
import java.util.Map;

import static de.rabea.request.HttpVerb.*;
import static de.rabea.response.head.StatusLine.NOT_ALLOWED;
import static de.rabea.response.head.StatusLine.OK;

public class MethodOptionsController extends Controller {
    private Map<HttpVerb, ResponseCreator> responsesForMethods = new HashMap<>();
    private ContentStorage contentStorage;

    public MethodOptionsController(ContentStorage contentStorage) {
        this.contentStorage = contentStorage;
        this.responsesForMethods = registerResponses();
    }

    @Override
    public HttpResponse getResponse(HttpRequest request) {
        return responsesForMethods.getOrDefault(
                request.requestLine().method(),
                new GetResponseCreator(NOT_ALLOWED))
                .create(request.body());
    }

    private Map<HttpVerb, ResponseCreator> registerResponses() {
        responsesForMethods.put(GET, new GetResponseCreator(OK));
        responsesForMethods.put(PUT, new PutResponseCreator(OK, contentStorage, this));
        responsesForMethods.put(POST, new PostResponseCreator(OK, contentStorage, this));
        responsesForMethods.put(HEAD, new HeadResponseCreator(OK));
        responsesForMethods.put(OPTIONS, new OptionsResponseCreator(OK, new OptionsResponseHeader(responsesForMethods)));
        return responsesForMethods;
    }

}
