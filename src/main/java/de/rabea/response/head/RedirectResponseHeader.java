package de.rabea.response.head;

import de.rabea.response.ResponseHeader;

public class RedirectResponseHeader implements ResponseHeader {

    private String newLocation;

    public RedirectResponseHeader(String newLocation) {
        this.newLocation = newLocation;
    }

    @Override
    public String create() {
        return "Location: " + newLocation;
    }
}
