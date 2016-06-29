package de.rabea.response.head;

import de.rabea.response.ResponseHeader;

public class AuthenticateResponseHeader implements ResponseHeader {

    @Override
    public String create() {
        return "WWW-Authenticate: Basic realm=\"secondServer\"";
    }
}
