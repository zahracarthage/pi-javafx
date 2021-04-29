/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import service.ResactivitesService;
import entite.Activites;
import entite.Resactivites;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;







/**
 * FXML Controller class
 *
 * @author Zannou
 */
public class FormulairereservationController implements Initializable {

    @FXML
    private TextField txtnbpersonnes;
    @FXML
    private DatePicker txtdateres;
   
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }  
    @FXML
     private void Reservation(ActionEvent event) throws SQLException {
        
      
          String nb = txtnbpersonnes.getText();
          int nbpersonnes = Integer.parseInt(nb);
          
          
         
          
        Resactivites r = new Resactivites(nbpersonnes,Date.valueOf(txtdateres.getValue().toString()));
        
        ResactivitesService rs = new ResactivitesService();
        
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

   /* @FXML
    private void sendMail(KeyEvent event) {
        
      // Recipient's email ID needs to be mentioned.
      String to = "ellilizeineb@gmail.com";

      // Sender's email ID needs to be mentioned
      String from = "zeineb.ellili@esprit.tn";
      final String username = "Zeineb ELLILI";//change accordingly
      final String password = "203JFT0980";//change accordingly

      // Assuming you are sending email through relay.jangosmtp.net
      String host = "relay.jangosmtp.net";

      Properties props = new Properties();
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.port", "25");

      // Get the Session object.
      Session session = Session.getInstance(props,
         new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(username, password);
	   }
         });

      try {
	   // Create a default MimeMessage object.
	   Message message = new MimeMessage(session);
	
	   // Set From: header field of the header.
	   message.setFrom(new InternetAddress(from));
	
	   // Set To: header field of the header.
	   message.setRecipients(Message.RecipientType.TO,
               InternetAddress.parse(to));
	
	   // Set Subject: header field
	   message.setSubject("reservation");
	
	   // Now set the actual message
	   message.setText("votre réservation est faite avec succées " +
		"email using JavaMailAPI ");

	   // Send message
	   Transport.send(message);

	   System.out.println("Sent message successfully....");

      } catch (MessagingException e) {
         throw new RuntimeException(e);
      }
   }*/
}
    
   


    

  

