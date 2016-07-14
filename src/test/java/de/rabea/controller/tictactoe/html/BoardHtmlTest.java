package de.rabea.controller.tictactoe.html;

import de.rabea.game.Board;
import org.junit.Test;

import static de.rabea.game.GameMode.*;
import static org.junit.Assert.assertTrue;

public class BoardHtmlTest {

    @Test
    public void generatesHtmlForUnfinishedHvHGameState() {
        Board board = new Board(3);
        Board newBoard = board.placeMark(1);
        BoardHtml boardHtml = new BoardHtml(newBoard, HumanVsHuman, 1);

        String html = boardHtml.generate();

        assertTrue(html.contains("<div class='cell full'>X</div>"));
        assertTrue(html.contains("<form class=\"cell-form\" method=\"post\" action=\"/ttt-hvh?game-number=1\">\n" +
                "<input class=\"hidden\" type=\"hidden\" name=\"position\" value=5>\n" +
                "<button class='cell' type=\"submit\"><div class='empty'>&nbsp;</div></button>\n" +
                "</form>"));
    }

    @Test
    public void generatesHtmlForUnfinishedHvCGameState() {
        Board board = new Board(3);
        Board newBoard = board.placeMark(1);
        BoardHtml boardHtml = new BoardHtml(newBoard, HumanVsComputer, 1);

        String html = boardHtml.generate();

        assertTrue(html.contains("<form class=\"cell-form\" method=\"post\" action=\"/ttt-hvc?game-number=1\">\n"));
    }

    @Test
    public void generatesHtmlForUnfinishedCvHGameState() {
        Board board = new Board(3);
        Board newBoard = board.placeMark(1);
        BoardHtml boardHtml = new BoardHtml(newBoard, ComputerVsHuman, 1);

        String html = boardHtml.generate();

        assertTrue(html.contains("<form class=\"cell-form\" method=\"post\" action=\"/ttt-cvh?game-number=1\">\n"));
    }

    @Test
    public void generatesHtmlForGameOverBoard() {
        GameOverBoard board = new GameOverBoard();
        BoardHtml boardHtml = new BoardHtml(board, HumanVsHuman, 1);

        String html = boardHtml.generate();

        assertTrue(html.contains("<div class='board disabled'></div>"));
    }

    @Test
    public void addsGameOverMessageForGameOverBoardState() {
        GameOverBoard board = new GameOverBoard();
        BoardHtml boardHtml = new BoardHtml(board, HumanVsHuman, 1);

        String html = boardHtml.generate();

        assertTrue(html.contains("<div class='game-end-message'>"));
        assertTrue(html.contains("Game over!"));
    }

    private class GameOverBoard extends Board {
        @Override
        public boolean gameOver() {
            return true;
        }
    }
}