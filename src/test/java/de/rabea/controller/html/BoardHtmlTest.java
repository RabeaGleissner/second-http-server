package de.rabea.controller.html;

import de.rabea.game.Board;
import org.junit.Test;

import static de.rabea.game.Mark.X;
import static org.junit.Assert.assertTrue;

public class BoardHtmlTest {

    @Test
    public void generatesHtmlForCurrentGameState() {
        Board board = new Board(3);
        Board newBoard = board.placeMark(1, X);
        BoardHtml boardHtml = new BoardHtml(newBoard.cells());
        String html = boardHtml.generate();
        assertTrue(html.contains("<li><div class='cell full'>X</div></li>"));
        assertTrue(html.contains("<li><form class=\"cell-form\" method=\"post\" action=\"/move\">\n" +
                "<input class=\"hidden\" type=\"hidden\" name=\"position\" value=0>\n" +
                "<button class='cell' type=\"submit\"><div class='empty'>&nbsp;</div></button>\n" +
                "</form></li>"));
    }
}