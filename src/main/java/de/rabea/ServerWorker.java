package de.rabea;

public class ServerWorker {

    private final SocketReader socketReader;
    private final String directory;

    public ServerWorker(SocketReader socketReader, String directory) {
        this.socketReader = socketReader;
        this.directory = directory;
    }

    public void start() {
        String request = socketReader.read();
        System.out.println(request);
    }
}
