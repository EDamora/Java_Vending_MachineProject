package service;

import model.Cart;
import model.Item;

import java.util.List;
import java.util.Scanner;

public class VendingMachineService {
    public void prosesPembayaran(Scanner scanner, Cart cart){
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
            cart.hapusBarang(0);
        } else {
            System.out.println("Uang tidak cukup. Transaksi dibatalkan");
        }
    }
}