/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;
import Entity.Users;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `kiftrip`.`users` (`id` , `username`, `email` , `password` , `role` , `image`) VALUES (NULL, '" + t.getUsername() + "' , '" + t.getEmail() + "', '" + t.getPassword() + "', '" + t.getRole() + "', '" + t.getImage() + "');";
        ste.executeUpdate(requeteInsert);

    }

    @Override
    public Users login(String email, String password) throws SQLException {
        Users u = new Users();
        try {
            String sql = "SELECT * from users WHERE email ='" + email + "' AND password='" + password + "'";

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            if (rs.next() == true) {
                int id = rs.getInt(1);
                String Username = rs.getString(2);
                String Email = rs.getString(3);
                String Password = rs.getString(4);
                String role = rs.getString(5);
                String Img = rs.getString(6);
                u = new Users(id, Username, Email, Password, role, Img);
                System.out.println(" |||  user  authentifié  |||");
                System.out.println(u);
              
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
        String sql = "UPDATE users SET username=?, email=?,role=?, image=? WHERE id=?";

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, t.getUsername());
        statement.setString(2, t.getEmail());
        
        statement.setString(3, "User");
        statement.setString(4, t.getImage());
        statement.setInt(5, id);
       

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
               
                
                    u = new Users(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return u;    }
    
}
