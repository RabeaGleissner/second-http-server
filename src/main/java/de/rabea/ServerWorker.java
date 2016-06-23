package de.rabea;

import de.rabea.communication.SocketReader;
import de.rabea.communication.SocketWriter;

public class ServerWorker {

    private final SocketReader socketReader;
    private SocketWriter socketWriter;

    public ServerWorker(SocketReader socketReader, SocketWriter socketWriter, String directory) {
        this.socketReader = socketReader;
        this.socketWriter = socketWriter;
    }

    public void start() {
        String request = socketReader.read();
        System.out.println(request);
        socketWriter.write("HTTP/1.1 200 OK\n");
    }
}
