package de.rabea.request;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class RangeParserTest {

    @Test
    public void returnsPartialContentWhenStartAndEndAreGiven() {
        RangeParser parser = new RangeParser("some content".getBytes(), "bytes=0-3");
        assertArrayEquals("some".getBytes(), parser.partialContent());
    }

    @Test
    public void returnsPartialContentWhenOnlyRangeEndIsGiven() {
        RangeParser parser = new RangeParser("some content".getBytes(), "bytes=-3");
        assertArrayEquals("ent".getBytes(), parser.partialContent());
    }

    @Test
    public void returnsPartialContentWhenOnlyRangeStartIsGiven() {
        RangeParser parser = new RangeParser("some content".getBytes(), "bytes=4-");
        assertArrayEquals(" content".getBytes(), parser.partialContent());
    }
}