package de.rabea.response;

public class EmptyResponseHeader implements ResponseHeader {

    @Override
    public String create() {
        return "";
    }
}
