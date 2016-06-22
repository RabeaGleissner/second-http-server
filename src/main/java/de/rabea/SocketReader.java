package de.rabea;

import java.io.*;
import java.net.Socket;

public class SocketReader {

    private Socket socket;

    public SocketReader(Socket socket) {
        this.socket = socket;
    }

    public String read(){
        try {
            return createReader().readLine();
        } catch (IOException e) {
            throw new SocketException("Could not read " + e.getMessage());
        }
    }

    private BufferedReader createReader() throws IOException {
        return new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

}
