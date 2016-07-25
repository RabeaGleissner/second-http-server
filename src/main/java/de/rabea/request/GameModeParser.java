package de.rabea.request;

import de.rabea.game.GameMode;

import static de.rabea.game.GameMode.ComputerVsHuman;
import static de.rabea.game.GameMode.HumanVsComputer;
import static de.rabea.game.GameMode.HumanVsHuman;

public class GameModeParser {

    private String fullBodyContent;

    public GameModeParser(String fullBodyContent) {
        this.fullBodyContent = fullBodyContent;
    }

    public GameMode mode() {
        int index = fullBodyContent.indexOf("=");
        return convertToEnum(fullBodyContent.substring(index + 1));
    }

    private GameMode convertToEnum(String givenGameMode) {
        if (givenGameMode.equals("HumanVsComputer")) {
           return HumanVsComputer;
        } else if (givenGameMode.equals("ComputerVsHuman")) {
            return ComputerVsHuman;
        }
        return HumanVsHuman;
    }
}
