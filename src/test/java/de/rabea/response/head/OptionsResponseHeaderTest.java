package de.rabea.response.head;

import org.junit.Test;

import static de.rabea.request.HttpVerb.*;
import static org.junit.Assert.assertEquals;

public class OptionsResponseHeaderTest {

    @Test
    public void returnsResponseWithAllowedMethods() {
        OptionsResponseHeader header = new OptionsResponseHeader(GET,HEAD,POST,OPTIONS,PUT);
        assertEquals("Allow: GET,HEAD,POST,OPTIONS,PUT", header.create());
    }
}