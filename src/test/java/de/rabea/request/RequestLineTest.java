package de.rabea.request;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RequestLineTest {
    private final String POST_REQUEST = "POST /form HTTP/1.1\n Content-Length: 11\n Host: localhost:5000\n\nMy=Data\n";

    @Test
    public void returnsRequestMethod() {
        RequestLine requestLine = new RequestLine(POST_REQUEST);
        assertEquals(HttpVerb.POST, requestLine.method());
    }

    @Test
    public void returnsRequestUri() {
        RequestLine requestLine = new RequestLine(POST_REQUEST);
        assertEquals("/form", requestLine.uri());
    }

    @Test
    public void returnsHttpProtocol() {
        RequestLine requestLine = new RequestLine(POST_REQUEST);
        assertEquals("HTTP/1.1", requestLine.protocol());
    }
}