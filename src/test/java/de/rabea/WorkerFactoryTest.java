package de.rabea;

import org.junit.Test;

import static org.junit.Assert.*;

public class WorkerFactoryTest {

    @Test
    public void createsNewServerWorker() {
        ServerWorkerFactory factory = new ServerWorkerFactory(new SocketReader(new SocketStub("")), "DIR");
        assertTrue(factory.create() instanceof ServerWorker);
    }
}