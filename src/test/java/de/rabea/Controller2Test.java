package de.rabea;

import de.rabea.response.HttpResponse;
import org.junit.Test;

import static de.rabea.response.head.StatusLine.NOT_ALLOWED;
import static org.junit.Assert.*;

public class Controller2Test {

    private HttpRequestDummy anyRequest = new HttpRequestDummy("GET / HTTP/1.1");
    private Controller2 controller2 = new Controller2();

    @Test
    public void returnsResponseForGetMethod() {
        HttpResponse response = controller2.doGet(anyRequest);
        assertEquals(NOT_ALLOWED, response.getStatusCode());
    }

    @Test
    public void returnsResponseForPutMethod() {
        HttpResponse response = controller2.doPut(anyRequest);
        assertEquals(NOT_ALLOWED, response.getStatusCode());
    }

    @Test
    public void returnsResponseForPostMethod() {
        HttpResponse response = controller2.doPost(anyRequest);
        assertEquals(NOT_ALLOWED, response.getStatusCode());
    }

    @Test
    public void returnsResponseForHeadMethod() {
        HttpResponse response = controller2.doHead(anyRequest);
        assertEquals(NOT_ALLOWED, response.getStatusCode());
    }

    @Test
    public void returnsResponseForDeleteMethod() {
        HttpResponse response = controller2.doDelete(anyRequest);
        assertEquals(NOT_ALLOWED, response.getStatusCode());
    }

    @Test
    public void returnsResponseForOptionsMethod() {
        HttpResponse response = controller2.doOptions(anyRequest);
        assertEquals(NOT_ALLOWED, response.getStatusCode());
    }

    @Test
    public void returnsResponseForPatchMethod() {
        HttpResponse response = controller2.doPatch(anyRequest);
        assertEquals(NOT_ALLOWED, response.getStatusCode());
    }
}