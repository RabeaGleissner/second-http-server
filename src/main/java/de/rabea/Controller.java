package de.rabea;

import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;

public interface Controller {
    HttpResponse getResponse(HttpRequest request);
}
