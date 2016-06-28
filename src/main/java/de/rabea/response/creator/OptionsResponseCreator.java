package de.rabea.response.creator;

import de.rabea.response.HttpResponse;
import de.rabea.response.ResponseCreator;
import de.rabea.response.head.OptionsResponseHeader;
import de.rabea.response.head.StatusLine;

public class OptionsResponseCreator implements ResponseCreator {

    private final StatusLine statusLine;
    private final OptionsResponseHeader optionsResponseHeader;

    public OptionsResponseCreator(StatusLine statusLine, OptionsResponseHeader optionsResponseHeader) {
        this.statusLine = statusLine;
        this.optionsResponseHeader = optionsResponseHeader;
    }

    @Override
    public HttpResponse create(byte[] body) {
        return new HttpResponse(statusLine, optionsResponseHeader);
    }
}