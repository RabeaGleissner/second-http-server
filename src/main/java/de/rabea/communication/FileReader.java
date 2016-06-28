package de.rabea.communication;

import de.rabea.request.HttpRequest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReader {

    public static String read(HttpRequest httpRequest, String directory) {
        String fileName = httpRequest.requestLine().uri();
        String filePath = directory + fileName;
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
