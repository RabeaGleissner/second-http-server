package de.rabea.communication;

import de.rabea.SocketStub;
import de.rabea.exceptions.SocketException;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
        SocketWriter network = new SocketWriter(socket);
        network.write("hey");
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
}