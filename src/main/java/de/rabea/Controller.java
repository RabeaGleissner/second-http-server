package de.rabea;

import de.rabea.request.HttpRequest;
import de.rabea.request.HttpVerb;
import de.rabea.response.HttpResponse;

import static de.rabea.response.head.StatusLine.NOT_ALLOWED;
import static de.rabea.response.head.StatusLine.OK;

public class Controller {

   private final HttpResponse methodNotAllowed = new HttpResponse(NOT_ALLOWED);

   public HttpResponse doGet(HttpRequest request) {
      return methodNotAllowed;
   }

   public HttpResponse doPut(HttpRequest request) {
      return methodNotAllowed;
   }

   public HttpResponse doPost(HttpRequest request) {
      return methodNotAllowed;
   }

   public HttpResponse doDelete(HttpRequest request) {
      return methodNotAllowed;
   }

   public HttpResponse doOptions(HttpRequest request) {
      return methodNotAllowed;
   }

   public HttpResponse doPatch(HttpRequest request) {
      return methodNotAllowed;
   }

   public HttpResponse doHead(HttpRequest request) {
      return methodNotAllowed;
   }

    public HttpResponse dispatch(HttpRequest httpRequest) {
        HttpVerb verb = httpRequest.requestLine().method();
        switch (verb) {
            case PUT: return doPut(httpRequest);
            case POST: return doPost(httpRequest);
            case HEAD: return doHead(httpRequest);
            case PATCH: return doPatch(httpRequest);
            case DELETE: return doDelete(httpRequest);
            case OPTIONS: return doOptions(httpRequest);
            case GET: return doGet(httpRequest);
            default: return new HttpResponse(NOT_ALLOWED);
        }
    }

    public HttpResponse okResponse() {
        return new HttpResponse(OK);
    }
}
