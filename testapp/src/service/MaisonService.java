/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import utils.DataSource;
import entite.Maison;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;





/**
 *
 * @author Asus
 */
public class MaisonService {
    
     private PreparedStatement pst; 
     Connection cnx = null;
     Statement st;
     public boolean addMaison(Maison m) throws Exception{
        Connection cnx = null;
        Statement st = null;
       
       
     String requette = "INSERT INTO maisonhote (nom,adresse,num,prix,note,image) VALUES ('"+m.getNom()+"','"+m.getAdresse()+"',"+m.getNum()+",'"+m.getPrix()+"','"+m.getNote()+"','"+m.getImage()+"')";


        //String requette = "INSERT INTO maisonhote (nom,adresse,num,prix,note,image) VALUES ('"+m.getNom()+"','"+m.getAdresse()+"','"+m.getNum()+"','"+m.getPrix()+"','"+m.getNote()+"','"m.getImage()+"')";
        try {
            cnx = DataSource.getInstance().getCnx();
            st = cnx.createStatement();
            st.executeUpdate(requette);
            MailService.sendMail();
            return true;
            
           
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }finally {
    
    if (st != null) {
        try {
            st.close();
        } catch (SQLException e) { /* Ignored */}
 
    }}}
     
     
      
    public ObservableList<Maison> showMaison(){
        Connection cnx =null;
        Statement st = null;
        ResultSet rs = null;
        ObservableList<Maison> liste = FXCollections.observableArrayList();
        String requette = "SELECT * FROM maisonhote";
        
        try {
            cnx = DataSource.getInstance().getCnx();
            st = cnx.createStatement();
            rs = st.executeQuery(requette);
            Maison testajout;
            while (rs.next()){
               testajout = new Maison(rs.getInt("id"), rs.getString("nom"), rs.getString("adresse"), rs.getInt("num"), rs.getInt("prix"), rs.getInt("note"), rs.getString("image"));
               liste.add(testajout);
            }
            
           
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
    if (rs != null) {
        try {
            rs.close();
        } catch (SQLException e) { /* Ignored */}
    }
    if (st != null) {
        try {
            st.close();
        } catch (SQLException e) { /* Ignored */}
    }
    }
        return liste;
        
}

    public boolean updateMaison(Maison m){
        Connection cnx =null;
        Statement st = null;
        
       String requette = "UPDATE maisonhote SET nom='"+m.getNom()+"',adresse='"+m.getAdresse()+"',num='"+m.getNum()+"',prix='"+m.getPrix()+"',note='"+m.getNote()+"',image='"+m.getImage()+"' WHERE id="+m.getId()+"";
        try {
            cnx = DataSource.getInstance().getCnx();
            st = cnx.createStatement();
            st.executeUpdate(requette);
            return true;
            
           
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }finally {
    
    if (st != null) {
        try {
            st.close();
        } catch (SQLException e) { /* Ignored  */}
    }
    }
    /*String req = "update maisonhote set nom=?, adresse=?, num=?, prix=?, note=?, image=?  where id = ?";

        try {
            pst = cnx.prepareStatement(req);
            pst.setInt(7, m.getId());
            pst.setString(1, m.getNom());
            pst.setString(2, m.getAdresse());
            pst.setInt(3, m.getNum());
            pst.setInt(4, m.getPrix());
            pst.setInt(5, m.getNote());
            pst.setString(6, m.getImage());
            pst.execute();
        } catch (Exception e) {
            Logger.getLogger(MaisonService.class.getName()).log(Level.SEVERE, null, e);
        }
         return false;*/
    }
        
        public boolean deleteMaison(Maison m){
        Connection cnx =null;
        Statement st = null;
        String requette = "DELETE FROM maisonhote WHERE id="+m.getId()+"";
        try {
            cnx = DataSource.getInstance().getCnx();
            st = cnx.createStatement();
            st.executeUpdate(requette);
            return true;
            
           
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }finally {
    
    if (st != null) {
        try {
            st.close();
        } catch (SQLException e) { /* Ignored */}
    }
    }
        
    }
        public ObservableList<Maison> getMaisonlistnew() throws SQLException {
            ResultSet rs;
        String req = "select id, nom, adresse, num, prix, note, image from maisonhote";
        ObservableList<Maison> list = FXCollections.observableArrayList();

        try {
            rs = st.executeQuery(req);
            while (rs.next()) {
                Maison m = new Maison();
                m.setId(rs.getInt("id"));
                m.setNom(rs.getString("nom"));
                m.setAdresse(rs.getString("adresse"));
                m.setPrix(rs.getInt("prix"));
                m.setNum(rs.getInt("num"));
                m.setNote(rs.getInt("note"));
                m.setImage(rs.getString("image"));
                list.add(m);

            }

        }
        catch (SQLException ex) {
                Logger.getLogger(MaisonService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }
        
        
         public ObservableList<Maison> findMaisonByName(String name){
        Connection cnx =null;
        Statement st = null;
        ResultSet rs = null;
        ObservableList<Maison> liste = FXCollections.observableArrayList();
        String requette = "SELECT * FROM maisonhote WHERE nom LIKE '%" + name + "%'";
        
        try {
            cnx = DataSource.getInstance().getCnx();
            st = cnx.createStatement();
            rs = st.executeQuery(requette);
            Maison testajout;
            while (rs.next()){
               testajout = new Maison(rs.getInt("id"), rs.getString("nom"), rs.getString("adresse"), rs.getInt("num"), rs.getInt("prix"), rs.getInt("note"), rs.getString("image"));
               liste.add(testajout);
            }
            
           
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
    if (rs != null) {
        try {
            rs.close();
        } catch (SQLException e) { /* Ignored */}
    }
    if (st != null) {
        try {
            st.close();
        } catch (SQLException e) { /* Ignored */}
    }
    }
        return liste;
        
}
         
          public ObservableList<Integer> showMaisonIds(){
        Connection cnx =null;
        Statement st = null;
        ResultSet rs = null;
        ObservableList<Integer> liste = FXCollections.observableArrayList();
        String requette = "SELECT * FROM maisonhote";
        
        try {
            cnx = DataSource.getInstance().getCnx();
            st = cnx.createStatement();
            rs = st.executeQuery(requette);
            Maison testajout;
            while (rs.next()){
               testajout = new Maison(rs.getInt("id"), rs.getString("nom"), rs.getString("adresse"), rs.getInt("num"), rs.getInt("prix"), rs.getInt("note"), rs.getString("image"));
               liste.add(testajout.getId());
            }
            
           
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
    if (rs != null) {
        try {
            rs.close();
        } catch (SQLException e) { /* Ignored */}
    }
    if (st != null) {
        try {
            st.close();
        } catch (SQLException e) { /* Ignored */}
    }
    }
        return liste;
        
}
         
    
    
}

