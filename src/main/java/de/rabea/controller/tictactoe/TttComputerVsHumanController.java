package de.rabea.controller.tictactoe;

import de.rabea.Controller;
import de.rabea.controller.tictactoe.html.BoardHtml;
import de.rabea.controller.tictactoe.html.TicTacToeHtmlGenerator;
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

    private GameTracker gameTracker;
    private final GameNumberParser gameNumberParser = new GameNumberParser();

    public TttComputerVsHumanController(GameTracker gameTracker) {
       this.gameTracker = gameTracker;
    }

    @Override
    public HttpResponse doGet(HttpRequest request) {
        int gameNumber = gameNumber(request);
        Board board = currentBoard(gameNumber);
        if (!board.gameOver()) {
            return boardHtml(request, playComputerMove(gameNumber, board));
        }
        return boardHtml(request, board);
    }

    @Override
    public HttpResponse doPost(HttpRequest request) {
        int gameNumber = gameNumber(request);
        playHumanMove(request, gameNumber);
        return new HttpResponse(REDIRECT,
                new RedirectResponseHeader("http://localhost:5000/ttt-cvh?game-number=" + gameNumber));
    }

    private void playHumanMove(HttpRequest request, int gameNumber) {
        Board board = currentBoard(gameNumber);
        Board nextBoard = board.placeMark(new MoveParser(request.body()).move());
        gameTracker.updateGameState(nextBoard, gameNumber);
    }

    private Board playComputerMove(int gameNumber, Board board) {
        Board nextBoard = board.placeMark(new UnbeatableComputerPlayer(O).getMove(board));
        gameTracker.updateGameState(nextBoard, gameNumber);
        return nextBoard;
    }

    private HttpResponse boardHtml(HttpRequest request, Board board) {
        int gameNumber = gameNumber(request);
        String html = new TicTacToeHtmlGenerator(new BoardHtml(board, ComputerVsHuman, gameNumber)).generate();
        return new HttpResponse(OK, html.getBytes());
    }

    private Board currentBoard(int gameNumber) {
        return gameTracker.boardForNumber(gameNumber);
    }

    private int gameNumber(HttpRequest request) {
        return gameNumberParser.parse(request.requestLine().uri());
    }
}
