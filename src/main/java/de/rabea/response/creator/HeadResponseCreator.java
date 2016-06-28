package de.rabea.response.creator;

import de.rabea.response.HttpResponse;
import de.rabea.response.ResponseCreator;
import de.rabea.response.head.StatusLine;

public class HeadResponseCreator implements ResponseCreator {

    private final StatusLine statusLine;

    public HeadResponseCreator(StatusLine statusLine) {
        this.statusLine = statusLine;
    }

    @Override
    public HttpResponse create(byte[] body) {
        return new HttpResponse(statusLine);
    }
}
