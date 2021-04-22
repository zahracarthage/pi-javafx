/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Session;
import javax.mail.Authenticator; 
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;
/**
 *
 * @author zcart
 */
public class mail {
    public static void sendMail(String recepient) throws Exception
    {
        
        System.out.println("sending");
        Properties properties = new Properties(); 
        
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable", "true"); 
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port","587"); 
        
        String myAccountEmail ="kiftrip@gmail.com"; 
        String password ="fwnuebbsvvcsxygh"; 
        
        Session session
              ;
                   
        //mail.smtp.host
        
        session = Session.getInstance(properties, new Authenticator()
                
        {
            protected PasswordAuthentication getPasswordAuthenticator() {
                
                return new PasswordAuthentication(myAccountEmail,password);
            }
                    
        }
                
        );
        
        Message message = prepareMessage(session, myAccountEmail, recepient);
        
        Transport.send(message);
        System.out.println("Message sent succesfully");
    }
        
        private static Message prepareMessage(Session session, String myAccountEmail, String recepient)
                
        {
            try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(recepient));
            message.setSubject("Thank you for reaching out to us"); 
            message.setText(" We will get back to you in three business days. ");
            
            return message;
            
            }
            catch (Exception ex) {
                
                Logger.getLogger(mail.class.getName()).log(Level.SEVERE,null, ex);
                
                
            }
            return null;
        }
                
        
        
    }
    

 