package de.rabea.controller.tictactoe;

import de.rabea.game.Board;
import de.rabea.game.UnbeatableComputerPlayer;
import de.rabea.request.HttpRequest;
import de.rabea.request.MoveParser;

import static de.rabea.game.Mark.*;

public class MoveMaker {

    private GameTracker gameTracker;

    public MoveMaker(GameTracker gameTracker) {
        this.gameTracker = gameTracker;
    }

    public Board playComputerMove(int gameNumber, Board board) {
        Board nextBoard = board.placeMark(new UnbeatableComputerPlayer(O).getMove(board));
        gameTracker.updateGameState(nextBoard, gameNumber);
        return nextBoard;
    }

    public Board playHumanMove(HttpRequest request, int gameNumber) {
        Board nextBoard = currentBoard(gameNumber).placeMark(new MoveParser(request.body()).move());
        gameTracker.updateGameState(nextBoard, gameNumber);
        return nextBoard;
    }

    private Board currentBoard(int gameNumber) {
        return gameTracker.boardForNumber(gameNumber);
    }
}
