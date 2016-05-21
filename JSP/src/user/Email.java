package user;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
	  public void sendemail(String email, String subject, String messageemail) {
			final String username="chriscampagnola@verizon.net";
			final String password="newyork1";
			Properties props=new Properties();
			
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.verizon.net");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");
		      // Get the default Session object.
		      Session session = Session.getDefaultInstance(props,
		    		  new javax.mail.Authenticator(){
		    	  protected PasswordAuthentication getPasswordAuthentication(){
		    		  return new PasswordAuthentication(username,password);
		    	  }
		      }
		    		  );
		     try{
		          // Create a default MimeMessage object.
		          Message message = new MimeMessage(session);

		          // Set From: header field of the header.
		          message.setFrom(new InternetAddress("chriscampagnola@verizon.net"));

		          // Set To: header field of the header.
		          message.addRecipient(Message.RecipientType.TO,
		                                   new InternetAddress(email));

		          // Set Subject: header field
		          message.setSubject(subject);

		          // Create the message part 
		        

		          // Fill the message
		          message.setText(messageemail);
		          
		         

		     

		          // Send message
		          Transport.send(message);
		          System.out.println("Sent message successfully....");
		       }catch (MessagingException mex) {
		          mex.printStackTrace();
		       }
		   }
	 
}
