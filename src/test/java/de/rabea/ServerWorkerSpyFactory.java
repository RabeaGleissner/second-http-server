package de.rabea;

public class ServerWorkerSpyFactory implements WorkerFactory {

    private ServerWorkerSpy workerSpy;

    public ServerWorkerSpyFactory(ServerWorkerSpy workerSpy) {
        this.workerSpy = workerSpy;
    }

    @Override
    public ServerWorker create() {
        return workerSpy;
    }
}
