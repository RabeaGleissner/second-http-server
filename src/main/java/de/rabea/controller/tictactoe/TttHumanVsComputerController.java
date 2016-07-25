package de.rabea.controller.tictactoe;

import de.rabea.Controller;
import de.rabea.controller.tictactoe.html.BoardHtml;
import de.rabea.controller.tictactoe.html.TicTacToeHtmlGenerator;
import de.rabea.game.Board;
import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;

import static de.rabea.game.GameMode.HumanVsComputer;
import static de.rabea.response.head.StatusLine.OK;

public class TttHumanVsComputerController extends Controller {

    private GameTracker gameTracker;
    private final GameNumberParser gameNumberParser = new GameNumberParser();
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
        return boardHtml(request, board);
    }

    private Board currentBoard(int gameNumber) {
        return gameTracker.boardForNumber(gameNumber);
    }

    @Override
    public HttpResponse doPost(HttpRequest request) {
        int gameNumber = gameNumber(request);
        Board nextBoard = moveMaker.playHumanMove(request, gameNumber);
        if (!nextBoard.gameOver()) {
            return boardHtml(request, moveMaker.playComputerMove(gameNumber, nextBoard));
        }
        return boardHtml(request, nextBoard);
    }

    private HttpResponse boardHtml(HttpRequest request, Board board) {
        int gameNumber = gameNumber(request);
        String html = new TicTacToeHtmlGenerator(new BoardHtml(board, HumanVsComputer, gameNumber)).generate();
        return new HttpResponse(OK, html.getBytes());
    }

    private int gameNumber(HttpRequest request) {
        return gameNumberParser.parse(request.requestLine().uri());
    }
}
