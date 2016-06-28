package de.rabea.response.head;

import de.rabea.response.HttpResponse;
import de.rabea.response.ResponseCreator;

public class FakeResponseCreator implements ResponseCreator {
    public FakeResponseCreator() {
        super();
    }

    @Override
    public HttpResponse create(String body) {
        return null;
    }
}
