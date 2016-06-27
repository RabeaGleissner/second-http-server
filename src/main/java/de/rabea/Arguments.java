package de.rabea;

import java.util.HashMap;

public class Arguments {

    private final int port;
    private final String directory;

    public Arguments(String[] commandLineArguments) {
        this.port = Integer.parseInt(parseArguments(commandLineArguments).get("-p"));
        this.directory = parseArguments(commandLineArguments).get("-d");
    }

    public int getPort() {
        return port;
    }

    public String getDirectory() {
        return directory;
    }

    private HashMap<String, String> parseArguments(String[] commandLineArguments) {
        HashMap<String, String> parsedArguments = new HashMap<>();
        int argumentNumber = commandLineArguments.length;

        if (argumentNumber == 0){
            parsedArguments = addDefaultDirectory(parsedArguments);
            parsedArguments = addDefaultPort(parsedArguments);
        }

        if (argumentNumber >= 2) {
            parsedArguments = parseFirstArgument(commandLineArguments, parsedArguments);
        }

        if (argumentNumber == 4) {
            parsedArguments = parseSecondArgument(commandLineArguments, parsedArguments);
        }
        return parsedArguments;
    }

    private HashMap<String, String> parseFirstArgument(String[] commandLineArguments, HashMap<String, String> parsedArguments) {
        String firstArgument = commandLineArguments[0];
        parsedArguments.put(firstArgument, commandLineArguments[1]);

        if (firstArgument.equals("-p")) {
            parsedArguments = addDefaultDirectory(parsedArguments);
        }

        if (firstArgument.equals("-d")) {
            parsedArguments = addDefaultPort(parsedArguments);
        }
        return parsedArguments;
    }

    private HashMap<String, String> parseSecondArgument(String[] commandLineArguments, HashMap<String, String> parsedArguments) {
        parsedArguments.put(commandLineArguments[2], commandLineArguments[3]);
        return parsedArguments;
    }

    private HashMap<String, String> addDefaultPort(HashMap<String, String> parsedArguments) {
        parsedArguments.put("-p", "5000");
        return parsedArguments;
    }

    private HashMap<String, String> addDefaultDirectory(HashMap<String, String> parsedArguments) {
        parsedArguments.put("-d", "PUBLIC_DIR");
        return parsedArguments;
    }
}
