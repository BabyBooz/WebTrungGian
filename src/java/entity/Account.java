
package entity;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author linhnghiem
 */
public class Account {
    int id;
    String user;
    String email;
    int phone;
    String describes;
    int role;
    String password;
    String token;
    Timestamp expiration_time;
    int status;
    float wallet;
    Date created_date;

    public Account() {
    }

     public Account(int id, String user, String password, String email, int phone, String describes) {
        this.id = id;
        this.user = user;
        this.email = email;
        this.phone = phone;
        this.describes = describes;
        this.password = password;
    }

    public Account(int id, String user, String email, int phone, String describes, int role, String password, String token, Timestamp expiration_time, int status) {
        this.id = id;
        this.user = user;
        this.email = email;
        this.phone = phone;
        this.describes = describes;
        this.role = role;
        this.password = password;
        this.token = token;
        this.expiration_time = expiration_time;
        this.status = status;
    }

    public Account(int id, String user, String email, int phone, String describes, int role, String password, String token, Timestamp expiration_time, int status, float wallet) {
        this.id = id;
        this.user = user;
        this.email = email;
        this.phone = phone;
        this.describes = describes;
        this.role = role;
        this.password = password;
        this.token = token;
        this.expiration_time = expiration_time;
        this.status = status;
        this.wallet = wallet;
    }

    
    public Account(int id, String user, String email, int phone, String describes, int role, String password) {
        this.id = id;
        this.user = user;
        this.email = email;
        this.phone = phone;
        this.describes = describes;
        this.role = role;
        this.password = password;
    }
    
   
    public Account(String user, String password, String email) {
        this.user = user;
        this.password = password;
        this.email = email;
    }

    public Account(String user, String password, String email, String token, Timestamp expiration, int status) {
        this.user = user;
        this.password = password;
        this.email = email;
        this.token = token;
        this.expiration_time = expiration;
        this.role = status;
    }

    public Account(int id, String user, String email, int phone, String describes, int role, String password, String token, Timestamp expiration_time, int status, float wallet, Date created_date) {
        this.id = id;
        this.user = user;
        this.email = email;
        this.phone = phone;
        this.describes = describes;
        this.role = role;
        this.password = password;
        this.token = token;
        this.expiration_time = expiration_time;
        this.status = status;
        this.wallet = wallet;
        this.created_date = created_date;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

 
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Timestamp getExpiration_time() {
        return expiration_time;
    }

    public void setExpiration_time(Timestamp expiration_time) {
        this.expiration_time = expiration_time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public float getWallet() {
        return wallet;
    }

    public void setWallet(float wallet) {
        this.wallet = wallet;
    }
    
    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", user=" + user + ", email=" + email + ", phone=" + phone + ", describes=" + describes + ", role=" + role + ", password=" + password + ", token=" + token + ", expiration_time=" + expiration_time + ", status=" + status + ", wallet=" + wallet + '}';
    }
}
