package de.rabea;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileLogger implements Logger {

    private String filePath;

    public FileLogger(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void log(String message) {
        try {
            FileWriter fileWriter = createFileWriter();
            fileWriter.write(message);
            fileWriter.flush();
        } catch (IOException e) {
            throw new FileWriterException();
        }

    }

    private FileWriter createFileWriter() throws IOException {
        return new FileWriter(filePath);
    }

    public byte[] getLogs() {
        try {
            return readFileContent();
        } catch (IOException e) {
            throw new FileReaderException();
        }
    }

    private byte[] readFileContent() throws IOException {
        return Files.readAllBytes(Paths.get(filePath));
    }

    private class FileWriterException extends RuntimeException {
        public FileWriterException() {
            super("Sorry, the FileWriter could not be created");
        }
    }

    private class FileReaderException extends RuntimeException {
        public FileReaderException() {
            super("Sorry, the log file could not be read");
        }
    }
}
