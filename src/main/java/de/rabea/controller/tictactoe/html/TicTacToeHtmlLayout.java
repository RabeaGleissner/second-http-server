package de.rabea.controller.tictactoe.html;

public class TicTacToeHtmlLayout {

    private InnerHtml innerHtml;

    public TicTacToeHtmlLayout(InnerHtml innerHtml) {
        this.innerHtml = innerHtml;
    }

    public String generate() {
        return header() + innerHtml.generate() + end();
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
