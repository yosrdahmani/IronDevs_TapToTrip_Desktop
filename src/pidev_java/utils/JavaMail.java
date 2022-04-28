/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.utils;


import java.util.Properties;
import java.util.logging.Level;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author seifeddine
 */
public class JavaMail extends Thread {

    public String recipient;
    public static String nomform;
    public int id;

    @Override
    public void run() {
        try {
            super.run(); //To change body of generated methods, choose Tools | Templates.
            sendMail(recipient,id);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(JavaMail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void sendMail(String recepient,int id) throws Exception {
        System.out.println("preparing to");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String myAccountEmail = "toptotrip@gmail.com";
        String myAccountPwd = "toptotrip123";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, myAccountPwd);
            }
        });
        ForgetPassword(session, myAccountEmail, recepient,id);

    }

  
    private static void ForgetPassword(Session session, String myAccountEmail, String recepient,int id) throws Exception {

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Quelqu'un veut changer votre mot de passe");
            MimeMultipart multipart = new MimeMultipart("related");
            BodyPart messageBodyPart = new MimeBodyPart();
            String htmlText
                    = "<center>"
                    + "   <br>"
                    + "   <h1>Votre demande de changement de mot de passe à été effectué, </h1>"
                    + "   <p>Si ce profil vous appartient</p>"
                    + "   <p>Executer ce lien</p>"
                    + "   <p><a href=\"http://127.0.0.1:8000/reset_pass/"+id+"\">Changer mot de pass</a></p>"
                    + "   <p>Pour toute autre information veuillez nous contacter sur notre email</p>"
                    + "</center>";
            messageBodyPart.setContent(htmlText, "text/html");
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            Transport.send(message);
            System.out.println("message sent");
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(JavaMail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
