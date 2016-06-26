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
import java.util.concurrent.Executors;

public class HttpServer  implements Runnable {
    private final ServerSocket serverSocket;
    private Router router;
    private final ExecutorService executorService;

    public HttpServer(ExecutorService executorService, ServerSocket serverSocket, Router router) {
        this.serverSocket = serverSocket;
        this.router = router;
        this.executorService = executorService;
    }

    public void start() {
        while (true) {
            run();
        }
    }

    public void shutdown() {
        executorService.shutdown();
    }

    @Override
    public void run() {
        Socket socket;
        try {
            socket = createSocket();
        } catch (IOException e) {
            throw new ServerSocketException("cannot create socket " + e.getMessage());
        }
        try {
            startServerWorker(socket);
        } catch (IOException e) {
           throw new SocketException("cannot get input stream " + e.getMessage());
        }
    }

    private Socket createSocket() throws IOException {
        return serverSocket.accept();
    }

    private void startServerWorker(Socket socket) throws IOException {
        new ServerWorker(
                new SocketReader(new BufferedReader(new InputStreamReader(socket.getInputStream()))),
                new SocketWriter(socket),
                router).start();
    }
}
