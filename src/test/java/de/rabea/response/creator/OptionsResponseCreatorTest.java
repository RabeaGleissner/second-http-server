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
        OptionsResponseCreator creator = new OptionsResponseCreator(OK, new OptionsResponseHeader(GET, POST));
        String response = creator.create("".getBytes()).asString();
        assertTrue(response.contains("GET"));
        assertTrue(response.contains("POST"));
        assertTrue(response.contains("Allow"));
    }
}