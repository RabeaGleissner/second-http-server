package de.rabea.controller.tictactoe;

import de.rabea.Controller;
import de.rabea.controller.tictactoe.html.BoardHtml;
import de.rabea.controller.tictactoe.html.TicTacToeHtmlGenerator;
import de.rabea.game.Board;
import de.rabea.request.HttpRequest;
import de.rabea.request.MoveParser;
import de.rabea.response.HttpResponse;

import static de.rabea.game.GameMode.HumanVsHuman;
import static de.rabea.response.head.StatusLine.OK;

public class TttHumanVsHumanController extends Controller {

    private Board board;

    @Override
    public HttpResponse doGet(HttpRequest request) {
        board = new Board(3);
        return htmlBoard(request);
    }

    @Override
    public HttpResponse doPost(HttpRequest request) {
        board = board.placeMark(new MoveParser(request.body()).move());
        return htmlBoard(request);
    }

    private HttpResponse htmlBoard(HttpRequest request) {
        int gameNumber = new GameNumberParser().parse(request.requestLine().uri());
        String html = new TicTacToeHtmlGenerator(new BoardHtml(board, HumanVsHuman, gameNumber)).generate();
        return new HttpResponse(OK, html.getBytes());
    }
}