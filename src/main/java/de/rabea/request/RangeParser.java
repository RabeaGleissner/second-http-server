package de.rabea.request;

import static java.util.Arrays.copyOfRange;

public class RangeParser {

    private final byte[] text;
    private final String range;
    private int textLength;
    private int rangeCharacterLength;

    public RangeParser(byte[] text, String rangeWithDescription) {
        this.text = text;
        this.range = strip(rangeWithDescription);
        this.textLength = text.length;
        this.rangeCharacterLength = range.length();
    }

    private String strip(String rangeWithDescription) {
        return rangeWithDescription.substring("bytes=".length());
    }

    public byte[] partialContent() {
        return readPartialContent(rangeStart(), rangeEnd());
    }

    private byte[] readPartialContent(int from, int to) {
        return copyOfRange(text, from, to);
    }

    private int rangeStart() {
        if (startIsGiven()) {
            return getStartingPosition();
        }
        return calculatePositionFromEnd();
    }

    private int rangeEnd() {
        if (reverse()) {
            return textLength;
        }
        if (endIsGiven()) {
            return getEndPosition();
        }
        return textLength;
    }

    private int getEndPosition() {
        String lastNumberInRange = range.substring(2);
        return Integer.parseInt(lastNumberInRange) + 1;
    }

    private boolean reverse() {
        return range.startsWith("-");
    }

    private boolean startIsGiven() {
        return !range.startsWith("-");
    }

    private boolean endIsGiven() {
        return !range.endsWith("-");
    }

    private int calculatePositionFromEnd() {
        return textLength - Integer.parseInt(range.substring(rangeCharacterLength - 1, rangeCharacterLength));
    }

    private int getStartingPosition() {
        return Integer.parseInt(range.substring(0, 1));
    }

}