package de.rabea.response.creator;

import de.rabea.ContentStorage;
import de.rabea.response.HttpResponse;
import org.junit.Test;

import static de.rabea.response.head.StatusLine.OK;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class PostResponseCreatorTest {

    @Test
    public void createsResponse() {
        PostResponseCreator creator = new PostResponseCreator(OK, new ContentStorage());
        HttpResponse response = creator.create("body content".getBytes());
        assertEquals("HTTP/1.1 200 OK\n\n", response.asString());
    }

    @Test
    public void savesBodyInContentStorage() {
        ContentStorage storage = new ContentStorage();
        PostResponseCreator creator = new PostResponseCreator(OK, storage);
        creator.create("body content".getBytes());
        assertArrayEquals("body content".getBytes(), storage.content());
    }
}