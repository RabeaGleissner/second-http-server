package de.rabea.request;

import org.junit.Test;

import static de.rabea.request.HttpVerb.NONE;
import static org.junit.Assert.assertEquals;

public class HttpVerbTest {
    @Test
    public void convertsStringToVerb() {
        String[] strings = {"GET", "POST", "PUT", "OPTIONS", "HEAD"};
        HttpVerb[] verbs = HttpVerb.class.getEnumConstants();
        for (int i = 0; i < strings.length; i++) {
            assertEquals(verbs[i], HttpVerb.convert(strings[i]));
        }
    }

    @Test
    public void returnsNullIfCannotConvertToVerb() {
        assertEquals(NONE, HttpVerb.convert("bogus-request"));
    }
}