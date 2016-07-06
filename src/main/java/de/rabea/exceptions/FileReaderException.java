package de.rabea.exceptions;

public class FileReaderException extends RuntimeException {

    public FileReaderException() {
        super("Cannot read file content");
    }
}
