package de.rabea;

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
        router.configure("/form", new FormController());
        HttpServer httpServer = new HttpServer(Executors.newFixedThreadPool(20), serverSocket, router);
        httpServer.start();
    }
}
