/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.sql.SQLException;
import Entity.Users;

/**
 *
 * @author malek
 * @param <T>
 */
public interface IUserService<T> {
     void register(T t)throws SQLException;
    boolean update(T t, int id)throws SQLException;
    boolean ResetPassword(String pass, int id)throws SQLException;
    Users login(String email, String password) throws SQLException;
    boolean delete(T t) throws SQLException;
    Users FindById(int id) throws SQLException;
    
}
