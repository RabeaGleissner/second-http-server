package de.rabea.controller.html;

import de.rabea.game.Board;
import de.rabea.game.GameMode;
import de.rabea.game.Mark;

import static de.rabea.game.GameMode.*;
import static de.rabea.game.Mark.EMPTY;

public class BoardHtml implements InnerHtml {

    private final String PLACE_HOLDER = "&nbsp;";
    private final Board board;
    private GameMode gameMode;
    private final int boardDimension;
    private final Mark[] currentMarks;

    public BoardHtml(Board board, GameMode gameMode) {
        this.board = board;
        this.gameMode = gameMode;
        this.boardDimension = board.getDimension();
        this.currentMarks = board.cells();
    }

    @Override
    public String generate() {
        String cells = addBoardCssClass();
        for (int i = 0; i < currentMarks.length; i += boardDimension)  {
            cells += createRow(boardDimension, currentMarks, i);
        }
        cells = addClosingDiv(cells);
        if (board.gameOver()) {
           cells = addGameOverMessage(cells);
        }
        return cells;
    }

    private String addGameOverMessage(String cells) {
        cells += "<div class='game-end-message'>" +
                "<p>Game over!</p>" +
                "<a class=\"play-again\" href=\"/ttt-menu\">Play again</a>" +
                "</div>";
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
        return "<form class=\"cell-form\" method=\"post\" action=\"/ttt-game/" + convertToUrl(gameMode) + "\">\n" +
                "<input class=\"hidden\" type=\"hidden\" name=\"position\" value="+ i +">\n" +
                "<button class='cell' type=\"submit\"><div class='empty'>" + PLACE_HOLDER + "</div></button>\n" +
                "</form>";
    }

    private String convertToUrl(GameMode gameMode) {
        if (gameMode == HumanVsHuman) {
            return "hvh";
        } else if (gameMode == ComputerVsHuman) {
           return "cvh" ;
        }
        return "hvc";
    }
}