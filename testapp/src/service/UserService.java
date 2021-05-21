/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import entite.Users;
import utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 *
 * @author malek
 */
public class UserService implements IUserService<Users> {
    private Connection con;
    private Statement ste;
    
    

    public UserService() {
        con = DataSource.getInstance().getCnx();
    }

    @Override
    public void register(Users t) throws SQLException {
        
String hashedPassword = BCrypt.hashpw(t.getPassword(), BCrypt.gensalt(13));
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `kiftrip`.`users` (`id` , `email`, `username` , `password`  , `image`) VALUES (NULL, '" + t.getEmail() + "' , '" + t.getUsername() + "', '" + hashedPassword + "', '" + t.getImage() + "');";
        ste.executeUpdate(requeteInsert);

    }
    

    @Override
    public Users login(String email, String password) throws SQLException {
        Users u = new Users();
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(13));
        try {
            String sql = "SELECT * from users WHERE email ='" + email + "'";
            System.out.println(sql);

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            
            if (rs.next() == true) {
                boolean matched = BCrypt.checkpw(password, rs.getString(4));
                if (matched){
                int id = rs.getInt(1);
                String Email = rs.getString(2);
                String Username = rs.getString(3);
                
                String Password =rs.getString(4);
                String Img = rs.getString(5);
                u = new Users(id, Username, Email, Password, Img);
                System.out.println(" |||  user  authentifié  |||");
                System.out.println(u);} else {
                 System.out.println("mp errrrr");   
                }
              
            } else {
                System.out.println("non trouvé");
            }
            

        } catch (SQLException ex) {
            Logger.getLogger(IUserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }

    @Override
    public boolean update(Users t, int id) throws SQLException {
        String sql = "UPDATE users SET username=?, email=?, image=? WHERE id=?";

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, t.getEmail());
        statement.setString(2, t.getUsername());
        
        
        statement.setString(3, t.getImage());
        statement.setInt(4, id);
       

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("An existing User was updated successfully!");
        }
        return true;
    }

    @Override
    public boolean ResetPassword(String pass, int id) throws SQLException {
        String sql = "UPDATE users SET password=? WHERE id=?";

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, pass);
        statement.setInt(2, id);
        
       

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("An existing User has reseted his password !");
        }
        return true;    }

    @Override
    public boolean delete(Users t) throws SQLException {
PreparedStatement pre = con.prepareStatement("DELETE FROM `kiftrip`.`users` where id =? AND username =?");
        pre.setInt(1, t.getId());
        pre.setString(2, t.getUsername());
        pre.executeUpdate();
        int rowsDeleted = pre.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("A User was deleted successfully!");
        }
        return true;    }

    @Override
    public Users FindById(int id) throws SQLException {
String req = "select * from users where id = ?";
            Users u = null;
            try {
                PreparedStatement ps = con.prepareStatement(req);
                 ps.setInt(1, id);
                ResultSet resultSet = ps.executeQuery();
                if (resultSet.next()) {
               
                
                    u = new Users(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return u;    }
    
}
