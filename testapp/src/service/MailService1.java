/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Asus
 */
public class MailService1 {
    
    public static void sendMail () throws Exception{

System.out.println("Preparing to send email");

Properties properties = new Properties();
properties.put("mail.smtp.auth", "true");
properties.put("mail.smtp.starttls.enable", "true");
properties.put("mail.smtp.host", "smtp.gmail.com");
properties.put("mail.smtp.port", "587");

String myAccountEmail = "zaienebaccar2015@gmail.com";
String password = "22/09/1998";

Session session = Session.getInstance(properties, new Authenticator() {

@Override
protected PasswordAuthentication getPasswordAuthentication() {
return new PasswordAuthentication(myAccountEmail, password);
}
});

Message message = prepareMessage(session, myAccountEmail, myAccountEmail);
Transport.send(message);
System.out.println("Message sent successfully");
}

private static Message prepareMessage(Session session, String myAccountEmail, String recepient) {
Message message = new MimeMessage(session);

try {
message.setFrom(new InternetAddress(myAccountEmail));
message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
message.setSubject(" AJOUT MAISON");
message.setText("Votre maison a été ajouté avec succés ");
} catch (Exception e) {
Logger.getLogger(MailService1.class.getName(), e.getMessage());
}
return message;
}

}
    

