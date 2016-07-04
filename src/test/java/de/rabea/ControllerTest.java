package de.rabea;

import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;
import org.junit.Before;
import org.junit.Test;

import static de.rabea.request.HttpVerb.*;
import static de.rabea.response.head.StatusLine.NOT_ALLOWED;
import static org.junit.Assert.*;

public class ControllerTest {

    private SampleControllerSpy controllerSpy;

    @Before
    public void setup() {
        controllerSpy = new SampleControllerSpy();
    }

    @Test
    public void returnsResponseForGetMethod() {
        HttpResponse response = controllerSpy.dispatch(new HttpRequestDummy(GET));
        assertEquals(NOT_ALLOWED, response.getStatusCode());
        assertTrue(controllerSpy.lastMethod.equals("GET"));
    }

    @Test
    public void returnsResponseForPutMethod() {
        HttpResponse response = controllerSpy.dispatch(new HttpRequestDummy(PUT));
        assertEquals(NOT_ALLOWED, response.getStatusCode());
        assertTrue(controllerSpy.lastMethod.equals("PUT"));
    }

    @Test
    public void returnsResponseForPostMethod() {
        HttpResponse response = controllerSpy.dispatch(new HttpRequestDummy(POST));
        assertEquals(NOT_ALLOWED, response.getStatusCode());
        assertTrue(controllerSpy.lastMethod.equals("POST"));
    }

    @Test
    public void returnsResponseForHeadMethod() {
        HttpResponse response = controllerSpy.dispatch(new HttpRequestDummy(HEAD));
        assertEquals(NOT_ALLOWED, response.getStatusCode());
        assertTrue(controllerSpy.lastMethod.equals("HEAD"));
    }

    @Test
    public void returnsResponseForDeleteMethod() {
        HttpResponse response = controllerSpy.dispatch(new HttpRequestDummy(DELETE));
        assertEquals(NOT_ALLOWED, response.getStatusCode());
        assertTrue(controllerSpy.lastMethod.equals("DELETE"));
    }

    @Test
    public void returnsResponseForOptionsMethod() {
        HttpResponse response = controllerSpy.dispatch(new HttpRequestDummy(OPTIONS));
        assertEquals(NOT_ALLOWED, response.getStatusCode());
        assertTrue(controllerSpy.lastMethod.equals("OPTIONS"));
    }

    @Test
    public void returnsResponseForPatchMethod() {
        HttpResponse response = controllerSpy.dispatch(new HttpRequestDummy(PATCH));
        assertEquals(NOT_ALLOWED, response.getStatusCode());
        assertTrue(controllerSpy.lastMethod.equals("PATCH"));
    }

    @Test
    public void returnsResponseForBogusRequest() {
        Controller controller = new Controller();
        HttpResponse response = controller.dispatch(new HttpRequestDummy(NONE));
        assertEquals(NOT_ALLOWED, response.getStatusCode());
    }

    private static class SampleControllerSpy extends Controller {

        public String lastMethod;

        @Override
        public HttpResponse doGet(HttpRequest request) {
            lastMethod = "GET";
            return super.doGet(request);
        }

        @Override
        public HttpResponse doPut(HttpRequest request) {
            lastMethod = "PUT";
            return super.doPut(request);
        }

        @Override
        public HttpResponse doPost(HttpRequest request) {
            lastMethod = "POST";
            return super.doPost(request);
        }

        @Override
        public HttpResponse doHead(HttpRequest request) {
            lastMethod = "HEAD";
            return super.doHead(request);
        }

        @Override
        public HttpResponse doDelete(HttpRequest request) {
            lastMethod = "DELETE";
            return super.doDelete(request);
        }

        @Override
        public HttpResponse doOptions(HttpRequest request) {
            lastMethod = "OPTIONS";
            return super.doOptions(request);
        }

        @Override
        public HttpResponse doPatch(HttpRequest request) {
            lastMethod = "PATCH";
            return super.doPatch(request);
        }
    }
}