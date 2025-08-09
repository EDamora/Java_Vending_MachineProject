package model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Item> keranjang = new ArrayList<>();

    public void tambahBarang(Item item){
        keranjang.add(item);
    }

    public boolean hapusBarang(int index){
        if (index >= 0 && index < keranjang.size()){
            keranjang.remove(index);
            return true;
        } else {
            return false;
        }
    }

    public List<Item> getKeranjang(){
        return keranjang;
    }

    public int totalHarga(){
        int total = 0;
        for (Item item : keranjang){
            total += item.getHarga();
        }
        return total;
    }

    public void tampilKeranjang(){
        System.out.println("==== Keranjang Kamu ====");
        for (int i = 0; i < keranjang.size(); i++){
            Item item = keranjang.get(i);
            System.out.println((i + 1) + " . " + item.getNama() + " - Rp " + item.getHarga());
            }
        System.out.println("Total Belanjaan Kamu : Rp " + totalHarga());
        }

    public void kosongkanKeranjang(){
        keranjang.clear();
    }
}