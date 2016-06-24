package de.rabea;

import de.rabea.communication.SocketReader;
import de.rabea.communication.SocketWriter;
import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;

public class ServerWorker {

    private final SocketReader socketReader;
    private final SocketWriter socketWriter;
    private Router router;

    public ServerWorker(SocketReader socketReader, SocketWriter socketWriter, Router router) {
        this.socketReader = socketReader;
        this.socketWriter = socketWriter;
        this.router = router;
    }

    public void start() {
        HttpRequest request = socketReader.read();
        Controller controller = router.getController(request);
        HttpResponse httpResponse = controller.getResponse();
        System.out.println(httpResponse.asBytes().toString());
        socketWriter.write(httpResponse.asBytes());
    }
}