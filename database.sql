

CREATE DATABASE IF NOT EXISTS jasa_cuci_sepatu;

USE jasa_cuci_sepatu;



CREATE TABLE IF NOT EXISTS pelanggan (
    id_pelanggan VARCHAR(10) PRIMARY KEY,
    nama VARCHAR(50) NOT NULL,
    no_telepon VARCHAR(15) NOT NULL,
    alamat VARCHAR(100) NOT NULL,
    member BOOLEAN NOT NULL
);



CREATE TABLE IF NOT EXISTS sepatu (
    id_sepatu VARCHAR(10) PRIMARY KEY,
    merek VARCHAR(50) NOT NULL,
    warna VARCHAR(30) NOT NULL,
    jenis VARCHAR(30) NOT NULL
);



CREATE TABLE IF NOT EXISTS transaksi (
    id_transaksi VARCHAR(10) PRIMARY KEY,
    id_pelanggan VARCHAR(10) NOT NULL,
    id_sepatu VARCHAR(10) NOT NULL,
    jenis_layanan VARCHAR(30) NOT NULL,
    harga INT NOT NULL,
    tanggal VARCHAR(20) NOT NULL,
    promo VARCHAR(30),
    persentase_diskon INT NOT NULL,
    status VARCHAR(20) NOT NULL,

    FOREIGN KEY (id_pelanggan)
        REFERENCES pelanggan(id_pelanggan),

    FOREIGN KEY (id_sepatu)
        REFERENCES sepatu(id_sepatu)
);



INSERT INTO pelanggan
(id_pelanggan, nama, no_telepon, alamat, member)
VALUES
('PL001', 'Syahla', '081234567890', 'Samarinda', TRUE),
('PL002', 'Cala', '082345678901', 'Bontang', FALSE);



INSERT INTO sepatu
(id_sepatu, merek, warna, jenis)
VALUES
('SP001', 'Nike', 'Putih', 'Sneakers'),
('SP002', 'Dr. Martens', 'Hitam', 'Boots');


INSERT INTO transaksi
(
    id_transaksi,
    id_pelanggan,
    id_sepatu,
    jenis_layanan,
    harga,
    tanggal,
    promo,
    persentase_diskon,
    status
)
VALUES
(
    'TR001',
    'PL001',
    'SP001',
    'Deep Clean',
    35000,
    '08-10-2026',
    'Diskon 10%',
    10,
    'Menunggu'
),
(
    'TR002',
    'PL002',
    'SP002',
    'Fast Clean',
    20000,
    '08-10-2026',
    'Tidak ada promo',
    0,
    'Diproses'
);
