package de.rabea.response.head;

import de.rabea.request.HttpVerb;
import de.rabea.response.ResponseHeader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OptionsResponseHeader implements ResponseHeader {

    private final List<HttpVerb> httpVerbs;

    public OptionsResponseHeader(HttpVerb...verb) {
        this.httpVerbs = new ArrayList<>(Arrays.asList(verb));
    }

    @Override
    public String create() {
        String allowedMethods = httpVerbs.stream().map(HttpVerb::toString).collect(Collectors.joining(","));
        return "Allow: " + allowedMethods;
    }
}