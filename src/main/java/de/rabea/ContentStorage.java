package de.rabea;

public class ContentStorage {
    private byte[] storage = new byte[0];

    public void store(byte[] body) {
        storage = body;
    }

    public boolean hasContentFor() {
        return !storage.equals("");
    }

    public byte[] content() {
        return storage;
    }

    public void deleteContentFor() {
       storage = new byte[0];
    }
}
