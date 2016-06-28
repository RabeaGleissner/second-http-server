package de.rabea.response.creator;

import de.rabea.ContentStorage;
import de.rabea.response.HttpResponse;
import de.rabea.response.ResponseCreator;
import de.rabea.response.head.StatusLine;

public class PostResponseCreator implements ResponseCreator {

    private final StatusLine statusLine;
    private final ContentStorage contentStorage;

    public PostResponseCreator(StatusLine statusLine, ContentStorage contentStorage) {
        this.statusLine = statusLine;
        this.contentStorage = contentStorage;
    }

    @Override
    public HttpResponse create(byte[] body) {
        contentStorage.store(body);
        return new HttpResponse(statusLine);
    }
}
