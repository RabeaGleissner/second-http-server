package de.rabea.controller.tictactoe;

import de.rabea.game.Board;

import java.util.HashMap;
import java.util.Map;

public class GameTracker {

    public Map<Integer, Board> games = new HashMap<>();

    public void updateGameState(Board board, int gameNumber) {
       games.put(gameNumber, board) ;
    }

    public Board boardFor(int gameNumber) {
        if (gameExists(gameNumber)) {
            return games.get(gameNumber);
        }
        return new Board(3);
    }

    private boolean gameExists(int gameNumber) {
        return games.containsKey(gameNumber);
    }
}
