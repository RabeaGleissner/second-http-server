package de.rabea;

import de.rabea.controller.*;
import de.rabea.request.Directory;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws IOException {
        Arguments arguments = new Arguments(args);
        int port = arguments.getPort();
        String givenDirectory = arguments.getDirectory();
        Directory directory = new Directory(givenDirectory);

        ServerSocket serverSocket = new ServerSocket(port);
        Router router = new Router();
        MultiLogger logger = new MultiLogger();
        logger.add(new FileLogger(new File("../../logs.txt").getAbsolutePath()), new ConsoleLogger(new ServerConsole()));
        router.configure("/", new RootController(directory));
        router.configure("/coffee", new CoffeeController());
        router.configure("/logs", new LogsController(logger));
        router.configure("/form", new FormController(new ContentStorage()));
        router.configure("/method_options", new MethodOptionsController());
        router.configure("/method_options2", new MethodOptions2Controller());
        router.configure("/parameters", new ParametersController());
        router.configure("/redirect", new RedirectController());
        router.configure("/tea", new TeaController());
        router.configure(directory, new AssetController(directory));
        HttpServer httpServer = new HttpServer(Executors.newFixedThreadPool(20), serverSocket, router, logger);
        httpServer.start(givenDirectory, port);
    }
}