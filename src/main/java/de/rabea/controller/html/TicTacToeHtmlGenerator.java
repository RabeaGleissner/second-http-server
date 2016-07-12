package de.rabea.controller.html;

public class TicTacToeHtmlGenerator {

    private BoardHtml boardHtml;

    public TicTacToeHtmlGenerator(BoardHtml boardHtml) {
        this.boardHtml = boardHtml;
    }

    public String generate() {
        return header() + boardHtml.generate() + end();
    }

    private String header() {
        return "<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                head() +
                "<body>" +
                "<h1>Tic Tac Toe</h1>";
    }

    private String end() {
        return "</body>" +
                "</html>";
    }

    private String head() {
        return "<head>" +
                "  <meta charset=\"UTF-8\">" +
                "  <title>Tic Tac Toe</title>" +
                "  <link rel=\"stylesheet\" href=\"/styles.css\">" +
                "</head>";
    }
}
