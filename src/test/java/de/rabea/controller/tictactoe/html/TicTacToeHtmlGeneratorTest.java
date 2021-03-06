package de.rabea.controller.tictactoe.html;

import de.rabea.game.Board;
import org.junit.Test;

import static de.rabea.game.GameMode.HumanVsHuman;
import static org.junit.Assert.assertTrue;

public class TicTacToeHtmlGeneratorTest {

    @Test
    public void createsHtmlPage() {
        BoardHtml boardHtml = new BoardHtml(new Board(3), HumanVsHuman);
        TicTacToeHtmlGenerator generator = new TicTacToeHtmlGenerator(boardHtml);
        String html = generator.generate();
        assertTrue(html.contains("<!DOCTYPE html><html lang=\"en\"><head>  " +
                "<meta charset=\"UTF-8\">" +
                "  <title>Tic Tac Toe</title>" +
                "  <link rel=\"stylesheet\"" +
                " href=\"/styles.css\">" +
                "</head><body>" +
                "<h1>Tic Tac Toe</h1>"));
    }
}