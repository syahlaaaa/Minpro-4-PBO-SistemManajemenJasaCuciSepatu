package Dao;

import Database.Koneksi;
import Model.Pelanggan;
import Model.Sepatu;
import Model.SepatuBoot;
import Model.SepatuSneakers;
import Model.Transaksi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TransaksiDao {


    public ArrayList<Transaksi> getAll() {

        ArrayList<Transaksi> daftar = new ArrayList<>();

        String sql = """
            SELECT
                t.id_transaksi,
                t.jenis_layanan,
                t.harga,
                t.tanggal,
                t.promo,
                t.persentase_diskon,
                t.status,

                p.id_pelanggan,
                p.nama,
                p.no_telepon,
                p.alamat,
                p.member,

                s.id_sepatu,
                s.merek,
                s.warna,
                s.jenis

            FROM transaksi t

            JOIN pelanggan p
                ON t.id_pelanggan = p.id_pelanggan

            JOIN sepatu s
                ON t.id_sepatu = s.id_sepatu
        """;

        try (
            Connection conn = Koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {


                Pelanggan pelanggan =
                        new Pelanggan(
                                rs.getString("id_pelanggan"),
                                rs.getString("nama"),
                                rs.getString("no_telepon"),
                                rs.getString("alamat"),
                                rs.getBoolean("member")
                        );

                Sepatu sepatu;

                if (rs.getString("jenis")
                        .equalsIgnoreCase("Sneakers")) {

                    sepatu =
                            new SepatuSneakers(
                                    rs.getString("id_sepatu"),
                                    rs.getString("merek"),
                                    rs.getString("warna")
                            );

                } else {

                    sepatu =
                            new SepatuBoot(
                                    rs.getString("id_sepatu"),
                                    rs.getString("merek"),
                                    rs.getString("warna")
                            );
                }


                Transaksi transaksi =
                        new Transaksi(
                                rs.getString("id_transaksi"),
                                pelanggan,
                                sepatu,
                                rs.getString("jenis_layanan"),
                                rs.getInt("harga"),
                                rs.getString("tanggal"),
                                rs.getString("status")
                        );

                daftar.add(transaksi);
            }

        } catch (Exception e) {

            System.out.println(
                    "Gagal mengambil data: "
                    + e.getMessage()
            );
        }

        return daftar;
    }


    public boolean tambah(
            Pelanggan pelanggan,
            Sepatu sepatu,
            Transaksi transaksi) {

        String sqlPelanggan = """
            INSERT INTO pelanggan
            (id_pelanggan, nama, no_telepon, alamat, member)
            VALUES (?, ?, ?, ?, ?)
        """;

        String sqlSepatu = """
            INSERT INTO sepatu
            (id_sepatu, merek, warna, jenis)
            VALUES (?, ?, ?, ?)
        """;

        String sqlTransaksi = """
            INSERT INTO transaksi
            (id_transaksi, id_pelanggan, id_sepatu,
             jenis_layanan, harga, tanggal, promo,
             persentase_diskon, status)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = Koneksi.getConnection()) {

            conn.setAutoCommit(false);

            try (
                PreparedStatement psPelanggan =
                        conn.prepareStatement(sqlPelanggan);

                PreparedStatement psSepatu =
                        conn.prepareStatement(sqlSepatu);

                PreparedStatement psTransaksi =
                        conn.prepareStatement(sqlTransaksi)
            ) {


                psPelanggan.setString(
                        1,
                        pelanggan.getIdPelanggan()
                );

                psPelanggan.setString(
                        2,
                        pelanggan.getNama()
                );

                psPelanggan.setString(
                        3,
                        pelanggan.getNoTelepon()
                );

                psPelanggan.setString(
                        4,
                        pelanggan.getAlamat()
                );

                psPelanggan.setBoolean(
                        5,
                        pelanggan.isMember()
                );

                psPelanggan.executeUpdate();


                psSepatu.setString(
                        1,
                        sepatu.getIdSepatu()
                );

                psSepatu.setString(
                        2,
                        sepatu.getMerek()
                );

                psSepatu.setString(
                        3,
                        sepatu.getWarna()
                );

                if (sepatu instanceof SepatuSneakers) {

                    psSepatu.setString(
                            4,
                            "Sneakers"
                    );

                } else {

                    psSepatu.setString(
                            4,
                            "Boots"
                    );
                }

                psSepatu.executeUpdate();


                psTransaksi.setString(
                        1,
                        transaksi.getIdTransaksi()
                );

                psTransaksi.setString(
                        2,
                        pelanggan.getIdPelanggan()
                );

                psTransaksi.setString(
                        3,
                        sepatu.getIdSepatu()
                );

                psTransaksi.setString(
                        4,
                        transaksi.getJenisLayanan()
                );

                psTransaksi.setInt(
                        5,
                        transaksi.getHarga()
                );

                psTransaksi.setString(
                        6,
                        transaksi.getTanggal()
                );

                psTransaksi.setString(
                        7,
                        transaksi.getPromo()
                );

                psTransaksi.setInt(
                        8,
                        transaksi.getPersentaseDiskon()
                );

                psTransaksi.setString(
                        9,
                        transaksi.getStatus()
                );

                psTransaksi.executeUpdate();


                conn.commit();

                return true;

            } catch (Exception e) {

                conn.rollback();

                System.out.println(
                        "Gagal menyimpan data: "
                        + e.getMessage()
                );

                return false;
            }

        } catch (Exception e) {

            System.out.println(
                    "Gagal terhubung ke database: "
                    + e.getMessage()
            );

            return false;
        }
    }


    public boolean updateStatus(
            String idTransaksi,
            String status) {

        String sql = """
            UPDATE transaksi
            SET status = ?
            WHERE id_transaksi = ?
        """;

        try (
            Connection conn = Koneksi.getConnection();
            PreparedStatement ps =
                    conn.prepareStatement(sql)
        ) {

            ps.setString(
                    1,
                    status
            );

            ps.setString(
                    2,
                    idTransaksi
            );

            int hasil =
                    ps.executeUpdate();

            return hasil > 0;

        } catch (Exception e) {

            System.out.println(
                    "Gagal mengubah status: "
                    + e.getMessage()
            );

            return false;
        }
    }


    public boolean hapus(
            String idTransaksi) {

        String sqlCari = """
            SELECT id_pelanggan, id_sepatu
            FROM transaksi
            WHERE id_transaksi = ?
        """;

        String sqlTransaksi = """
            DELETE FROM transaksi
            WHERE id_transaksi = ?
        """;

        String sqlPelanggan = """
            DELETE FROM pelanggan
            WHERE id_pelanggan = ?
        """;

        String sqlSepatu = """
            DELETE FROM sepatu
            WHERE id_sepatu = ?
        """;

        try (Connection conn =
                Koneksi.getConnection()) {

            conn.setAutoCommit(false);

            try (
                PreparedStatement psCari =
                        conn.prepareStatement(sqlCari);

                PreparedStatement psTransaksi =
                        conn.prepareStatement(sqlTransaksi);

                PreparedStatement psPelanggan =
                        conn.prepareStatement(sqlPelanggan);

                PreparedStatement psSepatu =
                        conn.prepareStatement(sqlSepatu)
            ) {


                psCari.setString(
                        1,
                        idTransaksi
                );

                ResultSet rs =
                        psCari.executeQuery();

                if (!rs.next()) {

                    conn.rollback();

                    return false;
                }

                String idPelanggan =
                        rs.getString(
                                "id_pelanggan"
                        );

                String idSepatu =
                        rs.getString(
                                "id_sepatu"
                        );

                psTransaksi.setString(
                        1,
                        idTransaksi
                );

                psTransaksi.executeUpdate();


                psPelanggan.setString(
                        1,
                        idPelanggan
                );

                psPelanggan.executeUpdate();


                psSepatu.setString(
                        1,
                        idSepatu
                );

                psSepatu.executeUpdate();


                conn.commit();

                return true;

            } catch (Exception e) {

                conn.rollback();

                System.out.println(
                        "Gagal menghapus data: "
                        + e.getMessage()
                );

                return false;
            }

        } catch (Exception e) {

            System.out.println(
                    "Gagal terhubung ke database: "
                    + e.getMessage()
            );

            return false;
        }
    }


    public ArrayList<Transaksi> cari(
            String keyword) {

        ArrayList<Transaksi> daftar =
                new ArrayList<>();

        String sql = """
            SELECT
                t.id_transaksi,
                t.jenis_layanan,
                t.harga,
                t.tanggal,
                t.promo,
                t.persentase_diskon,
                t.status,

                p.id_pelanggan,
                p.nama,
                p.no_telepon,
                p.alamat,
                p.member,

                s.id_sepatu,
                s.merek,
                s.warna,
                s.jenis

            FROM transaksi t

            JOIN pelanggan p
                ON t.id_pelanggan = p.id_pelanggan

            JOIN sepatu s
                ON t.id_sepatu = s.id_sepatu

            WHERE t.id_transaksi LIKE ?
               OR p.id_pelanggan LIKE ?
               OR p.nama LIKE ?
               OR s.id_sepatu LIKE ?
               OR s.merek LIKE ?
               OR t.jenis_layanan LIKE ?
        """;

        try (
            Connection conn =
                    Koneksi.getConnection();

            PreparedStatement ps =
                    conn.prepareStatement(sql)
        ) {

            String pola =
                    "%" + keyword + "%";


            ps.setString(
                    1,
                    pola
            );

            ps.setString(
                    2,
                    pola
            );

            ps.setString(
                    3,
                    pola
            );

            ps.setString(
                    4,
                    pola
            );

            ps.setString(
                    5,
                    pola
            );

            ps.setString(
                    6,
                    pola
            );


            ResultSet rs =
                    ps.executeQuery();


            while (rs.next()) {


                Pelanggan pelanggan =
                        new Pelanggan(
                                rs.getString(
                                        "id_pelanggan"
                                ),

                                rs.getString(
                                        "nama"
                                ),

                                rs.getString(
                                        "no_telepon"
                                ),

                                rs.getString(
                                        "alamat"
                                ),

                                rs.getBoolean(
                                        "member"
                                )
                        );


                Sepatu sepatu;

                if (rs.getString("jenis")
                        .equalsIgnoreCase(
                                "Sneakers"
                        )) {

                    sepatu =
                            new SepatuSneakers(
                                    rs.getString(
                                            "id_sepatu"
                                    ),

                                    rs.getString(
                                            "merek"
                                    ),

                                    rs.getString(
                                            "warna"
                                    )
                            );

                } else {

                    sepatu =
                            new SepatuBoot(
                                    rs.getString(
                                            "id_sepatu"
                                    ),

                                    rs.getString(
                                            "merek"
                                    ),

                                    rs.getString(
                                            "warna"
                                    )
                            );
                }


                Transaksi transaksi =
                        new Transaksi(
                                rs.getString(
                                        "id_transaksi"
                                ),

                                pelanggan,

                                sepatu,

                                rs.getString(
                                        "jenis_layanan"
                                ),

                                rs.getInt(
                                        "harga"
                                ),

                                rs.getString(
                                        "tanggal"
                                ),

                                rs.getString(
                                        "status"
                                )
                        );


                daftar.add(transaksi);
            }

        } catch (Exception e) {

            System.out.println(
                    "Gagal mencari data: "
                    + e.getMessage()
            );
        }

        return daftar;
    }


    public String generateIdPelanggan() {

        String sql = """
            SELECT MAX(
                CAST(
                    SUBSTRING(id_pelanggan, 3)
                    AS UNSIGNED
                )
            )
            FROM pelanggan
        """;

        try (
            Connection conn =
                    Koneksi.getConnection();

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery()
        ) {

            if (rs.next()) {

                int nomor =
                        rs.getInt(1);

                if (rs.wasNull()) {
                    nomor = 0;
                }

                return String.format(
                        "PL%03d",
                        nomor + 1
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Gagal membuat ID pelanggan: "
                    + e.getMessage()
            );
        }

        return "PL001";
    }


    public String generateIdSepatu() {

        String sql = """
            SELECT MAX(
                CAST(
                    SUBSTRING(id_sepatu, 3)
                    AS UNSIGNED
                )
            )
            FROM sepatu
        """;

        try (
            Connection conn =
                    Koneksi.getConnection();

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery()
        ) {

            if (rs.next()) {

                int nomor =
                        rs.getInt(1);

                if (rs.wasNull()) {
                    nomor = 0;
                }

                return String.format(
                        "SP%03d",
                        nomor + 1
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Gagal membuat ID sepatu: "
                    + e.getMessage()
            );
        }

        return "SP001";
    }


    public String generateIdTransaksi() {

        String sql = """
            SELECT MAX(
                CAST(
                    SUBSTRING(id_transaksi, 3)
                    AS UNSIGNED
                )
            )
            FROM transaksi
        """;

        try (
            Connection conn =
                    Koneksi.getConnection();

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery()
        ) {

            if (rs.next()) {

                int nomor =
                        rs.getInt(1);

                if (rs.wasNull()) {
                    nomor = 0;
                }

                return String.format(
                        "TR%03d",
                        nomor + 1
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Gagal membuat ID transaksi: "
                    + e.getMessage()
            );
        }

        return "TR001";
    }
}