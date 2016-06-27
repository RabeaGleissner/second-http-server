package de.rabea;

import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;

import static de.rabea.request.HttpVerb.PUT;

public class FormController extends Controller {

    @Override
    public HttpResponse getResponse(HttpRequest request) {
        if (request.requestLine().method() == PUT) {
            return ok200();
        }
        return methodNotAllowed();
    }
}
