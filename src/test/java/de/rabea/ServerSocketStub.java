package de.rabea;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketStub extends ServerSocket {
    private static boolean throwException;
    private static boolean createsSocketWithException;

    public ServerSocketStub() throws IOException {
        throwException = false;
        createsSocketWithException = false;
    }

    private ServerSocketStub(boolean throwException, boolean createsSocketWithException) throws IOException {
        super();
        ServerSocketStub.throwException = throwException;
        ServerSocketStub.createsSocketWithException = createsSocketWithException;
    }

    public static ServerSocket createsSocketWithException() throws IOException {
        createsSocketWithException = true;
        return new ServerSocketStub(throwException, createsSocketWithException);
    }

    @Override
    public Socket accept() throws IOException {
        if (throwException) {
            throw new IOException();
        }
        if (createsSocketWithException) {
            return new SocketStubWithException();
        }
        return new SocketStub();
    }

    private class SocketStubWithException extends Socket {

        @Override
        public InputStream getInputStream() throws IOException {
            throw new IOException();
        }
    }
}
