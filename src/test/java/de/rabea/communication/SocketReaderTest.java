package de.rabea.communication;

import de.rabea.SocketStub;
import de.rabea.exceptions.SocketException;
import de.rabea.request.HttpRequest;
import de.rabea.request.HttpVerb;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class SocketReaderTest {

    @Test(expected = SocketException.class)
    public void throwsSocketExceptionWhenItCannotGetInputStream() {
        SocketWithInputStreamException socket = new SocketWithInputStreamException();
        SocketReader reader = new SocketReader(socket);
        reader.read();
    }

    @Test
    public void returnsHttpRequest() {
        SocketReader socketReader = new SocketReader(new SocketStub("POST /form HTTP/1.1\n" +
                "Content-Length: 11\n" +
                "Host: localhost:5000\n" +
                "\n" +
                "My=Data\n"));
        HttpRequest httpRequest = socketReader.read();
        Map<String, String> requestHeaders = httpRequest.requestHeaders();

        assertEquals(HttpVerb.POST, httpRequest.requestLine().method());
        assertEquals("11", requestHeaders.get("Content-Length"));
        assertEquals("localhost:5000", requestHeaders.get("Host"));
        assertEquals("My=Data\n", httpRequest.body());
    }

    private class SocketWithInputStreamException extends Socket {
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