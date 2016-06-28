package de.rabea.response.head;

import org.junit.Test;

import static org.junit.Assert.*;

public class RedirectResponseHeaderTest {

    @Test
    public void createsResponseHeader() {
        RedirectResponseHeader header = new RedirectResponseHeader("internet");
        assertEquals("Location: internet", header.create());
    }
}