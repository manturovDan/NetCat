package server;

import java.io.IOException;

public class Server {
    public static void work() throws IOException {
        HTTPServerKitty.runServer();

        Eat eat = new Eat();
        Thread nutrition = new Thread(eat);
        nutrition.start();
    }

    public static void main(String[] args) throws IOException {
        work();
    }
}
