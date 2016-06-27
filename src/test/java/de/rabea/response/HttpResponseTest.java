package de.rabea.response;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class HttpResponseTest {
    @Test
    public void returnsResponseAsBytes() {
        String responseString = "hello";
        HttpResponse response = new HttpResponse("hello");
        assertArrayEquals(responseString.getBytes(), response.asBytes());
    }
}