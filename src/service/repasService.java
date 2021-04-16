/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import entite.repas;
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
public class repasService implements IService<repas> {
    
    private Statement ste; 
    private PreparedStatement pst; 
    private ResultSet rs; 
    
    private Connection conn; 
    
    public repasService()
    { conn= DataSource.getInstance().getCnx();
    }
    
    public void ajouterrepas(repas r)
    {
        String req = "insert into repas (nom, description, price, category, adresse, img) values  ('" + r.getNom() +"','" + r.getDescription() +  "','" + r.getPrice() + "','" + r.getCategory() + "','" + r.getAdresse() + "','" + r.getImg() + "')"; 
   
    try 
    {
        ste=conn.createStatement(); 
        ste.executeUpdate(req); 
    }
    
    catch (SQLException ex)
    {
    Logger.getLogger(repasService.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
    public List <repas> readAll()
    {
        String req = "select * from repas"; 
        
       List <repas> list = new ArrayList<>(); 
       try {
       ste = conn.createStatement(); 
       rs=ste.executeQuery(req); 
       
       while (rs.next())
       {
           list.add(new repas(rs.getInt("id"), rs.getString("nom"), rs.getString("description"), rs.getInt("price"), rs.getString("category"), rs.getString("adresse"), rs.getString("img"))); 
       }
       
       }
       catch (SQLException ex)
       {
       Logger.getLogger(repasService.class.getName()).log(Level.SEVERE, null, ex);
       }
    return list; 
    }
    
    public List <repas> liste2()
    {
        String req = "select id, nom, description, price, category, adresse from repas"; 
        
       List <repas> list = new ArrayList<>(); 
       try {
       ste = conn.createStatement(); 
       rs=ste.executeQuery(req); 
       
       while (rs.next())
       {
           list.add(new repas(rs.getInt("id"), rs.getString("nom"), rs.getString("description"), rs.getInt("price"), rs.getString("category"), rs.getString("adresse"))); 
       }
       
       }
       catch (SQLException ex)
       {
       Logger.getLogger(repasService.class.getName()).log(Level.SEVERE, null, ex);
       }
    return list; 
    }
    
    
     public void deleterepas(int id) {
       try {
            Statement stm=conn.createStatement();
            String query="delete from repas where id = '"+id+"'";
           
            stm.executeUpdate(query);
            
       } catch (SQLException ex) {
           Logger.getLogger(repasService.class.getName()).log(Level.SEVERE, null, ex);
       }
     }
     
       
      public void updaterepas (int id, String nom, String description, int price, String category, String adresse, String img){
         String requete="UPDATE repas SET nom='"+nom+"', description='"+description+"', price='"+price+"',category='"+category+"' ,adresse='"+adresse+"' ,img='"+img+"'  WHERE id='"+id+"'";
         
         
         try{
             ste = conn.createStatement();
            ste.executeUpdate(requete);
            System.out.println("annoncerepas modifié");
        } catch (SQLException ex) {
            Logger.getLogger(repasService.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
     public ObservableList<repas> getRepasList() throws SQLException {
           
        ObservableList<repas> repaslist = FXCollections.observableArrayList();
        
         List <repas> id = new ArrayList<>(); 
        Statement stm = conn.createStatement();
        String query = "select id, nom, description, price, category, adresse from repas";

        //ResultSet rs;
        rs = stm.executeQuery(query);
        repas repas;
        while (rs.next()) {
           repas= new repas(rs.getInt("id"), rs.getString("nom"), rs.getString("description"), rs.getInt("price"), rs.getString("category"), rs.getString("adresse")); 
            //System.out.println(events);
            repaslist.add(repas);

        }
        return repaslist;

    }
     
    public ObservableList<repas> getRepaslistnew() throws SQLException {
        String req = "select  id,nom, description, price , category , adresse, img from repas";
        ObservableList<repas> list = FXCollections.observableArrayList();

        try {
            rs = ste.executeQuery(req);
            while (rs.next()) {
                repas r = new repas();
                r.setId(rs.getInt("id"));
                r.setNom(rs.getString("nom"));
                r.setDescription(rs.getString("description"));
                r.setPrice(rs.getInt("price"));
                r.setCategory(rs.getString("category"));
                r.setAdresse(rs.getString("adresse"));
                r.setImg(rs.getString("img"));
                list.add(r);

            }

        } catch (SQLException ex) {
                Logger.getLogger(repasService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

     
    
    @Override
    public void insert(repas r)
    {
    throw new UnsupportedOperationException("not supported");}
   
    @Override
    public repas readById(int id)
    {
        throw new UnsupportedOperationException("not supported");
    }
    
}
