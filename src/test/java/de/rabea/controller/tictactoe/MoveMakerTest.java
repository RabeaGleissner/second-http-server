package de.rabea.controller.tictactoe;

import de.rabea.game.Board;
import de.rabea.request.HttpRequest;
import de.rabea.request.HttpVerb;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MoveMakerTest {
    private GameTracker gameTracker;

    @Before
    public void setup() {
        gameTracker = new GameTracker();
    }

    @Test
    public void makesMoveForComputer() {
        MoveMaker moveMaker = new MoveMaker(gameTracker);
        Board board = moveMaker.playComputerMove(1, new Board(3));
        assertEquals(8, board.emptyCells().size());
    }

    @Test
    public void makesMoveForHuman() {
        MoveMaker moveMaker = new MoveMaker(gameTracker);
        Board board = moveMaker.playHumanMove(new HttpRequest(HttpVerb.GET, "/ttt-game", "move=1"), 1);
        assertEquals(8, board.emptyCells().size());
    }
}