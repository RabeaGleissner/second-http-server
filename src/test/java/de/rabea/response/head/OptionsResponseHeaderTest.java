package de.rabea.response.head;

import de.rabea.request.HttpVerb;
import de.rabea.response.HttpResponse;
import de.rabea.response.ResponseCreator;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static de.rabea.request.HttpVerb.GET;
import static de.rabea.request.HttpVerb.POST;
import static org.junit.Assert.*;

public class OptionsResponseHeaderTest {

    @Test
    public void returnsResponseWithAllowedMethods() {
        Map<HttpVerb, ResponseCreator> responses = new HashMap<>();
        responses.put(GET, new FakeResponseCreator());
        responses.put(POST, new FakeResponseCreator());
        OptionsResponseHeader header = new OptionsResponseHeader(responses);
        String allowHeader = header.create();
        assertTrue(allowHeader.contains("GET"));
        assertTrue(allowHeader.contains("POST"));
        assertTrue(allowHeader.contains("Allow:"));
    }

    private class FakeResponseCreator extends ResponseCreator {
        public FakeResponseCreator() {
            super();
        }

        @Override
        public HttpResponse create(String body) {
            return null;
        }
    }
}