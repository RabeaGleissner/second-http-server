package de.rabea;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExecutorServiceFactory implements ExecutorServiceFactory {

    @Override
    public ExecutorService create(int pool_size) {
        return Executors.newFixedThreadPool(pool_size);
    }
}
