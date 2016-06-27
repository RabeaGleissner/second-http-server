package de.rabea.request;

public enum HttpVerb {
    GET,
    POST,
    PUT,
    OPTIONS,
    HEAD,
    DELETE,
    PATCH;

    public static HttpVerb convert(String word) {
        for (HttpVerb verb : HttpVerb.values()) {
            if (word.equals(verb.toString())) {
                return verb;
            }
        }
        throw new UnknownHttpVerbException();
    }

    public static class UnknownHttpVerbException extends RuntimeException {
        public UnknownHttpVerbException() {
            super("The verb to be converted was not a valid HTTP Verb. Please pass in a valid verb.");
        }
    }
}
