package de.rabea.logger;

import de.rabea.Console;

public class ConsoleLogger implements Logger {

    private Console console;

    public ConsoleLogger(Console console) {
        this.console = console;
    }

    @Override
    public void log(String message) {
        console.write(message);
    }

    @Override
    public String getLogs() {
        return "";
    }
}