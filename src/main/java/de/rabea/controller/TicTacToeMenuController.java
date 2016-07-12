package de.rabea.controller;

import de.rabea.Controller;
import de.rabea.controller.html.MenuHtml;
import de.rabea.controller.html.TicTacToeHtmlGenerator;
import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;

import static de.rabea.response.head.StatusLine.OK;

public class TicTacToeMenuController extends Controller {

    @Override
    public HttpResponse doGet(HttpRequest request) {
        String html = new TicTacToeHtmlGenerator(new MenuHtml()).generate();
        return new HttpResponse(OK, html.getBytes());
    }
}