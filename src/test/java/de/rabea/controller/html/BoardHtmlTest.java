package de.rabea.controller.html;

import de.rabea.game.Board;
import de.rabea.game.GameMode;
import org.junit.Test;

import static de.rabea.game.GameMode.ComputerVsHuman;
import static de.rabea.game.GameMode.HumanVsComputer;
import static de.rabea.game.GameMode.HumanVsHuman;
import static org.junit.Assert.assertTrue;

public class BoardHtmlTest {

    @Test
    public void generatesHtmlForUnfinishedHvHGameState() {
        Board board = new Board(3);
        Board newBoard = board.placeMark(1);
        BoardHtml boardHtml = new BoardHtml(newBoard, HumanVsHuman);
        String html = boardHtml.generate();
        assertTrue(html.contains("<div class='cell full'>X</div>"));
        assertTrue(html.contains("<form class=\"cell-form\" method=\"post\" action=\"/ttt-game/hvh\">\n" +
                "<input class=\"hidden\" type=\"hidden\" name=\"position\" value=5>\n" +
                "<button class='cell' type=\"submit\"><div class='empty'>&nbsp;</div></button>\n" +
                "</form>"));
    }

    @Test
    public void generatesHtmlForUnfinishedHvCGameState() {
        Board board = new Board(3);
        Board newBoard = board.placeMark(1);
        BoardHtml boardHtml = new BoardHtml(newBoard, HumanVsComputer);
        String html = boardHtml.generate();
        assertTrue(html.contains("<form class=\"cell-form\" method=\"post\" action=\"/ttt-game/hvc\">\n"));
    }

    @Test
    public void generatesHtmlForUnfinishedCvHGameState() {
        Board board = new Board(3);
        Board newBoard = board.placeMark(1);
        BoardHtml boardHtml = new BoardHtml(newBoard, ComputerVsHuman);
        String html = boardHtml.generate();
        assertTrue(html.contains("<form class=\"cell-form\" method=\"post\" action=\"/ttt-game/cvh\">\n"));
    }

    @Test
    public void generatesHtmlForGameOverBoard() {
        GameOverBoard board = new GameOverBoard();
        BoardHtml boardHtml = new BoardHtml(board, HumanVsHuman);
        String html = boardHtml.generate();
        assertTrue(html.contains("<div class='board disabled'></div>"));
    }

    @Test
    public void addsGameOverMessageForGameOverBoardState() {
        GameOverBoard board = new GameOverBoard();
        BoardHtml boardHtml = new BoardHtml(board, HumanVsHuman);
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