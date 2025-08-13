package service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.DatabaseConfig;
import model.ShoppingHistory;

public class ShoppingHistoryService {

    public List<ShoppingHistory> getAllShoppingHistory() {
        List<ShoppingHistory> historyList = new ArrayList<>();

        String sql = "SELECT id, nama_barang, jumlah, harga_total, tanggal FROM shopping_history";

        try (Connection conn = DatabaseConfig.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                historyList.add(new ShoppingHistory(
                        rs.getInt("id"),
                        rs.getString("nama_barang"),
                        rs.getInt("jumlah"),
                        rs.getDouble("harga_total"),
                        rs.getTimestamp("tanggal").toLocalDateTime()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return historyList;
    }
}
