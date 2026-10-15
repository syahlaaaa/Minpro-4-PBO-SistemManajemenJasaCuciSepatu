# 😶‍🌫️ Sistem Manajemen Jasa Cuci Sepatu

 # 👤 Identitas
 | Data | Keterangan |
|---|---|
| 👩 Nama | **Dliya Syahla Hariyanto** |
| 🆔 NIM | **2509116095** |
| 📚 Program Studi | **Sistem Informasi** |
| 📖 Mata Kuliah | **Praktikum PBO** |

# 📌 Deskripsi Singkat
Sistem Manajemen Jasa Cuci Sepatu adalah aplikasi berbasis Java yang digunakan untuk membantu proses pengelolaan jasa cuci sepatu. Program ini dikembangkan sebagai proyek mata kuliah Pemrograman Berorientasi Objek (PBO) dan menggunakan database MySQL untuk menyimpan data.

Aplikasi ini memiliki sistem login dengan dua peran, yaitu Admin dan Kasir. Data pelanggan, sepatu, dan transaksi dikelola melalui aplikasi dan disimpan ke dalam database agar dapat diakses kembali.

# 2. Tujuan Program
- Mempermudah pengelolaan data pelanggan dan sepatu.

- Membantu pencatatan transaksi jasa cuci sepatu.

- Mempermudah pencarian dan pengelolaan status transaksi.

- Menyimpan data secara permanen menggunakan database MySQL.

- Menerapkan konsep Pemrograman Berorientasi Objek dan JDBC.

# 3. fitur Program
  A. Login Pengguna

- Login menggunakan akun Admin atau Kasir.

- Validasi username dan password.

- Pembatasan percobaan login maksimal tiga kali.

B. Pengelolaan Transaksi

- Menambahkan transaksi jasa cuci sepatu.

- Menampilkan daftar transaksi yang tersimpan di database.

- Mengubah status transaksi.

- Menghapus transaksi.

- Mencari transaksi berdasarkan kata kunci.

C. Pengelolaan Data Pelanggan dan Sepatu

- Menyimpan data pelanggan yang melakukan pemesanan.

- Menyimpan informasi sepatu, seperti merek, warna, dan jenis sepatu.

- Menghubungkan data pelanggan dan sepatu dengan transaksi.

D. Layanan dan Pembayaran

- Memilih jenis layanan pencucian sepatu.

- Menghitung harga layanan dan diskon sesuai ketentuan program.

- Menampilkan informasi promo yang tersedia.

E. Cetak Struk

- Menampilkan struk transaksi yang berisi informasi pelanggan, sepatu, layanan, harga, diskon, dan status transaksi.

F. Penyimpanan Database

- Menggunakan database MySQL untuk menyimpan data.

- Menggunakan JDBC dan PreparedStatement untuk menjalankan query SQL.

- Menggunakan DAO untuk memisahkan proses pengelolaan data dari bagian tampilan program.

G. Fitur Tambahan

- Pembuatan ID pelanggan, sepatu, dan transaksi secara otomatis.

- Validasi input untuk mengurangi kesalahan saat memasukkan data.

- Animasi loading saat proses masuk ke menu utama.

# 4.  Struktur Program
<img width="388" height="472" alt="image" src="https://github.com/user-attachments/assets/4b5df535-43db-4b05-824c-12e760e9f11d" />

# 5. Struktur Database

Database yang digunakan bernama jasa_cuci_sepatu dan terdiri dari tiga tabel utama.

# Tabel pelanggan

- Menyimpan informasi pelanggan.

- id_pelanggan: ID unik pelanggan.

- nama: Nama pelanggan.

- no_telepon: Nomor telepon pelanggan.

- alamat: Alamat pelanggan.

- member: Status keanggotaan pelanggan.

# Tabel sepatu

- Menyimpan informasi sepatu milik pelanggan.

- id_sepatu: ID unik sepatu.

- merek: Merek sepatu.

- warna: Warna sepatu.
  
- jenis: Jenis sepatu.

  # Tabel transaksi

Menyimpan informasi transaksi jasa cuci sepatu.

- id_transaksi: ID unik transaksi.

- id_pelanggan: Referensi ke tabel pelanggan.

- id_sepatu: Referensi ke tabel sepatu.

- jenis_layanan: Jenis layanan pencucian.

- harga: Harga layanan.

- tanggal: Tanggal transaksi.

- promo: Informasi promo.

- persentase_diskon: Besaran diskon.

- status: Status transaksi.

- Relasi antar tabel menggunakan foreign key untuk menghubungkan transaksi dengan pelanggan dan sepatu.

# 6. Penerapan Konsep PBO

## Encapsulation

Encapsulation diterapkan dengan menggunakan atribut private dan method getter/setter untuk mengatur akses terhadap data objek.

## Inheritance

Inheritance diterapkan melalui class Sepatu sebagai class induk dan class SepatuBoot serta SepatuSneakers sebagai class turunan.

## Polymorphism

Polymorphism diterapkan melalui method overriding dan/atau overloading sesuai implementasi method pada class yang digunakan.

## Abstraction

Abstraction diterapkan melalui class abstrak Sepatu untuk mendefinisikan perilaku umum yang dapat diterapkan oleh class turunannya.

## Interface

Interface CetakStruk digunakan untuk mendefinisikan perilaku pencetakan struk transaksi.

# 7.Penerapan JDBC dan DAO

Aplikasi menggunakan JDBC (Java Database Connectivity) untuk menghubungkan program Java dengan database MySQL.

Class koneksi bertugas membuat koneksi ke database. Class DAO (Data Access Object) bertugas menjalankan operasi pengelolaan data, seperti menambah, menampilkan, mengubah, menghapus, dan mencari data.

PreparedStatement digunakan untuk menjalankan query SQL dengan parameter sehingga pengisian nilai ke query lebih terstruktur dan membantu mencegah SQL injection.

# 8. Alur Kerja Program

<img width="757" height="717" alt="image" src="https://github.com/user-attachments/assets/50b1c76f-ed64-4200-9418-c28917107534" />

# 9. Rancangan Database
### 📋 1. Tabel Pelanggan (`pelanggan`)

| Data | Keterangan |
|:---|:---|
| 🆔 `id_pelanggan` | `VARCHAR(10)` — Primary Key |
| 👤 `nama` | `VARCHAR(50)` — Nama pelanggan |
| 📞 `no_telepon` | `VARCHAR(15)` — Nomor telepon pelanggan |
| 🏠 `alamat` | `VARCHAR(100)` — Alamat pelanggan |
| ⭐ `member` | `BOOLEAN` — Status keanggotaan pelanggan |

### 👟 2. Tabel Sepatu (`sepatu`)

| Data | Keterangan |
|:---|:---|
| 🆔 `id_sepatu` | `VARCHAR(10)` — Primary Key |
| 🏷️ `merek` | `VARCHAR(50)` — Merek sepatu |
| 🎨 `warna` | `VARCHAR(30)` — Warna sepatu |
| 👟 `jenis` | `VARCHAR(30)` — Jenis sepatu |

### 🧾 3. Tabel Transaksi (`transaksi`)

| Data | Keterangan |
|:---|:---|
| 🆔 `id_transaksi` | `VARCHAR(10)` — Primary Key |
| 👤 `id_pelanggan` | `VARCHAR(10)` — Foreign Key |
| 👟 `id_sepatu` | `VARCHAR(10)` — Foreign Key |
| 🧼 `jenis_layanan` | `VARCHAR(30)` — Jenis layanan |
| 💰 `harga` | `INT` — Harga layanan |
| 📅 `tanggal` | `VARCHAR(20)` — Tanggal transaksi |
| 🎁 `promo` | `VARCHAR(30)` — Informasi promo |
| 🔖 `persentase_diskon` | `INT` — Persentase diskon |
| 📌 `status` | `VARCHAR(20)` — Status transaksi |
