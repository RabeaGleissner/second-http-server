package de.rabea.controller.tictactoe;

import de.rabea.ParameterParser;

public class GameNumberParser {

    public int parse(String route) {
        String parameters = new ParameterParser().parse(route);
        int index = parameters.indexOf("=");
        String sub = parameters.substring((index + 2), (index + 3));
        return Integer.parseInt(parameters.substring((index + 2), (index + 3)));
    }
}