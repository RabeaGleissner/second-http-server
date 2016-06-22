package de.rabea;

import java.util.HashMap;

public class Arguments {

    public HashMap<String, String> parse(String[] commandLineArguments) {
        HashMap<String, String> parsedArguments = new HashMap<>();

        if (commandLineArguments.length == 0){
            parsedArguments = addDefaults(parsedArguments);
        }

        if (commandLineArguments.length >= 2) {
            parsedArguments = parseFirstArgument(commandLineArguments, parsedArguments);
        }

        if (commandLineArguments.length == 4) {
            parsedArguments = parseSecondArgument(commandLineArguments, parsedArguments);
        }
        return parsedArguments;
    }

    private HashMap<String, String> parseSecondArgument(String[] commandLineArguments, HashMap<String, String> parsedArguments) {
        if (commandLineArguments[2].equals("-d")) {
            parsedArguments.put("directory", commandLineArguments[1]);
        } else {
            parsedArguments.put("port", commandLineArguments[3]);
        }
        return parsedArguments;
    }

    private HashMap<String, String> parseFirstArgument(String[] commandLineArguments, HashMap<String, String> parsedArguments) {
        if (commandLineArguments[0].equals("-p")) {
            parsedArguments.put("port", commandLineArguments[1]);
        } else {
            parsedArguments.put("port", "5000");
        }

        if (commandLineArguments[0].equals("-d")) {
            parsedArguments.put("directory", commandLineArguments[1]);
        } else {
            parsedArguments.put("directory", "PUBLIC_DIR");
        }
        return parsedArguments;
    }

    private HashMap<String, String> addDefaults(HashMap<String, String> parsedArguments) {
        parsedArguments.put("port", "5000");
        parsedArguments.put("directory", "PUBLIC_DIR");
        return parsedArguments;
    }
}
