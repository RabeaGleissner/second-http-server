package de.rabea.response.creator;

import de.rabea.request.HttpVerb;
import de.rabea.response.ResponseCreator;
import de.rabea.response.head.FakeResponseCreator;
import de.rabea.response.head.OptionsResponseHeader;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static de.rabea.request.HttpVerb.GET;
import static de.rabea.request.HttpVerb.POST;
import static de.rabea.response.head.StatusLine.OK;
import static org.junit.Assert.assertTrue;

public class OptionsResponseCreatorTest {

    @Test
    public void createResponse() {
        Map<HttpVerb, ResponseCreator> responses = new HashMap<>();
        responses.put(GET, new FakeResponseCreator());
        responses.put(POST, new FakeResponseCreator());
        OptionsResponseCreator creator = new OptionsResponseCreator(OK, new OptionsResponseHeader(responses));

        String response = creator.create("").asString();

        assertTrue(response.contains("GET"));
        assertTrue(response.contains("POST"));
        assertTrue(response.contains("Allow"));
    }
}