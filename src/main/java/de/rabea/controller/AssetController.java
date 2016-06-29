package de.rabea.controller;

import de.rabea.ContentStorage;
import de.rabea.Controller;
import de.rabea.communication.FileReader;
import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;

import static de.rabea.response.head.StatusLine.OK;

public class AssetController extends Controller {

    private String directory;
    private ContentStorage contentStorage;

    public AssetController(String directory, ContentStorage contentStorage) {
        this.directory = directory;
        this.contentStorage = contentStorage;
    }

    @Override
    public HttpResponse doGet(HttpRequest request) {
        contentStorage.store(FileReader.read(request, directory));
        return new HttpResponse(OK, contentStorage.content());
    }
}