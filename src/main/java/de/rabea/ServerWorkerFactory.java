package de.rabea;

public class ServerWorkerFactory implements WorkerFactory {

    private final SocketReader socketReader;
    private SocketWriter socketWriter;
    private final String directory;

    public ServerWorkerFactory(SocketReader socketReader, SocketWriter socketWriter, String directory) {
        this.socketReader = socketReader;
        this.socketWriter = socketWriter;
        this.directory = directory;
    }

    @Override
    public ServerWorker create() {
        return new ServerWorker(socketReader, socketWriter, directory);
    }
}
