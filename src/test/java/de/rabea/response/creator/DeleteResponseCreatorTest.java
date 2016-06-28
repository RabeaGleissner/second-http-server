package de.rabea.response.creator;

import de.rabea.ContentStorage;
import de.rabea.controller.RootController;
import de.rabea.response.HttpResponse;
import org.junit.Test;

import static de.rabea.response.head.StatusLine.NOT_FOUND;
import static org.junit.Assert.assertEquals;

public class DeleteResponseCreatorTest {

    private final String REQUEST_BODY = "My=Data\n";

    @Test
    public void createsNewHttpResponse() {
        DeleteResponseCreator creator = new DeleteResponseCreator(NOT_FOUND, new ContentStorage(), new RootController());
        HttpResponse response = creator.create(REQUEST_BODY);
        assertEquals("HTTP/1.1 404 Not Found\n", response.asString());
    }

    @Test
    public void deletesExistingContentInStorage() {
        RootController controller = new RootController();
        ContentStorage contentStorage = new ContentStorage();
        contentStorage.store(controller, "some content");
        DeleteResponseCreator creator = new DeleteResponseCreator(NOT_FOUND, contentStorage, controller);

        HttpResponse response = creator.create(REQUEST_BODY);

        assertEquals("HTTP/1.1 404 Not Found\n", response.asString());
    }
}