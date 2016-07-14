package de.rabea.controller;

import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;
import org.junit.Test;

import static de.rabea.game.GameMode.*;
import static de.rabea.request.HttpVerb.GET;
import static de.rabea.request.HttpVerb.POST;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TicTacToeMenuControllerTest {

    @Test
    public void returnsResponseToGetRequest() {
        TicTacToeMenuController controller = new TicTacToeMenuController();
        HttpResponse response = controller.dispatch(new HttpRequest(GET, "/ttt-menu"));
        String httpResponse = response.asString();
        assertTrue(httpResponse.contains("HTTP/1.1 200 OK"));
        assertTrue(httpResponse.contains("<h2 class='menu'>Please choose a game option:</h2>"));
    }

    @Test
    public void redirectsToHvHGameUrlForPostRequestWithHvHChoice() {
        TicTacToeMenuController controller = new TicTacToeMenuController();
        HttpResponse response = controller.dispatch(new HttpRequest(POST, "/ttt-menu", HumanVsHuman));
        String httpResponse = response.asString();
        assertEquals("HTTP/1.1 302 Found\nLocation: http://localhost:5000/ttt-game/hvh\n", httpResponse);
    }

    @Test
    public void redirectsToHvCGameUrlForPostRequestWithHvCChoice() {
        TicTacToeMenuController controller = new TicTacToeMenuController();
        HttpResponse response = controller.dispatch(new HttpRequest(POST, "/ttt-menu", HumanVsComputer));
        String httpResponse = response.asString();
        assertEquals("HTTP/1.1 302 Found\nLocation: http://localhost:5000/ttt-game/hvc\n", httpResponse);
    }

    @Test
    public void redirectsToHvCGameUrlForPostRequestWithCvHChoice() {
        TicTacToeMenuController controller = new TicTacToeMenuController();
        HttpResponse response = controller.dispatch(new HttpRequest(POST, "/ttt-menu", ComputerVsHuman));
        String httpResponse = response.asString();
        assertEquals("HTTP/1.1 302 Found\nLocation: http://localhost:5000/ttt-game/cvh\n", httpResponse);
    }
}