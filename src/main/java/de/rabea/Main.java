package de.rabea;

import de.rabea.controller.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws IOException {
        Arguments arguments = new Arguments(args);
        int port = arguments.getPort();
        String directory = arguments.getDirectory();

        ServerSocket serverSocket = new ServerSocket(port);
        Router router = new Router(directory);
        Logger logger = new Logger();
        router.configure("/", new RootController());
        router.configure("/coffee", new CoffeeController());
        router.configure("/logs", new LogsController(logger));
        router.configure("/form", new FormController(new ContentStorage()));
        router.configure("/method_options", new MethodOptionsController());
        router.configure("/method_options2", new MethodOptions2Controller());
        router.configure("/redirect", new RedirectController());
        router.configure("/tea", new TeaController());
        router.configure(directory, new AssetController(directory, new ContentStorage()));
        HttpServer httpServer = new HttpServer(Executors.newFixedThreadPool(20), serverSocket, router, logger);
        httpServer.start(directory, port);
    }
}