package de.rabea;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MultiLogger implements Logger {
    private final List<String> requests;
    private List<Logger> loggers = new ArrayList<>();

    public MultiLogger() {
        requests = new ArrayList<>();
    }

    public void log(String message) {
        for (Logger logger : loggers) {
            logger.log(message);
        }
        requests.add(message);
    }

    public byte[] getLogs() {
        String logs = logsAsString();
        return logs.getBytes();
    }

    public String logsAsString() {
        return requests.stream().collect(Collectors.joining(", "));
    }

    public void add(Logger logger) {
        loggers.add(logger);
    }
}
