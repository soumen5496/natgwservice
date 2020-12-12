package com.natgrid.webservice.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.natgrid.webservice.dao.ApiLogDtlsRepo;
import com.natgrid.webservice.report.ReportDtlsInterfc;
import com.natgrid.webservice.report.ReportUtil;

@RestController
@Configuration
@EnableScheduling
public class NatgridEmailController {

	@Autowired
	private ApiLogDtlsRepo apilogdtlsrepo;
	
	 @GetMapping("/sendemail")
	   public void sendEmail() {
		 
		 try 
		 {   
			 String bccaddress[]={"pal.soumen@cris.org.in"};
			 String toaddress[]={"pikpal90@gmail.com","bagsusmita@yahoo.in"};
	         //String bccaddress[]={"amitsaini@cris.org.in","pikpal90@gmail.com"};
			 fnsendmail("2020-11",toaddress,bccaddress);
			
		 } catch (MessagingException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      //return "Email sent successfully";
	   }   
	 
	  @Scheduled(cron = "10 30 18 19 * ?")
      public void sendTestEmail() {
		 
		 try 
		 {   
			 String bccaddress[]={"pal.soumen@cris.org.in"};
			 String toaddress[]={"pikpal90@gmail.com","bagsusmita@yahoo.in"};
	         //String bccaddress[]={"amitsaini@cris.org.in","pikpal90@gmail.com"};
			 fnsendmail("2020-11",toaddress,bccaddress);
			
		 } catch (MessagingException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      //return "Email sent successfully";
	   }   
	 
	 private void fnsendmail(String monthyear,String toaddress[],String[] bccaddress) throws AddressException, MessagingException, IOException {
		 
			String months[]={"null","January","February","March","April","May","Jun","July","August","September","October","November","December"};
			String arr[]=monthyear.split("-");
			String ipmn=months[Integer.parseInt(arr[1])];
			 
			List<ReportDtlsInterfc> reportob=apilogdtlsrepo.genReport2(monthyear.replaceAll("-", ""));
			ReportUtil ob=new ReportUtil();
			String filename=ob.genpdf(ipmn,arr[0], reportob);
		 
		    final String username ="dswn@cris.org.in"; 
	        final String password ="lotus12345"; 
	        final String hostid= "172.16.1.206";
	       
	        Properties prop = new Properties();
	        prop.put("mail.smtp.host", hostid);
	        prop.put("mail.smtp.auth", "true");
	        prop.put("mail.smtp.starttls.enable", "true"); //TLS*/
	        
	        //System.out.println("1::"+LocalDateTime.now());

	        Session session = Session.getInstance(prop,
	                new javax.mail.Authenticator() {
	                    protected PasswordAuthentication getPasswordAuthentication() {
	                        return new PasswordAuthentication(username, password);
	                    }
	                });
	        
	        String emailbody="<b><p> Respected Sir/Madam,<br><br>"
					+ "<br>Monthly Summary report of Data Sharing with NATGRID for the month of "+ipmn+", "+arr[0]+" is attached for information please."
					+"<br><br><br> Regards"
					+"<br><br>DSWN Team"
					+"<br>Centre for Railway Information Systems,"
					+"<br>Chanakyapuri, New Delhi</p></b>";
	        
            MimeMessage message = new MimeMessage(session);    
        	
            message.setFrom(new InternetAddress("dswn@cris.org.in")); //"no-reply.smms@cris.org.in"
          
            for(int j=0;j<toaddress.length;j++)
            {
            	InternetAddress tmpadd = new InternetAddress(toaddress[j]);
            	message.addRecipient(Message.RecipientType.TO, tmpadd);
            }
           
            for(int j=0;j<bccaddress.length;j++)
            {
            	InternetAddress tmpadd = new InternetAddress(bccaddress[j]);
            	message.addRecipient(Message.RecipientType.BCC, tmpadd);
            }
            
            message.setSubject("SECRET: Monthly Summary report of Data Sharing with NATGRID for the month of "+ipmn+", "+arr[0]);
            
            // create MimeBodyPart object and set message text        
            BodyPart messageBodyPart1 = new MimeBodyPart();     
            messageBodyPart1.setContent(emailbody, "text/html");          

            // create new MimeBodyPart object and set DataHandler object to this object        
            MimeBodyPart messageBodyPart2 = new MimeBodyPart();      
            DataSource source = new FileDataSource(filename);    
            messageBodyPart2.setDataHandler(new DataHandler(source));    
            messageBodyPart2.setFileName(filename);             

            // create Multipart object and add MimeBodyPart objects to this object        
            Multipart multipart = new MimeMultipart();    
            multipart.addBodyPart(messageBodyPart1);     
            multipart.addBodyPart(messageBodyPart2);      

            //6) set the multiplart object to the message object    
            message.setContent(multipart );        
            
            Transport.send(message);
            
            System.out.println("Email Sent ! at " +LocalDateTime.now());
		    //return "Done";
	 }
}
