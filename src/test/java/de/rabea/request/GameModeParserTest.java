package de.rabea.request;

import org.junit.Test;

import static de.rabea.game.GameMode.HumanVsHuman;
import static org.junit.Assert.assertEquals;

public class GameModeParserTest {

    @Test
    public void extractsTheGameModeFromBodyContent() {
        GameModeParser parser = new GameModeParser("mode=HumanVsHuman");
        assertEquals(HumanVsHuman, parser.mode());
    }
}