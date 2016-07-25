package de.rabea.controller.tictactoe;

import de.rabea.Controller;
import de.rabea.controller.tictactoe.html.TicTacToeHtml;
import de.rabea.game.Board;
import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;

import static de.rabea.game.GameMode.HumanVsComputer;

public class TttHumanVsComputerController extends Controller {

    private final GameTracker gameTracker;
    private final GameNumberParser gameNumberParser = new GameNumberParser();
    private final TicTacToeHtml ticTacToeHtml = new TicTacToeHtml();
    private final MoveMaker moveMaker;

    public TttHumanVsComputerController(GameTracker gameTracker) {
        this.gameTracker = gameTracker;
        this.moveMaker = new MoveMaker(gameTracker);
    }

    @Override
    public HttpResponse doGet(HttpRequest request) {
        int gameNumber = gameNumber(request);
        Board board = currentBoard(gameNumber);
        gameTracker.updateGameState(board, gameNumber);
        return ticTacToeHtml.generateBoard(board, gameNumber(request), HumanVsComputer);
    }

    private Board currentBoard(int gameNumber) {
        return gameTracker.boardFor(gameNumber);
    }

    @Override
    public HttpResponse doPost(HttpRequest request) {
        int gameNumber = gameNumber(request);
        Board nextBoard = moveMaker.playHumanMove(request, gameNumber);
        if (!nextBoard.gameOver()) {
            return ticTacToeHtml.generateBoard(moveMaker.playComputerMove(gameNumber, nextBoard), gameNumber, HumanVsComputer);
        }
        return ticTacToeHtml.generateBoard(nextBoard, gameNumber, HumanVsComputer);
    }

    private int gameNumber(HttpRequest request) {
        return gameNumberParser.parse(request.requestLine().uri());
    }
}
