package de.rabea;

import de.rabea.communication.SocketReader;
import de.rabea.communication.SocketWriter;
import de.rabea.controller.MethodOptions2Controller;
import de.rabea.logger.ConsoleLogger;
import de.rabea.logger.FileLogger;
import de.rabea.logger.Logger;
import de.rabea.logger.MultiLogger;
import de.rabea.request.HttpRequest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ServerWorkerTest {

    private String pathToEmptyFile;
    private File file;
    private List<Logger> loggers;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void setup() throws IOException {
        file = folder.newFile("log.txt");
        pathToEmptyFile = file.getAbsolutePath();
        loggers = new ArrayList<>(Arrays.asList(new FileLogger(pathToEmptyFile), new ConsoleLogger(new ServerConsole())));
    }

    @Test
    public void startsServerWorker() {
        SocketReaderStub socketReaderStub = new SocketReaderStub();
        SocketWriterSpy socketWriterSpy = new SocketWriterSpy(new SocketStub());
        Router router = new Router();
        router.configure("/method_options2", new MethodOptions2Controller());
        ServerWorker worker = new ServerWorker(socketReaderStub, socketWriterSpy, router, new MultiLogger(loggers));
        worker.run();
        assertEquals("HTTP/1.1 200 OK\n\n", socketWriterSpy.responseWritten());
    }

    private class SocketReaderStub extends SocketReader {

        private SocketReaderStub() {
            super(null);
        }

        @Override
        public HttpRequest read() {
            return new HttpRequestDummy("GET /method_options2 HTTP/1.1");
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