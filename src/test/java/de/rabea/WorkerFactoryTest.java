package de.rabea;

import de.rabea.communication.SocketReader;
import de.rabea.communication.SocketWriter;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class WorkerFactoryTest {

    @Test
    public void createsNewServerWorker() {
        ServerWorkerFactory factory = new ServerWorkerFactory(new SocketReader(new BufferedReaderStub()),
                new SocketWriter(new SocketStub("")));
        assertTrue(factory.create() instanceof ServerWorker);
    }
}