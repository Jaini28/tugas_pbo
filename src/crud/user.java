package crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class user {
    private String database_name = "ahmad_jaini_2210010042_pbo";
    private String username = "root";
    private String password = "";
    public Connection koneksiDB; 

    public user() {
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

    public void Simpan_user(String name, String email, String status) {
        if (koneksiDB != null) {
            try {
                String sql = "INSERT INTO user (name, email, status) VALUES (?, ?, ?)";
                PreparedStatement perintah = koneksiDB.prepareStatement(sql);
                perintah.setString(1, name);
                perintah.setString(2, email);
                perintah.setString(3, status);
                perintah.executeUpdate();
                System.out.println("Berhasil Disimpan");
            } catch (SQLException e) {
                System.out.println("Gagal menyimpan: " + e.getMessage());
            }
        } else {
            System.out.println("Koneksi ke database belum diinisialisasi.");
        }
    }

    public void Ubah_user(int user_id, String email, String status, String name) {
        if (koneksiDB != null) {
            try {
                String sql = "UPDATE user SET email = ?, status = ?, WHERE name= ?";
                PreparedStatement perintah = koneksiDB.prepareStatement(sql);
                perintah.setString(1, email);
                perintah.setString(2, status);
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

    public void Hapus_user(int user_id) {
        if (koneksiDB != null) {
            try {
                String sql = "DELETE FROM user WHERE user_id=?";
                PreparedStatement perintah = koneksiDB.prepareStatement(sql);
                perintah.setInt(1, user_id);
                perintah.executeUpdate();
                System.out.println("Berhasil Dihapus");
            } catch (SQLException e) {
                System.out.println("Gagal menghapus: " + e.getMessage());
            }
        } else {
            System.out.println("Koneksi ke database belum diinisialisasi.");
        }
    }
    public void Getstatus(String status) {
        try {
            String sql = "select * from user where status=?";
            PreparedStatement perintah = koneksiDB.prepareStatement(sql);
            
            perintah.setString(1, status);
            
            ResultSet data = perintah.executeQuery();

            while (data.next()) {
                System.out.println("status: "+ data.getString("status"));
                System.out.println("email: "+ data.getString("email"));
                System.out.println("name: "+ data.getString("name"));
            }

    } catch (Exception e) {
        System.out.println(e.getMessage());
   }
}
    public void GetRecord() {
        try {
            String sql = "select * from user order by status asc"; 
            Statement perintah = koneksiDB.createStatement();

            ResultSet data = perintah.executeQuery(sql);

            while(data.next()) {
                System.out.println(
                data.getString("status") + " " +
                data.getString("email") + " " +
                data.getString("name") 
                );
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public int JumlahRecord() {
        int jumlah = 0;
        try {
            String sql = "select * from user order by status asc";
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
