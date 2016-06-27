package de.rabea;

import de.rabea.request.HttpRequest;
import de.rabea.request.HttpVerb;
import de.rabea.response.HttpResponse;

import java.util.HashMap;
import java.util.Map;

import static de.rabea.request.HttpVerb.*;
import static de.rabea.request.HttpVerb.PUT;

public class FormController extends Controller {

    Map<HttpVerb, HttpResponse> responsesForMethods = new HashMap<>();

    public FormController() {
        this.responsesForMethods = registerResponses();
    }

    @Override
    public HttpResponse getResponse(HttpRequest request) {
        return responsesForMethods.getOrDefault(request.requestLine().method(),
                methodNotAllowed());
    }

    private Map<HttpVerb, HttpResponse> registerResponses() {
        HttpResponse okResponse = ok200();
        responsesForMethods.put(PUT, okResponse);
        responsesForMethods.put(POST, okResponse);
        return responsesForMethods;
    }
}
