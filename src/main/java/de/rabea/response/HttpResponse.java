package de.rabea.response;

public class HttpResponse {

    private String response;

    public HttpResponse(String response) {
        this.response = response;
    }

    public byte[] asBytes() {
        return response.getBytes();
    }
}
