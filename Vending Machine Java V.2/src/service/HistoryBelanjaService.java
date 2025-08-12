package service;

import config.DatabaseConfig;
import dto.HistoryBelanja;

import java.sql.*;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class HistoryBelanjaService {

    public List<HistoryBelanja> getAllHistory() {
        List<HistoryBelanja> result = new ArrayList<>();
        String sql = "SELECT id, nama_barang, jumlah, harga_total, tanggal FROM history_belanja ORDER BY tanggal DESC";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nama = rs.getString("nama_barang");
                int jumlah = rs.getInt("jumlah");
                double total = rs.getDouble("harga_total");
                Timestamp ts = rs.getTimestamp("tanggal");
                String tanggalIso = ts != null ? ts.toInstant().atZone(ZoneId.systemDefault()).toString() : null;

                result.add(new HistoryBelanja(id, nama, jumlah, total, tanggalIso));
            }

        } catch (SQLException e) {
            System.err.println("Gagal mengambil history: " + e.getMessage());
        }
        return result;
    }

    public boolean simpanHistory(HistoryBelanja history) {
        String sql = "INSERT INTO history_belanja (nama_barang, jumlah, harga_total, tanggal) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, history.getNamaBarang());
            ps.setInt(2, history.getJumlah());
            ps.setDouble(3, history.getHargaTotal());

            if (history.getTanggal() != null) {
                ps.setTimestamp(4, Timestamp.valueOf(history.getTanggal().replace("T", " ").substring(0, 19)));
            } else {
                ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            }

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.err.println("Gagal menyimpan history: " + e.getMessage());
            return false;
        }
    }
}
