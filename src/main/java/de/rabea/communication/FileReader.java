package de.rabea.communication;

import de.rabea.request.HttpRequest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReader {

    public static byte[] read(HttpRequest httpRequest, String directory) {
        String fileName = httpRequest.requestLine().uri();
        String filePath = directory + fileName;
        try {
            return fileContent(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    private static byte[] fileContent(String filePath) throws IOException {
        return Files.readAllBytes(Paths.get(filePath));
    }
}
