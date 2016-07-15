package de.rabea.controller.tictactoe;

import de.rabea.game.Board;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTrackerTest {

    @Test
    public void updatesSavedGameState() {
        int gameNumber = 1;
        GameTracker tracker = new GameTracker();
        Board newBoard = new Board(3);
        tracker.games.put(gameNumber, newBoard);
        Board nextBoard = newBoard.placeMark(2);

        tracker.updateGameState(nextBoard, gameNumber);

        assertEquals(8, tracker.boardForNumber(gameNumber).emptyCells().size());
    }

    @Test
    public void returnsNewBoardIfBoardDoesNotExistForGameNumber() {
        int gameNumber = 1;
        GameTracker tracker = new GameTracker();
        assertEquals(9, tracker.boardForNumber(gameNumber).emptyCells().size());
    }

    @Test
    public void returnsExistingBoard() {
        int gameNumber = 1;
        int move = 3;
        GameTracker tracker = new GameTracker();
        Board board = new Board(3);
        Board nextBoard = board.placeMark(move);
        tracker.games.put(gameNumber, nextBoard);

        assertFalse(tracker.boardForNumber(gameNumber).isPositionAvailable(move));
    }
}