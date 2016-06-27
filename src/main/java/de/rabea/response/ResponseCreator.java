package de.rabea.response;

public class ResponseCreator {

    private final StatusLine statusLine;
    private final ResponseHeader responseHeader;
    private final String PROTOCOL = "HTTP/1.1";

    public ResponseCreator(StatusLine statusLine, ResponseHeader responseHeader) {
        this.statusLine = statusLine;
        this.responseHeader = responseHeader;
    }

    public String create() {
        return status() + responseHeader.create();
    }

    private String status() {
        return PROTOCOL + " " + statusLine.printable() + "\n";
    }
}
