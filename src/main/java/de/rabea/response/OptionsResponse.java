package de.rabea.response;

import de.rabea.response.head.OptionsResponseHeader;
import de.rabea.response.head.StatusLine;

public class OptionsResponse extends ResponseCreator {

    private final StatusLine statusLine;
    private final OptionsResponseHeader optionsResponseHeader;

    public OptionsResponse(StatusLine statusLine, OptionsResponseHeader optionsResponseHeader) {
        this.statusLine = statusLine;
        this.optionsResponseHeader = optionsResponseHeader;
    }

    @Override
    public HttpResponse create(String body) {
        return new HttpResponse(statusLine, optionsResponseHeader);
    }
}
