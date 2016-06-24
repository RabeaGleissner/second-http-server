package de.rabea;

import de.rabea.communication.SocketReader;
import de.rabea.communication.SocketWriter;

public class ServerWorkerFactory implements WorkerFactory {

    private final SocketReader socketReader;
    private final SocketWriter socketWriter;

    public ServerWorkerFactory(SocketReader socketReader, SocketWriter socketWriter) {
        this.socketReader = socketReader;
        this.socketWriter = socketWriter;
    }

    @Override
    public ServerWorker create() {
        return new ServerWorker(socketReader, socketWriter, new Router());
    }
}
