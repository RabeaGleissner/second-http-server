package de.rabea;

import de.rabea.controller.NotFoundController;
import de.rabea.request.HttpRequest;

import java.util.HashMap;
import java.util.Map;

public class Router {
    private Map<String, Controller> configuredRoutes = new HashMap<>();

    public Controller getController(HttpRequest request) {
        if (configuredRoutes.containsKey(request.requestLine().uri())) {
            return configuredRoutes.get(request.requestLine().uri());
        }
        return new NotFoundController();
    }

    public void configure(String route, Controller controller) {
        configuredRoutes.put(route, controller);
    }
}