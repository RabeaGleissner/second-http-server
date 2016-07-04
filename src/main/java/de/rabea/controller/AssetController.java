package de.rabea.controller;

import de.rabea.ContentStorage;
import de.rabea.Controller;
import de.rabea.EntityTagChecker;
import de.rabea.request.Directory;
import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;

import static de.rabea.response.head.StatusLine.NO_CONTENT;
import static de.rabea.response.head.StatusLine.OK;
import static de.rabea.response.head.StatusLine.PARTIAL_CONTENT;

public class AssetController extends Controller {

    private final Directory directory;
    private final ContentStorage contentStorage;

    public AssetController(Directory directory, ContentStorage contentStorage) {
        this.directory = directory;
        this.contentStorage = contentStorage;
    }

    @Override
    public HttpResponse doGet(HttpRequest request) {
        String file = request.requestLine().uri();
        if (containsRange(request)) {
           return new HttpResponse(PARTIAL_CONTENT, directory.partialFileContent(file, getRange(request)));
        }
        return new HttpResponse(OK, directory.fileContent(file));
    }

    private String getRange(HttpRequest request) {
        return request.requestHeaders().get("Range");
    }

    private boolean containsRange(HttpRequest request) {
        return request.requestHeaders().containsKey("Range");
    }

    @Override
    public HttpResponse doPatch(HttpRequest request) {
        if (EntityTagChecker.isCorrectTag(directory.fileContent(request.requestLine().uri()), request.requestHeaders().get("If-Match"))) {
            directory.updateFile(request.requestLine().route(), request.body());
        }
        return new HttpResponse(NO_CONTENT);
    }
}