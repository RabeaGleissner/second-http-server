package de.rabea.response.head;

public enum StatusLine {
    NOT_FOUND("404 Not Found"),
    SERVER_ERROR("500 Internal Server Error"),
    NOT_ALLOWED("405 Method Not Allowed"),
    TEAPOT("418 I'm a teapot"),
    REDIRECT("302 Found"),
    OK("200 OK");

    private final String status;

    StatusLine(String status){
        this.status = status;
    }

    public String printable() {
        return status;
    }
}
