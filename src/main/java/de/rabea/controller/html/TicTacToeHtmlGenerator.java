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
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "  <meta charset=\"UTF-8\">\n" +
                "  <title>Tic Tac Toe</title>\n" +
                "</head>\n" +
                "<body>";
    }

    private String end() {
        return "</body>\n" +
                "</html>";
    }
}
