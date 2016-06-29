package de.rabea;

import de.rabea.request.RequestLine;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Logger {
    private List<RequestLine> requests;

    public Logger() {
        requests = new ArrayList<>();
    }

    public void log(RequestLine requestLine) {
       requests.add(requestLine);
    }

    public byte[] getLogs() {
        String logs = logsAsString();
        return logs.getBytes();
    }

    public String logsAsString() {
        return requests.stream().map(requestLine -> requestLine.asString()).collect(Collectors.joining(", "));
    }
}
