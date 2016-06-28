package de.rabea.response.creator;

import de.rabea.ContentStorage;
import de.rabea.response.HttpResponse;
import de.rabea.response.ResponseCreator;
import de.rabea.response.head.StatusLine;

public class DeleteResponseCreator implements ResponseCreator {

    private final StatusLine statusLine;
    private final ContentStorage contentStorage;

    public DeleteResponseCreator(StatusLine statusLine, ContentStorage contentStorage) {
        this.statusLine = statusLine;
        this.contentStorage = contentStorage;
    }

    @Override
    public HttpResponse create(byte[] body) {
        if (contentStorage.hasContentFor()) {
            contentStorage.deleteContentFor();
        }
        return new HttpResponse(statusLine);
    }
}
