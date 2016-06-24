package de.rabea;

import de.rabea.controller.RootController;
import de.rabea.request.HttpRequest;

public class Router {

    public Controller getController(HttpRequest request) {
        return new RootController(request);
    }
}