package de.rabea;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArgumentsTest {

    @Test
    public void returnsDefaultArguments() {
        String[] commandLineArguments = {};
        Arguments arguments = new Arguments(commandLineArguments);
        assertEquals(5000, arguments.getPort());
        assertEquals("vendor/cob_spec/public", arguments.getDirectory());
    }

    @Test
    public void returnsPortAsSpecifiedAndDefaultDirectory() {
        String[] commandLineArguments = {"-p", "1234"};
        Arguments arguments = new Arguments(commandLineArguments);
        assertEquals(1234, arguments.getPort());
        assertEquals("vendor/cob_spec/public", arguments.getDirectory());
    }

    @Test
    public void returnsDirAsSpecifiedAndDefaultPort() {
        String[] commandLineArguments = {"-d", "NEW_DIR"};
        Arguments arguments = new Arguments(commandLineArguments);
        assertEquals(5000, arguments.getPort());
        assertEquals("NEW_DIR", arguments.getDirectory());
    }

    @Test
    public void returnsGivenDirectoryAndPort() {
        String[] commandLineArguments = {"-d", "NEW_DIR", "-p", "1234"};
        Arguments arguments = new Arguments(commandLineArguments);
        assertEquals(1234, arguments.getPort());
        assertEquals("NEW_DIR", arguments.getDirectory());
    }

    @Test
    public void returnsGivenPortAndDirectory() {
        String[] commandLineArguments = {"-p", "1111", "-d", "DIR"};
        Arguments arguments = new Arguments(commandLineArguments);
        assertEquals(1111, arguments.getPort());
        assertEquals("DIR", arguments.getDirectory());
    }
}