package crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class food_history {
    private String database_name = "Ahmad_jaini_2210010042_PBO";
    private String username = "root";
    private String password = "";
    public Connection koneksiDB; 

    public food_history () {
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

    public void Simpan_food_history(int history_id, int user_id, int food_id, int timestamp) {
        if (koneksiDB != null) {
            try {
                String sql = "INSERT INTO food_history (history_id, user_id, food_id, timestamp) VALUES (?, ?, ?, ?)";
                PreparedStatement perintah = koneksiDB.prepareStatement(sql);
                perintah.setInt(1, history_id);
                perintah.setInt(2, user_id);
                perintah.setInt(3, food_id);
                perintah.setInt(4, timestamp);
                perintah.executeUpdate();
                System.out.println("Berhasil Disimpan");
            } catch (SQLException e) {
                System.out.println("Gagal menyimpan: " + e.getMessage());
            }
        } else {
            System.out.println("Koneksi ke database belum diinisialisasi.");
        }
    }

    public void Ubah_food_history(int user_id, int food_id, int timestamp, int history_id) {
        if (koneksiDB != null) {
            try {
                String sql = "UPDATE food_history SET user_id=?, food_id =?, timestamp =? WHERE history_id=?";
                PreparedStatement perintah = koneksiDB.prepareStatement(sql);
                perintah.setInt(1, user_id);
                perintah.setInt(2, food_id);
                perintah.setInt(3, timestamp);
                perintah.setInt(4, history_id);
                perintah.executeUpdate();
                System.out.println("Berhasil Diubah");
            } catch (SQLException e) {
                System.out.println("Gagal mengubah: " + e.getMessage());
            }
        } else {
            System.out.println("Koneksi ke database belum diinisialisasi.");
        }
    }

    public void Hapus_food_history(int history_id) {
        if (koneksiDB != null) {
            try {
                String sql = "DELETE FROM food_history WHERE history_id=?";
                PreparedStatement perintah = koneksiDB.prepareStatement(sql);
                perintah.setInt(1, history_id);
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
    public void Gethistory_id(String history_id) {
        try {
            String sql = "select * from food_history where history_id=?";
            PreparedStatement perintah = koneksiDB.prepareStatement(sql);
            
            perintah.setString(1, history_id);
            
            ResultSet data = perintah.executeQuery();

            while (data.next()) {
                System.out.println("User_id: "+ data.getString("User_id"));
                System.out.println("food_id: "+ data.getString("food_id"));
                System.out.println("timestamp: "+ data.getString("timestamp"));
                System.out.println("history_id: "+ data.getString("history_id"));
            }

    } catch (Exception e) {
        System.out.println(e.getMessage());
   }
}
    public void GetRecord() {
        try {
            String sql = "select * from food_history order by history_id asc"; 
            Statement perintah = koneksiDB.createStatement();

            ResultSet data = perintah.executeQuery(sql);

            while(data.next()) {
                System.out.println(
                data.getString("User_id") + " | " +
                data.getString("food_id") + " " +
                data.getString("timestamp") + " | " +
                data.getString("history_id") 
                );
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public int JumlahRecord() {
        int jumlah = 0;
        try {
            String sql = "select * from food_history order by history_id asc";
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
