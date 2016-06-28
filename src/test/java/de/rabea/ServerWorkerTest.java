package de.rabea;

import de.rabea.communication.SocketReader;
import de.rabea.communication.SocketWriter;
import de.rabea.controller.RootController;
import de.rabea.request.HttpRequest;
import org.junit.Ignore;
import org.junit.Test;

import java.net.Socket;

import static org.junit.Assert.assertEquals;

public class ServerWorkerTest {

    @Test
    @Ignore
    public void startsServerWorker() {
        SocketReaderStub socketReaderStub = new SocketReaderStub();
        SocketWriterSpy socketWriterSpy = new SocketWriterSpy(new SocketStub());
        Router router = new Router("DIR");
        router.configure("/", new RootController());
        ServerWorker worker = new ServerWorker(socketReaderStub, socketWriterSpy, router);
        worker.start();
        assertEquals("HTTP/1.1 200 OK\n\n", socketWriterSpy.responseWritten());
    }

    private class SocketReaderStub extends SocketReader {

        private SocketReaderStub() {
            super(null);
        }

        @Override
        public HttpRequest read() {
            return new HttpRequestDummy("GET / HTTP/1.1");
        }
    }

    private class SocketWriterSpy extends SocketWriter {
        private String written;

        private SocketWriterSpy(Socket socket) {
            super(socket);
        }

        @Override
        public void write(byte[] bytes) {
            written = new String(bytes);

        }

        public String responseWritten() {
            return written;
        }
    }
}