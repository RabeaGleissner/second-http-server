package de.rabea;

import de.rabea.communication.SocketReader;
import de.rabea.communication.SocketWriter;
import de.rabea.exceptions.ServerSocketException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

public class HttpServer {
    private final ServerSocket serverSocket;
    private final ExecutorService executorService;
    private final int POOL_SIZE = 20;

    public HttpServer(ExecutorServiceFactory executorServiceFactory, ServerSocket serverSocket) {
        this.executorService = executorServiceFactory.create(POOL_SIZE);
        this.serverSocket = serverSocket;
    }

    public void start(Router router) {
        while (true) {
            execute(router);
        }
    }

    public void execute(Router router) {
        try {
            executeServerRunnerInThread(router);
        } catch (IOException e) {
            shutdown();
            throw new ServerSocketException("Cannot create Socket" + e.getMessage());
        }
    }

    public void shutdown() {
        executorService.shutdown();
    }

    private void executeServerRunnerInThread(Router router) throws IOException {
        Socket socket = serverSocket.accept();
        executorService.execute(new HttpServerRunner(
                new ServerWorkerFactory(
                        new SocketReader(new BufferedReader(new InputStreamReader(socket.getInputStream()))),
                        new SocketWriter(socket),
                        router
                )));
    }
}
