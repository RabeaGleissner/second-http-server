package de.rabea;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class ArgumentsTest {

    @Test
    public void returnsDefaultArguments() {
        Arguments arguments = new Arguments();
        String[] commandLineArguments = {};
        HashMap<String, String> parsedArguments = arguments.parse(commandLineArguments);
        assertEquals(parsedArguments.get("port"), "5000");
        assertEquals(parsedArguments.get("directory"), "PUBLIC_DIR");
    }

    @Test
    public void returnsPortAsSpecifiedAndDefaultDirectory() {
        Arguments arguments = new Arguments();
        String[] commandLineArguments = {"-p", "1234"};
        HashMap<String, String> parsedArguments = arguments.parse(commandLineArguments);
        assertEquals("1234", parsedArguments.get("port"));
        assertEquals( "PUBLIC_DIR", parsedArguments.get("directory"));
    }
}