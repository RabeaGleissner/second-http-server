package de.rabea.controller;

import de.rabea.Controller;
import de.rabea.controller.html.BoardHtml;
import de.rabea.controller.html.TicTacToeHtmlGenerator;
import de.rabea.game.Board;
import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;
import de.rabea.response.head.StatusLine;

public class TicTacToeController extends Controller {

    @Override
    public HttpResponse doGet(HttpRequest request) {
        Board board = new Board(3);
        String html = new TicTacToeHtmlGenerator(new BoardHtml(board)).generate();
        return new HttpResponse(StatusLine.OK, html.getBytes());
    }

}
