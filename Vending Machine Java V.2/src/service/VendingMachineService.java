package service;

import config.DatabaseConfig;
import model.Cart;
import model.Item;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.sql.Timestamp;

public class VendingMachineService {

    // proses pembayaran: setelah sukses -> simpan history (1 baris per transaksi)
    public void prosesPembayaran(Scanner scanner, Cart cart) {
        List<Item> keranjang = cart.getKeranjang();
        if (keranjang.isEmpty()) {
            System.out.println("Keranjang Masih Kosong. tidak bisa melakukan pembayaran.");
            return;
        }

        int total = cart.totalHarga();
        System.out.println("Total yang harus dibayar: Rp " + total);
        System.out.print("Masukkan jumlah uang: Rp ");
        int uang = scanner.nextInt();

        if (uang >= total) {
            System.out.println("Pembayaran berhasil. Kembalian Anda: Rp " + (uang - total));

            // Gabungkan nama barang menjadi satu String Exs : "Air Mineral, Snack")
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < keranjang.size(); i++) {
                sb.append(keranjang.get(i).getNama());
                if (i < keranjang.size() - 1) sb.append(", ");
            }
            String namaBarangGabungan = sb.toString();

            // Simpan History ke DB
            simpanHistory(namaBarangGabungan, total);

            cart.hapusBarang(0);
        } else {
            System.out.println("Uang tidak cukup. Transaksi dibatalkan");
        }
    }

    // Simpan satu record history ke table history_belanja
    private void simpanHistory(String namaBarang, int totalHarga) {
        String sql = "INSERT INTO history_belanja (nama_barang, total_harga) VALUES (?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, namaBarang);
            ps.setInt(2, totalHarga);
            ps.executeUpdate();
            System.out.println("History belanja berhasil disimpan ke database");
        } catch (SQLException e) {
            System.out.println("Gagal menyimpan history: " + e.getMessage());
        }
    }

    // tampilkan semua history (menu read)
    public void lihatHistory() {
        String sql = "SELECT id, nama_barang, total_harga, tanggal FROM history_belanja ORDER BY tanggal DESC";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\n===== HISTORY BELANJA =====");
            System.out.printf("%-5s %-40s %-12s %-20s%n", "ID", "Nama Barang", "Total (Rp)", "Tanggal");
            System.out.println("-------------------------------------------------------------------------------");
            boolean ada = false;
            while (rs.next()) {
                ada = true;
                int id = rs.getInt("id");
                String nama = rs.getString("nama_barang");
                int total = rs.getInt("total_harga");
                Timestamp ts = rs.getTimestamp("tanggal");
                System.out.printf("%-5d %-40s %-12d %-20s%n", id, nama, total, ts.toString());
            }
            if (!ada) {
                System.out.println("Belum ada history transaksi.");
            }
            System.out.println("=============================\n");
        } catch (SQLException e) {
            System.out.println("Gagal mengambil history: " + e.getMessage());
        }
    }
}