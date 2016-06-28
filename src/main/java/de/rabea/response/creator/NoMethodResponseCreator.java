package de.rabea.response.creator;

import de.rabea.response.HttpResponse;
import de.rabea.response.ResponseCreator;
import de.rabea.response.head.StatusLine;

public class NoMethodResponseCreator implements ResponseCreator {

    @Override
    public HttpResponse create(String body) {
        return new HttpResponse(StatusLine.NOT_ALLOWED);
    }
}
