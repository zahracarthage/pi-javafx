/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.reclamation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DataSource;

/**
 *
 * @author zcart
 */
public class reclamationService {
     private Statement ste; 
    private PreparedStatement pst; 
    private ResultSet rs; 
    
    private Connection conn; 
    
    public reclamationService()
    { conn= DataSource.getInstance().getCnx();
    }
    
    public void ajoutereclamation(reclamation c)
    {
        String req = "insert into reclamation (nom, prenom, email,subject,message, etat) values  ('" + c.getNom()+"','" + c.getPrenom()+  "','" + c.getEmail()+ "','" + c.getSubject()+"','" + c.getMessage()+ 1 +"')"; 
   
    try 
    {
        ste=conn.createStatement(); 
        ste.executeUpdate(req); 
    }
    
    catch (SQLException ex)
    {
    Logger.getLogger(reclamationService.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
    public List <reclamation> readAll()
    {
        String req = "select * from reclamation"; 
        
       List <reclamation> list = new ArrayList<>(); 
       try {
       ste = conn.createStatement(); 
       rs=ste.executeQuery(req); 
       
       while (rs.next())
       {
           list.add(new reclamation(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("subject"),rs.getString("message"))); 
       }
       
       }
       catch (SQLException ex)
       {
       Logger.getLogger(reclamationService.class.getName()).log(Level.SEVERE, null, ex);
       }
    return list; 
    }
    
    public List <reclamation> liste2()
    {
        String req = "select id, nom, description, price, category, adresse from repas"; 
        
       List <reclamation> list = new ArrayList<>(); 
       try {
       ste = conn.createStatement(); 
       rs=ste.executeQuery(req); 
       
       while (rs.next())
       {
           list.add(new reclamation(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("subject"),rs.getString("message"),rs.getInt("etat"))); 
       }
       
       }
       catch (SQLException ex)
       {
       Logger.getLogger(reclamationService.class.getName()).log(Level.SEVERE, null, ex);
       }
    return list; 
    }
    
    
     public void supprimerreclamation(int id) {
       try {
            Statement stm=conn.createStatement();
            String query="delete from reclamation where id = '"+id+"'";
           
            stm.executeUpdate(query);
            
       } catch (SQLException ex) {
           Logger.getLogger(reclamationService.class.getName()).log(Level.SEVERE, null, ex);
       }
     }
     
       
      public void modifierreclamation (int etat, int id){
         String requete="UPDATE reclamation SET etat='"+etat+"' where id = '"+id+"'";
         

         
         
         try{
             ste = conn.createStatement();
            ste.executeUpdate(requete);
            System.out.println("reclamation modifié");
        } catch (SQLException ex) {
            Logger.getLogger(reclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
     public ObservableList<reclamation> getReclamationList() throws SQLException {
           
        ObservableList<reclamation> reclamationlist = FXCollections.observableArrayList();
        
         List <reclamation> id = new ArrayList<>(); 
        Statement stm = conn.createStatement();
        String query = "select id, nom, prenom, email, subject, message from reclamation";

        //ResultSet rs;
        rs = stm.executeQuery(query);
        reclamation reclamation;
        while (rs.next()) {
           reclamation = new reclamation(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("subject"), rs.getString("message")); 
            //System.out.println(events);
            reclamationlist.add(reclamation);

        }
        return reclamationlist;

    }
     
    public ObservableList<reclamation> getReclamationListnew() throws SQLException {
        String req = "select  id,nom, prenom, email,subject,message  from reclamation";
        ObservableList<reclamation> list = FXCollections.observableArrayList();

        try {
            rs = ste.executeQuery(req);
            while (rs.next()) {
                reclamation r = new reclamation();
                r.setId(rs.getInt("id"));
                r.setEtat(rs.getInt("etat"));
                list.add(r);

            }

        } catch (SQLException ex) {
                Logger.getLogger(reclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    
}
