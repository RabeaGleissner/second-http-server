package de.rabea;

import de.rabea.request.HttpRequest;
import de.rabea.request.HttpVerb;
import de.rabea.request.RequestLine;

public class HttpRequestDummy extends HttpRequest {

    public HttpRequestDummy(String requestLine) {
        super(new RequestLine(requestLine), null, "");
    }

    public HttpRequestDummy(HttpVerb verb ) {
        super(verb, "/");
    }
}
