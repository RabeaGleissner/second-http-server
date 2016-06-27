package de.rabea.response;

public class PostResponse extends ResponseCreator {

    @Override
    public HttpResponse create() {
        return new HttpResponse();
    }
}
