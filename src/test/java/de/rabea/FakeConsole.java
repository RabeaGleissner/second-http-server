package de.rabea;

public class FakeConsole implements Console {
    private String wordsWritten = "";

    @Override
    public void write(String message) {
       wordsWritten += message;
    }

    public String wordsWritten() {
       return wordsWritten;
    }
}
