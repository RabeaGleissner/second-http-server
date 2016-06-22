package de.rabea;

import org.junit.Test;

import static org.junit.Assert.*;

public class HttpServerRunnerTest {
    @Test
    public void createsAndStartsServerWorker() {
        ServerWorkerSpy workerSpy = new ServerWorkerSpy(new SocketReader(new SocketStub()), "");
        ServerWorkerSpyFactory spyFactory = new ServerWorkerSpyFactory(workerSpy);
        HttpServerRunner runner = new HttpServerRunner(spyFactory);
        runner.run();
        assertTrue(workerSpy.wasStarted);
    }
}