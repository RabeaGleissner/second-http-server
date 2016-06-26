package de.rabea;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BufferedReaderStub extends BufferedReader {
    public BufferedReaderStub() {
        super(new BufferedReader(new InputStreamReader(new SocketStub("").getInputStream())));
    }
}
