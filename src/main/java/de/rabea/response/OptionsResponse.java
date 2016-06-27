package de.rabea.response;

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
