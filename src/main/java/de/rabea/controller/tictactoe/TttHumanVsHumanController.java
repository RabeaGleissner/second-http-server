package de.rabea.controller.tictactoe;

import de.rabea.Controller;
import de.rabea.controller.tictactoe.html.TicTacToeHtml;
import de.rabea.game.Board;
import de.rabea.request.HttpRequest;
import de.rabea.request.MoveParser;
import de.rabea.response.HttpResponse;

import static de.rabea.game.GameMode.HumanVsHuman;

public class TttHumanVsHumanController extends Controller {

    private final GameNumberParser gameNumberParser = new GameNumberParser();
    private final TicTacToeHtml ticTacToeHtml = new TicTacToeHtml();
    private final GameTracker gameTracker;

    public TttHumanVsHumanController(GameTracker gameTracker) {
        this.gameTracker = gameTracker;
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
        return ticTacToeHtml.generateBoard(playMove(request, gameNumber), gameNumber(request), HumanVsHuman);
    }

    private Board playMove(HttpRequest request, int gameNumber) {
        Board nextBoard = currentBoard(gameNumber).placeMark(new MoveParser(request.body()).move());
        gameTracker.updateGameState(nextBoard, gameNumber);
        return nextBoard;
    }

    private Board currentBoard(int gameNumber) {
        return gameTracker.boardForNumber(gameNumber);
    }

    private int gameNumber(HttpRequest request) {
        return gameNumberParser.parse(request.requestLine().uri());
    }
}