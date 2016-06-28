package de.rabea.response.creator;

import de.rabea.ContentStorage;
import de.rabea.controller.RootController;
import de.rabea.response.HttpResponse;
import org.junit.Test;

import static de.rabea.response.head.StatusLine.OK;
import static org.junit.Assert.assertEquals;

public class GetResponseCreatorTest {

    private final String REQUEST_BODY = "My=Data\n";

    @Test
    public void createsSimpleResponse() {
        GetResponseCreator creator = new GetResponseCreator(OK);
        HttpResponse response = creator.create(REQUEST_BODY);
        assertEquals("HTTP/1.1 200 OK\n", response.asString());
    }

    @Test
    public void createsResponseWithBody() {
        ContentStorage storage = new ContentStorage();
        RootController controller = new RootController();
        storage.store("some content");
        GetResponseCreator creator = new GetResponseCreator(OK, storage);
        HttpResponse response = creator.create(REQUEST_BODY);
        assertEquals("HTTP/1.1 200 OK\n\nsome content", response.asString());
    }
}