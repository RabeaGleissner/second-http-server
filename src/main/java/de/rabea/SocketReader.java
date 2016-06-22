package de.rabea;

import java.io.*;
import java.net.Socket;

public class SocketReader {

    private Socket socket;

    public SocketReader(Socket socket) {
        this.socket = socket;
    }

    public BufferedReader createReader() {
        try {
            return new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            throw new SocketException("Could not get InputStream " + e.getMessage());
        }
    }

    public String read(){
        System.out.println("hello");
        try {
            return createReader().readLine();
        } catch (IOException e) {
            throw new SocketException("Could not read " + e.getMessage());
        }
    }
}
