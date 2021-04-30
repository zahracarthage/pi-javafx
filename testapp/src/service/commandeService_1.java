/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.commande;
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
public class commandeService_1 {
    
     private Statement ste; 
    private PreparedStatement pst; 
    private ResultSet rs; 
    
    private Connection conn; 
    
    public commandeService_1()
    { conn= DataSource.getInstance().getCnx();
    }
    
    public void ajoutercommande(commande c)
    {
        String req = "insert into commande (date, quantite, prix_total) values  ('" + c.getDate()+"','" + c.getQuantite()+  "','" + c.getPrixT()+ "')"; 
   
    try 
    {
        ste=conn.createStatement(); 
        ste.executeUpdate(req); 
    }
    
    catch (SQLException ex)
    {
    Logger.getLogger(commandeService_1.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
    public List <commande> readAll()
    {
        String req = "select * from commande"; 
        
       List <commande> list = new ArrayList<>(); 
       try {
       ste = conn.createStatement(); 
       rs=ste.executeQuery(req); 
       
       while (rs.next())
       {
           list.add(new commande(rs.getInt("id"), rs.getString("date"), rs.getInt("quantite"), rs.getInt("prix_total"))); 
       }
       
       }
       catch (SQLException ex)
       {
       Logger.getLogger(commandeService_1.class.getName()).log(Level.SEVERE, null, ex);
       }
    return list; 
    }
    
    public List <commande> liste2()
    {
        String req = "select id, date, quantite, prix_total from commande"; 
        
       List <commande> list = new ArrayList<>(); 
       try {
       ste = conn.createStatement(); 
       rs=ste.executeQuery(req); 
       
       while (rs.next())
       {
           list.add(new commande(rs.getInt("id"), rs.getString("date"), rs.getInt("quantite"), rs.getInt("prix_total"))); 
       }
       
       }
       catch (SQLException ex)
       {
       Logger.getLogger(commandeService_1.class.getName()).log(Level.SEVERE, null, ex);
       }
    return list; 
    }
    
    
     public void supprimercommande(int id) {
       try {
            Statement stm=conn.createStatement();
            String query="delete from commande WHERE id='"+id+"'";
           
            stm.executeUpdate(query);
            
       } catch (SQLException ex) {
           Logger.getLogger(commandeService_1.class.getName()).log(Level.SEVERE, null, ex);
       }
     }
     
       
      public void modifiercommande (int id, String date, int quantite, int prix_total){
         String requete="UPDATE commande SET date='"+date+"', quantite='"+quantite+"', prix_total='"+prix_total+"' WHERE id='"+id+"'";
         
         
         try{
             ste = conn.createStatement();
            ste.executeUpdate(requete);
            System.out.println("commande modifié");
        } catch (SQLException ex) {
            Logger.getLogger(commandeService_1.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
     public ObservableList<commande> getCommandeList() throws SQLException {
           
        ObservableList<commande> commandelist = FXCollections.observableArrayList();
        
         List <commande> id = new ArrayList<>(); 
        Statement stm = conn.createStatement();
        String query = "select id, date, quantite, prix_total from commande";

        //ResultSet rs;
        rs = stm.executeQuery(query);
        commande commande;
        while (rs.next()) {
           commande= new commande(rs.getInt("id"), rs.getString("date"), rs.getInt("quantite"), rs.getInt("prix_total")); 
            //System.out.println(events);
            commandelist.add(commande);

        }
        return commandelist;

    }
     
    public ObservableList<commande> getCommandeListnew() throws SQLException {
        String req = "select  id,date, quantite, prix_total  from commande";
        ObservableList<commande> list = FXCollections.observableArrayList();

        try {
            rs = ste.executeQuery(req);
            while (rs.next()) {
                commande r = new commande();
                r.setId(rs.getInt("id"));
                r.setDate(rs.getString("date"));
                r.setQuantite(rs.getInt("quantite"));
                r.setPrixT(rs.getInt("prix_total"));
                list.add(r);

            }

        } catch (SQLException ex) {
                Logger.getLogger(commandeService_1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

     
    
    
    
}
