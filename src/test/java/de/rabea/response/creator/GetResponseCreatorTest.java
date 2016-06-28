package de.rabea.response.creator;

import de.rabea.ContentStorage;
import de.rabea.response.HttpResponse;
import org.junit.Test;

import static de.rabea.response.head.StatusLine.OK;
import static org.junit.Assert.assertEquals;

public class GetResponseCreatorTest {

    private final String REQUEST_BODY = "My=Data\n";

    @Test
    public void createsSimpleResponse() {
        GetResponseCreator creator = new GetResponseCreator(OK);
        HttpResponse response = creator.create(REQUEST_BODY.getBytes());
        assertEquals("HTTP/1.1 200 OK\n\n", response.asString());
    }

    @Test
    public void createsResponseWithBody() {
        GetResponseCreator creator = new GetResponseCreator(OK, new ContentStorage());
        HttpResponse response = creator.create(REQUEST_BODY.getBytes());
        assertEquals("HTTP/1.1 200 OK\n\nMy=Data\n", response.asString());
    }
}