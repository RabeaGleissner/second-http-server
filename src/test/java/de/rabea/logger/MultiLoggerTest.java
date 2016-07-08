package de.rabea.logger;

import de.rabea.ServerConsole;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MultiLoggerTest {
    private String pathToEmptyFile;
    private List<Logger> loggers;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void setup() throws IOException {
        File file = folder.newFile("log.txt");
        pathToEmptyFile = file.getAbsolutePath();
        loggers = new ArrayList<>(Arrays.asList(new FileLogger(pathToEmptyFile), new ConsoleLogger(new ServerConsole())));
    }

    @Test
    public void savesRequestLine() {
        MultiLogger multiLogger = new MultiLogger(loggers);
        multiLogger.log("GET /logs HTTP/1.1");
        multiLogger.log("POST /logs HTTP/1.1");
        assertEquals("GET /logs HTTP/1.1, POST /logs HTTP/1.1, ", multiLogger.getLogs());
    }

    @Test
    public void returnsLogsAsBytes() {
        MultiLogger multiLogger = new MultiLogger(loggers);
        multiLogger.log("GET /logs HTTP/1.1");
        multiLogger.log("POST /logs HTTP/1.1");
        assertEquals("GET /logs HTTP/1.1, POST /logs HTTP/1.1, ", multiLogger.getLogs());
    }
}