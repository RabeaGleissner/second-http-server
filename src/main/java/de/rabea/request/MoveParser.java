package de.rabea.request;

public class MoveParser {

    private String fullBodyContent;

    public MoveParser(String fullBodyContent) {
        this.fullBodyContent = fullBodyContent;
    }

    public int move() {
        int index = fullBodyContent.indexOf("=");
        return Integer.parseInt(fullBodyContent.substring(index + 1));
    }
}
