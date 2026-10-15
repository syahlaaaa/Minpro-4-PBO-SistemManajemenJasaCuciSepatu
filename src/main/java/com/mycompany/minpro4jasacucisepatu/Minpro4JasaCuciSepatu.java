package com.mycompany.minpro4jasacucisepatu;

import Controller.TransaksiController;
import Model.Admin;
import Model.Kasir;
import View.Menu;

import java.util.Scanner;

public class Minpro4JasaCuciSepatu {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

       
        Admin admin = new Admin(
                "admin",
                "admin123"
        );

        Kasir kasir = new Kasir(
                "kasir",
                "kasir123"
        );

        boolean berhasilLogin = false;
        String role = "";

        System.out.println("========================================");
        System.out.println("     SISTEM MANAJEMEN JASA CUCI SEPATU");
        System.out.println("========================================");
        System.out.println("                 LOGIN");
        System.out.println("========================================");


        for (int percobaan = 1; percobaan <= 3; percobaan++) {

            System.out.print("Username : ");
            String username =
                    input.nextLine().trim();

            System.out.print("Password : ");
            String password =
                    input.nextLine().trim();

            if (admin.login(username, password)) {

                berhasilLogin = true;
                role = "Admin";

                System.out.println();
                System.out.println(
                        "Login berhasil. Selamat datang, "
                        + admin.getUsername() + "!"
                );

                System.out.println(
                        "Role : Admin"
                );

                break;
            }

            if (kasir.login(username, password)) {

                berhasilLogin = true;
                role = "Kasir";

                System.out.println(
                        "Login berhasil. Selamat datang, "
                        + kasir.getUsername() + "!"
                );

                System.out.println(
                        "Role : Kasir"
                );

                break;
            }

            System.out.println(
                    "Username atau password salah."
            );

            System.out.println(
                    "Percobaan ke-"
                    + percobaan
                    + " dari 3."
            );

        }


        if (!berhasilLogin) {

            System.out.println(
                    "Batas percobaan login telah habis."
            );

            System.out.println(
                    "Program dihentikan."
            );

            input.close();
            return;
        }

        loadingAnimation();
        TransaksiController controller =
                new TransaksiController();


        Menu menu =
                new Menu(
                        controller,
                        role,
                        input
                );

        menu.tampilkanMenu();


        input.close();
    }


    private static void loadingAnimation() {

        System.out.print("Memuat sistem ");

        for (int i = 0; i < 3; i++) {

            try {

                Thread.sleep(500);

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();
            }

            System.out.print(".");
        }

        System.out.println(" Selesai!");
        System.out.println(
                "Sistem siap digunakan."
        );
    }
}