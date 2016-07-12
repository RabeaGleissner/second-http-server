package de.rabea.request;

public class BodyParser {

    private String fullBodyContent;

    public BodyParser(String fullBodyContent) {
        this.fullBodyContent = fullBodyContent;
    }

    public String content() {
        int index = fullBodyContent.indexOf("=");
        return fullBodyContent.substring(index + 1);
    }
}
