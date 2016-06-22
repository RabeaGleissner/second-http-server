package de.rabea;

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
