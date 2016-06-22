package de.rabea;

import java.util.concurrent.ExecutorService;

public class ExecutorServiceSpyFactory implements ExecutorServiceFactory {

    private ExecutorServiceSpy executorServiceSpy;

    public ExecutorServiceSpyFactory(ExecutorServiceSpy executorServiceSpy) {
        this.executorServiceSpy = executorServiceSpy;
    }

    @Override
    public ExecutorService create(int pool_size) {
        return executorServiceSpy;
    }
}
