import java.util.Scanner;

public class Vending_Machine {
    // Daftar barang dan harga
    static String[] barang = {"Air Mineral", "Kopi", "Snack", "Soda", "Teh Botol"};
    static int[] harga = {5000, 8000, 7000, 9000, 6000};

    // Menyimpan barang yang dipilih (belum dipilih = -1)
    static int indexBarangTerpilih = -1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String ulangi;

        do {
            // Reset pilihan setiap pembelian baru
            indexBarangTerpilih = -1;
            // Menampilkan list barang
            tampilkanBarang();
            // Memilih barang
            pilihBarang(scanner);
            // Melakukan pembayaran
            bayarBarang(scanner);

            //Tanya ke user apakah mau beli lagi atau tidak
            System.out.print("Apakah Kamu ingin beli lagi? (y/n): ");
            ulangi = scanner.next();
            System.out.println();

        } while (ulangi.equalsIgnoreCase("y"));

        System.out.println("Program Selesai. Terimakasih & Sampai berjumpa kembali");
        scanner.close(); // menutup scanner setelah selesai
    }

    // ------------------ METHOD #1 ------------------
    // Menampilkan daftar barang dan harga
    public static void tampilkanBarang() {
        System.out.println("=== Daftar Barang Vending Machine ===");
        for (int i = 0; i < barang.length; i++) {
            System.out.println((i + 1) + ". " + barang[i] + " - Rp " + harga[i]);
        }
        System.out.println("=====================================");
    }

    // ------------------ METHOD #2 ------------------
    // Memilih barang berdasarkan input user
    public static void pilihBarang(Scanner scanner) {
        System.out.print("Pilih nomor barang yang ingin dibeli (1-" + barang.length + "): ");
        int pilihan = scanner.nextInt();

        // Validasi input user
        if (pilihan >= 1 && pilihan <= barang.length) {
            indexBarangTerpilih = pilihan - 1; // dikurangi 1 karena index array mulai dari 0
            System.out.println("Kamu memilih: " + barang[indexBarangTerpilih]);
        } else {
            System.out.println("Pilihan tidak valid. Program berhenti.");
            System.exit(0); // keluar dari program
        }
    }

    // ------------------ METHOD #3 ------------------
    // Memproses pembayaran
    public static void bayarBarang(Scanner scanner) {
        if (indexBarangTerpilih == -1) {
            System.out.println("Kamu belum memilih barang.");
            return; // keluar dari method
        }

        int hargaBarang = harga[indexBarangTerpilih];
        System.out.print("Masukkan jumlah uang (Rp): ");
        int uang = scanner.nextInt();

        // Logika pembayaran
        if (uang >= hargaBarang) {
            int kembalian = uang - hargaBarang;
            System.out.println("Pembayaran berhasil! Kamu membeli: " + barang[indexBarangTerpilih]);
            if (kembalian > 0) {
                System.out.println("Kembalian kamu: Rp " + kembalian);
            } else {
                System.out.println("Tidak ada kembalian.");
            }
            System.out.println("Terima kasih sudah menggunakan vending machine.");
        } else {
            System.out.println("Uang tidak cukup. Pembayaran gagal.");
        }
    }
}
