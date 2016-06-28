package de.rabea.response.head;

import de.rabea.response.ResponseHeader;

public class EmptyResponseHeader implements ResponseHeader {

    @Override
    public String create() {
        return "";
    }
}
