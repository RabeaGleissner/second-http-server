package de.rabea.controller.html;

import de.rabea.game.Board;
import de.rabea.game.Mark;

import static de.rabea.game.Mark.EMPTY;

public class BoardHtml {

    private Board board;
    private final String PLACE_HOLDER = "&nbsp;";

    public BoardHtml(Board board) {
        this.board = board;
    }

    public String generate() {
        String cells = addBoardCssClass();
        int dimension = board.getDimension();
        Mark[] currentMarks = board.cells();

        for (int i = 0; i < currentMarks.length; i += dimension)  {
            cells += createRow(dimension, currentMarks, i);
        }
        cells = addClosingDiv(cells);
        return cells;
    }

    private String addBoardCssClass() {
        String cells;
        if (board.gameOver()) {
            cells = "<div class='board disabled'>";
        } else {
            cells = "<div class='board'>";
        }
        return cells;
    }

    private String createRow(int dimension, Mark[] cells, int index) {
        String row = "<div class='row'>";
        for (int i = 0; i < dimension; i ++) {
            row += "<div class='wrapper'>" +
                    createCell(cells[i + index], i + index) +
                    "</div>";
        }
        row = addClosingDiv(row);
        return row;
    }

    private String addClosingDiv(String html) {
        html += "</div>";
        return html;
    }

    private String createCell(Mark cell, int i) {
        if (cell == EMPTY) {
            return formForEmptyCell(i);
        }
        return displayFullCell(cell);
    }

    private String displayFullCell(Mark cell) {
        return "<div class='cell full'>"+ cell +"</div>";
    }

    private String formForEmptyCell(int i) {
        return "<form class=\"cell-form\" method=\"post\" action=\"/ttt-game\">\n" +
                "<input class=\"hidden\" type=\"hidden\" name=\"position\" value="+ i +">\n" +
                "<button class='cell' type=\"submit\"><div class='empty'>" + PLACE_HOLDER + "</div></button>\n" +
                "</form>";
    }
}