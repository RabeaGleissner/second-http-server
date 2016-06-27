package de.rabea.response;

import de.rabea.request.HttpVerb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static de.rabea.request.HttpVerb.*;

public class OptionsResponseHeader implements ResponseHeader {

    private final Map<HttpVerb, HttpResponse> responsesForMethods;

    public OptionsResponseHeader(Map<HttpVerb, HttpResponse> responsesForMethods) {
        this.responsesForMethods = responsesForMethods;
    }

    @Override
    public String create() {
        List<HttpVerb> verbs = new ArrayList<>(responsesForMethods.keySet());
        String stringBuilder = "";
        for (HttpVerb verb : verbs) {
            stringBuilder += verb;
            stringBuilder += ",";
        }
        return "Allow: " + stringBuilder + OPTIONS;
    }
}