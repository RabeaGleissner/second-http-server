package de.rabea;

import de.rabea.request.HttpRequest;

import java.util.Base64;

public class ServerAuthenticator {

    private final String userName;
    private final String password;

    public ServerAuthenticator(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public boolean hasCorrectCredentials(HttpRequest request) {
        return request.requestHeaders().containsKey("Authorization") &&
                request.requestHeaders().get("Authorization").equals("Basic " + encodedCredentials());
    }

    private String encodedCredentials() {
        String credentials = userName + ":" + password;
        return Base64.getEncoder().encodeToString(credentials.getBytes());
    }
}
