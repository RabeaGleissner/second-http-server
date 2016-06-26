package de.rabea;

import de.rabea.controller.FakeHttpRequest;
import de.rabea.controller.RootController;
import org.junit.Test;

import static org.junit.Assert.*;

public class RouterTest {

    @Test
    public void getsControllerForGivenRoute() {
        Router router = new Router();
        router.configure("/", new RootController());
        Controller controller = router.getController(new FakeHttpRequest("GET / HTTP/1.1\n"));
        assertTrue(controller instanceof RootController);
    }
}