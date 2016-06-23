package de.rabea.communication;

import java.util.*;

public class RequestParser {

    public Map<String, String> parseHeaders(String request) {
        List<String> headers = getHeaders(request.split("\n|: "));
        Map<String, String> parsedHeaders = new HashMap<>();

        for (int i = 0; i < headers.size(); i += 2) {
            parsedHeaders.put(headers.get(i), headers.get(i + 1));
        }

        return parsedHeaders;
    }

    private List<String> getHeaders(String[] requestLines) {
        List<String> lines = new ArrayList<>();
        lines.addAll(Arrays.asList(requestLines));
        lines.remove(0);
        return removeBody(lines);
    }

    private List<String> removeBody(List<String> lines) {
        if (lines.contains("")) {
            lines.subList(lines.indexOf(""), lines.size()).clear();
        }
        return lines;
    }

    public String getBody(String rawRequest) {
        String[] headAndBody = rawRequest.split("\n\n");
        return headAndBody[1];
    }

    public int contentLength(String request) {
        String[] lines = request.trim().split("\n");
        for (String line : lines) {
            Integer length = length(line);
            if (length != null) return length;
        }
        return 0;
    }

    private Integer length(String line) {
        String[] words = line.split(" ");
        if (words[0].equals("Content-Length:")) {
            return Integer.parseInt(words[1]);
        }
        return null;
    }

}
