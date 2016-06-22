package de.rabea;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        Arguments arguments = new Arguments();
        HashMap<String, String> parsedArguments = arguments.parse(args);
        String port = parsedArguments.get("port");
        String directory = parsedArguments.get("directory");

        System.out.println("Server started at port " + port + " and directory " + directory);
        ServerSocket serverSocket = new ServerSocket(Integer.parseInt(port));
        HttpServer httpServer = new HttpServer(new ThreadPoolExecutorServiceFactory(), serverSocket, directory);
        httpServer.start();
    }
}
