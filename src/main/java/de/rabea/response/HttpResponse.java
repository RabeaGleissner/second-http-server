package de.rabea.response;

public class HttpResponse {

    private String response;

    public HttpResponse(StatusLine statusLine) {
        this.response = new ResponseBuilder(statusLine, new EmptyResponseHeader()).create();
    }

    public HttpResponse(StatusLine statusLine, ResponseHeader responseHeader) {
        this.response = new ResponseBuilder(statusLine, responseHeader).create();
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
