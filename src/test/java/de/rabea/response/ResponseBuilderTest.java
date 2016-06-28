package de.rabea.response;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static de.rabea.response.head.StatusLine.OK;

public class ResponseBuilderTest {

    @Test(expected = ResponseBuilder.OutPutStreamWriterException.class)
    public void throwsException() throws IOException {
        ResponseBuilder responseBuilder = new ResponseBuilder(OK, new byte[0]);
        ByteArrayOutputStreamDummy stream = new ByteArrayOutputStreamDummy();
        responseBuilder.create(stream);
    }

    private class ByteArrayOutputStreamDummy extends ByteArrayOutputStream {

        public void write(byte b[]) throws IOException {
            throw new IOException();
        }
    }
}