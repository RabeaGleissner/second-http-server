package de.rabea.controller.html;

import org.junit.Test;

import static org.junit.Assert.*;

public class MenuHtmlTest {

    @Test
    public void generatesHtmlForGameOptionsMenu() {
        MenuHtml menuHtml = new MenuHtml();
        String html = menuHtml.generate();
        assertTrue(html.contains("<h2 class='menu'>Please choose a game option:</h2><"));
        assertTrue(html.contains("<p>" +
                "<input id=human-vs-human class=\"option-choice\" type=\"radio\" name=\"option\" value=human-vs-human>" +
                "<label for=human-vs-human>Human vs Human</label>" +
                "</p>"));
    }

}