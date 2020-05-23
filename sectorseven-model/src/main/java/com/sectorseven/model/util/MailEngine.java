/*package com.sectorseven.model.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

*//**
 * @author Ramesh Naidu 
 *
 *//*

@Component
public final class MailEngine {

    public static final String MAIL_AUTH = "true";
    public static final String MAIL_AUTH_FALSE = "false";
    public static final String MAIL_PORT = "587";
   // public static final String MAIL_PORT = "465";
    public static final String SENDER_EMAIL = "ramesh.terle@gmail.com";
    public static final String SENDER_PASSWORD = "Rameshnaidu@!993";
   
    public static final String SENDER_EMAIL = "harithapalle.sector7@gmail.com";
    public static final String SENDER_PASSWORD = "harithareddy";
    public static final String CONTENT_TYPE = "text/html";
    public static final String SMTP_HOST = "smtp.gmail.com";
    public static final String MAIL_STARTTLS = "true";
  

    public static boolean sendMail(String content, String subject, String receipient) {

        
          Properties props = new Properties();
          boolean mailSent = true;
          
          // For Gmail Authentication Start
          
        //  props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
          props.put("mail.smtp.socketFactory.fallback", MAIL_AUTH_FALSE);
         // props.put("mail.smtp.ssl.enable", MAIL_AUTH);
          props.put("mail.smtp.auth", MAIL_AUTH);
          props.put("mail.smtp.host", SMTP_HOST);
          props.put("mail.smtp.socketFactory.port", MAIL_PORT);
          props.put("mail.smtp.port", MAIL_PORT);
          props.put("mail.smtp.starttls.enable", MAIL_STARTTLS);
          
        
          // For Gmail Authentication End
          
          // For Sent An mail to the users
          
          Session session = Session.getInstance(props, new javax.mail.Authenticator() {
          protected PasswordAuthentication getPasswordAuthentication() {
          return new PasswordAuthentication(SENDER_EMAIL, SENDER_PASSWORD);
          }
          });
          
          try {
          MimeMessage message = new MimeMessage(session);
          message.setHeader("Content-Type", CONTENT_TYPE);
          message.setFrom(new InternetAddress(SENDER_EMAIL));
          message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receipient));
          message.setSubject(subject);
          message.setContent(content, CONTENT_TYPE);
          Transport.send(message);
          } catch (MessagingException e) {
          mailSent = false;
          throw new RuntimeException(e);
          }
          return mailSent;
         
    }

}
*/
package com.sectorseven.model.util;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;


/**
 * @author Ramesh Naidu 
 *
 */

@Component
public final class MailEngine {

	public static final String MAIL_AUTH = "true";
    public static final String MAIL_AUTH_FALSE = "false";
    public static final String MAIL_PORT = "587";
   // public static final String SENDER_EMAIL = "harithapalle.sector7@gmail.com";
   // public static final String SENDER_PASSWORD = "harithareddy";
    public static final String SENDER_EMAIL = "support@e-nnovation.co.in";
    public static final String SENDER_PASSWORD = "server@Sector7";
    public static final String CONTENT_TYPE = "text/html";
   // public static final String SMTP_HOST = "smtp.gmail.com";
    public static final String SMTP_HOST = "mail.e-nnovation.co.in";
    public static final String MAIL_STARTTLS = "true";
    

    public static String subject = "Verification Request : Test1232 D011-1902-031192-ED01 Ml Dahanukar College Of Commerce India";
    public static  String rec =" newverifier_UAT@dataflowgroup.com";
    public static  String body =" Mail";
    
    
    
    
     public static boolean sendMail(String content, String subject, String receipient) {
        Properties props = new Properties();
        boolean mailSent = true;
        // For Gmail Authentication Start
        
       // props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", MAIL_AUTH_FALSE);
       // props.put("mail.smtp.ssl.enable", MAIL_AUTH);
        props.put("mail.smtp.auth", MAIL_AUTH);
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.socketFactory.port", MAIL_PORT);
        props.put("mail.smtp.port", MAIL_PORT);
        props.put("mail.smtp.starttls.enable", MAIL_STARTTLS);
        
        // For Gmail Authentication End
        
        // For Sent An mail to the users
        
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(SENDER_EMAIL, SENDER_PASSWORD);
        }
        });
        
        try {
        MimeMessage message = new MimeMessage(session);
        message.setHeader("Content-Type", CONTENT_TYPE);
        message.setFrom(new InternetAddress(SENDER_EMAIL));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receipient));
        message.setSubject(subject);
        message.setContent(content, CONTENT_TYPE);
        
        Transport.send(message);
        } catch (MessagingException e) {
        mailSent = false;
        	throw new RuntimeException(e); 
        }
        return mailSent;
       
  }
     
     public static boolean sendMultipartMail(Multipart content, String subject, String receipient) {
         Properties props = new Properties();
         boolean mailSent = true;
         // For Gmail Authentication Start
         
        // props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
         props.put("mail.smtp.socketFactory.fallback", MAIL_AUTH_FALSE);
        // props.put("mail.smtp.ssl.enable", MAIL_AUTH);
         props.put("mail.smtp.auth", MAIL_AUTH);
         props.put("mail.smtp.host", SMTP_HOST);
         props.put("mail.smtp.socketFactory.port", MAIL_PORT);
         props.put("mail.smtp.port", MAIL_PORT);
         props.put("mail.smtp.starttls.enable", MAIL_STARTTLS);
         
         // For Gmail Authentication End
         
         // For Sent An mail to the users
         
         Session session = Session.getInstance(props, new javax.mail.Authenticator() {
         protected PasswordAuthentication getPasswordAuthentication() {
         return new PasswordAuthentication(SENDER_EMAIL, SENDER_PASSWORD);
         }
         });
         
         try {
         MimeMessage message = new MimeMessage(session);
         message.setHeader("Content-Type", "multipart/mixed");
         message.setFrom(new InternetAddress(SENDER_EMAIL));
         message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receipient));
         message.setSubject(subject);
         message.setContent(content, "multipart/mixed");
         
         Transport.send(message);
         } catch (MessagingException e) {
         mailSent = false;
         	throw new RuntimeException(e); 
         }
         return mailSent;
        
   }
       /*public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			sendMail(body + " "+i, subject, rec);
		}
	}*/
}