package de.rabea.communication;

import de.rabea.exceptions.FileReaderException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReader {

    public static byte[] read(String filePath) {
        try {
            return fileContent(filePath);
        } catch (IOException e) {
            throw new FileReaderException(e.getMessage());
        }
    }

    private static byte[] fileContent(String filePath) throws IOException {
        return Files.readAllBytes(Paths.get(filePath));
    }
}
