package de.rabea.controller.tictactoe.html;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MenuHtmlTest {

    @Test
    public void generatesHtmlForGameOptionsMenu() {
        MenuHtml menuHtml = new MenuHtml();
        String html = menuHtml.generate();
        assertTrue(html.contains("<h2 class='menu'>Please choose a game option:</h2><"));
        assertTrue(html.contains("<p>" +
                "<input id=HumanVsHuman class=\"option-choice\" type=\"radio\" name=\"option\" value=HumanVsHuman>" +
                "<label for=HumanVsHuman>Human vs Human</label>" +
                "</p>"));
    }
}