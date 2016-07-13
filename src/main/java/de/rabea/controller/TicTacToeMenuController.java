package de.rabea.controller;

import de.rabea.Controller;
import de.rabea.controller.html.MenuHtml;
import de.rabea.controller.html.TicTacToeHtmlGenerator;
import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;
import de.rabea.response.head.RedirectResponseHeader;

import static de.rabea.response.head.StatusLine.OK;
import static de.rabea.response.head.StatusLine.REDIRECT;

public class TicTacToeMenuController extends Controller {

    @Override
    public HttpResponse doGet(HttpRequest request) {
        String html = new TicTacToeHtmlGenerator(new MenuHtml()).generate();
        return new HttpResponse(OK, html.getBytes());
    }

    @Override
    public HttpResponse doPost(HttpRequest request) {
        return new HttpResponse(REDIRECT, new RedirectResponseHeader("http://localhost:5000/ttt-game/hvh"));
    }
}