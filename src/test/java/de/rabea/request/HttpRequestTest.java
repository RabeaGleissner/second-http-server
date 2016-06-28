package de.rabea.request;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static de.rabea.request.HttpVerb.POST;
import static org.junit.Assert.assertEquals;

public class HttpRequestTest {
    private HttpRequest request;
    private Map<String, String> requestHeader;
    private final String POST_REQUEST = "POST /form HTTP/1.1\n Content-Length: 11\n Host: localhost:5000\n\nMy=Data\n";

    @Before
    public void setup() {
        requestHeader = new HashMap<>();
        requestHeader.put("Content-Length", "11");
        requestHeader.put("Host", "localhost:5000");
        request = new HttpRequest(new RequestLine(POST_REQUEST), requestHeader, "My=Data");
    }

    @Test
    public void returnsRequestHeader() {
        assertEquals(requestHeader, request.requestHeaders());
    }

    @Test
    public void returnsRequestLine() {
        RequestLine requestLine = request.requestLine();
        assertEquals(POST, requestLine.method());
        assertEquals("/form", requestLine.uri());
        assertEquals("HTTP/1.1", requestLine.protocol());
    }

    @Test
    public void returnsBody() {
       assertEquals("My=Data", request.body());
    }
}