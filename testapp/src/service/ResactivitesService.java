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
public class ResactivitesService {
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    private Connection conn;

    public ResactivitesService() {
        conn = DataSource.getInstance().getCnx();
    }


    public List<Resactivites> readAll() {
        String req = "select * from resactivites";

        List<Resactivites> list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new Resactivites(rs.getInt("idresactivites"), rs.getInt("nbpersonnes"), rs.getDate("dateres")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ActivitesService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
     public List <Resactivites> liste3()
    {
        String req = "select idresactivites,nbpersonnes, date from resactivites"; 
        
       List <Resactivites> list = new ArrayList<>(); 
       try {
       ste = conn.createStatement(); 
       rs=ste.executeQuery(req); 
       
       while (rs.next())
       {
           list.add(new Resactivites(rs.getInt("idresactivites"), rs.getInt("nbpersonnes"), rs.getDate("date")));
       }
       
       }
       catch (SQLException ex)
       {
       Logger.getLogger(ActivitesService.class.getName()).log(Level.SEVERE, null, ex);
       }
    return list; 
    }
    
    
     public void deleteresactivite(int idresactivites) {
       try {
            Statement stm=conn.createStatement();
            String query="delete from resactivites where idresactivites = '"+idresactivites+"'";
           
            stm.executeUpdate(query);
            
       } catch (SQLException ex) {
           Logger.getLogger(ActivitesService.class.getName()).log(Level.SEVERE, null, ex);
       }
     }
     
       
     public void updateresactivite (Resactivites r){
         String req = "update resactivites set nbpersonnes=?,  date=?";

        try {
            pst = conn.prepareStatement(req);
            
            pst.setInt(1, r.getNbpersonnes());
            pst.setDate(2, (Date) r.getDateres());
   
           
            pst.execute();
        } catch (Exception e) {
            Logger.getLogger(ActivitesService.class.getName()).log(Level.SEVERE, null, e);
        }
        }
     
      
         
     
     public ObservableList<Resactivites> getResactivitesList() throws SQLException {
           
        ObservableList<Resactivites> Resactiviteslist = FXCollections.observableArrayList();
        
         List <Resactivites> id = new ArrayList<>(); 
        Statement stm = conn.createStatement();
        String query = "select idresactivites,nbpersonnes, date from resactivites";

        //ResultSet rs;
        rs = stm.executeQuery(query);
        Resactivites resactivites;
        while (rs.next()) {
           resactivites= new Resactivites(rs.getInt("idresactivites"), rs.getInt("nbpersonnes"), rs.getDate("date")); 
            //System.out.println(events);
            Resactiviteslist.add(resactivites);

        }
        return Resactiviteslist;

    }
     
    public ObservableList<Resactivites> getResactiviteslistnew() throws SQLException {
        String req = "select idresactivites,nbpersonnes, date from resactivites";
        ObservableList<Resactivites> Resactiviteslist = FXCollections.observableArrayList();

        try {
            rs = ste.executeQuery(req);
            while (rs.next()) {
                Resactivites r = new Resactivites();
                r.setIdresactivites(rs.getInt("idresactivites"));
                r.setNbpersonnes(rs.getInt("nbpersonnes"));
                r.setDateres(rs.getDate("date"));
               
                Resactiviteslist.add(r);

            }

        }
        catch (SQLException ex) {
                Logger.getLogger(ActivitesService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Resactiviteslist;

    }

     public void Reservation (Resactivites r) {
        String req = "insert into resactivites (nbpersonnes,date) values (?,?)";

        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, r.getNbpersonnes());
            pst.setDate(2, (Date) r.getDateres());
      

            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ActivitesService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
 
}