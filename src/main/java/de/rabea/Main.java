package de.rabea;

import de.rabea.controller.FormController;
import de.rabea.controller.MethodOptions2Controller;
import de.rabea.controller.MethodOptionsController;
import de.rabea.controller.RootController;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws IOException {
        Arguments arguments = new Arguments(args);
        int port = arguments.getPort();
        String directory = arguments.getDirectory();

        System.out.println("Server started at port " + port + " and directory " + directory);
        ServerSocket serverSocket = new ServerSocket(port);
        Router router = new Router();
        router.configure("/", new RootController());
        router.configure("/form", new FormController(new ContentStorage()));
        router.configure("/method_options", new MethodOptionsController(new ContentStorage()));
        router.configure("/method_options2", new MethodOptions2Controller());
        HttpServer httpServer = new HttpServer(Executors.newFixedThreadPool(20), serverSocket, router);
        httpServer.start();
    }
}
