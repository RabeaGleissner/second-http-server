package de.rabea.communication;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class RequestParserTest {
    private RequestParser parser;
    private String otherRequestDetails;
    private String requestLine;

    @Before
    public void setup() {
        parser = new RequestParser();
        requestLine = "POST /form HTTP/1.1\n";
        otherRequestDetails = "Host: localhost:5000\n" +
                "Connection: Keep-Alive\n" +
                "User-Agent: Apache-HttpClient/4.3.5 (java 1.5)\n" +
                "Accept-Encoding: gzip,deflate\n";
    }

    @Test
    public void returnsContentLength() {
        assertEquals(11, parser.contentLength(requestLine + "Content-Length: 11\n" + otherRequestDetails));
    }

    @Test
    public void getsRequestBody() {
        assertEquals("My=Data", parser.getBody(requestLine +  "Content-Length: 11\n" + otherRequestDetails + "\nMy=Data"));
    }

    @Test
    public void returnsEmptyStringIfRequestHasNoBody() {
        assertEquals("", parser.getBody(requestLine + otherRequestDetails));
    }

    @Test
    public void returnsHeaders() {
        Map<String, String> parsedHeaders = parser.parseHeaders(requestLine + otherRequestDetails + "\nMy=Data");
        assertEquals("localhost:5000", parsedHeaders.get("Host"));
    }
}