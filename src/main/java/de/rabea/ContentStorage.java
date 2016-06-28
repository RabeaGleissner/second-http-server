package de.rabea;

import java.util.HashMap;
import java.util.Map;

public class ContentStorage {
    private Map<Controller, String> storage = new HashMap<>();

    public void store(Controller controller, String body) {
        storage.put(controller, body);
    }

    public boolean hasContentFor(Controller controller) {
        return storage.containsKey(controller);
    }

    public String contentFor(Controller controller) {
        return storage.get(controller);
    }

    public void deleteContentFor(Controller controller) {
       storage.remove(controller);
    }
}
