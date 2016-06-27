package de.rabea.response;

import de.rabea.response.head.StatusLine;

public class PutResponse extends ResponseCreator {

    private StatusLine statusLine;

    public PutResponse(StatusLine statusLine) {
        this.statusLine = statusLine;
    }

    @Override
    public HttpResponse create(String body) {
        return new HttpResponse(statusLine);
    }
}
