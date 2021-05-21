/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entite.Reservation;
import service.ReservationService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import java.sql.Date;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class FrontReservationController implements Initializable {

    @FXML
    private DatePicker de;
    @FXML
    private DatePicker ds;

    

    
   
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }  
    
        
        
     

    @FXML
    private void reservation(ActionEvent event) {
        Reservation r = new Reservation(Date.valueOf(de.getValue().toString()),Date.valueOf(ds.getValue().toString()));
        
        ReservationService rs = new ReservationService();
        
        rs.Reservation(r);
        
         try {
              
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Done");
                alert.setContentText("Reservation effectué avec succès!");
                alert.show();
             
            } catch (Exception ee) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur!");
                alert.show();
            }
        
    }

    
    
}
