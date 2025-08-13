import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

import controller.ShoppingHistoryController;

public class Vending_Machine_V2 {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/shopping-history", new ShoppingHistoryController());
        server.setExecutor(null);
        System.out.println("Server running on http://localhost:8080");
        server.start();
    }
}