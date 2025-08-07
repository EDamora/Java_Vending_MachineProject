package model;

public class Item {
    private String nama;
    private int harga;

    public Item(String nama, int harga){
        this.nama = nama;
        this.harga = harga;
    }

    public String getNama(){
        return nama;
    }

    public int getHarga(){
        return harga;
    }
}