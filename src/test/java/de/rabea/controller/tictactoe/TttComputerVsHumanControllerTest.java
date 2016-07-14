package de.rabea.controller.tictactoe;

import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;
import org.junit.Test;

import static de.rabea.request.HttpVerb.GET;
import static de.rabea.request.HttpVerb.POST;
import static org.junit.Assert.assertTrue;

public class TttComputerVsHumanControllerTest {

    @Test
    public void makesMoveAndReturnsHtmlAsResponseForGetRequest() {
        TttComputerVsHumanController controller = new TttComputerVsHumanController();

        HttpResponse response = controller.dispatch(new HttpRequest(GET, "/ttt-cvh?game-number=1"));
        String httpResponse = response.asString();

        assertTrue(httpResponse.contains("HTTP/1.1 200 OK\n" +
                "\n" +
                "<!DOCTYPE html><html lang=\"en\"><head>  " +
                "<meta charset=\"UTF-8\">  " +
                "<title>Tic Tac Toe</title>  "));
        assertTrue(httpResponse.contains("<div class='cell full'>X</div>"));
    }

    @Test
    public void returnsRedirectResponseForPostRequest() {
        TttComputerVsHumanController controller = new TttComputerVsHumanController();
        controller.dispatch(new HttpRequest(GET, "/ttt-cvh?game-number=1"));

        HttpResponse response = controller.dispatch(new HttpRequest(POST, "/ttt-cvh?game-number=1", "move=1"));
        String httpResponse = response.asString();

        assertTrue(httpResponse.contains("HTTP/1.1 302 Found\n" +
                "Location: http://localhost:5000/ttt-cvh"));
    }

    @Test
    public void addsAnotherMarkToExistingBoardWithMarks() {
        TttComputerVsHumanController controller = new TttComputerVsHumanController();
        controller.dispatch(new HttpRequest(GET, "/ttt-cvh?game-number=1"));
        controller.dispatch(new HttpRequest(POST, "/ttt-cvh?game-number=1", "move=3"));

        HttpResponse response = controller.dispatch(new HttpRequest(GET, "/ttt-cvh?game-number=1"));
        String httpResponse = response.asString();

        assertTrue(httpResponse.contains("<div class='cell full'>X</div>"));
        assertTrue(httpResponse.contains("<div class='cell full'>O</div>"));
    }
}