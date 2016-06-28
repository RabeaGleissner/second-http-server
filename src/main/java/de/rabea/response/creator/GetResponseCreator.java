package de.rabea.response.creator;

import de.rabea.ContentStorage;
import de.rabea.response.HttpResponse;
import de.rabea.response.ResponseCreator;
import de.rabea.response.head.StatusLine;

public class GetResponseCreator implements ResponseCreator {

    private final StatusLine statusLine;
    private ContentStorage contentStorage;
    private final boolean simpleResponse;

    public GetResponseCreator(StatusLine statusLine, ContentStorage contentStorage) {
        this.statusLine = statusLine;
        this.contentStorage = contentStorage;
        this.simpleResponse = false;
    }

    public GetResponseCreator(StatusLine statusLine) {
        this.statusLine = statusLine;
        this.simpleResponse = true;
    }

    @Override
    public HttpResponse create(byte[] body) {
        if (simpleResponse) {
            return new HttpResponse(statusLine);
        } else {
            if (body.length != 0) {
                contentStorage.store(body);
            }
            return responseWithBody();
        }
    }

    private HttpResponse responseWithBody() {
        if (contentStorage.hasContentFor()) {
            return new HttpResponse(statusLine, contentStorage.content());
        } else {
            return new HttpResponse(statusLine);
        }
    }
}
