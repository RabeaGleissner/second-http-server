package de.rabea.communication;

import de.rabea.exceptions.SocketException;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class SocketWriter {

    private Socket socket;

    public SocketWriter(Socket socket) {
        this.socket = socket;
    }

    public void write(String responseHead) {
        try {
            System.out.println("writes");
            createWriter().write(responseHead.getBytes());
        } catch (IOException e) {
            throw new SocketException("Could not write" + e.getMessage());
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private OutputStream createWriter() throws IOException {
        System.out.println("creates writer");
        return new DataOutputStream(socket.getOutputStream());
    }
}