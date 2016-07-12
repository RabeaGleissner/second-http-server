package de.rabea;

import de.rabea.communication.ResourceReader;
import de.rabea.controller.*;
import de.rabea.logger.ConsoleLogger;
import de.rabea.logger.FileLogger;
import de.rabea.logger.Logger;
import de.rabea.logger.MultiLogger;
import de.rabea.request.Directory;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Executors;

public class Main {
    private static final String logFilePath = new File("../../logs.txt").getAbsolutePath();

    public static void main(String[] args) throws IOException {
        Arguments arguments = new Arguments(args);
        int port = arguments.getPort();
        String givenDirectory = arguments.getDirectory();
        Directory directory = new Directory(givenDirectory);

        ServerSocket serverSocket = new ServerSocket(port);
        Router router = new Router();

        Logger logger = new MultiLogger(new ArrayList<>(Arrays.asList(new FileLogger(logFilePath),
                new ConsoleLogger(new ServerConsole()))));

        router.configure("/", new RootController(directory));
        router.configure("/coffee", new CoffeeController());
        router.configure("/logs", new LogsController(logger));
        router.configure("/form", new FormController(new ContentStorage()));
        router.configure("/method_options", new MethodOptionsController());
        router.configure("/method_options2", new MethodOptions2Controller());
        router.configure("/parameters", new ParametersController());
        router.configure("/redirect", new RedirectController());
        router.configure("/tea", new TeaController());
        router.configure("/ttt-game", new TicTacToeController());
        router.configure("/ttt-menu", new TicTacToeMenuController());
        router.configure("/styles.css", new StyleSheetController(new ResourceReader()));
        router.configure(directory, new AssetController(directory));

        HttpServer httpServer = new HttpServer(Executors.newFixedThreadPool(20), serverSocket, router, logger);
        httpServer.start(givenDirectory, port);
    }
}