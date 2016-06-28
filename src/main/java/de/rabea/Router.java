package de.rabea;

import de.rabea.controller.NotFoundController;
import de.rabea.request.HttpRequest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Router {
    private final Map<String, Controller> controllersForRoutes = new HashMap<>();
    private final NotFoundController notFoundController;

    public Router() {
        this.notFoundController =  new NotFoundController();
    }

    public Controller getController(HttpRequest request) {
        return controllersForRoutes.getOrDefault(request.requestLine().uri(), notFoundController());
    }

    private Controller notFoundController() {
        return notFoundController;
    }

    public void configure(String route, Controller controller) {
        if (isPublicDirectory(route)) {
            try {
                registerControllerForFiles(route, controller);
            } catch (IOException e) {
                throw new FileException();
            }
        }
        controllersForRoutes.put(route, controller);
    }

    private boolean isPublicDirectory(String route) {
//        return route.equals("public");
        return route.equals("vendor/cob_spec/public");
    }

    private void registerControllerForFiles(String route, Controller controller) throws IOException {
        Files.walk(Paths.get(route)).forEach(filePath -> {
            if (Files.isRegularFile(filePath)) {
                System.out.println("file path" + fileName(filePath));
                controllersForRoutes.put(fileName(filePath), controller);
            }
        });
    }

    private String fileName(Path filePath) {
        String fullFilePath = String.valueOf(filePath);
        String[] folders = fullFilePath.split("/");
        return "/" + folders[folders.length - 1];
    }

    private class FileException extends RuntimeException {
        public FileException() {
            super("Cannot read files in given directory. Please make sure the directory is correct.");
        }
    }
}