package de.rabea.request;

import org.junit.Test;

import static org.junit.Assert.*;

public class MoveParserTest {

    @Test
    public void returnsBodyContentAfterEqualsSign() {
        MoveParser parser = new MoveParser("data=1");
        assertEquals(1, parser.move());
    }
}