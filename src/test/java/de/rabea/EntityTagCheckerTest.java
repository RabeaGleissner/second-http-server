package de.rabea;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class EntityTagCheckerTest {

    @Test
    public void returnsTrueIfGivenEncodedTextIsTheSameAsGivenText() {
        assertTrue(EntityTagChecker.isCorrectTag("default content".getBytes(), "dc50a0d27dda2eee9f65644cd7e4c9cf11de8bec"));
    }
}