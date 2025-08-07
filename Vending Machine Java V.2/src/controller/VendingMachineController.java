package controller;

import model.Cart;
import model.Item;

import java.util.List;
import java.util.Scanner;

public class VendingMachineController {
    private Item[] items;

    public VendingMachineController(){
        items = new Item[]{
                new Item("Air Mineral", 5000),
        new Item("Teh Kotak", 7000),
        new Item("Mizone", 8000),
        new Item("Snack", 3000),
        new Item("Coklat", 10000),
        new Item("Ale - Ale", 2000)
        };
    }

    public Item[] getItems(){
        return items;
    }

    public void pilihBarang(Scanner scanner, Cart cart){
        while (true){
            System.out.println("Masukkan Nomor Barang yang ingin dimasukkan ke keranjang (0 untuk batal): ");
            int pilihan = scanner.nextInt();
            if (pilihan == 0) break;
            if (pilihan > 0 && pilihan <= items.length) {
                cart.tambahBarang(items[pilihan - 1]);
                System.out.println("Barang ditambahkan ke keranjang.");
            } else {
                System.out.println("Pilihan Tidak Valid");
            }
        }
    }

    public void hapusBarang(Scanner scanner, Cart cart){
        List<Item> isiKeranjang = cart.getKeranjang();
        if (isiKeranjang.isEmpty()) {
            System.out.println("Keranjang Kosong.");
            return;
        }

        cart.tampilKeranjang();
        System.out.println("Masukkan nomor barang yang mau dihapus ( 0 untuk batal: ");
        int pilihan = scanner.nextInt();
        if (pilihan == 0) return;

        if (cart.hapusBarang(pilihan - 1)) {
            System.out.println("Barang berhasil di hapus dari keranjang");
        } else {
            System.out.println("Pilihan tidak valid.");
        }
    }

}
