package de.rabea.request;

public enum HttpVerb {
    GET,
    POST,
    PUT,
    OPTIONS,
    HEAD,
    DELETE,
    PATCH,
    NONE;

    public static HttpVerb convert(String word) {
        for (HttpVerb verb : HttpVerb.values()) {
            if (word.equals(verb.toString())) {
                return verb;
            }
        }
        return NONE;
    }
}
