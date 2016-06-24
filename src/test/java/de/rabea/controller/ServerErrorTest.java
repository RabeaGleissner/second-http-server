package de.rabea.controller;

import de.rabea.HttpRequestDummy;
import de.rabea.response.HttpResponse;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ServerErrorTest {

    @Test
    public void createsAnHttpResponseForServerError() {
        ServerError serverError = new ServerError();
        HttpResponse response = serverError.getResponse(new HttpRequestDummy());
        assertTrue(response.asString().contains("500"));
    }
}