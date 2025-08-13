package model;

import java.time.LocalDateTime;

public class ShoppingHistory {
    private int id;                 // ID di database
    private String namaBarang;      // Nama barang yang dibeli
    private int jumlah;             // Jumlah yang dibeli
    private double hargaTotal;      // Total harga (jumlah * harga per item)
    private LocalDateTime tanggal;  // Tanggal pembelian

    // Constructor kosong (untuk kebutuhan JDBC)
    public ShoppingHistory() {}

    // Constructor lengkap
    public ShoppingHistory(int id, String namaBarang, int jumlah, double hargaTotal, LocalDateTime tanggal) {
        this.id = id;
        this.namaBarang = namaBarang;
        this.jumlah = jumlah;
        this.hargaTotal = hargaTotal;
        this.tanggal = tanggal;
    }

    public ShoppingHistory(String namaBarang, int jumlah, double hargaTotal, LocalDateTime tanggal) {
        this.namaBarang = namaBarang;
        this.jumlah = jumlah;
        this.hargaTotal = hargaTotal;
        this.tanggal = tanggal;
    }

    // Getter & Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public double getHargaTotal() {
        return hargaTotal;
    }

    public void setHargaTotal(double hargaTotal) {
        this.hargaTotal = hargaTotal;
    }

    public LocalDateTime getTanggal() {
        return tanggal;
    }

    public void setTanggal(LocalDateTime tanggal) {
        this.tanggal = tanggal;
    }

    @Override
    public String toString() {
        return "HistoryBelanja{" +
                "id=" + id +
                ", namaBarang='" + namaBarang + '\'' +
                ", jumlah=" + jumlah +
                ", hargaTotal=" + hargaTotal +
                ", tanggal=" + tanggal +
                '}';
    }
}
