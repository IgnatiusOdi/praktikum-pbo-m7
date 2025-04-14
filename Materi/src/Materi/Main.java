package Materi;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Scanner scanint = new Scanner(System.in);
        ArrayList<Data> pelanggan = new ArrayList<>();
        ArrayList<Data> antrian = new ArrayList<>();
        ArrayList<Menu> menu = new ArrayList<>();

        System.out.print("Masukkan nama owner : ");
        String namaowner = scan.nextLine();
        int pendapatan = 0;
        menu.add(new Menu("Burger",20000));
        menu.add(new Menu("Pepsi",10000));
        do{
            System.out.println("Selamat datang di Resto " + namaowner);
            System.out.println("1. Masuk sebagai Pelayan");
            System.out.println("2. Masuk sebagai Owner");
            System.out.println("0. Exit");
            System.out.print(">>> ");
            int input = scanint.nextInt();
            if (input == 1) {
                //PELAYAN
                System.out.println("Login sebagai Pelayan");
                do {
                    System.out.println("1. Tambah Pelanggan dan pesanan");
                    System.out.println("2. Cetak daftar antrian");
                    System.out.println("0. Logout");
                    System.out.print(">>> ");
                    input = scanint.nextInt();
                    if (input == 1) {
                        //TAMBAH PELANGGAN DAN PESANAN
                        System.out.print("Nama pelanggan : ");
                        String nama = scan.nextLine();
                        pelanggan.add(new Data(nama));
                        int pointer = pelanggan.size()-1;
                        String inputan;
                        do{
                            System.out.println("Input pesanan :");
                            System.out.println("(input 'd' saat sudah selesai");
                            for (int i = 0; i < menu.size(); i++) {
                                System.out.println((i+1) + ". " + menu.get(i).getNama());
                            }
                            System.out.print(">> ");
                            inputan = scan.nextLine();
                            int temp = 0;
                            if (!inputan.equals("d")) {
                                temp = Integer.parseInt(inputan);
                                temp--;
                                if (temp > -1 && temp < menu.size()) {
                                    pelanggan.get(pointer).add(menu.get(temp));
                                    System.out.println("Berhasil menambah " + menu.get(temp).getNama() + ".");
                                }
                            } else  {
                                antrian.add(new Data(pelanggan.get(pointer).getNama(),pelanggan.get(pointer).getPesanan()));
                                System.out.println("Berhasil tambah pelanggan beserta pesanannya.");
                                break;
                            }
                        } while(true);
                    } else if (input == 2) {
                        System.out.println("=== List Antrian ===");
                        for (int i = 0; i < antrian.size(); i++) {
                            System.out.println((i+1) + ". " + (String) antrian.get(i).getNama() + "-" + antrian.get(i).getPesananSize() + " item");
                            for (int j = 0; j < antrian.get(i).getPesananSize(); j++) {
                                System.out.println("\t- " + antrian.get(i).getPesananKe(j));
                            }
                        }
                    } else if (input == 0) {
                        break;
                    }
                } while(true);
            } else if (input == 2) {
                //OWNER
                System.out.println("Login sebagai Owner");
                do {
                    System.out.println("Pendapatan resto : Rp " + pendapatan);
                    System.out.println("1. Tambah menu");
                    System.out.println("2. Cetak daftar antrian");
                    System.out.println("3. Buat pesanan pelanggan");
                    System.out.println("4. Tutup toko");
                    System.out.println("5. Ganti hari");
                    System.out.println("0. Logout");
                    System.out.print(">> ");
                    input = scanint.nextInt();
                    if (input == 1) {
                        //TAMBAH MENU
                        System.out.print("Nama menu : ");
                        String nama = scan.nextLine();
                        System.out.print("Harga     : ");
                        int harga = scanint.nextInt();
                        menu.add(new Menu(nama,harga));
                        System.out.println("Berhasil tambah menu " + nama + "!");
                    } else if (input == 2) {
                        //ANTRIAN
                        System.out.println("=== List Antrian ===");
                        for (int i = 0; i < antrian.size(); i++) {
                            System.out.println((i+1) + ". " + antrian.get(i).getNama() + "-" + antrian.get(i).getPesananSize() + " item");
                            for (int j = 0; j < antrian.get(i).getPesananSize(); j++) {
                                System.out.println("\t- " + antrian.get(i).getPesananKe(j));
                            }
                        }
                    } else if (input == 3) {
                        //BUAT PESANAN
                        if (antrian.size() > 0) {
                            int counter = 0;
                            do {
                                int sum = 0;
                                int counter2 = 0;
                                do {
                                    for (Menu m : menu) {
                                        if (antrian.get(counter).getPesananKe(counter2).equals(m.getNama())) {
                                            sum += (int) m.getHarga();
                                            counter2++;
                                            break;
                                        }
                                    }
                                } while(counter2 != antrian.get(counter).getPesananSize());
                                System.out.println("Berhasil membuat semua pesanan milik " + antrian.get(counter).getNama() + "! (+Rp " + sum + ")");
                                antrian.remove(counter);
                                pendapatan += sum;
                            } while (counter != antrian.size());
                        }
                    } else if (input == 4) {
                        //TUTUP TOKO
                        System.out.println("Toko akan segera tutup!");
                        if (antrian.size() > 0) {
                            int counter = 0;
                            do {
                                if (antrian.get(counter).getPesananSize() > 5) {
                                    System.out.println("Pesanan milik " + antrian.get(counter).getNama() + " dibatalkan!");
                                    antrian.remove(counter);
                                } else {
                                    counter++;
                                }
                            }while(counter != antrian.size());
                        }
                    } else if (input == 5) {
                        //GANTI HARI
                        System.out.println("Daftar pelanggan yang belum dilayani : ");
                        int totalrugi = 0;
                        for (int i = 0; i < antrian.size(); i++) {
                            int sum = 0;
                            int counter2 = 0;
                            do {
                                for (Menu m : menu) {
                                    if (antrian.get(i).getPesananKe(counter2).equals(m.getNama())) {
                                        sum += (int) m.getHarga();
                                        counter2++;
                                        break;
                                    }
                                }
                            } while(counter2 != antrian.get(i).getPesananSize());
                            System.out.println((i+1) + ". " + antrian.get(i).getNama() + "-" + antrian.get(i).getPesananSize() + " Item - Rp " + sum);
                            totalrugi += sum;
                        }
                        System.out.println("Pendapatan hari ini terpotong Rp " + totalrugi + ".");
                        pendapatan -= totalrugi;
                        System.out.println("RESTO DITUTUP! Pendapatan hari ini : Rp " + pendapatan);
                    } else if (input == 0) {
                        break;
                    }
                } while (true);
            } else if (input == 0) {
                break;
            }
        }while(true);
    }
}
