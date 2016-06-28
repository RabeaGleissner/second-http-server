package de.rabea.controller;

import de.rabea.ContentStorage;
import de.rabea.Controller;
import de.rabea.Controller2;
import de.rabea.communication.FileReader;
import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;
import de.rabea.response.creator.GetResponseCreator;

import static de.rabea.response.head.StatusLine.OK;

public class AssetController extends Controller2 implements Controller {

    private String directory;
    private ContentStorage contentStorage;

    public AssetController(String directory, ContentStorage contentStorage) {
        this.directory = directory;
        this.contentStorage = contentStorage;
    }

    @Override
    public HttpResponse getResponse(HttpRequest httpRequest) {
        throw new RuntimeException("I should not be called");
    }

    @Override
    public HttpResponse doGet(HttpRequest request) {
        return new GetResponseCreator(OK, contentStorage).create(FileReader.read(request, directory));
    }
}