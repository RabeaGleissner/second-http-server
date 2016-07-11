package de.rabea.controller.html;

public class TicTacToeHtmlGenerator {

    private BoardHtml boardHtml;

    public TicTacToeHtmlGenerator(BoardHtml boardHtml) {
        this.boardHtml = boardHtml;
    }

    public String generate() {
        String html = head() + boardHtml.generate() + end();
        return html;
    }

    private String head() {
        return "<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "  <meta charset=\"UTF-8\">" +
                "  <title>Tic Tac Toe</title>" +
                "  <link rel=\"stylesheet\" href=\"/styles.css\">" +
                "</head>" +
                "<body>";
    }

    private String end() {
        return "</body>" +
                "</html>";
    }
}
