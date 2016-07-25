package de.rabea.controller.tictactoe;

import de.rabea.Controller;
import de.rabea.controller.tictactoe.html.TicTacToeHtml;
import de.rabea.game.Board;
import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;

import static de.rabea.game.GameMode.HumanVsHuman;

public class TttHumanVsHumanController extends Controller {

    private final GameNumberParser gameNumberParser = new GameNumberParser();
    private final TicTacToeHtml ticTacToeHtml = new TicTacToeHtml();
    private final GameTracker gameTracker;
    private final MoveMaker moveMaker;

    public TttHumanVsHumanController(GameTracker gameTracker) {
        this.gameTracker = gameTracker;
        this.moveMaker = new MoveMaker(gameTracker);
    }

    @Override
    public HttpResponse doGet(HttpRequest request) {
        int gameNumber = gameNumber(request);
        Board board = currentBoard(gameNumber);
        gameTracker.updateGameState(board, gameNumber);
        return ticTacToeHtml.generateBoard(board, gameNumber(request), HumanVsHuman);
    }

    @Override
    public HttpResponse doPost(HttpRequest request) {
        int gameNumber = gameNumber(request);
        return ticTacToeHtml.generateBoard(moveMaker.playHumanMove(request, gameNumber), gameNumber(request), HumanVsHuman);
    }

    private Board currentBoard(int gameNumber) {
        return gameTracker.boardFor(gameNumber);
    }

    private int gameNumber(HttpRequest request) {
        return gameNumberParser.parse(request.requestLine().uri());
    }
}