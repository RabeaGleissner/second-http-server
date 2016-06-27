package de.rabea;

import de.rabea.controller.NotFoundController;
import de.rabea.request.HttpRequest;

import java.util.HashMap;
import java.util.Map;

public class Router {
    private final Map<String, Controller> controllersForRoutes = new HashMap<>();
    private final NotFoundController notFoundController;

    public Router() {
        this.notFoundController =  new NotFoundController();
    }

    public Controller getController(HttpRequest request) {
        return controllersForRoutes.getOrDefault(request.requestLine().uri(), notFoundController());
    }

    private Controller notFoundController() {
        return notFoundController;
    }

    public void configure(String route, Controller controller) {
        controllersForRoutes.put(route, controller);
    }
}