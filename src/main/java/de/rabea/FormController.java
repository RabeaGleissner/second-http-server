package de.rabea;

import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;

import static de.rabea.request.HttpVerb.PUT;
import static de.rabea.response.StatusLine.NOT_ALLOWED;
import static de.rabea.response.StatusLine.OK;

public class FormController implements Controller {

    @Override
    public HttpResponse getResponse(HttpRequest request) {
        if (request.requestLine().method() == PUT) {
            return ok200();
        }
        return methodNotAllowed();
    }

    private HttpResponse methodNotAllowed() {
        return new HttpResponse(NOT_ALLOWED);
    }

    private HttpResponse ok200() {
        return new HttpResponse(OK);
    }
}
