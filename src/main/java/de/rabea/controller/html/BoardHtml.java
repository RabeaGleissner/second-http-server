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
        String cells = "";
        int dimension = board.getDimension();
        Mark[] currentMarks = board.cells();

        for (int i = 0; i < currentMarks.length; i += dimension)  {
            cells += createRow(dimension, currentMarks, i);
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
        row += "</div>";
        return row;
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