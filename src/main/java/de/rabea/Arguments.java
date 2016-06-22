package de.rabea;

import java.util.HashMap;

public class Arguments {

    public HashMap<String, String> parse(String[] commandLineArguments) {
        HashMap<String, String> parsedArguments = new HashMap<>();
        if (commandLineArguments.length > 0 && commandLineArguments[0].equals("-p")) {
            parsedArguments.put("port", commandLineArguments[1]);
        } else {
            parsedArguments.put("port", "5000");
        }

        parsedArguments.put("directory", "PUBLIC_DIR");
        return parsedArguments;
    }
}
