package controller;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import service.HistoryBelanjaService;
import dto.HistoryBelanja;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ApiServer {

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // Service
        HistoryBelanjaService service = new HistoryBelanjaService();

        // Endpoint POST /history untuk simpan data belanja
        server.createContext("/history", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {
                    String body = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);

                    // Format: namaBarang,harga,jumlah
                    String[] parts = body.split(",");
                    if (parts.length == 3) {
                        try {
                            String namaBarang = parts[0].trim();
                            double harga = Double.parseDouble(parts[1].trim());
                            int jumlah = Integer.parseInt(parts[2].trim());

                            HistoryBelanja history = new HistoryBelanja(namaBarang, harga, jumlah);
                            boolean success = service.simpanHistory(history);

                            String response = success ? "Data tersimpan" : "Gagal menyimpan";
                            exchange.sendResponseHeaders(success ? 200 : 500, response.getBytes().length);
                            try (OutputStream os = exchange.getResponseBody()) {
                                os.write(response.getBytes());
                            }
                        } catch (NumberFormatException e) {
                            String response = "Harga dan jumlah harus berupa angka!";
                            exchange.sendResponseHeaders(400, response.getBytes().length);
                            try (OutputStream os = exchange.getResponseBody()) {
                                os.write(response.getBytes());
                            }
                        }
                    } else {
                        String response = "Format body salah. Gunakan: namaBarang,harga,jumlah";
                        exchange.sendResponseHeaders(400, response.getBytes().length);
                        try (OutputStream os = exchange.getResponseBody()) {
                            os.write(response.getBytes());
                        }
                    }

                } else if ("GET".equalsIgnoreCase(exchange.getRequestMethod())) {
                    // Ambil semua history belanja
                    List<HistoryBelanja> histories = service.getAllHistory();

                    StringBuilder sb = new StringBuilder();
                    sb.append("<html><body>");
                    sb.append("<h1>History Belanja</h1>");
                    sb.append("<table border='1'>");
                    sb.append("<tr><th>Nama Barang</th><th>Harga</th><th>Jumlah</th><th>Total</th></tr>");
                    for (HistoryBelanja h : histories) {
                        sb.append("<tr>");
                        sb.append("<td>").append(h.getNamaBarang()).append("</td>");
                        sb.append("<td>").append(h.getHarga()).append("</td>");
                        sb.append("<td>").append(h.getJumlah()).append("</td>");
                        sb.append("<td>").append(h.getHarga() * h.getJumlah()).append("</td>");
                        sb.append("</tr>");
                    }
                    sb.append("</table>");
                    sb.append("</body></html>");

                    byte[] responseBytes = sb.toString().getBytes(StandardCharsets.UTF_8);
                    exchange.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
                    exchange.sendResponseHeaders(200, responseBytes.length);
                    try (OutputStream os = exchange.getResponseBody()) {
                        os.write(responseBytes);
                    }
                } else {
                    exchange.sendResponseHeaders(405, -1); // Method Not Allowed
                }
            }
        });

        System.out.println("Server running di http://localhost:8080");
        server.start();
    }
}