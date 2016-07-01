package de.rabea.controller;

import de.rabea.ContentStorage;
import de.rabea.Controller;
import de.rabea.request.Directory;
import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;

import static de.rabea.response.head.StatusLine.OK;

public class AssetController extends Controller {

    private Directory directory;
    private ContentStorage contentStorage;

    public AssetController(Directory directory, ContentStorage contentStorage) {
        this.directory = directory;
        this.contentStorage = contentStorage;
    }

    @Override
    public HttpResponse doGet(HttpRequest request) {
        contentStorage.storeFileContent(directory.contentOfFile(request.requestLine().uri()));
        return new HttpResponse(OK, contentStorage.content());
    }
}