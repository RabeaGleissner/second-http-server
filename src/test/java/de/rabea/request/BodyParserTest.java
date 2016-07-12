package de.rabea.request;

import org.junit.Test;

import static org.junit.Assert.*;

public class BodyParserTest {

    @Test
    public void returnsBodyContentAfterEqualsSign() {
        BodyParser parser = new BodyParser("data=something");
        assertEquals("something", parser.content());
    }
}