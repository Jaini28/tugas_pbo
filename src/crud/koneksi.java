package crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class koneksi {
    private String database_name = "Ahmad_jaini_2210010042_PBO";
    private String username = "root";
    private String password = "";
    public Connection koneksiDB; 

    public koneksi() {
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

    public void Simpan(String nik, String nama, String telp) {
        if (koneksiDB != null) {
            try {
                String sql = "INSERT INTO belajar (nik, nama, telp) VALUES (?, ?, ?)";
                PreparedStatement perintah = koneksiDB.prepareStatement(sql);
                perintah.setString(1, nik);
                perintah.setString(2, nama);
                perintah.setString(3, telp);
                perintah.executeUpdate();
                System.out.println("Berhasil Disimpan");
            } catch (SQLException e) {
                System.out.println("Gagal menyimpan: " + e.getMessage());
            }
        } else {
            System.out.println("Koneksi ke database belum diinisialisasi.");
        }
    }

    public void Ubah(String nik, String nama, String telp) {
        if (koneksiDB != null) {
            try {
                String sql = "UPDATE belajar SET nama=?, telp=? WHERE nik=?";
                PreparedStatement perintah = koneksiDB.prepareStatement(sql);
                perintah.setString(1, nama);
                perintah.setString(2, telp);
                perintah.setString(3, nik);
                perintah.executeUpdate();
                System.out.println("Berhasil Diubah");
            } catch (SQLException e) {
                System.out.println("Gagal mengubah: " + e.getMessage());
            }
        } else {
            System.out.println("Koneksi ke database belum diinisialisasi.");
        }
    }

    public void Hapus(String nik) {
        if (koneksiDB != null) {
            try {
                String sql = "DELETE FROM belajar WHERE nik=?";
                PreparedStatement perintah = koneksiDB.prepareStatement(sql);
                perintah.setString(1, nik);
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
    public void GetNIK(String nik) {
        try {
            String sql = "select * from belajar where nik=?";
            PreparedStatement perintah = koneksiDB.prepareStatement(sql);
            
            perintah.setString(1, nik);
            
            ResultSet data = perintah.executeQuery();

            while (data.next()) {
                System.out.println("nik: "+ data.getString("nik"));
                System.out.println("nama: "+ data.getString("nama"));
                System.out.println("telp: "+ data.getString("telp"));
            }

    } catch (Exception e) {
        System.out.println(e.getMessage());
   }
}
    public void GetRecord() {
        try {
            String sql = "select * from belajar order by nik asc"; 
            Statement perintah = koneksiDB.createStatement();

            ResultSet data = perintah.executeQuery(sql);

            while(data.next()) {
                System.out.println(
                data.getString("nik") + " | " +
                data.getString("nama") + " " +
                data.getString("telp") 
                );
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public int JumlahRecord() {
        int jumlah = 0;
        try {
            String sql = "select * from belajar order by nik asc";
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