package de.rabea;

import de.rabea.ServerAuthenticator;
import de.rabea.request.HttpRequest;
import de.rabea.request.RequestLine;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class ServerAuthenticatorTest {

    @Test
    public void returnsTrueWhenRequestIsAuthenticated() {
        ServerAuthenticator authenticator = new ServerAuthenticator("admin", "hunter2");
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Basic YWRtaW46aHVudGVyMg==");
        RequestLine requestLine = new RequestLine("GET /logs HTTP/1.1");
        assertTrue(authenticator.hasCorrectCredentials(new HttpRequest(requestLine, headers, "")));
    }

    @Test
    public void returnsFalseWhenPasswordIsIncorrect() {
        ServerAuthenticator authenticator = new ServerAuthenticator("admin", "hunter1");
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Basic YWRtaW46aHVudGVyMg==");
        RequestLine requestLine = new RequestLine("GET /logs HTTP/1.1");
        assertFalse(authenticator.hasCorrectCredentials(new HttpRequest(requestLine, headers, "")));
    }

    @Test
    public void returnsFalseIfRequestHasNoAuthorizationHeader() {
        ServerAuthenticator authenticator = new ServerAuthenticator("admin", "hunter2");
        Map<String, String> headers = new HashMap<>();
        RequestLine requestLine = new RequestLine("GET /logs HTTP/1.1");
        assertFalse(authenticator.hasCorrectCredentials(new HttpRequest(requestLine, headers, "")));
    }
}
