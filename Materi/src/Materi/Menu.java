package Materi;

import java.util.ArrayList;

public class Menu <T> {
    T nama;
    T harga;

    public Menu(T nama, T harga) {
        this.nama = nama;
        this.harga = harga;
    }

    public T getNama() {
        return nama;
    }

    public void setNama(T nama) {
        this.nama = nama;
    }

    public T getHarga() {
        return harga;
    }

    public void setHarga(T harga) {
        this.harga = harga;
    }

    public void print() {
        System.out.println(nama);
    }
}
