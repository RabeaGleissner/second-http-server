package de.rabea;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class MultiLoggerTest {
    private String pathToEmptyFile;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void setup() throws IOException {
        File file = folder.newFile("log.txt");
        pathToEmptyFile = file.getAbsolutePath();
    }

    @Test
    public void savesRequestLine() {
        MultiLogger multiLogger = new MultiLogger();
        multiLogger.add(new FileLogger(pathToEmptyFile));
        multiLogger.log("GET /logs HTTP/1.1");
        multiLogger.log("POST /logs HTTP/1.1");
        assertEquals("GET /logs HTTP/1.1, POST /logs HTTP/1.1, ", multiLogger.getLogs());
    }

    @Test
    public void returnsLogsAsBytes() {
        MultiLogger multiLogger = new MultiLogger();
        multiLogger.add(new FileLogger(pathToEmptyFile));
        multiLogger.log("GET /logs HTTP/1.1");
        multiLogger.log("POST /logs HTTP/1.1");
        assertEquals("GET /logs HTTP/1.1, POST /logs HTTP/1.1, ", multiLogger.getLogs());
    }
}