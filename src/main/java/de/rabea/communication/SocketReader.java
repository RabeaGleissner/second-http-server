package de.rabea.communication;

import de.rabea.exceptions.SocketException;
import de.rabea.request.HttpRequest;
import de.rabea.request.RequestLine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Map;

public class SocketReader {

    private final Socket socket;
    private final BufferedReader reader;
    private final RequestParser requestParser;

    public SocketReader(Socket socket) {
        this.socket = socket;
        this.reader = createReader();
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
        String request = "";
        try {
            request = readLines();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return request;
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

    private BufferedReader createReader() {
        try {
            return new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            throw new SocketException("Could not get InputStream" + e.getMessage());
        }
    }
}
