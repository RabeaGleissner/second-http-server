package de.rabea.response;

import org.junit.Test;

import static de.rabea.response.head.StatusLine.OK;
import static org.junit.Assert.assertArrayEquals;

public class HttpResponseTest {
    @Test
    public void returnsResponseAsBytes() {
        String responseString = "HTTP/1.1 200 OK\n";
        HttpResponse response = new HttpResponse(OK);
        assertArrayEquals(responseString.getBytes(), response.asBytes());
    }
}