package de.rabea.controller;

import de.rabea.ContentStorage;
import de.rabea.Controller;
import de.rabea.request.HttpRequest;
import de.rabea.request.HttpVerb;
import de.rabea.response.*;

import java.util.HashMap;
import java.util.Map;

import static de.rabea.request.HttpVerb.*;
import static de.rabea.request.HttpVerb.PUT;
import static de.rabea.response.head.StatusLine.*;

public class FormController extends Controller {

    private Map<HttpVerb, ResponseCreator> responsesForMethods = new HashMap<>();
    private ContentStorage contentStorage;

    public FormController(ContentStorage contentStorage) {
        this.contentStorage = contentStorage;
        this.responsesForMethods = registerResponses();
    }

    @Override
    public HttpResponse getResponse(HttpRequest request) {
        return responsesForMethods.getOrDefault(
                request.requestLine().method(),
                new GetResponse(NOT_ALLOWED, contentStorage, this))
                .create(request.body());
    }

    private Map<HttpVerb, ResponseCreator> registerResponses() {
        responsesForMethods.put(PUT, new PutResponse(OK));
        responsesForMethods.put(GET, new GetResponse(OK, contentStorage, this));
        responsesForMethods.put(POST, new PostResponse(OK, contentStorage, this));
        responsesForMethods.put(DELETE, new DeleteResponse(OK, contentStorage, this));
        return responsesForMethods;
    }
}
