package de.rabea.response.creator;

import de.rabea.ContentStorage;
import de.rabea.controller.RootController;
import de.rabea.response.HttpResponse;
import de.rabea.response.head.StatusLine;
import org.junit.Test;

import static de.rabea.response.head.StatusLine.*;
import static org.junit.Assert.*;

public class PostResponseCreatorTest {

    @Test
    public void createsResponse() {
        PostResponseCreator creator = new PostResponseCreator(OK, new ContentStorage(), new RootController());
        HttpResponse response = creator.create("body content");
        assertEquals("HTTP/1.1 200 OK\n", response.asString());
    }

    @Test
    public void savesBodyInContentStorage() {
        ContentStorage storage = new ContentStorage();
        RootController controller = new RootController();
        PostResponseCreator creator = new PostResponseCreator(OK, storage, controller);
        creator.create("body content");
        assertEquals("body content", storage.contentFor(controller));
    }
}