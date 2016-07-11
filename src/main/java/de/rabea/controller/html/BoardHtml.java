package de.rabea.controller.html;

import de.rabea.game.Mark;

import static de.rabea.game.Mark.*;

public class BoardHtml {

    private Mark[] cells;

    public BoardHtml(Mark[] cells) {
        this.cells = cells;
    }

    public String generate() {
        String html = createList();
        return html;
    }

    private String createList() {
        String list = "<ul>";
        for (int i = 0; i < cells.length; i++)  {
           list += "<li>" +  cell(cells[i], i) + "</li>";
        }
        list += "</ul>";
        return list;
    }

    private String cell(Mark cell, int i) {
        if (cell == EMPTY) {
            return "<form class=\"cell-form\" method=\"post\" action=\"/move\">\n" +
                    "<input class=\"hidden\" type=\"hidden\" name=\"position\" value="+ i +">\n" +
                    "<button class='cell' type=\"submit\"><div class='empty'>&nbsp;</div></button>\n" +
                    "</form>";
        }
        return "<div class='cell full'>"+ cell +"</div>";
    }
}
