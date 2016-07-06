package de.rabea;

public class ServerConsole implements Console {

    @Override
    public void write(String message) {
        System.out.println(message);
    }
}
