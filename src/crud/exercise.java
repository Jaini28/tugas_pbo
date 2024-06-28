 package crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class exercise {
    private String database_name = "Ahmad_jaini_2210010042_PBO";
    private String username = "root";
    private String password = "";
    public Connection koneksiDB; 

    public exercise() {
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

    public void Simpan_exercise(String name, double calories_burned) {
        if (koneksiDB != null) {
            try {
                String sql = "INSERT INTO exercise (name, calories_burned) VALUES (?, ?)";
                PreparedStatement perintah = koneksiDB.prepareStatement(sql);
                perintah.setString(1, name);
                perintah.setDouble(2, calories_burned);
                perintah.executeUpdate();
                System.out.println("Berhasil Disimpan");
            } catch (SQLException e) {
                System.out.println("Gagal menyimpan: " + e.getMessage());
            }
        } else {
            System.out.println("Koneksi ke database belum diinisialisasi.");
        }
    }

    public void Ubah_exercise(double calories_burned, String name) {
        if (koneksiDB != null) {
            try {
                String sql = "UPDATE exercise SET calories_burned=? WHERE name=?";
                PreparedStatement perintah = koneksiDB.prepareStatement(sql);
                perintah.setDouble(1, calories_burned);
                perintah.setString(2, name);
                perintah.executeUpdate();
                System.out.println("Berhasil Diubah");
            } catch (SQLException e) {
                System.out.println("Gagal mengubah: " + e.getMessage());
            }
        } else {
            System.out.println("Koneksi ke database belum diinisialisasi.");
        }
    }

    public void Hapus_exercise(String calories_burned) {
        if (koneksiDB != null) {
            try {
                String sql = "DELETE FROM exercise WHERE calories_burned=?";
                PreparedStatement perintah = koneksiDB.prepareStatement(sql);
                perintah.setString(1, calories_burned);
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
    public void GetName(String name) {
        try {
            String sql = "select * from exercise where name=?";
            PreparedStatement perintah = koneksiDB.prepareStatement(sql);
            
            perintah.setString(1, name);
            
            ResultSet data = perintah.executeQuery();

            while (data.next()) {
                System.out.println("name: "+ data.getString("name"));
                System.out.println("calories_burned: "+ data.getString("calories_burned"));
            }

    } catch (Exception e) {
        System.out.println(e.getMessage());
   }
}
    public void GetRecord() {
        try {
            String sql = "select * from exercise order by name asc"; 
            Statement perintah = koneksiDB.createStatement();

            ResultSet data = perintah.executeQuery(sql);

            while(data.next()) {
                System.out.println(
                data.getString("name") + " " +
                data.getString("calories_burned") 
                );
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public int JumlahRecord() {
        int jumlah = 0;
        try {
            String sql = "select * from exercise order by name asc";
            Statement perintah = koneksiDB.createStatement();

            ResultSet data = perintah.executeQuery(sql);

            while (data.next()) {
                jumlah = jumlah + 1;
            }
        } catch (Exception e) {
        System.out.println(e.getMessage());
        }
        return jumlah;
 }
}
