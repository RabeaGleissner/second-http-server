package de.rabea.controller;

import de.rabea.response.ResponseHeader;

public class ContentTypeHeader implements ResponseHeader {

    private String type;

    public ContentTypeHeader(String type) {
        this.type = type;
    }

    @Override
    public String create() {
        return "Content-Type: " + type;
    }
}
