
package service;

import java.sql.Connection;
import entite.Event;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.io.File;
import java.io.IOException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import utils.DataSource;


public class EventService {
    public boolean addEvent(Event a){
        Connection cnx =null;
        Statement st = null;
       
       
        String requette = "INSERT INTO events (nom,date,capacite,description,adresse) VALUES ('"+a.getNom()+"','" +a.getDate()+ "'," +a.getCapacite()+",'"+a.getDescription()+"','"+a.getAdresse()+"')";
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
    public ObservableList<Event> showEvent(){
        Connection cnx =null;
        Statement st = null;
        ResultSet rs = null;
        ObservableList<Event> liste = FXCollections.observableArrayList();
        String requette = "SELECT * FROM events";
        
        try {
  cnx = DataSource.getInstance().getCnx();            st = cnx.createStatement();
            rs = st.executeQuery(requette);
            Event testajout;
            while (rs.next()){
               testajout = new Event(rs.getInt("id"), rs.getString("nom"), rs.getString("date"), rs.getInt("capacite"), rs.getString("description"), rs.getString("adresse"));
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

    public boolean updateEvent(Event a){
        Connection cnx =null;
        Statement st = null;
        
        
        String requette = "UPDATE events SET nom='"+a.getNom()+"',date='"+a.getDate()+"',adresse='"+a.getAdresse()+"',capacite="+a.getCapacite()+",description='"+a.getDescription()+"' WHERE id="+a.getId()+"";
        try {
  cnx = DataSource.getInstance().getCnx();            st = cnx.createStatement();
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
    }}
        
        public boolean deleteEvent(Event a){
        Connection cnx =null;
        Statement st = null;
        String requette = "DELETE FROM events WHERE id="+a.getId()+"";
        try {
  cnx = DataSource.getInstance().getCnx();            st = cnx.createStatement();
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
        
        
        
        
        
        
        
        
        public String QR (String A){
        
       
              
             try {
            String qrCodeData = "Event "+A+"";
            String filePath = "C:\\Users\\zcart\\Desktop\\testapp\\src\\QR\\"+A+".png";
            
            String charset = "UTF-8"; // or "ISO-8859-1"
            Map < EncodeHintType, ErrorCorrectionLevel > hintMap = new HashMap < EncodeHintType, ErrorCorrectionLevel > ();
            
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            BitMatrix matrix = new MultiFormatWriter().encode(
                new String(qrCodeData.getBytes(charset), charset),
                BarcodeFormat.QR_CODE, 200, 200, hintMap);
            MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
                .lastIndexOf('.') + 1), new File(filePath));
            System.out.println("QR Code image created successfully!");
            return filePath;
        } catch (Exception e) {
            System.err.println(e);
           return "";
        }
               
       }
        
        
       public void sendEmail(String path) throws IOException {
           final String username ="sokalie1000@gmail.com";
        final String password ="faglvebmnbpjbabh";
        String from = "sokalie1000@gmail.com";
        String to = "mohamednour.benhassouna@esprit.tn"; // change this 

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        Session session = Session.getInstance(properties, new javax.mail.Authenticator(){
        
           protected PasswordAuthentication getPasswordAuthentication(){
               return new PasswordAuthentication(username,password);
           }
        });
        MimeMessage msg =new MimeMessage(session);
        try{
            msg.setFrom(from);
          msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            msg.setSubject("Event ajouté ");
            Multipart con = new MimeMultipart();
            MimeBodyPart text =new MimeBodyPart();
            text.setText("Voici le QR code de cet event");
            MimeBodyPart img = new MimeBodyPart();
            img.attachFile(path);
            con.addBodyPart(text);
            con.addBodyPart(img);
            msg.setContent(con);
            
            Transport.send(msg);
        }catch(MessagingException e){}
       }
        
        
        
        public int rowEvent(){
        Connection cnx =null;
        Statement st = null;
        ResultSet rs = null;
        ObservableList<Event> liste = FXCollections.observableArrayList();
        String requette = "SELECT * FROM events";
        int i=0;
        
        try {
            cnx = DataSource.getInstance().getCnx();
            st = cnx.createStatement();
            rs = st.executeQuery(requette);
            Event chambres;
            while (rs.next()){
               i=i+1;
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
        return i;
        
}
        
        
        
}  

