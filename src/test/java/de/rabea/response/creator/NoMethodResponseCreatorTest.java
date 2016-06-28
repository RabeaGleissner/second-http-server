package de.rabea.response.creator;

import de.rabea.response.HttpResponse;
import org.junit.Test;

import static org.junit.Assert.*;

public class NoMethodResponseCreatorTest {

    @Test
    public void returnsResponse() {
        NoMethodResponseCreator responseCreator = new NoMethodResponseCreator();
        HttpResponse response = responseCreator.create("");
        assertEquals("HTTP/1.1 405 Method Not Allowed\n", response.asString());
    }
}