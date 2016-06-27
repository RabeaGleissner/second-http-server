package de.rabea.request;

public class RequestLine {

    private final String firstLine;

    public RequestLine(String rawRequest) {
        this.firstLine = getFirstLine(rawRequest);
    }

    public HttpVerb method() {
        return HttpVerb.convert(firstLine.split(" ")[0]);
    }

    public String uri() {
        return firstLine.split(" ")[1];
    }

    public String protocol() {
        return firstLine.split(" ")[2];
    }

    private String getFirstLine(String rawRequest) {
        return rawRequest.split("\n")[0];
    }
}