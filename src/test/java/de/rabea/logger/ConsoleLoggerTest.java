package de.rabea.logger;

import de.rabea.FakeConsole;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConsoleLoggerTest {

    @Test
    public void printsLogsToConsole() {
        FakeConsole console = new FakeConsole();
        ConsoleLogger consoleLogger = new ConsoleLogger(console);
        consoleLogger.log("hello");
        assertEquals("hello", console.wordsWritten());
    }
}