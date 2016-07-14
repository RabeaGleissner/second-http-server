package de.rabea;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParameterParser {

    public String parse(String requestUri) {
        String parsed = "";
        List<String> parameters = split(requestUri);
        for (String parameter : parameters) {
            try {
                parsed += decode(parameter) + "\n\n";
            } catch (UnsupportedEncodingException e) {
                throw new UrlDecodingException();
            }
        }
        return removeLastNewLines(parsed);
    }

    public List<String> split(String requestUri) {
        String paramsWithoutRoute = requestUri.substring(requestUri.indexOf("?") + 1);
        return new ArrayList<>(Arrays.asList(paramsWithoutRoute.split("&")));
    }

    public String decode(String parameter) throws UnsupportedEncodingException {
        return URLDecoder.decode(addSpaceAroundEquals(parameter), "UTF-8");
    }

    private String addSpaceAroundEquals(String parameters) {
        return parameters.replace("=", " = ");
    }

    public class UrlDecodingException extends RuntimeException {
        public UrlDecodingException() {
            super("Apologies, but unfortunately there was a problem with decoding the URL!");
        }
    }

    private String removeLastNewLines(String parsed) {
        return parsed.substring(0, parsed.length() - 2);
    }

}
