package de.rabea;

import de.rabea.request.RequestLine;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class LoggerTest {

    @Test
    public void savesRequestLine() {
        Logger logger = new Logger();
        logger.log(new RequestLine("GET /logs HTTP/1.1"));
        logger.log(new RequestLine("POST /logs HTTP/1.1"));
        assertEquals("GET /logs HTTP/1.1, POST /logs HTTP/1.1", logger.logsAsString());
    }

    @Test
    public void returnsLogsAsBytes() {
        Logger logger = new Logger();
        logger.log(new RequestLine("GET /logs HTTP/1.1"));
        logger.log(new RequestLine("POST /logs HTTP/1.1"));
        assertArrayEquals("GET /logs HTTP/1.1, POST /logs HTTP/1.1".getBytes(), logger.getLogs());
    }
}