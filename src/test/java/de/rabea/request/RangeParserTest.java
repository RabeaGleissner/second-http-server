package de.rabea.request;

import org.junit.Test;

import static org.junit.Assert.*;

public class RangeParserTest {

    @Test
    public void returnsPartialContentWhenStartAndEndAreGiven() {
        RangeParser parser = new RangeParser("some content".getBytes(), "0-3");
        assertArrayEquals("som".getBytes(), parser.partialContent());
    }

    @Test
    public void returnsPartialContentWhenOnlyRangeEndIsGiven() {
        RangeParser parser = new RangeParser("some content".getBytes(), "-3");
        assertArrayEquals("ent".getBytes(), parser.partialContent());
    }

    @Test
    public void returnsPartialContentWhenOnlyRangeStartIsGiven() {
        RangeParser parser = new RangeParser("some content".getBytes(), "4-");
        assertArrayEquals(" content".getBytes(), parser.partialContent());
    }
}