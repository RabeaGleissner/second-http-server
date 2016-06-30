package de.rabea.response.head;

import org.junit.Test;

import static org.junit.Assert.*;

public class AuthenticateResponseHeaderTest {

    @Test
    public void createsResponseHeader() {
        AuthenticateResponseHeader header = new AuthenticateResponseHeader("realm");
        assertEquals("WWW-Authenticate: Basic realm=\"realm\"", header.create());
    }
}