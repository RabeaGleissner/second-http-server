package de.rabea.request;

import java.util.Map;

public class HttpRequest {

    private final RequestLine requestLine;
    private final Map<String, String> requestHeader;
    private final String requestBody;

    public HttpRequest(RequestLine requestLine, Map<String, String> requestHeader, String requestBody) {
        this.requestLine = requestLine;
        this.requestHeader = requestHeader;
        this.requestBody = requestBody;
    }

    public Map<String, String> requestHeaders() {
        return requestHeader;
    }

    public RequestLine requestLine() {
        return requestLine;
    }

    public String body() {
        return requestBody;
    }
}