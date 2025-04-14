package Materi;

import java.util.ArrayList;

public class Data <T> {
    private T nama;
    private ArrayList<T> pesanan = new ArrayList<>();

    public Data(T nama) {
        this.nama = nama;
    }

    public Data(T nama, ArrayList<T> pesanan) {
        this.nama = nama;
        this.pesanan.addAll(pesanan);
    }

    public T getNama() {
        return nama;
    }

    public void setNama(T nama) {
        this.nama = nama;
    }

    public void add(T m) {
        pesanan.add(m);
    }

    public ArrayList<T> getPesanan() {
        return pesanan;
    }

    public int getPesananSize() {
        return pesanan.size();
    }

    public T getPesananKe(int index) {
        return pesanan.get(index);
    }

}
