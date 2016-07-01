package de.rabea.response.head;

public enum StatusLine {
    NOT_FOUND("404 Not Found"),
    UNAUTHORIZED("401 Unauthorized"),
    NOT_ALLOWED("405 Method Not Allowed"),
    TEAPOT("418 I'm a teapot"),
    REDIRECT("302 Found"),
    NO_CONTENT("204 No Content"),
    OK("200 OK");

    private final String status;

    StatusLine(String status){
        this.status = status;
    }

    public String printable() {
        return status;
    }
}
