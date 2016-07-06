package de.rabea;

import de.rabea.request.RequestLine;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class MultiLoggerTest {

    @Test
    public void savesRequestLine() {
        MultiLogger multiLogger = new MultiLogger();
        multiLogger.log("GET /logs HTTP/1.1");
        multiLogger.log("POST /logs HTTP/1.1");
        assertEquals("GET /logs HTTP/1.1, POST /logs HTTP/1.1", multiLogger.logsAsString());
    }

    @Test
    public void returnsLogsAsBytes() {
        MultiLogger multiLogger = new MultiLogger();
        multiLogger.log("GET /logs HTTP/1.1");
        multiLogger.log("POST /logs HTTP/1.1");
        assertArrayEquals("GET /logs HTTP/1.1, POST /logs HTTP/1.1".getBytes(), multiLogger.getLogs());
    }
}