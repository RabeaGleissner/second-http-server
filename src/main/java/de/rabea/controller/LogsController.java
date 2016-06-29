package de.rabea.controller;

import de.rabea.Controller;
import de.rabea.Logger;
import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;
import de.rabea.response.head.AuthenticateResponseHeader;

import static de.rabea.response.head.StatusLine.OK;
import static de.rabea.response.head.StatusLine.UNAUTHORIZED;

public class LogsController extends Controller {

    private Logger logger;

    public LogsController(Logger logger) {
        super();
        this.logger = logger;
    }

    @Override
    public HttpResponse doGet(HttpRequest request) {
        if (authorized(request)) {
            return new HttpResponse(OK, logger.getLogs());
        }
        return new HttpResponse(UNAUTHORIZED, new AuthenticateResponseHeader());

    }

    private boolean authorized(HttpRequest request) {
        if (request.requestHeaders().containsKey("Authorization")) {
            return request.requestHeaders().get("Authorization").equals("Basic YWRtaW46aHVudGVyMg==");
        }
        return false;
    }
}