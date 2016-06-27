package de.rabea;

import de.rabea.controller.NotFoundController;
import de.rabea.controller.RootController;
import de.rabea.request.HttpRequest;
import org.junit.Test;

import static de.rabea.request.HttpVerb.GET;
import static org.junit.Assert.assertTrue;

public class RouterTest {

    @Test
    public void returnsControllerForGivenRoute() {
        Router router = new Router();
        router.configure("/", new RootController());
        Controller controller = router.getController(new HttpRequest(GET, "/"));
        assertTrue(controller instanceof RootController);
    }

    @Test
    public void returnsControllerForNonExistingRoute() {
        Router router = new Router();
        Controller controller = router.getController(new HttpRequest(GET, "/some-route"));
        assertTrue(controller instanceof NotFoundController);
    }
}