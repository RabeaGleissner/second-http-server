package de.rabea.controller;

import de.rabea.Controller;
import de.rabea.Logger;
import de.rabea.ServerAuthenticator;
import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;
import de.rabea.response.head.AuthenticateResponseHeader;

import static de.rabea.response.head.StatusLine.OK;
import static de.rabea.response.head.StatusLine.UNAUTHORIZED;

public class LogsController extends Controller {

    private final Logger logger;

    public LogsController(Logger logger) {
        this.logger = logger;
    }

    @Override
    public HttpResponse doGet(HttpRequest request) {
        ServerAuthenticator authenticator = new ServerAuthenticator("admin", "hunter2");
        if (authenticator.hasCorrectCredentials(request)) {
            return new HttpResponse(OK, logger.getLogs());
        }
        return new HttpResponse(UNAUTHORIZED, new AuthenticateResponseHeader("Logger"));

    }
}