package View;

import Controller.TransaksiController;
import Dao.TransaksiDao;
import Model.Pelanggan;
import Model.Sepatu;
import Model.SepatuBoot;
import Model.SepatuSneakers;
import Model.Transaksi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    private final TransaksiController controller;
    private final Scanner input;
    private final String role;

    public Menu(
            TransaksiController controller,
            String role,
            Scanner input) {

        this.controller = controller;
        this.role = role;
        this.input = input;
    }

    public void tampilkanMenu() {

        if (role.equalsIgnoreCase("Admin")) {
            tampilkanMenuAdmin();
        } else {
            tampilkanMenuKasir();
        }
    }


    private void tampilkanMenuAdmin() {

        int pilihan;

        do {

            System.out.println("\n========================================");
            System.out.println("     SISTEM MANAJEMEN JASA CUCI SEPATU");
            System.out.println("========================================");
            System.out.println("Login sebagai : ADMIN");
            System.out.println("========================================");
            System.out.println("1. Tambah Data");
            System.out.println("2. Lihat Data");
            System.out.println("3. Ubah Status Transaksi");
            System.out.println("4. Hapus Data");
            System.out.println("5. Cari Data");
            System.out.println("6. Cetak Struk");
            System.out.println("7. Keluar");
            System.out.println("========================================");

            pilihan = inputPilihan(
                    1,
                    7,
                    "Pilih menu: "
            );

            switch (pilihan) {

                case 1:
                    tambahData();
                    break;

                case 2:
                    lihatData();
                    break;

                case 3:
                    ubahStatus();
                    break;

                case 4:
                    hapusData();
                    break;

                case 5:
                    cariData();
                    break;

                case 6:
                    cetakStruk();
                    break;

                case 7:
                    System.out.println(
                            "Terima kasih telah menggunakan program."
                    );
                    break;
            }

        } while (pilihan != 7);
    }


    private void tampilkanMenuKasir() {

        int pilihan;

        do {

            System.out.println("\n========================================");
            System.out.println("     SISTEM MANAJEMEN JASA CUCI SEPATU");
            System.out.println("========================================");
            System.out.println("Login sebagai : KASIR");
            System.out.println("========================================");
            System.out.println("1. Lihat Data");
            System.out.println("2. Cari Data");
            System.out.println("3. Cetak Struk");
            System.out.println("4. Keluar");
            System.out.println("========================================");

            pilihan = inputPilihan(
                    1,
                    4,
                    "Pilih menu: "
            );

            switch (pilihan) {

                case 1:
                    lihatData();
                    break;

                case 2:
                    cariData();
                    break;

                case 3:
                    cetakStruk();
                    break;

                case 4:
                    System.out.println(
                            "Terima kasih telah menggunakan program."
                    );
                    break;
            }

        } while (pilihan != 4);
    }


    private void tambahData() {

        System.out.println("\n========== TAMBAH DATA ==========");


        TransaksiDao transaksiDao =
                new TransaksiDao();

        String idPelanggan =
                transaksiDao.generateIdPelanggan();

        String idSepatu =
                transaksiDao.generateIdSepatu();

        String idTransaksi =
                transaksiDao.generateIdTransaksi();

        System.out.println(
                "ID Pelanggan : " + idPelanggan
        );

        System.out.println(
                "ID Sepatu    : " + idSepatu
        );

        System.out.println(
                "ID Transaksi : " + idTransaksi
        );


        String nama =
                inputNama("Nama Pelanggan: ");

        String telepon =
                inputTelepon("No. Telepon: ");

        String alamat =
                inputAlamat("Alamat: ");


        System.out.println("\nJenis Sepatu");
        System.out.println("1. Sneakers");
        System.out.println("2. Boots");

        int jenis =
                inputPilihan(
                        1,
                        2,
                        "Pilih jenis sepatu: "
                );

        String merek =
                inputTeks(
                        "Merek Sepatu: ",
                        "Merek"
                );

        String warna =
                inputTeks(
                        "Warna Sepatu: ",
                        "Warna"
                );


        System.out.println("\nJenis Layanan");
        System.out.println("1. Fast Clean - Rp20000");
        System.out.println("2. Deep Clean - Rp35000");

        int layanan =
                inputPilihan(
                        1,
                        2,
                        "Pilih layanan: "
                );

        String namaLayanan;
        int harga;

        if (layanan == 1) {

            namaLayanan = "Fast Clean";
            harga = 20000;

        } else {

            namaLayanan = "Deep Clean";
            harga = 35000;
        }


        String tanggal =
                LocalDate.now().format(
                        DateTimeFormatter.ofPattern(
                                "dd-MM-yyyy"
                        )
                );


        Pelanggan pelanggan =
                new Pelanggan(
                        idPelanggan,
                        nama,
                        telepon,
                        alamat,
                        false
                );


        Sepatu sepatu;

        if (jenis == 1) {

            sepatu =
                    new SepatuSneakers(
                            idSepatu,
                            merek,
                            warna
                    );

        } else {

            sepatu =
                    new SepatuBoot(
                            idSepatu,
                            merek,
                            warna
                    );
        }


        Transaksi transaksi =
                new Transaksi(
                        idTransaksi,
                        pelanggan,
                        sepatu,
                        namaLayanan,
                        harga,
                        tanggal,
                        "Menunggu"
                );


        if (transaksiDao.tambah(
                pelanggan,
                sepatu,
                transaksi)) {

            controller.tambah(transaksi);

            System.out.println(
                    "\nData berhasil ditambahkan!"
            );


            if (transaksi.getPersentaseDiskon() > 0) {

                System.out.println(
                        "\n========================================"
                );

                System.out.println(
                        "             SURPRISE PROMO!"
                );

                System.out.println(
                        "========================================"
                );

                if (transaksi.getPersentaseDiskon()
                        == 10) {

                    System.out.println(
                            "Wow, kamu beruntung!"
                    );

                    System.out.println(
                            "Kamu mendapatkan diskon 10%!"
                    );

                } else {

                    System.out.println(
                            "Selamat! Kamu mendapatkan diskon 5%!"
                    );
                }

                System.out.println(
                        "----------------------------------------"
                );

                System.out.println(
                        "Harga Awal  : Rp"
                        + transaksi.getHarga()
                );

                System.out.println(
                        "Promo       : "
                        + transaksi.getPromo()
                );

                System.out.println(
                        "Potongan    : Rp"
                        + (int) transaksi.getDiskon()
                );

                System.out.println(
                        "Total Bayar : Rp"
                        + (int) transaksi.getTotalHarga()
                );

                System.out.println(
                        "========================================"
                );

            } else {

                System.out.println(
                        "\nKali ini belum mendapatkan promo."
                );

                System.out.println(
                        "Total Bayar : Rp"
                        + (int) transaksi.getTotalHarga()
                );
            }

            System.out.println(
                    "\nDetail Transaksi:"
            );

            transaksi.tampilkanData(true);

        } else {

            System.out.println(
                    "Data gagal ditambahkan."
            );
        }
    }


    private void lihatData() {

        System.out.println(
                "\n========== DATA TRANSAKSI =========="
        );

        TransaksiDao transaksiDao =
                new TransaksiDao();

        ArrayList<Transaksi> daftarTransaksi =
                transaksiDao.getAll();

        if (daftarTransaksi.isEmpty()) {

            System.out.println(
                    "Belum ada data transaksi."
            );

            return;
        }

        for (Transaksi transaksi :
                daftarTransaksi) {

            System.out.println(
                    "\n----------------------------------------"
            );

            transaksi.tampilkanData();
        }
    }


    private Transaksi pilihTransaksi() {


        TransaksiDao transaksiDao =
                new TransaksiDao();

        ArrayList<Transaksi> daftarTransaksi =
                transaksiDao.getAll();

        if (daftarTransaksi.isEmpty()) {

            System.out.println(
                    "Belum ada data transaksi."
            );

            return null;
        }

        System.out.println(
                "\n========== PILIH TRANSAKSI =========="
        );

        for (int i = 0;
                i < daftarTransaksi.size();
                i++) {

            Transaksi t =
                    daftarTransaksi.get(i);

            System.out.println(
                    (i + 1)
                    + ". "
                    + t.getIdTransaksi()
                    + " | "
                    + t.getPelanggan().getNama()
                    + " | "
                    + t.getSepatu().getMerek()
                    + " | "
                    + t.getStatus()
            );
        }

        System.out.println("0. Kembali");

        int pilihan =
                inputPilihan(
                        0,
                        daftarTransaksi.size(),
                        "Pilih transaksi: "
                );

        if (pilihan == 0) {
            return null;
        }

        return daftarTransaksi.get(
                pilihan - 1
        );
    }


    private void ubahStatus() {

        System.out.println(
                "\n========== UBAH STATUS =========="
        );

        Transaksi t =
                pilihTransaksi();

        if (t == null) {
            return;
        }

        String status =
                t.getStatus();

        if (status.equalsIgnoreCase("Diambil")
                || status.equalsIgnoreCase("Batal")) {

            System.out.println(
                    "Status transaksi sudah final "
                    + "dan tidak dapat diubah."
            );

            return;
        }

        System.out.println(
                "Status saat ini: "
                + status
        );

        String statusBaru;

        if (status.equalsIgnoreCase("Menunggu")) {

            System.out.println("1. Diproses");
            System.out.println("2. Batal");

            int pilihan =
                    inputPilihan(
                            1,
                            2,
                            "Pilih status: "
                    );

            if (pilihan == 1) {

                statusBaru = "Diproses";

            } else {

                statusBaru = "Batal";
            }

        } else if (
                status.equalsIgnoreCase("Diproses")
        ) {

            System.out.println("1. Selesai");
            System.out.println("2. Batal");

            int pilihan =
                    inputPilihan(
                            1,
                            2,
                            "Pilih status: "
                    );

            if (pilihan == 1) {

                statusBaru = "Selesai";

            } else {

                statusBaru = "Batal";
            }

        } else if (
                status.equalsIgnoreCase("Selesai")
        ) {

            System.out.println("1. Diambil");
            System.out.println("2. Batal");

            int pilihan =
                    inputPilihan(
                            1,
                            2,
                            "Pilih status: "
                    );

            if (pilihan == 1) {

                statusBaru = "Diambil";

            } else {

                statusBaru = "Batal";
            }

        } else {

            System.out.println(
                    "Status tidak dikenali."
            );

            return;
        }


        TransaksiDao transaksiDao =
                new TransaksiDao();

        if (transaksiDao.updateStatus(
                t.getIdTransaksi(),
                statusBaru)) {

            System.out.println(
                    "Status berhasil diubah menjadi "
                    + statusBaru + "."
            );

        } else {

            System.out.println(
                    "Status gagal diubah."
            );
        }
    }


    private void hapusData() {

        System.out.println(
                "\n========== HAPUS DATA =========="
        );

        Transaksi t =
                pilihTransaksi();

        if (t == null) {
            return;
        }

        if (t.getStatus().equalsIgnoreCase("Diambil")) {

            System.out.println(
                    "Transaksi yang sudah Diambil "
                    + "tidak dapat dihapus."
            );

            return;
        }

        String jawaban =
                inputKonfirmasi(
                        "Yakin ingin menghapus? (Y/T): "
                );

        if (jawaban.equals("Y")) {

            TransaksiDao transaksiDao =
                    new TransaksiDao();

            if (transaksiDao.hapus(
                    t.getIdTransaksi())) {

        

                controller.hapus(
                        t.getIdTransaksi()
                );

                System.out.println(
                        "Data berhasil dihapus dari database."
                );

            } else {

                System.out.println(
                        "Data gagal dihapus."
                );
            }

        } else {

            System.out.println(
                    "Penghapusan dibatalkan."
            );
        }
    }

    private void cariData() {

        System.out.println(
                "\n========== CARI DATA =========="
        );

        System.out.print(
                "Masukkan ID transaksi atau nama pelanggan: "
        );

        String keyword =
                input.nextLine().trim();

        if (keyword.isEmpty()) {

            System.out.println(
                    "Kata pencarian tidak boleh kosong."
            );

            return;
        }

        TransaksiDao transaksiDao =
                new TransaksiDao();

        ArrayList<Transaksi> hasil =
                transaksiDao.cari(keyword);

        if (hasil.isEmpty()) {

            System.out.println(
                    "Data tidak ditemukan."
            );

            return;
        }

        System.out.println(
                "\nData ditemukan:"
        );

        for (Transaksi transaksi :
                hasil) {

            System.out.println(
                    "\n----------------------------------------"
            );

            transaksi.tampilkanData(true);
        }
    }

    private void cetakStruk() {

        System.out.println(
                "\n========== CETAK STRUK =========="
        );

        Transaksi t =
                pilihTransaksi();

        if (t == null) {
            return;
        }

        if (!t.getStatus()
                .equalsIgnoreCase("Diambil")) {

            System.out.println(
                    "Struk hanya dapat dicetak "
                    + "setelah status Diambil."
            );

            return;
        }

        t.cetak();
    }
    private String inputNama(
            String pesan) {

        while (true) {

            System.out.print(pesan);

            String nama =
                    input.nextLine().trim();

            if (!nama.matches(
                    "[\\p{L}]+( [\\p{L}]+)*")) {

                System.out.println(
                        "Nama hanya boleh berisi "
                        + "huruf dan spasi antar kata."
                );

                continue;
            }

            int jumlahHuruf =
                    nama.replace(" ", "").length();

            if (jumlahHuruf < 3
                    || jumlahHuruf > 20) {

                System.out.println(
                        "Nama terlalu pendek/panjang. "
                        + "Minimal 3 huruf dan maksimal 20 huruf."
                );

                continue;
            }

            return nama;
        }
    }

    private String inputTelepon(
            String pesan) {

        while (true) {

            System.out.print(pesan);

            String nomor =
                    input.nextLine().trim();

            if (!nomor.matches(
                    "08\\d{8,11}")) {

                System.out.println(
                        "Nomor telepon harus diawali 08 "
                        + "dan terdiri dari 10-13 digit."
                );

                continue;
            }

            return nomor;
        }
    }
    private String inputAlamat(
            String pesan) {

        while (true) {

            System.out.print(pesan);

            String alamat =
                    input.nextLine().trim();

            if (alamat.length() < 5
                    || alamat.length() > 100
                    || !alamat.matches(
                            ".*[\\p{L}].*")) {

                System.out.println(
                        "Alamat harus 5-100 karakter "
                        + "dan mengandung huruf."
                );

                continue;
            }

            return alamat;
        }
    }

    private String inputTeks(
            String pesan,
            String jenis) {

        while (true) {

            System.out.print(pesan);

            String teks =
                    input.nextLine().trim();

            if (teks.length() < 2
                    || teks.length() > 30
                    || !teks.matches(
                            ".*[\\p{L}].*")) {

                System.out.println(
                        jenis
                        + " harus 2-30 karakter "
                        + "dan mengandung huruf."
                );

                continue;
            }

            return teks;
        }
    }
    private int inputPilihan(
            int minimum,
            int maksimum,
            String pesan) {

        while (true) {

            System.out.print(pesan);

            String nilai =
                    input.nextLine().trim();

            if (!nilai.matches("\\d+")) {

                System.out.println(
                        "Masukkan angka "
                        + minimum
                        + "-"
                        + maksimum
                        + "."
                );

                continue;
            }

            try {

                int pilihan =
                        Integer.parseInt(nilai);

                if (pilihan < minimum
                        || pilihan > maksimum) {

                    System.out.println(
                            "Pilihan tidak tersedia."
                    );

                    continue;
                }

                return pilihan;

            } catch (NumberFormatException e) {

                System.out.println(
                        "Angka terlalu besar."
                );
            }
        }
    }
    private String inputKonfirmasi(
            String pesan) {

        while (true) {

            System.out.print(pesan);

            String jawaban =
                    input.nextLine()
                            .trim()
                            .toUpperCase();

            if (jawaban.equals("Y")
                    || jawaban.equals("T")) {

                return jawaban;
            }

            System.out.println(
                    "Masukkan Y atau T."
            );
        }
    }
}
