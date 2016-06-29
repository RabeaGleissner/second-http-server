package de.rabea.controller;

import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;
import org.junit.Test;

import static de.rabea.request.HttpVerb.GET;
import static org.junit.Assert.assertEquals;

public class CoffeeControllerTest {

    @Test
    public void returnsResponseForCoffeeRoute() {
        CoffeeController controller = new CoffeeController();
        HttpResponse response = controller.dispatch(new HttpRequest(GET, "/teapot"));
        assertEquals("HTTP/1.1 418 I'm a teapot\n\nI'm a teapot", response.asString());
    }
}