package crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class food {
    private String database_name = "Ahmad_jaini_2210010042_PBO";
    private String username = "root";
    private String password = "";
    public Connection koneksiDB; 

    public food() {
        try {
            String lokasi = "jdbc:mysql://localhost:3306/" + database_name;
            Class.forName("com.mysql.cj.jdbc.Driver");
            koneksiDB = DriverManager.getConnection(lokasi, username, password);
            System.out.println("Database Terkoneksi");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver tidak ditemukan: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Koneksi gagal: " + e.getMessage());
        }
    }

    public void Simpan_food(String name, double calorie, double weight) {
        if (koneksiDB != null) {
            try {
                String sql = "INSERT INTO food (name, calorie, weight) VALUES (?, ?, ?)";
                PreparedStatement perintah = koneksiDB.prepareStatement(sql);
                perintah.setString(1, name);
                perintah.setDouble(2, calorie);
                perintah.setDouble(3, weight);
                perintah.executeUpdate();
                System.out.println("Berhasil Disimpan");
            } catch (SQLException e) {
                System.out.println("Gagal menyimpan: " + e.getMessage());
            }
        } else {
            System.out.println("Koneksi ke database belum diinisialisasi.");
        }
    }

    public void Ubah_food(double calorie, double weight, String name) {
        if (koneksiDB != null) {
            try {
                String sql = "UPDATE food SET calorie=?, weight=? WHERE name=?";
                PreparedStatement perintah = koneksiDB.prepareStatement(sql);
                perintah.setDouble(1, calorie);
                perintah.setDouble(2, weight);
                perintah.setString(3, name);
                perintah.executeUpdate();
                System.out.println("Berhasil Diubah");
            } catch (SQLException e) {
                System.out.println("Gagal mengubah: " + e.getMessage());
            }
        } else {
            System.out.println("Koneksi ke database belum diinisialisasi.");
        }
    }

    public void Hapus_food(String name) {
        if (koneksiDB != null) {
            try {
                String sql = "DELETE FROM food WHERE name=?";
                PreparedStatement perintah = koneksiDB.prepareStatement(sql);
                perintah.setString(1, name);
//                perintah.setString(2, nama);
//                perintah.setString(3, telp);
                perintah.executeUpdate();
                System.out.println("Berhasil Dihapus");
            } catch (SQLException e) {
                System.out.println("Gagal menghapus: " + e.getMessage());
            }
        } else {
            System.out.println("Koneksi ke database belum diinisialisasi.");
        }
    }
}