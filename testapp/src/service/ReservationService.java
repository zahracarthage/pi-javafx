/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import utils.DataSource;
import entite.Reservation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Asus
 */
public class ReservationService {
    
    
      private PreparedStatement pst; 
     Connection cnx = null;
     Statement st;
     public boolean addReservation(Reservation r){
        Connection cnx = null;
        Statement st = null;
       
       
     String requette = "INSERT INTO resmaison (dateentre,datesortie,idmm_id) VALUES ('"+r.getDateentre().toString()+"','"+r.getDatesortie().toString()+"',0)";


        
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
 
    }}}
     
     
      
    public ObservableList<Reservation> showReservation(){
        Connection cnx =null;
        Statement st = null;
        ResultSet rs = null;
        ObservableList<Reservation> liste = FXCollections.observableArrayList();
        String requette = "SELECT * FROM resmaison";
        
        try {
            cnx = DataSource.getInstance().getCnx();
            st = cnx.createStatement();
            rs = st.executeQuery(requette);
            Reservation testajout;
            while (rs.next()){
               testajout = new Reservation(rs.getInt("id"), rs.getDate("dateentre"), rs.getDate("datesortie"));
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

    public boolean updateReservation(Reservation r){
        Connection cnx =null;
        Statement st = null;
    
        
       String requette = "UPDATE resmaison SET dateentre='"+r.getDateentre().toString()+"',datesortie='"+r.getDatesortie().toString()+"' WHERE id="+r.getId()+"";
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
    
    }
        
        public boolean deleteReservation(Reservation r){
        Connection cnx =null;
        Statement st = null;
        String requette = "DELETE FROM resmaison WHERE id="+r.getId()+"";
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
        public ObservableList<Reservation> getReservationlistnew() throws SQLException {
            ResultSet rs;
        String req = "select id, dateentre, datesortie from resmaison";
        ObservableList<Reservation> list = FXCollections.observableArrayList();

        try {
            rs = st.executeQuery(req);
            while (rs.next()) {
                Reservation r = new Reservation();
                r.setId(rs.getInt("id"));
                r.setDateentre(rs.getDate("dateentre"));
                r.setDatesortie(rs.getDate("datesortie"));
              
                list.add(r);

            }

        }
        catch (SQLException ex) {
                Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }
        
        public void Reservation (Reservation r) {
       Connection cnx = null;
        Statement st = null;
       
       
     String requette = "INSERT INTO resmaison (dateentre,datesortie,idmm_id) VALUES ('"+r.getDateentre()+"','"+r.getDatesortie()+"',0)";


        
        try {
            cnx = DataSource.getInstance().getCnx();
            st = cnx.createStatement();
            st.executeUpdate(requette);
            
            
           
        } catch (SQLException ex) {
            ex.printStackTrace();
          
        }finally {
    
    if (st != null) {
        try {
            st.close();
        } catch (SQLException e) { /* Ignored */}
 
    }}}
}
