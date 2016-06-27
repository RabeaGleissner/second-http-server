package de.rabea;

import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;

public abstract class Controller {
    public abstract HttpResponse getResponse(HttpRequest httpRequest);
}
