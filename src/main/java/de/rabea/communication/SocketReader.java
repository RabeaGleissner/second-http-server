package de.rabea.communication;

import de.rabea.exceptions.BufferedReaderException;
import de.rabea.request.HttpRequest;
import de.rabea.request.RequestLine;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

public class SocketReader {

    private final BufferedReader reader;
    private final RequestParser requestParser;

    public SocketReader(BufferedReader bufferedReader) {
        this.reader = bufferedReader;
        this.requestParser = new RequestParser();
    }

    public HttpRequest read() {
        String rawRequest = readRequest();
        System.out.println("rawRequest = " + rawRequest);

        RequestLine requestLine = new RequestLine(rawRequest);
        Map<String, String> requestHeader = requestParser.parseHeaders(rawRequest);
        String requestBody = requestParser.getBody(rawRequest);

        return new HttpRequest(requestLine, requestHeader, requestBody);
    }

    private String readRequest() {
        try {
            return readLines();
        } catch (IOException e) {
            throw new BufferedReaderException("BufferedReader could not read " + e.getMessage());
        }
    }

    private String readLines() throws IOException {
        String requestBuilder = "";
        String line;
        while ((line = reader.readLine()) != null) {
            requestBuilder += (line + "\n");
            if (isEmptyLine(line)) {
                requestBuilder += readBody(requestBuilder);
                break;
            }
        }
        return requestBuilder;
    }

    private String readBody(String requestBuilder) throws IOException {
        String charAccumulator = "";
        int length = requestParser.contentLength(requestBuilder);

        for (int i = 0; i < length; i++) {
            charAccumulator = charAccumulator + ((char) reader.read());
        }
        return charAccumulator;
    }

    private boolean isEmptyLine(String line) {
        return line.equals("");
    }
}
