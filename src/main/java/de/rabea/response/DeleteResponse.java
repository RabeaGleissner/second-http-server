package de.rabea.response;

import de.rabea.ContentStorage;
import de.rabea.controller.FormController;
import de.rabea.response.HttpResponse;

public class DeleteResponse extends ResponseCreator {

    private final ContentStorage contentStorage;
    private final FormController formController;

    public DeleteResponse(ContentStorage contentStorage, FormController formController) {
        this.contentStorage = contentStorage;
        this.formController = formController;
    }

    @Override
    public HttpResponse create() {
        return null;
    }
}
