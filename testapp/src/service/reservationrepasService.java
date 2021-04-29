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
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;
import entite.reservationrepas;
import java.awt.AWTException;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author zcart
 */
public class reservationrepasService {
    private Statement ste; 
    private PreparedStatement pst; 
    private ResultSet rs; 
    
    private Connection conn; 
    
    public reservationrepasService()
    { conn= DataSource.getInstance().getCnx();
    }
    
    public  void ajouterreservationrepas (reservationrepas rr)
    {
                String req = "insert into reservationrepas (idr, date, nbrpersonnes, message, nom, email, phone) values  ('" + rr.getIdr() +"','" + rr.getDate() +  "','" + rr.getNbrpersonnes() + "','" + rr.getMessage() +  "','" + rr.getNom()+ "','" + rr.getEmail() +"','" + rr.getPhone()+ "')"; 
   
    try 
    {
        ste=conn.createStatement(); 
        ste.executeUpdate(req); 
    }
    
    catch (SQLException ex)
    {
    Logger.getLogger(reservationrepasService.class.getName()).log(Level.SEVERE, null, ex);
    }

    }
    
    
      public void supprimerreservation(int id) {
       try {
            Statement stm=conn.createStatement();
            String query="delete from reservationrepas where id = '"+id+"'";
           
            stm.executeUpdate(query);
            
       } catch (SQLException ex) {
           Logger.getLogger(reservationrepasService.class.getName()).log(Level.SEVERE, null, ex);
       }
     }
      
      
     public void displayTray() throws AWTException, InterruptedException {
        //Obtain only one instance of the SystemTray object
       SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
        //java.awt.Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
        //Alternative (if the icon is on the classpath):
        java.awt.Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("/srcs/icon.png"));

        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
        
         trayIcon.displayMessage("Notification", "You have a new event!", TrayIcon.MessageType.INFO);
        Thread.sleep(2000);        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        trayIcon.setToolTip("New Event!");
        
        tray.add(trayIcon);
        Thread.sleep(4000); 
        tray.remove(trayIcon);

    }
     
       
    public List <reservationrepas> liste2()
    {
        String req = "select * from reservationrepas"; 
        
       List <reservationrepas> list = new ArrayList<>(); 
       try {
       ste = conn.createStatement(); 
       rs=ste.executeQuery(req); 
       
       while (rs.next())
       {
           list.add(new reservationrepas(rs.getInt("id"), rs.getInt("idr"), rs.getString("date"), rs.getInt("nbrpersonnes"), rs.getString("message"), rs.getString("nom"), rs.getString("email"), rs.getString("phone")));
       }
       
       }
       catch (SQLException ex)
       {
       Logger.getLogger(reservationrepasService.class.getName()).log(Level.SEVERE, null, ex);
       }
    return list; 
    }
    
     
     
      public ObservableList<reservationrepas> getresrepasliste() throws SQLException {
           
        ObservableList<reservationrepas> resrepasliste = FXCollections.observableArrayList();
        
         List <reservationrepas> id = new ArrayList<>(); 
        Statement stm = conn.createStatement();
        String query = "select * from reservationrepas";

        //ResultSet rs;
        rs = stm.executeQuery(query);
        reservationrepas reservationrepas;
        while (rs.next()) {
            
           reservationrepas= new reservationrepas(rs.getInt("id"), rs.getInt("idr"), rs.getString("date"), rs.getInt("nbrpersonnes"), rs.getString("message"), rs.getString("nom"), rs.getString("email"), rs.getString("phone")); 
            //System.out.println(events);
            
            resrepasliste.add(reservationrepas);

        }
        return resrepasliste;

    }
      
       
    
    
       public List<String> listidr() throws SQLException {
        List<String> idr = new ArrayList<>();
        Statement stm = conn.createStatement();
        String query = "select distinct id from repas";
        //ResultSet rs;
        rs = stm.executeQuery(query);
        
        while (rs.next()) {
            idr.add(rs.getString("id"));
            System.out.println(idr);
            
            

        }
        return idr;
        
        

    }

        

}
