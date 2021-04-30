/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import entite.article; 
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
public class articleService_1 {
    private Statement ste; 
    private PreparedStatement pst; 
    private ResultSet rs; 
    
    private Connection conn; 
    
    public articleService_1()
    { 
        conn= DataSource.getInstance().getCnx();
    }
    
    
    
    //fonction ajouter article
    public void ajouterarticle(article a)
    {
        String req = "insert into article (nom, description, categorie, prix, image) values  ('" + a.getNom() +"','" + a.getDescription() +  "','" + a.getCategorie() + "','" + a.getPrix() +  "','" + a.getImg() + "')"; 
   
    try 
    {
        ste=conn.createStatement(); 
        ste.executeUpdate(req); 
    }
    
    catch (SQLException ex)
    {
    Logger.getLogger(articleService_1.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
     public List <article> readAll()
    {
        String req = "select * from article"; 
        
       List <article> list = new ArrayList<>(); 
       try {
       ste = conn.createStatement(); 
       rs=ste.executeQuery(req); 
       
       while (rs.next())
       {
           list.add(new article(rs.getInt("id"), rs.getString("nom"), rs.getString("description"), rs.getString("categorie"), rs.getInt("prix"), rs.getString("img"))); 
       }
       
       }
       catch (SQLException ex)
       {
       Logger.getLogger(articleService_1.class.getName()).log(Level.SEVERE, null, ex);
       }
    return list; 
    }
    
    public List <article> liste2()
    {
        String req = "select id, nom, description, categorie, prix from article"; 
        
       List <article> list = new ArrayList<>(); 
       try {
       ste = conn.createStatement(); 
       rs=ste.executeQuery(req); 
       
       while (rs.next())
       {
           list.add(new article(rs.getInt("id"), rs.getString("nom"), rs.getString("description"), rs.getString("categorie"), rs.getInt("prix"))); 
; 
       }
       
       }
       catch (SQLException ex)
       {
       Logger.getLogger(articleService_1.class.getName()).log(Level.SEVERE, null, ex);
       }
    return list; 
    }
    
    
     public void supprimerarticle(int id) {
       try {
            Statement stm=conn.createStatement();
            String query="delete from article where id = '"+id+"'";
           
            stm.executeUpdate(query);
            
       } catch (SQLException ex) {
           Logger.getLogger(articleService_1.class.getName()).log(Level.SEVERE, null, ex);
       }
     }
     
       
      public void modifierarticle (int id, String nom, String description,String categorie, int prix, String image){
         String requete="UPDATE article SET nom='"+nom+"', description='"+description+"', categorie='"+categorie+"',prix='"+prix+"',image='"+image+"'  WHERE id='"+id+"'";
         
         
         try{
             ste = conn.createStatement();
            ste.executeUpdate(requete);
            System.out.println("annoncerepas modifié");
        } catch (SQLException ex) {
            Logger.getLogger(repasService.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
     public ObservableList<article> getArticleListe() throws SQLException {
           
        ObservableList<article> articleliste = FXCollections.observableArrayList();
        
         List <article> id = new ArrayList<>(); 
        Statement stm = conn.createStatement();
        String query = "select id, nom, description, categorie, prix from article";

        //ResultSet rs;
        rs = stm.executeQuery(query);
        article article;
        while (rs.next()) {
             article = new article(rs.getInt("id"), rs.getString("nom"), rs.getString("description"), rs.getString("categorie"), rs.getInt("prix")); 
            //System.out.println(events);
            articleliste.add(article);

        }
        return articleliste;

    }
     
    public ObservableList<article> getArticleListenew() throws SQLException {
        String req = "select  id,nom, description, categorie , prix, image from article";
        ObservableList<article> list = FXCollections.observableArrayList();

        try {
            rs = ste.executeQuery(req);
            while (rs.next()) {
                article a = new article();
                a.setId(rs.getInt("id"));
                a.setNom(rs.getString("nom"));
                a.setDescription(rs.getString("description"));
                a.setCategorie(rs.getString("categorie"));
                a.setPrix(rs.getInt("prix"));
                a.setImg(rs.getString("image"));
                list.add(a);

               
            }

        } catch (SQLException ex) {
                Logger.getLogger(articleService_1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    }

    

     
    
    
    

