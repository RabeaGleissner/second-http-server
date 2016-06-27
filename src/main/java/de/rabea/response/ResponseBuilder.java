package de.rabea.response;

import de.rabea.response.head.EmptyResponseHeader;
import de.rabea.response.head.StatusLine;

public class ResponseBuilder {

    private final StatusLine statusLine;
    private final String body;
    private final ResponseHeader responseHeader;
    private final String PROTOCOL = "HTTP/1.1";

    public ResponseBuilder(StatusLine statusLine, ResponseHeader responseHeader) {
        this.statusLine = statusLine;
        this.responseHeader = responseHeader;
        this.body = "";
    }

    public ResponseBuilder(StatusLine statusLine, String body) {
        this.statusLine = statusLine;
        this.responseHeader = new EmptyResponseHeader();
        this.body = "\n" + body;
    }

    public String create() {
        return status() + responseHeader.create() + body;
    }

    private String status() {
        return PROTOCOL + " " + statusLine.printable() + "\n";
    }
}
