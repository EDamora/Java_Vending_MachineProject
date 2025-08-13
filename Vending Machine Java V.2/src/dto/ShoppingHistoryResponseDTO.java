package dto;

public class ShoppingHistoryResponseDTO {
    private int id;
    private String namaBarang;
    private int jumlah;
    private double harga; // harga satuan
    private double hargaTotal;
    private String tanggal; // ISO string

    // Constructor untuk ApiServer (3 parameter)
    public ShoppingHistoryResponseDTO(String namaBarang, double harga, int jumlah) {
        this.namaBarang = namaBarang;
        this.harga = harga;
        this.jumlah = jumlah;
        this.hargaTotal = harga * jumlah;
    }

    // Constructor untuk HistoryBelanjaService (5 parameter)
    public ShoppingHistoryResponseDTO(int id, String namaBarang, int jumlah, double hargaTotal, String tanggal) {
        this.id = id;
        this.namaBarang = namaBarang;
        this.jumlah = jumlah;
        this.hargaTotal = hargaTotal;
        this.tanggal = tanggal;
    }

    // Getter & Setter
    public int getId() {
        return id;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public int getJumlah() {
        return jumlah;
    }

    public double getHarga() {
        return harga;
    }

    public double getHargaTotal() {
        return hargaTotal;
    }

    public String getTanggal() {
        return tanggal;
    }
}