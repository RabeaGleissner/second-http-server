package de.rabea.controller;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.io.FileTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import de.rabea.Controller;
import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;

import java.io.IOException;

import static de.rabea.response.head.StatusLine.OK;

public class TicTacToeController extends Controller {

    @Override
    public HttpResponse doGet(HttpRequest request) {
        TemplateLoader loader = new FileTemplateLoader("/Users/rabeagleissner/code/java/http-server/second-http-server/src/main/Resources/templates", ".mustache");
        Handlebars handlebars = new Handlebars(loader);
        try {
            String html = handlebars.compile("ttt-game").text();
            return new HttpResponse(OK, html.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
