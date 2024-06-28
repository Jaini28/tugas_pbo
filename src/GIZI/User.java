/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GIZI;

/**
 *
 * @author acer
 */
public class User extends Food {
    String Nama, email, status;
    
    public User() {}
    public User(String Nama,String email,String status) {
        this.Nama = Nama;
        this.email = email;
        this.status = status;
    }
        
        public void inputNama(String Nama) {
            this.Nama = Nama;
        }
        public String Nama() {
            return this.Nama;
        }

        public void Nomoremail(String email) {
            this.email = email;
        }
        public String email() {
            return this.email;
        }
        
        public void inputstatus(String status) {
            this.status = status;
        }
        public String status() {
            return this . status;   
}
}