package de.rabea;

import java.util.HashMap;

public class Arguments {

    public HashMap<String, String> parse(String[] commandLineArguments) {
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
        if (firstArgument.equals("-p")) {
            parsedArguments.put("port", commandLineArguments[1]);
            parsedArguments = addDefaultDirectory(parsedArguments);
        }

        if (firstArgument.equals("-d")) {
            parsedArguments.put("directory", commandLineArguments[1]);
            parsedArguments = addDefaultPort(parsedArguments);
        }
        return parsedArguments;
    }

    private HashMap<String, String> parseSecondArgument(String[] commandLineArguments, HashMap<String, String> parsedArguments) {
        String secondArgument = commandLineArguments[2];
        if (secondArgument.equals("-d")) {
            parsedArguments.put("directory", commandLineArguments[3]);
        }

        if (secondArgument.equals("-p")) {
            parsedArguments.put("port", commandLineArguments[3]);
        }
        return parsedArguments;
    }

    private HashMap<String, String> addDefaultPort(HashMap<String, String> parsedArguments) {
        parsedArguments.put("port", "5000");
        return parsedArguments;
    }

    private HashMap<String, String> addDefaultDirectory(HashMap<String, String> parsedArguments) {
        parsedArguments.put("directory", "PUBLIC_DIR");
        return parsedArguments;
    }
}
