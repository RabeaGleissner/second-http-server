package de.rabea.communication;

import de.rabea.request.HttpRequest;
import org.junit.Before;
import org.junit.Test;

import static de.rabea.request.HttpVerb.POST;
import static org.junit.Assert.assertEquals;

public class RequestParserTest {
    private RequestParser parser;
    private String REQUEST_HEADERS;
    private String REQUEST_LINE;
    private String CONTENT_LENGTH;
    private String BODY;

    @Before
    public void setup() {
        parser = new RequestParser();
        REQUEST_LINE = "POST /form HTTP/1.1\n";
        REQUEST_HEADERS = "Host: localhost:5000\n" +
                "Connection: Keep-Alive\n" +
                "User-Agent: Apache-HttpClient/4.3.5 (java 1.5)\n" +
                "Accept-Encoding: gzip,deflate\n";
        CONTENT_LENGTH = "Content-Length: 11\n";
        BODY = "\nMy=Data";
    }

    @Test
    public void returnsNewHttpRequest() {
        HttpRequest request = parser.parse(REQUEST_LINE + CONTENT_LENGTH + REQUEST_HEADERS + BODY);
        assertEquals(POST, request.requestLine().method());
        assertEquals("/form", request.requestLine().uri());
        assertEquals("HTTP/1.1", request.requestLine().protocol());
        assertEquals("My=Data", request.body());
        assertEquals("localhost:5000", request.requestHeaders().get("Host"));
    }

    @Test
    public void returnsEmptyStringForBodyWhenRequestHasNoBody() {
        HttpRequest request = parser.parse(REQUEST_LINE + REQUEST_HEADERS);
        assertEquals("", request.body());
    }

    @Test
    public void returnsContentLength() {
        assertEquals(11, parser.contentLength(REQUEST_LINE + CONTENT_LENGTH + REQUEST_HEADERS));
    }

    @Test
    public void returns0IfNoContentLengthSpecified() {
        assertEquals(0, parser.contentLength(REQUEST_LINE + REQUEST_HEADERS));
    }
}