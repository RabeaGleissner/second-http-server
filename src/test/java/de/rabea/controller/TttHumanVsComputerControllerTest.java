package de.rabea.controller;

import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;
import org.junit.Test;

import static de.rabea.request.HttpVerb.GET;
import static de.rabea.request.HttpVerb.POST;
import static org.junit.Assert.assertTrue;

public class TttHumanVsComputerControllerTest {

    @Test
    public void returnsHtmlAsResponseForGetRequest() {
        TttHumanVsComputerController controller = new TttHumanVsComputerController();
        HttpResponse response = controller.dispatch(new HttpRequest(GET, "/ttt-board"));
        String httpResponse = response.asString();
        assertTrue(httpResponse.contains("HTTP/1.1 200 OK\n" +
                "\n" +
                "<!DOCTYPE html><html lang=\"en\"><head>  " +
                "<meta charset=\"UTF-8\">  " +
                "<title>Tic Tac Toe</title>  "));
    }

    @Test
    public void placesMarkAndReturnsOKResponseForPostRequest() {
        TttHumanVsComputerController controller = new TttHumanVsComputerController();
        controller.dispatch(new HttpRequest(GET, "/ttt-board"));
        HttpResponse response = controller.dispatch(new HttpRequest(POST, "/ttt-board", "move=1"));
        String httpResponse = response.asString();
        assertTrue(httpResponse.contains("<div class='cell full'>X</div>"));
        assertTrue(httpResponse.contains("<div class='cell full'>O</div>"));
    }
}