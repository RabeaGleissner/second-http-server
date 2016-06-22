package de.rabea;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class ArgumentsTest {

    Arguments arguments;

    @Before
    public void setup() {
        arguments = new Arguments();
    }

    @Test
    public void returnsDefaultArguments() {
        String[] commandLineArguments = {};
        HashMap<String, String> parsedArguments = arguments.parse(commandLineArguments);
        assertEquals(parsedArguments.get("port"), "5000");
        assertEquals(parsedArguments.get("directory"), "PUBLIC_DIR");
    }

    @Test
    public void returnsPortAsSpecifiedAndDefaultDirectory() {
        String[] commandLineArguments = {"-p", "1234"};
        HashMap<String, String> parsedArguments = arguments.parse(commandLineArguments);
        assertEquals("1234", parsedArguments.get("port"));
        assertEquals( "PUBLIC_DIR", parsedArguments.get("directory"));
    }

    @Test
    public void returnsDirAsSpecifiedAndDefaultPort() {
        String[] commandLineArguments = {"-d", "NEW_DIR"};
        HashMap<String, String> parsedArguments = arguments.parse(commandLineArguments);
        assertEquals("5000", parsedArguments.get("port"));
        assertEquals("NEW_DIR", parsedArguments.get("directory"));
    }

    @Test
    public void returnsGivenDirectoryAndPort() {
        String[] commandLineArguments = {"-d", "NEW_DIR", "-p", "1234"};
        HashMap<String, String> parsedArguments = arguments.parse(commandLineArguments);
        assertEquals("1234", parsedArguments.get("port"));
        assertEquals("NEW_DIR", parsedArguments.get("directory"));
    }

    @Test
    public void returnsGivenPortAndDirectory() {
        String[] commandLineArguments = {"-p", "1111", "-d", "DIR"};
        HashMap<String, String> parsedArguments = arguments.parse(commandLineArguments);
        assertEquals("1111", parsedArguments.get("port"));
        assertEquals("DIR", parsedArguments.get("directory"));
    }
}