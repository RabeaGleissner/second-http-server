package de.rabea;

import de.rabea.controller.RootController;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {
    public static void main(String[] args) throws IOException {
        Arguments arguments = new Arguments(args);
        int port = arguments.getPort();
        String directory = arguments.getDirectory();

        System.out.println("Server started at port " + port + " and directory " + directory);
        ServerSocket serverSocket = new ServerSocket(port);
        HttpServer httpServer = new HttpServer(new ThreadPoolExecutorServiceFactory(), serverSocket);
        Router router = new Router();
        router.configure("/", new RootController());
        httpServer.start(router);
    }
}
