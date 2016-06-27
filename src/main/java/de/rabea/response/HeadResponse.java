package de.rabea.response;

import de.rabea.response.head.StatusLine;

public class HeadResponse extends ResponseCreator {

    private StatusLine statusLine;

    public HeadResponse(StatusLine statusLine) {
        this.statusLine = statusLine;
    }

    @Override
    public HttpResponse create(String body) {
        return new HttpResponse(statusLine);
    }
}
