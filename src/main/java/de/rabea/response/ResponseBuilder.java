package de.rabea.response;

import de.rabea.response.head.EmptyResponseHeader;
import de.rabea.response.head.StatusLine;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ResponseBuilder {

    private final StatusLine statusLine;
    private final byte[] body;
    private final ResponseHeader responseHeader;
    private final String PROTOCOL = "HTTP/1.1";

    public ResponseBuilder(StatusLine statusLine, ResponseHeader responseHeader) {
        this.statusLine = statusLine;
        this.responseHeader = responseHeader;
        this.body = new byte[0];
    }

    public ResponseBuilder(StatusLine statusLine, byte[] body) {
        this.statusLine = statusLine;
        this.responseHeader = new EmptyResponseHeader();
        this.body = body;
    }

    public byte[] create(ByteArrayOutputStream out) {
        try {
            return combinedHeadAndBody(status() + responseHeader.create(), body, out);
        } catch (IOException e) {
            throw new OutPutStreamWriterException();
        }
    }

    public byte[] combinedHeadAndBody(String head, byte[] body, ByteArrayOutputStream out) throws IOException {
        out.write(head.getBytes());
        out.write("\n".getBytes());
        out.write(body);
        return out.toByteArray();
    }

    public class OutPutStreamWriterException extends RuntimeException {

       public OutPutStreamWriterException() {
           super("Apologies, something went wrong with writing to the ByteArrayOutputStream");
        }
    }

    private String status() {
        return PROTOCOL + " " + statusLine.printable() + "\n";
    }

}
