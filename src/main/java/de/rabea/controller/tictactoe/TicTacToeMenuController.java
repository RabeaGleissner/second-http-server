package de.rabea.controller.tictactoe;

import de.rabea.Controller;
import de.rabea.controller.tictactoe.html.TicTacToeHtml;
import de.rabea.game.GameMode;
import de.rabea.request.GameModeParser;
import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;
import de.rabea.response.head.RedirectResponseHeader;

import static de.rabea.game.GameMode.HumanVsComputer;
import static de.rabea.game.GameMode.HumanVsHuman;
import static de.rabea.response.head.StatusLine.REDIRECT;

public class TicTacToeMenuController extends Controller {

    private int gameNumber = 0;
    private final String DOMAIN = "http://localhost:5000/ttt-";
    private final TicTacToeHtml ticTacToeHtml = new TicTacToeHtml();

    @Override
    public HttpResponse doGet(HttpRequest request) {
        return ticTacToeHtml.generateMenu();
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