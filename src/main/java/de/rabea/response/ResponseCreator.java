package de.rabea.response;

public interface ResponseCreator {
    HttpResponse create(String body);
}
