package de.rabea;

import java.util.Map;

public class LinkGenerator {

    public static String generate(Map<String, String> anchorWithLinks) {
        String links = "";
        for (Map.Entry<String, String> entry : anchorWithLinks.entrySet()) {
            links += "<a href=\"" + entry.getValue() + "\">" + entry.getKey() + "</a>";
        }
        return links;
    }
}
