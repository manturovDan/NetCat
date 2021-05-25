package server;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

class CatHttpServer implements HttpHandler {
    public CatHttpServer() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8001), 0);
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

        server.createContext("/kitty", new  CatHttpServer());
        server.setExecutor(threadPoolExecutor);
        server.start();
    }

    public String constructHTML() throws IOException {
        String page = Files.readString(Path.of("src/server/resources/Kitty.html"));
        return page;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        byte[] bytes = constructHTML().getBytes();
        exchange.sendResponseHeaders(200, bytes.length);

        OutputStream os = exchange.getResponseBody();
        os.write(bytes);
        os.close();
    }
}

public class HTTPServer {
    public static void main(String[] args) throws Exception {
        CatHttpServer cat = new CatHttpServer();
    }
}
