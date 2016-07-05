package de.rabea;

import de.rabea.communication.SocketReader;
import de.rabea.communication.SocketWriter;
import de.rabea.exceptions.ServerSocketException;
import de.rabea.exceptions.SocketException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

public class HttpServer {
    private final ServerSocket serverSocket;
    private final Router router;
    private final ExecutorService executorService;
    private final Logger logger;

    public HttpServer(ExecutorService executorService, ServerSocket serverSocket, Router router, Logger logger) {
        this.serverSocket = serverSocket;
        this.router = router;
        this.executorService = executorService;
        this.logger = logger;
    }

    public void start(String directory, int port) {
        System.out.println("Server started at port " + port + " and directory " + directory);
        try {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                startServerWorker(clientSocket);
            }
        } catch (IOException e) {
            shutdown();
            throw new SocketException("Apologies, the socket could not create the input stream " + e.getMessage());
        }
    }

    public void shutdown() {
        executorService.shutdown();
    }

    private void startServerWorker(Socket clientSocket) throws IOException {
        executorService.execute(new ServerWorker(
                new SocketReader(new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))),
                new SocketWriter(clientSocket),
                router,
                logger));
    }
}
