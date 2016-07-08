package de.rabea;

import de.rabea.communication.SocketReader;
import de.rabea.communication.SocketWriter;
import de.rabea.logger.Logger;
import de.rabea.request.HttpRequest;
import de.rabea.response.HttpResponse;

public class ServerWorker implements Runnable {

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

    @Override
    public void run() {
        HttpRequest request = socketReader.read();
        logger.log(request.requestLine().asString());
        Controller controller = router.getController(request);
        HttpResponse httpResponse = controller.dispatch(request);
        socketWriter.write(httpResponse.asBytes());
    }
}