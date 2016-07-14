package de.rabea.controller.tictactoe;

import de.rabea.controller.tictactoe.TttComputerVsHumanController;
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
        HttpResponse response = controller.dispatch(new HttpRequest(GET, "/ttt-board"));
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
        controller.dispatch(new HttpRequest(GET, "/ttt-board"));
        HttpResponse response = controller.dispatch(new HttpRequest(POST, "/ttt-board", "move=1"));
        String httpResponse = response.asString();
        assertTrue(httpResponse.contains("HTTP/1.1 302 Found\n" +
                "Location: http://localhost:5000/ttt-cvh"));
    }
}