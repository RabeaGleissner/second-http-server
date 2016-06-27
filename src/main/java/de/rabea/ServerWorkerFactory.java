package de.rabea;

import de.rabea.communication.SocketReader;
import de.rabea.communication.SocketWriter;

public class ServerWorkerFactory implements WorkerFactory {

    private final SocketReader socketReader;
    private final SocketWriter socketWriter;
    private Router router;

    public ServerWorkerFactory(SocketReader socketReader, SocketWriter socketWriter, Router router) {
        this.socketReader = socketReader;
        this.socketWriter = socketWriter;
        this.router = router;
    }

    @Override
    public ServerWorker create() {
        return new ServerWorker(socketReader, socketWriter, router);
    }
}
