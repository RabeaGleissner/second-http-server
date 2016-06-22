package de.rabea;

import java.util.concurrent.ExecutorService;

public interface ExecutorServiceFactory {
    ExecutorService create(int pool_size);
}
