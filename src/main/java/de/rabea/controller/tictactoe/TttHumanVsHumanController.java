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

    private final GameNumberParser gameNumberParser = new GameNumberParser();
    private GameTracker gameTracker;

    public TttHumanVsHumanController(GameTracker gameTracker) {
        this.gameTracker = gameTracker;
    }

    @Override
    public HttpResponse doGet(HttpRequest request) {
        int gameNumber = gameNumber(request);
        Board board = gameTracker.boardForNumber(gameNumber);
        gameTracker.updateGameState(board, gameNumber);
        return boardHtml(request, board);
    }

    @Override
    public HttpResponse doPost(HttpRequest request) {
        int gameNumber = gameNumber(request);
        Board board = gameTracker.boardForNumber(gameNumber);
        Board nextBoard = board.placeMark(new MoveParser(request.body()).move());
        gameTracker.updateGameState(nextBoard, gameNumber);
        return boardHtml(request, nextBoard);
    }

    private HttpResponse boardHtml(HttpRequest request, Board board) {
        int gameNumber = gameNumber(request) ;
        String html = new TicTacToeHtmlGenerator(new BoardHtml(board, HumanVsHuman, gameNumber)).generate();
        return new HttpResponse(OK, html.getBytes());
    }

    private int gameNumber(HttpRequest request) {
        return gameNumberParser.parse(request.requestLine().uri());
    }
}