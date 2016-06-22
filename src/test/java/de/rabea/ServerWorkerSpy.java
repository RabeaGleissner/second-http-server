package de.rabea;

public class ServerWorkerSpy extends ServerWorker {

    public boolean wasStarted = false;

    public ServerWorkerSpy(SocketReader socketReader, String directory) {
        super(socketReader, directory);
    }

    @Override
    public void start() {
        wasStarted = true;
    }
}
