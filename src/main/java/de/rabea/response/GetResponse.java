package de.rabea.response;

public class GetResponse extends ResponseCreator {

    private StatusLine statusLine;

    public GetResponse(StatusLine statusLine) {
        this.statusLine = statusLine;
    }

    @Override
    public HttpResponse create() {
        return new HttpResponse(statusLine);
    }
}
