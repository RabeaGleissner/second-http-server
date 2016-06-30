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

    public String route() {
        String uri = uri();
        if (hasParams(uri)) {
            return uriWithoutParams(uri);
        }
        return uri;
    }

    private String uriWithoutParams(String uri) {
        return uri.substring(0, uri.indexOf("?"));
    }

    private boolean hasParams(String uri) {
        return uri.contains("?");
    }

    public String protocol() {
        return firstLine.split(" ")[2];
    }

    public String asString() {
       return firstLine;
    }

    private String getFirstLine(String rawRequest) {
        return rawRequest.split("\n")[0];
    }

}