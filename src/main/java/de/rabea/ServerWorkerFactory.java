package de.rabea;

public class ServerWorkerFactory implements WorkerFactory {

    private final SocketReader socketReader;
    private final String directory;

    public ServerWorkerFactory(SocketReader socketReader, String directory) {

        this.socketReader = socketReader;
        this.directory = directory;
    }

    @Override
    public ServerWorker create() {
        return new ServerWorker(socketReader, directory);
    }
}
