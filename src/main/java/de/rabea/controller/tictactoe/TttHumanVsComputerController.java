package de.rabea.controller.tictactoe;

import de.rabea.Controller;
import de.rabea.controller.tictactoe.html.BoardHtml;
import de.rabea.controller.tictactoe.html.TicTacToeHtmlGenerator;
import de.rabea.game.Board;
import de.rabea.game.UnbeatableComputerPlayer;
import de.rabea.request.HttpRequest;
import de.rabea.request.MoveParser;
import de.rabea.response.HttpResponse;

import static de.rabea.game.GameMode.HumanVsComputer;
import static de.rabea.game.Mark.O;
import static de.rabea.response.head.StatusLine.OK;

public class TttHumanVsComputerController extends Controller {

    private Board board;

    @Override
    public HttpResponse doGet(HttpRequest request) {
        board = new Board(3);
        return htmlBoard(request);
    }

    @Override
    public HttpResponse doPost(HttpRequest request) {
        board = board.placeMark(new MoveParser(request.body()).move());
        if (!board.gameOver()) {
            board = board.placeMark(new UnbeatableComputerPlayer(O).getMove(board));
        }
        return htmlBoard(request);
    }

    private HttpResponse htmlBoard(HttpRequest request) {
        int gameNumber = new GameNumberParser().parse(request.requestLine().uri());
        String html = new TicTacToeHtmlGenerator(new BoardHtml(board, HumanVsComputer, gameNumber)).generate();
        return new HttpResponse(OK, html.getBytes());
    }
}
