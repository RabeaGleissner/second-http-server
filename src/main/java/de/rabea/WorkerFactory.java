package de.rabea;

public class WorkerFactory {

    private final SocketReader socketReader;
    private final String directory;

    public WorkerFactory(SocketReader socketReader, String directory) {

        this.socketReader = socketReader;
        this.directory = directory;
    }

    public ServerWorker create() {
        return new ServerWorker(socketReader, directory);
    }
}
