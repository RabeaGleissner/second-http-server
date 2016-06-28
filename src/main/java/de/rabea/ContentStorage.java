package de.rabea;

public class ContentStorage {
    private String storage = "";

    public void store(String body) {
        storage = body;
    }

    public boolean hasContentFor() {
        return !storage.equals("");
    }

    public String contentFor() {
        return storage;
    }

    public void deleteContentFor() {
       storage = "";
    }
}
