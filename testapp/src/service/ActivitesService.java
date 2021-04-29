/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.Activites;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;
import entite.Resactivites;
import java.sql.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Zannou
 */
public class ActivitesService {
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    private Connection conn;

    public ActivitesService() {
        conn = DataSource.getInstance().getCnx();
    }

 public void ajouterActivitePst (Activites a) {
        String req = "insert into activites (nom,description,categorie,date,image,prix) values (?,?,?,?,?,?)";

        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, a.getNom());
            pst.setString(2, a.getDescription());
            pst.setString(3, a.getCategorie());
            pst.setDate(4, (Date) a.getDate());
            pst.setString(5, a.getImage());
            pst.setInt(6, a.getPrix());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ActivitesService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public List<Activites> readAll() {
        String req = "select * from activites";

        List<Activites> list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
           list.add(new Activites(rs.getInt("idact"), rs.getString("nom"), rs.getString("description"), rs.getString("categorie"), rs.getDate("date"), rs.getString("image"), rs.getInt("prix") )); 
       }

        } catch (SQLException ex) {
            Logger.getLogger(ActivitesService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
     public List <Activites> liste2()
    {
        String req = "select idact, nom, description, categorie, date, image, prix from activites"; 
        
       List <Activites> list = new ArrayList<>(); 
       try {
       ste = conn.createStatement(); 
       rs=ste.executeQuery(req); 
       
       while (rs.next())
       {
           list.add(new Activites(rs.getInt("idact"), rs.getString("nom"), rs.getString("description"), rs.getString("categorie"), rs.getDate("date"), rs.getString("image"), rs.getInt("prix") )); 
       }
       
       }
       catch (SQLException ex)
       {
       Logger.getLogger(ActivitesService.class.getName()).log(Level.SEVERE, null, ex);
       }
    return list; 
    }
    
    
     public void deleteactivite(int idact) {
       try {
            Statement stm=conn.createStatement();
            String query="delete from activites where idact = '"+idact+"'";
           
            stm.executeUpdate(query);
            
       } catch (SQLException ex) {
           Logger.getLogger(ActivitesService.class.getName()).log(Level.SEVERE, null, ex);
       }
     }
     
       
     public void updateactivite (Activites a){
         String req = "update activites set nom=?, description=?, categorie=?, date=?, image=?, prix=?,";

        try {
            pst = conn.prepareStatement(req);
            
            pst.setString(1, a.getNom());
            pst.setString(2, a.getDescription());
            pst.setString(3, a.getCategorie());
            pst.setDate(4, (Date) a.getDate());
             pst.setString(5, a.getImage());
             pst.setInt(6, a.getPrix()); 
   
           
            pst.execute();
        } catch (Exception e) {
            Logger.getLogger(ActivitesService.class.getName()).log(Level.SEVERE, null, e);
        }
        }
     
      
         
     
     public ObservableList<Activites> getActivitesList() throws SQLException {
           
        ObservableList<Activites> Activiteslist = FXCollections.observableArrayList();
        
         List <Activites> id = new ArrayList<>(); 
        Statement stm = conn.createStatement();
        String query = "select idact, nom, description, categorie, date, image, prix from activites";

        //ResultSet rs;
        rs = stm.executeQuery(query);
        Activites activites;
        while (rs.next()) {
           activites= new Activites(rs.getInt("idact"), rs.getString("nom"), rs.getString("description"), rs.getString("categorie"), rs.getDate("date"), rs.getString("image"), rs.getInt("prix") );  
            //System.out.println(events);
            Activiteslist.add(activites);

        }
        return Activiteslist;

    }
     
    public ObservableList<Activites> getActiviteslistnew() throws SQLException {
        String req = "select idact, nom, description, categorie, date, image, prix from activites";
        ObservableList<Activites> Activiteslist = FXCollections.observableArrayList();

        try {
            rs = ste.executeQuery(req);
            while (rs.next()) {
                Activites a = new Activites();
                a.setIdact(rs.getInt("idact"));
                a.setNom(rs.getString("nom"));
                a.setDescription(rs.getString("description"));
                a.setCategorie(rs.getString("categorie"));
                a.setDate(rs.getDate("date"));
                a.setImage(rs.getString("image"));
                a.setPrix(rs.getInt("prix"));

                Activiteslist.add(a);

            }

        }
        catch (SQLException ex) {
                Logger.getLogger(ActivitesService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Activiteslist;

    }

    

}
