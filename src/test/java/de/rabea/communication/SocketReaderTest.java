package de.rabea.communication;

import de.rabea.SocketStub;
import de.rabea.exceptions.SocketException;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import static org.junit.Assert.assertEquals;

public class SocketReaderTest {

    @Test
    public void readsOneLineOfInputForSimpleGETRequest() {
        String request = "GET/form HTTP/1.1\n";
        SocketStub socketStub = new SocketStub(request);
        SocketReader reader = new SocketReader(socketStub);
        assertEquals("GET/form HTTP/1.1", reader.read());
    }

    @Test(expected = SocketException.class)
    public void throwsSocketExceptionWhenItCannotGetInputStream() {
        SocketWithInputStreamException socket = new SocketWithInputStreamException();
        SocketReader reader = new SocketReader(socket);
        reader.read();
    }

    public class SocketWithInputStreamException extends Socket {
        @Override
        public void close() throws IOException {
        }

        @Override
        public InputStream getInputStream() throws IOException {
            throw new IOException();
        }

        @Override
        public OutputStream getOutputStream() throws IOException {
            return new ByteArrayOutputStream();
        }
    }
}