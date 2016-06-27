package de.rabea;

import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;

import static de.rabea.request.HttpVerb.PUT;

public class FormController implements Controller {

    @Override
    public HttpResponse getResponse(HttpRequest request) {
        if (request.requestLine().method() == PUT) {
            return ok200();
        }
        return methodNotAllowed();
    }

    private HttpResponse methodNotAllowed() {
        return new HttpResponse("HTTP/1.1 405 Method Not Allowed\n");
    }

    private HttpResponse ok200() {
        return new HttpResponse("HTTP/1.1 200 OK\n");
    }
}
