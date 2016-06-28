package de.rabea.response;

import de.rabea.response.head.EmptyResponseHeader;
import de.rabea.response.head.StatusLine;

public class HttpResponse {

    private String response;

    public HttpResponse(StatusLine statusLine) {
        this.response = new ResponseBuilder(statusLine, new EmptyResponseHeader()).create();
    }

    public HttpResponse(StatusLine statusLine, ResponseHeader responseHeader) {
        this.response = new ResponseBuilder(statusLine, responseHeader).create();
    }

    public HttpResponse(StatusLine statusLine, String body) {
        this.response = new ResponseBuilder(statusLine, body).create();
    }

    public byte[] asBytes() {
        return response.getBytes();
    }

    public String asString() {
       return response;
    }
}
