package de.rabea.controller.html;

import de.rabea.game.Board;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class BoardHtmlTest {

    @Test
    public void generatesHtmlForUnfinishedGameState() {
        Board board = new Board(3);
        Board newBoard = board.placeMark(1);
        BoardHtml boardHtml = new BoardHtml(newBoard);
        String html = boardHtml.generate();
        assertTrue(html.contains("<div class='cell full'>X</div>"));
        assertTrue(html.contains("<form class=\"cell-form\" method=\"post\" action=\"/ttt-game\">\n" +
                "<input class=\"hidden\" type=\"hidden\" name=\"position\" value=5>\n" +
                "<button class='cell' type=\"submit\"><div class='empty'>&nbsp;</div></button>\n" +
                "</form>"));
    }

    @Test
    public void generatesHtmlForGameOverBoard() {
        GameOverBoard board = new GameOverBoard();
        BoardHtml boardHtml = new BoardHtml(board);
        String html = boardHtml.generate();
        assertTrue(html.contains("<div class='board disabled'></div>"));
    }

    @Test
    public void addsGameOverMessageForGameOverBoardState() {
        GameOverBoard board = new GameOverBoard();
        BoardHtml boardHtml = new BoardHtml(board);
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