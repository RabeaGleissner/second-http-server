package de.rabea;

import de.rabea.communication.SocketReader;
import de.rabea.communication.SocketWriter;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class HttpServerRunnerTest {
    @Test
    public void createsAndStartsServerWorker() {
        ServerWorkerSpy workerSpy = new ServerWorkerSpy(new SocketReader(new SocketStub()), new SocketWriter(new SocketStub()), "");
        ServerWorkerSpyFactory spyFactory = new ServerWorkerSpyFactory(workerSpy);
        HttpServerRunner runner = new HttpServerRunner(spyFactory);
        runner.run();
        assertTrue(workerSpy.wasStarted);
    }
}