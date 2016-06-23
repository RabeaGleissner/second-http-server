package de.rabea;

import de.rabea.exceptions.ServerSocketException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class HttpServerTest {
    private ExecutorServiceSpy executorServiceSpy;
    private HttpServer server;

    @Before
    public void setup() throws IOException {
        executorServiceSpy = new ExecutorServiceSpy();
        server = new HttpServer(new ExecutorServiceSpyFactory(executorServiceSpy), new ServerSocketStub(), "PUBLIC_DIR");
    }

    @Test
    public void executesHttpServerRunner() {
        server.execute();
        assertTrue(executorServiceSpy.executesHttpServerRunner);
    }

    @Test
    public void executorServiceShutsDown() {
        server.shutdown();
        assertTrue(executorServiceSpy.hasShutDown);
    }

    @Test(expected = ServerSocketException.class)
    public void throwsExceptionWhenServerSocketCannotCreateSocket() throws IOException {
        HttpServer server = new HttpServer(new ExecutorServiceSpyFactory(executorServiceSpy),
                ServerSocketStub.throwsException(), "PUBLIC_DIR");
        server.execute();
    }
}