package de.rabea.communication;

import de.rabea.request.HttpRequest;
import de.rabea.request.RequestLine;

import java.util.*;

public class RequestParser {

    public HttpRequest parse(String rawRequest) {
        return new HttpRequest(
                new RequestLine(rawRequest),
                parseHeaders(rawRequest),
                getBody(rawRequest));
    }

    public int contentLength(String request) {
        String[] lines = request.trim().split("\n");
        for (String line : lines) {
            Integer length = length(line);
            if (length != null) return length;
        }
        return 0;
    }

    private Map<String, String> parseHeaders(String request) {
        List<String> headers = getHeaders(request.split("\n|: "));
        Map<String, String> parsedHeaders = new HashMap<>();

        for (int i = 0; i < headers.size(); i += 2) {
            parsedHeaders.put(headers.get(i), headers.get(i + 1));
        }

        return parsedHeaders;
    }

    private String getBody(String rawRequest) {
        if (hasBody(rawRequest)) {
            String[] headAndBody = rawRequest.split("\n\n");
            return headAndBody[1].replaceAll("\uFFFF", "");
        } else {
            return "";
        }
    }

    private boolean hasBody(String request) {
        return contentLength(request) > 0;
    }

    private List<String> getHeaders(String[] linesOfRequest) {
        List<String> lines = new ArrayList<>();
        lines.addAll(Arrays.asList(linesOfRequest));
        lines = removeRequestLine(lines);
        return removeBodyLines(lines);
    }

    private List<String> removeRequestLine(List<String> lines) {
        lines.remove(0);
        return lines;
    }

    private List<String> removeBodyLines(List<String> lines) {
        if (hasEmptyLine(lines)) {
            lines = removeLinesAfterEmptyLine(lines);
        }
        return lines;
    }

    private boolean hasEmptyLine(List<String> lines) {
        return lines.contains("");
    }

    private List<String> removeLinesAfterEmptyLine(List<String> lines) {
        lines.subList(lines.indexOf(""), lines.size()).clear();
        return lines;
    }

    private Integer length(String line) {
        String[] words = line.split(" ");
        if (words[0].equals("Content-Length:")) {
            return Integer.parseInt(words[1]);
        }
        return null;
    }
}
