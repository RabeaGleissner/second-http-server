package de.rabea;

import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;

import static de.rabea.response.head.StatusLine.*;

public class Controller2 {


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
}
