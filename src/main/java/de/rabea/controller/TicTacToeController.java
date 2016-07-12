package de.rabea.controller;

import de.rabea.Controller;
import de.rabea.controller.html.BoardHtml;
import de.rabea.controller.html.TicTacToeHtmlGenerator;
import de.rabea.game.Board;
import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;

import static de.rabea.response.head.StatusLine.OK;

public class TicTacToeController extends Controller {

    private Board board;

    public TicTacToeController() {
        board = new Board(3);
    }

    @Override
    public HttpResponse doGet(HttpRequest request) {
        String html = new TicTacToeHtmlGenerator(new BoardHtml(board)).generate();
        return new HttpResponse(OK, html.getBytes());
    }

    @Override
    public HttpResponse doPost(HttpRequest request) {
        String requestBody = request.body();
        int position = Integer.parseInt(requestBody.substring(requestBody.length() - 1));
        board = board.placeMark(position);
        String html = new TicTacToeHtmlGenerator(new BoardHtml(board)).generate();
        return new HttpResponse(OK, html.getBytes());
    }
}
