/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author malek
 */
public class Users {
    private int id;
    private String username;
    private String email;
    private String password;
    private String role;
    private String image;

    public Users(int id, String username, String email, String password, String role, String image) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.image = image;
    }

    public Users(String username, String email, String password, String role, String image) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.image = image;
    }
     public Users(int id, String username, String email, String role) {
        this.id = id;
        this.username = username;
        this.email = email;
        
        this.role = role;
        
    }

    public Users() {
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Users{" + "id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + ", role=" + role + ", image=" + image + '}';
    }

    
}
