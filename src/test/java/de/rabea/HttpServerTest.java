package de.rabea;

import de.rabea.exceptions.SocketException;
import de.rabea.logger.MultiLogger;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

import static org.junit.Assert.assertTrue;

public class HttpServerTest {
    private ExecutorServiceSpy executorServiceSpy;

    @Before
    public void setup() throws IOException {
        executorServiceSpy = new ExecutorServiceSpy();
    }

    @Test
    public void executorServiceShutsDown() throws IOException {
        HttpServer server = new HttpServer(executorServiceSpy, new ServerSocketStub(), new Router(), new MultiLogger());
        server.shutdown();
        assertTrue(executorServiceSpy.hasShutDown);
    }

    @Test(expected = SocketException.class)
    public void throwsExceptionWhenInputStreamCannotBeCreated() throws IOException {
        HttpServer server = new HttpServer(executorServiceSpy, new ServerSocketStub().createsSocketWithException(), new Router(), new MultiLogger());
        server.start("dir", 1234);
    }

    private class ExecutorServiceSpy implements ExecutorService {
       public boolean hasShutDown = false;

       @Override
       public void shutdown() {
           hasShutDown = true;
       }

       @Override
       public List<Runnable> shutdownNow() {
           return null;
       }

       @Override
       public boolean isShutdown() {
           return false;
       }

       @Override
       public boolean isTerminated() {
           return false;
       }

       @Override
       public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
           return false;
       }

       @Override
       public <T> Future<T> submit(Callable<T> task) {
           return null;
       }

       @Override
       public <T> Future<T> submit(Runnable task, T result) {
           return null;
       }

       @Override
       public Future<?> submit(Runnable task) {
           return null;
       }

       @Override
       public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
           return null;
       }

       @Override
       public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException {
           return null;
       }

       @Override
       public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
           return null;
       }

       @Override
       public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
           return null;
       }

       @Override
       public void execute(Runnable command) {

       }
   }
}