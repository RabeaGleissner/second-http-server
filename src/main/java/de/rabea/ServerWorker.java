package de.rabea;

import de.rabea.communication.SocketReader;
import de.rabea.communication.SocketWriter;
import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;

public class ServerWorker {

    private final SocketReader socketReader;
    private final SocketWriter socketWriter;
    private final Router router;
    private final Logger logger;

    public ServerWorker(SocketReader socketReader, SocketWriter socketWriter, Router router, Logger logger) {
        this.socketReader = socketReader;
        this.socketWriter = socketWriter;
        this.router = router;
        this.logger = logger;
    }

    public void start() {
        HttpRequest request = socketReader.read();
        logger.log(request.requestLine());
        Controller controller = router.getController(request);
        HttpResponse httpResponse = controller.dispatch(request);
        socketWriter.write(httpResponse.asBytes());
    }
}