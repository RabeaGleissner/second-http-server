package de.rabea;

public class ContentStorage {
    private byte[] storage = new byte[0];

    public void storeResponseBody(String body) {
        storage = body.getBytes();
    }

    public void storeFileContent(byte[] body) {
        storage = body;
    }

    public byte[] content() {
        return storage;
    }

    public void deleteContent() {
       storage = new byte[0];
    }
}
