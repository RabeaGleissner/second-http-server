package de.rabea.response.creator;

import de.rabea.response.HttpResponse;
import org.junit.Test;

import static de.rabea.response.head.StatusLine.OK;
import static org.junit.Assert.assertEquals;

public class HeadResponseCreatorTest {

    @Test
    public void createsResponse() {
        HeadResponseCreator creator = new HeadResponseCreator(OK);
        HttpResponse response = creator.create(new byte[0]);
        assertEquals("HTTP/1.1 200 OK\n\n", response.asString());
    }
}