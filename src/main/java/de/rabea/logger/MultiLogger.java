package de.rabea.logger;

import java.util.ArrayList;
import java.util.List;

public class MultiLogger implements Logger {

    private List<Logger> existingLoggers = new ArrayList<>();

    public MultiLogger(List<Logger> existingLoggers) {
        this.existingLoggers = existingLoggers;
    }

    @Override
    public void log(String message) {
        for (Logger logger : existingLoggers) {
            logger.log(message);
        }
    }

    @Override
    public String getLogs() {
        String logs = "";
        for (Logger logger : existingLoggers) {
            logs += logger.getLogs();
        }
        return logs;
    }
}