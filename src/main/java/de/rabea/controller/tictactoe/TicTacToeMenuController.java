package de.rabea.controller.tictactoe;

import de.rabea.Controller;
import de.rabea.controller.tictactoe.html.MenuHtml;
import de.rabea.controller.tictactoe.html.TicTacToeHtmlGenerator;
import de.rabea.game.GameMode;
import de.rabea.request.GameModeParser;
import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;
import de.rabea.response.head.RedirectResponseHeader;

import static de.rabea.game.GameMode.*;
import static de.rabea.response.head.StatusLine.OK;
import static de.rabea.response.head.StatusLine.REDIRECT;

public class TicTacToeMenuController extends Controller {

    private int gameNumber = 0;
    private final String DOMAIN = "http://localhost:5000/ttt-";

    @Override
    public HttpResponse doGet(HttpRequest request) {
        String html = new TicTacToeHtmlGenerator(new MenuHtml()).generate();
        return new HttpResponse(OK, html.getBytes());
    }

    @Override
    public HttpResponse doPost(HttpRequest request) {
        String redirectUrl = redirectUrl(new GameModeParser(request.body()).mode());
        return new HttpResponse(REDIRECT, new RedirectResponseHeader(redirectUrl));
    }

    private String redirectUrl(GameMode gameMode) {
        gameNumber++;
        if (gameMode == HumanVsHuman) {
            return url("hvh");
        } else if (gameMode == HumanVsComputer) {
            return url("hvc");
        }
        return  url("cvh");
    }

    private String url(String gameMode) {
        return  DOMAIN + gameMode + "?game-number=" + gameNumber;
    }
}