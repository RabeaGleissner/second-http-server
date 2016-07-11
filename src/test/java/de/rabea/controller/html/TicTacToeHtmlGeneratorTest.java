package de.rabea.controller.html;

import de.rabea.game.Mark;
import org.junit.Test;

import static de.rabea.game.Mark.*;
import static org.junit.Assert.*;

public class TicTacToeHtmlGeneratorTest {

    @Test
    public void createsHtmlPage() {
        Mark[] cells = {X, O, EMPTY};
        BoardHtml boardHtml = new BoardHtml(cells);
        TicTacToeHtmlGenerator generator = new TicTacToeHtmlGenerator(boardHtml);
        String html = generator.generate();
        assertTrue(html.contains("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "  <meta charset=\"UTF-8\">\n" +
                "  <title>Tic Tac Toe</title>\n" +
                "  <link rel=\"stylesheet\" href=\"/styles.css\">" +
                "</head>\n"));
    }
}