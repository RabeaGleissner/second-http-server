package de.rabea.communication;

import de.rabea.SocketStub;
import de.rabea.exceptions.BufferedReaderException;
import de.rabea.exceptions.SocketException;
import de.rabea.request.HttpRequest;
import de.rabea.request.HttpVerb;
import org.junit.Test;

import java.io.*;
import java.net.Socket;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class SocketReaderTest {

    @Test
    public void returnsHttpRequest() {
        SocketReader socketReader = new SocketReader(
                createReader(new SocketStub("POST /form HTTP/1.1\n" +
                "Content-Length: 11\n" +
                "Host: localhost:5000\n" +
                "\n" +
                "My=Data\n")));
        HttpRequest httpRequest = socketReader.read();
        Map<String, String> requestHeaders = httpRequest.requestHeaders();

        assertEquals(HttpVerb.POST, httpRequest.requestLine().method());
        assertEquals("11", requestHeaders.get("Content-Length"));
        assertEquals("localhost:5000", requestHeaders.get("Host"));
        assertEquals("My=Data\n", httpRequest.body());
    }

    @Test(expected = SocketException.class)
    public void throwsSocketExceptionWhenItCannotGetInputStream() {
        SocketWithInputStreamException socket = new SocketWithInputStreamException();
        SocketReader reader = new SocketReader(createReader(socket));
        reader.read();
    }

    @Test(expected = BufferedReaderException.class)
    public void throwsBufferedReaderExceptionIfItCannotRead() {
        SocketReader socketReader = new SocketReader(createReaderWithException(new SocketStub("")));
        socketReader.read();
    }

    private BufferedReader createReader(Socket socket) {
        try {
            return new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            throw new SocketException("Could not get InputStream" + e.getMessage());
        }
    }

    private BufferedReader createReaderWithException(Socket socket) {
        try {
            return new ReaderWithException(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            throw new BufferedReaderException("Could not create Reader");
        }
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

    private class ReaderWithException extends BufferedReader {
        public ReaderWithException(Reader in) {
            super(in);
        }

        @Override
        public String readLine() throws IOException {
            throw new IOException();
        }
    }
}