package de.rabea.exceptions;

public class FileReaderException extends RuntimeException {

    public FileReaderException(String message) {
        super("Cannot read file content " + message);
    }
}
