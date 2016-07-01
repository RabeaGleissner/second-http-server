package de.rabea.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EntityTagChecker {
    private final static char[] hexArray = "0123456789abcdef".toCharArray();

    public static boolean isCorrectTag(byte[] existingContent, String eTag) {
        try {
            return computeSha1OfByteArray(existingContent).equals(eTag);
        } catch (NoSuchAlgorithmException e) {
            throw new ShaEncodingException();
        }
    }

    private static String computeSha1OfByteArray(byte[] message) throws UnsupportedOperationException, NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
        messageDigest.update(message);
        return toHexString(messageDigest.digest());
    }

    private static String toHexString(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    private static class ShaEncodingException extends RuntimeException {

        public ShaEncodingException() {
            super("Apologies, but unfortunately something went wrong when trying to encode the file content");
        }
    }
}
