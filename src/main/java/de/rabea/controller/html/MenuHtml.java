package de.rabea.controller.html;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MenuHtml implements InnerHtml {
    Map<String, String> menuOptions;

    public MenuHtml() {
        menuOptions = addMenuOptions();
    }

    private Map<String, String> addMenuOptions() {
        menuOptions = new LinkedHashMap<>();
        menuOptions.put("HumanVsHuman", "Human vs Human");
        menuOptions.put("HumanVsComputer", "Human vs Computer");
        menuOptions.put("ComputerVsHuman", "Computer vs Human");
        return menuOptions;
    }

    @Override
    public String generate() {
        String html = "";
        html += menuPrompt();
        html += createForm();
        return html;
    }

    private String menuPrompt() {
        return "<h2 class='menu'>Please choose a game option:</h2>";
    }

    private String createForm() {
        String form = "";
        form += formOpeningTag();
        form += menuOptions(form);
        form += submitButton();
        form += formClosingTag();
        return form ;
    }

    private String formOpeningTag() {
        return "<form class=\"options-form\" method=\"post\" action=\"/ttt-menu\">";
    }

    private String submitButton() {
        return "<button type=\"submit\">Start game</button>\n";
    }

    private String menuOptions(String form) {
        form += menuOptions.entrySet().stream().map(entry ->
                "<p>" +
                    "<input id=" + entry.getKey() + " class=\"option-choice\" type=\"radio\" name=\"option\" value=" +
                        entry.getKey() + ">" +
                    "<label for=" + entry.getKey() + ">" + entry.getValue() +
                    "</label>" +
                "</p>")
                .collect(Collectors.joining());
        return form;
    }

    private String formClosingTag() {
        return "</form>";
    }
}