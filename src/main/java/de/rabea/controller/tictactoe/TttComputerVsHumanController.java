package de.rabea.controller.tictactoe;

import de.rabea.Controller;
import de.rabea.controller.tictactoe.html.TicTacToeHtml;
import de.rabea.game.Board;
import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;
import de.rabea.response.head.RedirectResponseHeader;

import static de.rabea.game.GameMode.ComputerVsHuman;
import static de.rabea.response.head.StatusLine.REDIRECT;

public class TttComputerVsHumanController extends Controller {

    private final GameTracker gameTracker;
    private final GameNumberParser gameNumberParser = new GameNumberParser();
    private final TicTacToeHtml ticTacToeHtml = new TicTacToeHtml();
    private final MoveMaker moveMaker;

    public TttComputerVsHumanController(GameTracker gameTracker) {
        this.gameTracker = gameTracker;
        this.moveMaker = new MoveMaker(gameTracker);
    }

    @Override
    public HttpResponse doGet(HttpRequest request) {
        int gameNumber = gameNumber(request);
        Board board = currentBoard(gameNumber);
        if (!board.gameOver()) {
            return ticTacToeHtml.generateBoard(moveMaker.playComputerMove(gameNumber, board), gameNumber, ComputerVsHuman);
        }
        return ticTacToeHtml.generateBoard(board, gameNumber, ComputerVsHuman);
    }

    @Override
    public HttpResponse doPost(HttpRequest request) {
        int gameNumber = gameNumber(request);
        moveMaker.playHumanMove(request, gameNumber);
        return new HttpResponse(REDIRECT,
                new RedirectResponseHeader("http://localhost:5000/ttt-cvh?game-number=" + gameNumber));
    }

    private Board currentBoard(int gameNumber) {
        return gameTracker.boardFor(gameNumber);
    }

    private int gameNumber(HttpRequest request) {
        return gameNumberParser.parse(request.requestLine().uri());
    }
}
