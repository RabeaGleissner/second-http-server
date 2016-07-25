package de.rabea.controller.tictactoe;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameNumberParserTest {

    @Test
    public void returnsGameNumber() {
        GameNumberParser parser = new GameNumberParser();
        assertEquals(4, parser.parse("http://localhost:5000/ttt-hvh?game-number=4"));
    }

    @Test
    public void returnsDoubleDigitGameNumber() {
        GameNumberParser parser = new GameNumberParser();
        assertEquals(40, parser.parse("http://localhost:5000/ttt-hvh?game-number=40"));
    }
}