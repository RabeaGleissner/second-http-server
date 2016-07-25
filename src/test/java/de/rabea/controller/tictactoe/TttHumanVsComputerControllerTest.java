package de.rabea.controller.tictactoe;

import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;
import org.junit.Test;

import static de.rabea.request.HttpVerb.GET;
import static de.rabea.request.HttpVerb.POST;
import static org.junit.Assert.assertTrue;

public class TttHumanVsComputerControllerTest {

    @Test
    public void returnsHtmlAsResponseForGetRequest() {
        TttHumanVsComputerController controller = new TttHumanVsComputerController(new GameTracker());
        HttpResponse response = controller.dispatch(new HttpRequest(GET, "/ttt-hvc?game-number=1"));
        String httpResponse = response.asString();
        assertTrue(httpResponse.contains("HTTP/1.1 200 OK\n" +
                "\n" +
                "<!DOCTYPE html><html lang=\"en\"><head>  " +
                "<meta charset=\"UTF-8\">  " +
                "<title>Tic Tac Toe</title>  "));
    }

    @Test
    public void makesMovesAndReturnsOKResponseForPostRequest() {
        TttHumanVsComputerController controller = new TttHumanVsComputerController(new GameTracker());
        controller.dispatch(new HttpRequest(GET, "/ttt-hvc?game-number=1"));
        HttpResponse response = controller.dispatch(new HttpRequest(POST, "/ttt-hvc?game-number=1", "move=1"));
        String httpResponse = response.asString();
        assertTrue(httpResponse.contains("<div class='cell full'>X</div>"));
        assertTrue(httpResponse.contains("<div class='cell full'>O</div>"));
    }
}