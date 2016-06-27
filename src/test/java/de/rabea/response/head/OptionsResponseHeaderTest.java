package de.rabea.response.head;

import de.rabea.request.HttpVerb;
import de.rabea.response.HttpResponse;
import de.rabea.response.head.OptionsResponseHeader;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static de.rabea.request.HttpVerb.GET;
import static de.rabea.request.HttpVerb.POST;
import static org.junit.Assert.*;

public class OptionsResponseHeaderTest {

    @Test
    public void returnsResponseWithAllowedMethods() {
        Map<HttpVerb, HttpResponse> responses = new HashMap<>();
        responses.put(GET, new FakeHttpResponse());
        responses.put(POST, new FakeHttpResponse());
        OptionsResponseHeader header = new OptionsResponseHeader(responses);
        String allowHeader = header.create();
        assertTrue(allowHeader.contains("GET"));
        assertTrue(allowHeader.contains("POST"));
        assertTrue(allowHeader.contains("Allow:"));
    }

    private class FakeHttpResponse extends HttpResponse {
        public FakeHttpResponse() {
            super();
        }
    }
}