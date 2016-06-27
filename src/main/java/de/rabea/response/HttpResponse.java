package de.rabea.response;

import de.rabea.response.head.StatusLine;

public class HttpResponse {

    private StatusLine statusLine;
    private String body;
    private String response;

    public HttpResponse(StatusLine statusLine) {
        this.response = new ResponseBuilder(statusLine, new EmptyResponseHeader()).create();
    }

    public HttpResponse(StatusLine statusLine, ResponseHeader responseHeader) {
        this.response = new ResponseBuilder(statusLine, responseHeader).create();
    }

    public HttpResponse(StatusLine statusLine, String body) {
        this.statusLine = statusLine;
        this.body = body;
        this.response = new ResponseBuilder(statusLine, body).create();
    }

    public HttpResponse() {
    }

    public byte[] asBytes() {
        return response.getBytes();
    }

    public String asString() {
       return response;
    }
}
