package de.rabea.communication;

import de.rabea.SocketStub;
import de.rabea.exceptions.SocketException;
import org.junit.Test;

import java.io.*;
import java.net.Socket;

import static org.junit.Assert.assertEquals;

public class SocketWriterTest {

    @Test
    public void sendsHeaderAndBodyToClient() {
        SocketStub socketStub = new SocketStub();
        SocketWriter socketWriter = new SocketWriter(socketStub);
        String message = "hello!";
        socketWriter.write(message);
        assertEquals("hello!", socketStub.messageSent());
    }

    @Test(expected = SocketException.class)
    public void throwsSocketExceptionWhenItCannotGetOutputStream() {
        SocketWithOutputStreamException socket = new SocketWithOutputStreamException();
        SocketWriter writer = new SocketWriter(socket);
        writer.write("hey");
    }

    @Test(expected = SocketException.class)
    public void throwsSocketExceptionWhenItCannotClose() {
        SocketWithCloseException socket = new SocketWithCloseException();
        SocketWriter writer = new SocketWriter(socket);
        writer.write("");
    }

    public class SocketWithOutputStreamException extends Socket {
        @Override
        public void close() throws IOException {
        }

        @Override
        public InputStream getInputStream() throws IOException {
            return new ByteArrayInputStream("".getBytes()) {
            };
        }

        @Override
        public OutputStream getOutputStream() throws IOException {
            throw new IOException();
        }
    }

    public class SocketWithCloseException extends Socket {
        @Override
        public void close() throws IOException {
            throw new IOException();
        }

        @Override
        public InputStream getInputStream() throws IOException {
            return new ByteArrayInputStream("".getBytes()) {
            };
        }

        @Override
        public OutputStream getOutputStream() throws IOException {
            return new ByteArrayOutputStream();
        }

    }
}