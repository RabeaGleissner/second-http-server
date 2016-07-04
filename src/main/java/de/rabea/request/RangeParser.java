package de.rabea.request;

import static java.util.Arrays.copyOfRange;

public class RangeParser {

    private final byte[] text;
    private final String range;
    private int textLength;
    private int rangeCharacterLength;

    public RangeParser(byte[] text, String range) {
        this.text = text;
        this.range = range;
        this.textLength = text.length;
        this.rangeCharacterLength = range.length();
    }

    public byte[] partialContent() {
        return readPartialContent(rangeStart(), rangeEnd());
    }

    private byte[] readPartialContent(int from, int to) {
        return copyOfRange(text, from, to);
    }

    private int rangeStart() {
        if (startIsGiven()) {
            return Integer.parseInt(range.substring(0, 1));
        }
        return textLength - Integer.parseInt(range.substring(rangeCharacterLength - 1, rangeCharacterLength));
    }

    private int rangeEnd() {
        if (reverse()) {
            return textLength;
        }
        if (endIsGiven()) {
            return Integer.parseInt(range.substring(2));
        }
        return textLength;
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
}