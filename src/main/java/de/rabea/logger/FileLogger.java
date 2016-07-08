package de.rabea.logger;

import de.rabea.exceptions.FileReaderException;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileLogger implements Logger {

    private String filePath;
    private FileWriter fileWriter;

    public FileLogger(String filePath) {
        this.filePath = filePath;
        try {
            this.fileWriter = createFileWriter();
        } catch (IOException e) {
            throw new FileWriterException(e.getMessage());
        }
    }

    @Override
    public void log(String message) {
        try {
            fileWriter.write(formatted(message));
            fileWriter.flush();
        } catch (IOException e) {
            throw new FileWriterException(e.getMessage());
        }
    }

    @Override
    public String getLogs() {
        try {
            return new String(readFileContent());
        } catch (IOException e) {
            throw new FileReaderException(e.getMessage());
        }
    }

    public byte[] readFileContent() throws IOException {
        return Files.readAllBytes(Paths.get(filePath));
    }

    public FileWriter createFileWriter() throws IOException {
        return new FileWriter(filePath);
    }

    private String formatted(String message) {
        return message + ", ";
    }

    public class FileWriterException extends RuntimeException {
        public FileWriterException(String message) {
            super("Sorry, the FileWriter could not be created " + message);
        }
    }
}
