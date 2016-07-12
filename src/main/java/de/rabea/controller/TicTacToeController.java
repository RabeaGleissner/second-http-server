package de.rabea.controller;

import de.rabea.Controller;
import de.rabea.controller.html.BoardHtml;
import de.rabea.controller.html.TicTacToeHtmlGenerator;
import de.rabea.game.Board;
import de.rabea.request.BodyParser;
import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;

import static de.rabea.response.head.StatusLine.OK;

public class TicTacToeController extends Controller {

    private Board board;

    @Override
    public HttpResponse doGet(HttpRequest request) {
        board = new Board(3);
        return htmlBoard();
    }

    @Override
    public HttpResponse doPost(HttpRequest request) {
        board = board.placeMark(Integer.parseInt(new BodyParser(request.body()).content()));
        return htmlBoard();
    }

    private HttpResponse htmlBoard() {
        String html = new TicTacToeHtmlGenerator(new BoardHtml(board)).generate();
        return new HttpResponse(OK, html.getBytes());
    }
}