package de.rabea;

public class HttpServerRunner implements Runnable {

    private final WorkerFactory workerFactory;

    public HttpServerRunner(WorkerFactory workerFactory) {
        this.workerFactory = workerFactory;
    }

    @Override
    public void run() {
        workerFactory.create().start();
    }
}
