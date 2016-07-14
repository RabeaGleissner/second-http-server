package de.rabea.controller;

import de.rabea.Controller;
import de.rabea.controller.html.BoardHtml;
import de.rabea.controller.html.TicTacToeHtmlGenerator;
import de.rabea.game.Board;
import de.rabea.game.UnbeatableComputerPlayer;
import de.rabea.request.HttpRequest;
import de.rabea.request.MoveParser;
import de.rabea.response.HttpResponse;
import de.rabea.response.head.RedirectResponseHeader;

import static de.rabea.game.GameMode.ComputerVsHuman;
import static de.rabea.game.Mark.O;
import static de.rabea.response.head.StatusLine.OK;
import static de.rabea.response.head.StatusLine.REDIRECT;

public class TttComputerVsHumanController extends Controller {

    private Board board;
    private boolean boardCreated = false;

    @Override
    public HttpResponse doGet(HttpRequest request) {
        board = createNewBoard();
        if (!board.gameOver()) {
            board = board.placeMark(new UnbeatableComputerPlayer(O).getMove(board));
        }
        return htmlBoard();
    }

    @Override
    public HttpResponse doPost(HttpRequest request) {
        board = board.placeMark(new MoveParser(request.body()).move());
        return new HttpResponse(REDIRECT, new RedirectResponseHeader("http://localhost:5000/ttt-cvh"));
    }

    private Board createNewBoard() {
        if (!boardCreated) {
            boardCreated = true;
            return new Board(3);
        }
        return board;
    }


    private HttpResponse htmlBoard() {
        String html = new TicTacToeHtmlGenerator(new BoardHtml(board, ComputerVsHuman)).generate();
        return new HttpResponse(OK, html.getBytes());
    }
}
