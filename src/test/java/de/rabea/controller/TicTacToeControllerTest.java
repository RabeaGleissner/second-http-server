package de.rabea.controller;

import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;
import org.junit.Test;

import static de.rabea.request.HttpVerb.GET;
import static org.junit.Assert.assertTrue;

public class TicTacToeControllerTest {

    @Test
    public void returnsHtmlAsResponseForGetRequest() {
        TicTacToeController controller = new TicTacToeController();
        HttpResponse response = controller.doGet(new HttpRequest(GET, "/ttt-board"));
        String httpResponse = response.asString();
        assertTrue(httpResponse.contains("HTTP/1.1 200 OK\n" +
                "\n" +
                "<!DOCTYPE html><html lang=\"en\"><head>  " +
                "<meta charset=\"UTF-8\">  " +
                "<title>Tic Tac Toe</title>  "));
    }
}