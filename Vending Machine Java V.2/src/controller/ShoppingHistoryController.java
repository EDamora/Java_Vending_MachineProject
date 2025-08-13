package controller;

import java.io.IOException;
import java.io.OutputStream;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import model.ShoppingHistory;
import service.ShoppingHistoryService;

public class ShoppingHistoryController implements HttpHandler {

    private final ShoppingHistoryService service = new ShoppingHistoryService();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("GET".equalsIgnoreCase(exchange.getRequestMethod())) {
            List<ShoppingHistory> historyList = service.getAllShoppingHistory();

            // Convert to JSON manually
            StringBuilder json = new StringBuilder("[");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            for (int i = 0; i < historyList.size(); i++) {
                ShoppingHistory h = historyList.get(i);
                json.append("{")
                        .append("\"id\":").append(h.getId()).append(",")
                        .append("\"namaBarang\":\"").append(h.getNamaBarang()).append("\",")
                        .append("\"jumlah\":").append(h.getJumlah()).append(",")
                        .append("\"hargaTotal\":").append(h.getHargaTotal()).append(",")
                        .append("\"tanggal\":\"").append(h.getTanggal().format(formatter)).append("\"")
                        .append("}");

                if (i < historyList.size() - 1) {
                    json.append(",");
                }
            }
            json.append("]");

            // Send response
            exchange.getResponseHeaders().add("Content-Type", "application/json; charset=UTF-8");
            byte[] responseBytes = json.toString().getBytes();
            exchange.sendResponseHeaders(200, responseBytes.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(responseBytes);
            }
        } else {
            exchange.sendResponseHeaders(405, -1); // Method Not Allowed
        }
    }

}
