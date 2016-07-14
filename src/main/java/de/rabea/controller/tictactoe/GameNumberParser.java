package de.rabea.controller.tictactoe;

import de.rabea.ParameterParser;

public class GameNumberParser {

    public int parse(String route) {
        String parameters = new ParameterParser().parse(route);
        int locationOfNumber = parameters.indexOf("=") + 2;
        return Integer.parseInt(parameters.substring(locationOfNumber));
    }
}