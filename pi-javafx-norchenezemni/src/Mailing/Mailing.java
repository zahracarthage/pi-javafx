/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mailing;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import utils.DataSource;
/**
 *
 * @author zemni
 */
public class Mailing{

    private Connection con;
    private Statement ste;

    public Mailing() {
        con = DataSource.getInstance().getCnx();

    }

    public static void mailing() throws Exception {

        Properties prop = new Properties();
        final String moncompteEmail = "kiftrip@gmail.com";
        final String psw = "Kiftrip2021";
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        Session ses = Session.getInstance(prop, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(moncompteEmail, psw);
            }
        });

        try {

            Message msg = new MimeMessage(ses);
            msg.setFrom(new InternetAddress(moncompteEmail));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(moncompteEmail));
            msg.setSubject("Article ajouté avec succès");
            msg.setText("Bonjour Norchène, votre article a été bien ajouté ! " );

            Transport.send(msg);

        } catch (MessagingException ex) {
            Logger.getLogger(Mailing.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    



}
