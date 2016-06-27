package de.rabea.response;

public abstract class ResponseCreator {
    public abstract HttpResponse create(String body);
}
