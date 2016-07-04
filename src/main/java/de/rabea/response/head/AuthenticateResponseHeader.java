package de.rabea.response.head;

import de.rabea.response.ResponseHeader;

public class AuthenticateResponseHeader implements ResponseHeader {

    private final String realm;

    public AuthenticateResponseHeader(String realm) {
        this.realm = realm;
    }

    @Override
    public String create() {
        return "WWW-Authenticate: Basic realm=\"" + realm + "\"";
    }
}
