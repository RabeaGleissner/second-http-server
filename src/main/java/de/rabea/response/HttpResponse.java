package de.rabea.response;

public class HttpResponse {

    private String response;

    public HttpResponse(StatusLine statusLine) {
        this.response = new ResponseCreator(statusLine).create();
    }

    public byte[] asBytes() {
        return response.getBytes();
    }

    public String asString() {
       return response;
    }
}
