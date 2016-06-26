package de.rabea;

import de.rabea.communication.SocketReader;
import de.rabea.communication.SocketWriter;

public class ServerWorkerFactory implements WorkerFactory {

    private final SocketReader socketReader;
    private final SocketWriter socketWriter;
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
