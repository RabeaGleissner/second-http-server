package de.rabea.response;

import de.rabea.response.head.EmptyResponseHeader;
import de.rabea.response.head.StatusLine;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

public class HttpResponse {

    private final byte[] response;

    public HttpResponse(StatusLine statusLine) {
        this.response = new ResponseBuilder(statusLine, new EmptyResponseHeader()).create(new ByteArrayOutputStream());
    }

    public HttpResponse(StatusLine statusLine, ResponseHeader responseHeader) {
        this.response = new ResponseBuilder(statusLine, responseHeader).create(new ByteArrayOutputStream());
    }

    public HttpResponse(StatusLine statusLine, byte[] body) {
        this.response = new ResponseBuilder(statusLine, body).create(new ByteArrayOutputStream());
    }

    public byte[] asBytes() {
        return response;
    }

    public String asString() {
        try {
            return new String(response, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}