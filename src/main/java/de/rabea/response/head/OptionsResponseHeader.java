package de.rabea.response.head;

import de.rabea.request.HttpVerb;
import de.rabea.response.ResponseCreator;
import de.rabea.response.ResponseHeader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static de.rabea.request.HttpVerb.OPTIONS;

public class OptionsResponseHeader implements ResponseHeader {

    private final Map<HttpVerb, ResponseCreator> responsesForMethods;

    public OptionsResponseHeader(Map<HttpVerb, ResponseCreator> responsesForMethods) {
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