package de.rabea.controller.tictactoe.html;

import de.rabea.game.Board;
import de.rabea.game.GameMode;
import de.rabea.response.HttpResponse;

import static de.rabea.response.head.StatusLine.OK;

public class TicTacToeHtml {

    public HttpResponse generateBoard(Board board, int gameNumber, GameMode gameMode) {
        String html = new TicTacToeHtmlLayout(new BoardHtml(board, gameMode, gameNumber)).generate();
        return new HttpResponse(OK, html.getBytes());
    }

    public HttpResponse generateMenu() {
        String html = new TicTacToeHtmlLayout(new MenuHtml()).generate();
        return new HttpResponse(OK, html.getBytes());
    }
}
