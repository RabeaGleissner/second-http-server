package de.rabea;

import de.rabea.communication.SocketReader;
import de.rabea.communication.SocketWriter;

public class ServerWorkerSpy extends ServerWorker {

    public boolean wasStarted = false;

    public ServerWorkerSpy(SocketReader socketReader, SocketWriter socketWriter, String directory) {
        super(socketReader, socketWriter, directory);
    }

    @Override
    public void start() {
        wasStarted = true;
    }
}