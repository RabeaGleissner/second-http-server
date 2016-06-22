package de.rabea;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;

public class HttpServer {

    private final ServerSocket serverSocket;
    private final ExecutorService executorService;
    private final String directory;
    private final int POOL_SIZE = 20;

    public HttpServer(ThreadPoolExecutorServiceFactory threadPoolExecutorServiceFactory, ServerSocket serverSocket,
                      String directory) {
        this.serverSocket = serverSocket;
        this.executorService = threadPoolExecutorServiceFactory.create(POOL_SIZE);
        this.directory = directory;
    }

    public void start() {
        while (true) {
            execute();
        }
    }

    public void execute() {
        try {
            executeServerRunnerInThread();
        } catch (IOException e) {
            shutdown();
            throw new ServerSocketException("Cannot create Socket" + e.getMessage());
        }
    }

    public void shutdown() {
        executorService.shutdown();
    }

    private void executeServerRunnerInThread() throws IOException {
        executorService.execute(new HttpServerRunner(
                new WorkerFactory(
                        new SocketReader(serverSocket.accept()), directory)));
    }
}
