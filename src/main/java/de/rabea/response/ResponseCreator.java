package de.rabea.response;

public class ResponseCreator {

    private StatusLine statusLine;
    private final String PROTOCOL = "HTTP/1.1";

    public ResponseCreator(StatusLine statusLine) {
        this.statusLine = statusLine;
    }

    public String create() {
        return status();
    }

    private String status() {
        return PROTOCOL + " " + statusLine.printable() + "\n";
    }
}
