package de.rabea.controller;

import de.rabea.Controller;
import de.rabea.Logger;
import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;

import static de.rabea.response.head.StatusLine.OK;

public class LogsController extends Controller {

    private Logger logger;

    public LogsController(Logger logger) {
        super();
        this.logger = logger;
    }

    @Override
    public HttpResponse doGet(HttpRequest request) {
        return new HttpResponse(OK, logger.getLogs());
    }
}