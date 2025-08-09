package view;

import model.Item;

public class VendingMachineView {
    public void tampilkanMenu(Item[] items) {
        System.out.println("=== Daftar Barang ===");
        for ( int i = 0; i < items.length; i++) {
            System.out.println((i + 1) + ". " + items[i].getNama() + " - Rp " + items[i].getHarga());
        }
        System.out.println("========================");
    }

    public void tampilkanMenuUtama() {
        System.out.println("\n===== MENU UTAMA ======");
        System.out.println("1. Lihat Barang");
        System.out.println("2. Pilih Barang");
        System.out.println("3. Hapus Barang dari Keranjang");
        System.out.println("4. Lihat Keranjang");
        System.out.println("5. Bayar");
        System.out.println("6. Lihat History Belanja");
        System.out.println("0. Keluar");
        System.out.print("Pilihan: ");
    }
}