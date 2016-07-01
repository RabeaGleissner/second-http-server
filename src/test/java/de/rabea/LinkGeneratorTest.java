package de.rabea;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class LinkGeneratorTest {

    @Test
    public void returnsLinksForGivenMap() {
        Map<String, String> anchorWithLinks = new HashMap<>();
        anchorWithLinks.put("anchor1", "/href1");
        anchorWithLinks.put("anchor2", "/href2");
        assertEquals("<a href=\"/href2\">anchor2</a><a href=\"/href1\">anchor1</a>",
                LinkGenerator.generate(anchorWithLinks));
    }
}