package de.rabea.controller;

import de.rabea.request.HttpRequest;
import de.rabea.request.RequestLine;

public class FakeHttpRequest extends HttpRequest {

    private String request_line;

    public FakeHttpRequest(String request_line) {
        super(null, null, null);
        this.request_line = request_line;
    }

    public RequestLine requestLine() {
        return new RequestLine(request_line);
    }
}
