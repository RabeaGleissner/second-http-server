package de.rabea.controller.tictactoe;

import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;
import org.junit.Test;

import static de.rabea.request.HttpVerb.GET;
import static de.rabea.request.HttpVerb.POST;
import static org.junit.Assert.assertTrue;

public class TttHumanVsHumanControllerTest {

    @Test
    public void returnsHtmlAsResponseForGetRequest() {
        TttHumanVsHumanController controller = new TttHumanVsHumanController();
        HttpResponse response = controller.doGet(new HttpRequest(GET, "/ttt-board"));
        String httpResponse = response.asString();
        assertTrue(httpResponse.contains("HTTP/1.1 200 OK\n" +
                "\n" +
                "<!DOCTYPE html><html lang=\"en\"><head>  " +
                "<meta charset=\"UTF-8\">  " +
                "<title>Tic Tac Toe</title>  "));
    }

    @Test
    public void returnsResponseToPostRequest() {
        TttHumanVsHumanController controller = new TttHumanVsHumanController();
        controller.doGet(new HttpRequest(GET, "/ttt-board"));
        HttpResponse response = controller.doPost(new HttpRequest(POST, "/ttt-board", "move=1"));
        String httpResponse = response.asString();
        assertTrue(httpResponse.contains("<div class='cell full'>X</div>"));
    }
}