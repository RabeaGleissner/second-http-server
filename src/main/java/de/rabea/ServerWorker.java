package de.rabea;

import de.rabea.communication.SocketReader;
import de.rabea.communication.SocketWriter;
import de.rabea.request.HttpRequest;

public class ServerWorker {

    private final SocketReader socketReader;
    private final SocketWriter socketWriter;

    public ServerWorker(SocketReader socketReader, SocketWriter socketWriter, String directory) {
        this.socketReader = socketReader;
        this.socketWriter = socketWriter;
    }

    public void start() {
        HttpRequest request = socketReader.read();
        socketWriter.write("HTTP/1.1 200 OK\n");
    }
}
