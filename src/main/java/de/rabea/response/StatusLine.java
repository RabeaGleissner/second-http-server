package de.rabea.response;

public enum StatusLine {
    NOT_FOUND("404 Not Found"),
    SERVER_ERROR("500 Internal Server Error"),
    NOT_ALLOWED("405 Method Not Allowed"),
    OK("200 OK");

    private String status;

    StatusLine(String status){
        this.status = status;
    }

    public String printable() {
        return status;
    }
}