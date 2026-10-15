# 😶‍🌫️ Sistem Manajemen Jasa Cuci Sepatu

 # 👤 Identitas
 | Data | Keterangan |
|---|---|
| 👩 Nama | **Dliya Syahla Hariyanto** |
| 🆔 NIM | **2509116095** |
| 📚 Program Studi | **Sistem Informasi** |
| 📖 Mata Kuliah | **Praktikum PBO** |

# 📌 Deskripsi Singkat
Sistem Manajemen Jasa Cuci Sepatu merupakan program berbasis Java yang dibuat untuk membantu pengelolaan layanan cuci sepatu. Program ini memiliki fitur untuk mengelola data pelanggan, data sepatu, dan transaksi layanan.

Program menyediakan fitur login dengan hak akses Admin dan Kasir. Admin dapat mengelola transaksi, sedangkan Kasir dapat melihat data transaksi dan mencetak struk. Program menggunakan MySQL sebagai database untuk menyimpan data agar dapat dikelola secara terstruktur.

# 2. Alur Program

1. Alur kerja program Sistem Manajemen Jasa Cuci Sepatu adalah sebagai berikut:

2. Program dijalankan melalui class utama.

3. Pengguna melakukan login sebagai Admin atau Kasir.

4. Program memeriksa data login dan memberikan akses sesuai peran pengguna.

5. Pengguna memilih menu yang tersedia sesuai hak aksesnya.

6. Jika pengguna melakukan operasi transaksi, Controller meneruskan proses ke DAO.

7. DAO menjalankan operasi database menggunakan JDBC untuk mengambil atau mengubah data pada MySQL.

8. Hasil pengolahan data ditampilkan kembali melalui menu program.
Pengguna dapat melihat data transaksi, mencari transaksi, mengubah status sesuai hak akses, menghapus transaksi jika diizinkan, atau mencetak struk sesuai fitur yang tersedia.

# 3. Tujuan Program
- Mempermudah pengelolaan data pelanggan dan sepatu.

- Membantu pencatatan transaksi jasa cuci sepatu.

- Mempermudah pencarian dan pengelolaan status transaksi.

- Menyimpan data secara permanen menggunakan database MySQL.

- Menerapkan konsep Pemrograman Berorientasi Objek dan JDBC.

# 4. fitur Program
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

# 5.  Struktur Program

<img width="388" height="472" alt="image" src="https://github.com/user-attachments/assets/4b5df535-43db-4b05-824c-12e760e9f11d" />


# 6. Penerapan Konsep PBO

## Encapsulation

Encapsulation merupakan konsep PBO yang digunakan untuk melindungi data di dalam class. Penerapannya dilakukan dengan menggunakan atribut yang memiliki access modifier seperti private, kemudian menyediakan method getter dan setter untuk mengakses atau mengubah data sesuai kebutuhan.

Pada program ini, konsep encapsulation diterapkan pada class model, seperti Pelanggan, Sepatu, dan Transaksi, untuk mengelola atribut objek dengan lebih terstruktur.

## Inheritance

Inheritance merupakan konsep pewarisan atribut dan method dari class induk ke class turunan.

Pada program ini, class Sepatu menjadi class induk, sedangkan SepatuBoot dan SepatuSneakers menjadi class turunan. Kedua class turunan tersebut mewarisi karakteristik umum sepatu dari class Sepatu, sehingga kode dapat digunakan kembali dan setiap jenis sepatu dapat memiliki perilaku yang sesuai.

## Polymorphism

Polymorphism merupakan kemampuan suatu objek untuk memiliki perilaku yang berbeda melalui satu konsep atau antarmuka yang sama.

Pada program ini, penerapannya terlihat pada penggunaan objek sepatu melalui tipe Sepatu, yang dapat merujuk pada objek SepatuBoot atau SepatuSneakers. Pemilihan objek dilakukan berdasarkan jenis sepatu yang digunakan dalam transaksi.

## Abstraction

Abstraction merupakan konsep untuk menyembunyikan detail implementasi dan menampilkan bagian penting yang dibutuhkan pengguna atau bagian program lainnya.

Pada program ini, class Sepatu digunakan sebagai class abstrak untuk merepresentasikan karakteristik umum sepatu. Class SepatuBoot dan SepatuSneakers kemudian digunakan untuk merepresentasikan jenis sepatu yang lebih spesifik.

Program juga memiliki CetakStruk sebagai interface untuk mendefinisikan perilaku yang berkaitan dengan pencetakan struk.

## Interface

Interface CetakStruk digunakan untuk mendefinisikan perilaku pencetakan struk transaksi.

# 6.Penerapan JDBC dan DAO

JDBC (Java Database Connectivity) digunakan untuk menghubungkan program Java dengan database MySQL. Melalui JDBC, program dapat menjalankan perintah SQL untuk mengelola data pelanggan, sepatu, dan transaksi.

Penerapan JDBC pada program ini menggunakan beberapa komponen berikut:

- Connection untuk membuat koneksi ke database MySQL.

- PreparedStatement untuk menjalankan perintah SQL dengan parameter.

- ResultSet untuk membaca hasil query yang mengambil data dari database.

- TransaksiDao untuk memisahkan proses akses database dari bagian program lainnya.

Dengan JDBC, program dapat melakukan operasi seperti menambahkan transaksi, menampilkan seluruh transaksi, mencari transaksi berdasarkan kata kunci, mengubah status transaksi, dan menghapus transaksi.

Penggunaan PreparedStatement juga membantu penanganan parameter query dengan lebih aman dibandingkan menyusun query dengan menggabungkan input pengguna secara langsung.

# 7. Alur Kerja Program

<img width="757" height="717" alt="image" src="https://github.com/user-attachments/assets/50b1c76f-ed64-4200-9418-c28917107534" />

# 8. Rancangan Database
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

# 10. 🔌 Koneksi Database dan DAO

Program Sistem Manajemen Jasa Cuci Sepatu menggunakan JDBC untuk menghubungkan aplikasi Java dengan database MySQL. Dengan koneksi ini, data transaksi dapat disimpan dan diambil dari database.

🗄️ A. JDBC (Java Database Connectivity)

JDBC merupakan teknologi yang digunakan untuk menghubungkan program Java dengan database. Pada program ini, JDBC digunakan untuk mengakses database jasa_cuci_sepatu.

Koneksi database diatur melalui class Koneksi.java, yang menyediakan koneksi agar aplikasi dapat menjalankan operasi database.

🔐 B. PreparedStatement

PreparedStatement digunakan untuk menjalankan perintah SQL dengan parameter. Penggunaannya membantu pengolahan input dan mengurangi risiko kesalahan saat memasukkan nilai ke dalam query SQL.

📂 C. DAO (Data Access Object)

DAO merupakan pola pemrograman yang memisahkan proses akses database dari bagian program lainnya.

Pada program ini, class TransaksiDao bertugas menangani operasi data transaksi, seperti:

Menambahkan data transaksi (Create).

Menampilkan data transaksi (Read).

Mengubah data transaksi (Update).

Menghapus data transaksi (Delete).

Mencari transaksi berdasarkan informasi yang tersedia.

Operasi yang tersedia mengikuti method yang diterapkan pada source code program.

🎮 D. Controller

Class TransaksiController bertugas mengatur alur pengolahan transaksi dan menghubungkan interaksi pengguna dengan proses pengolahan data.

🖥️ E. View

Bagian View menangani tampilan menu dan interaksi dengan pengguna. Pada rancangan program ini, class Menu digunakan untuk menampilkan pilihan menu yang dapat dijalankan pengguna.

📦 F. Model

- Model berisi class yang merepresentasikan data dan objek dalam aplikasi, seperti:

- Pelanggan untuk merepresentasikan data pelanggan.

- Sepatu untuk merepresentasikan data sepatu.

- Transaksi untuk merepresentasikan transaksi jasa cuci sepatu.

- Admin dan Kasir untuk merepresentasikan peran pengguna.

🔄 G. Alur Pengolahan Data

- Pengguna memilih menu pada bagian View.

- Controller memproses tindakan yang dipilih.

- DAO menjalankan operasi database menggunakan JDBC.

- Database MySQL memproses perintah SQL.

- Hasil pengolahan data dikembalikan untuk ditampilkan kepada pengguna.

# 10. 🔑 Akun Login dan Hak Akses Pengguna
Aplikasi Sistem Manajemen Jasa Cuci Sepatu menyediakan dua peran pengguna, yaitu Admin dan Kasir. Setiap peran memiliki hak akses yang berbeda sesuai dengan fitur yang tersedia pada program.

👨‍💼 A. Admin

Admin memiliki akses untuk mengelola transaksi jasa cuci sepatu, meliputi:

- Menambahkan transaksi baru.

- Melihat daftar transaksi.

- Mencari transaksi.

- Mengubah status transaksi.

- Menghapus transaksi.

- Mencetak struk transaksi.
🧾 B. Kasir

Kasir memiliki akses untuk menjalankan kegiatan operasional, meliputi:

- Melihat daftar transaksi.

- Mencari transaksi.

- Mencetak struk transaksi.

🔐 C. Informasi Login

Gunakan akun yang telah ditentukan pada source code program untuk masuk ke aplikasi. Username dan password harus sesuai dengan konfigurasi login yang digunakan.


# 11. Kesimpulan
Program Sistem Manajemen Jasa Cuci Sepatu merupakan aplikasi berbasis Java yang dibuat untuk membantu pengelolaan data pelanggan, data sepatu, dan transaksi layanan cuci sepatu. Program ini menerapkan konsep Object-Oriented Programming (OOP), seperti encapsulation, inheritance, polymorphism, dan abstraction, serta menggunakan JDBC untuk menghubungkan aplikasi dengan database MySQL.

Dengan adanya program ini, proses pengelolaan transaksi, pencarian data, perubahan status layanan, dan pencetakan struk dapat dilakukan secara lebih terstruktur. Diharapkan program ini dapat mempermudah pengelolaan jasa cuci sepatu dan menjadi sarana penerapan materi Pemrograman Berorientasi Objek dalam sebuah studi kasus nyata.
