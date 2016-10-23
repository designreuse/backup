
package com.nvidia.cosmos.cloud.rest.util;


import java.io.StringWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;


/**
 * @author Pavan
 *
 */
public class EmailSenderFactory {
	
	private static Logger logger = LoggerFactory.getLogger(EmailSenderFactory.class);
	
	@Autowired
	Environment environment;
	
	/**
	 * @param owner
	 * @throws BaseUMSException
	 */
	private static String EMAILS_YES_NO = null;
	private static String ACCESSID = null;
	private static String INVITE_EMAIL = null;
	private static String REPLY_TO_EMAIL = "";
	private static String SECRET_KEY = null;
    AmazonSimpleEmailServiceClient client = null;
	
	private static EmailSenderFactory instance;
	
	private EmailSenderFactory(String EMAILS_YES_NO, String ACCESSID, String INVITE_EMAIL, String REPLY_TO_EMAIL, String SECRET_KEY){
		try{
			this.EMAILS_YES_NO = EMAILS_YES_NO;
			this.ACCESSID=ACCESSID;
			this.INVITE_EMAIL=INVITE_EMAIL;
			this.REPLY_TO_EMAIL=REPLY_TO_EMAIL;
			this.SECRET_KEY=SECRET_KEY;
			//logger.info("Invite Email="+INVITE_EMAIL+" \n AccessID="+ACCESSID+" \n Secrect Key="+SECRET_KEY);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static EmailSenderFactory getInstance() throws Exception {
		if(instance != null){
			return instance;
		}
		return null;
	}
	public static EmailSenderFactory init(String EMAILS_YES_NO, String ACCESSID, String INVITE_EMAIL, String REPLY_TO_EMAIL, String SECRET_KEY) throws Exception {
		if (instance == null) {
			return instance = new EmailSenderFactory(EMAILS_YES_NO, ACCESSID, INVITE_EMAIL, REPLY_TO_EMAIL, SECRET_KEY);
		} else {
			return instance;
		}
	}
	
	public boolean sendEmail(String m_to, String subject, Map<String, Object> model, String templateName) {
		boolean emailSend = true;
		try {
			if(EMAILS_YES_NO.equalsIgnoreCase("yes")){
				if (logger.isInfoEnabled()) {
					logger.info("Sending an email to "+m_to);
				}
				VelocityEngine velocityEngine = new VelocityEngine(); 
	            velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath"); 
	            velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName()); 
	            velocityEngine.init(); 
	            VelocityContext context = new VelocityContext(); 
	            Iterator<String> iterator = model.keySet().iterator(); 
	            while(iterator.hasNext()) { 
	                 String key = iterator.next(); 
	                 Object value = model.get(key); 
	                 context.put(key,value);      
	            } 
	            StringWriter strWriter = new StringWriter();
	            Template template = velocityEngine.getTemplate(templateName);
	            template.merge(context, strWriter);
	            String emailcontent = strWriter.toString();
	            
	            
	            if(client==null)
				{
					BasicAWSCredentials credentials=new BasicAWSCredentials(ACCESSID, SECRET_KEY);
					client=new AmazonSimpleEmailServiceClient(credentials);
				}
	            Destination destination = new Destination().withToAddresses(new String[]{m_to});
	            com.amazonaws.services.simpleemail.model.Content subject1 = new com.amazonaws.services.simpleemail.model.Content().withData(subject);
		        com.amazonaws.services.simpleemail.model.Content textBody = new  com.amazonaws.services.simpleemail.model.Content().withData(emailcontent); 
		        Body body = new Body().withHtml(textBody);
		        
		        Message message = new Message().withSubject(subject1).withBody(body);
		        
		        SendEmailRequest request = new SendEmailRequest().withSource(INVITE_EMAIL).withDestination(destination).withMessage(message);
		        if(REPLY_TO_EMAIL.length()>0){
		        	List<String> emails = new LinkedList<String>();
		        	emails.add(REPLY_TO_EMAIL);
			        request.setReplyToAddresses(emails);
		        }
		        try
		        {        
	 
		            Region REGION = Region.getRegion(Regions.US_WEST_2);
		            client.setRegion(REGION);
		            client.sendEmail(request);  
		            logger.info("Email has successfully sent to "+m_to);
		            return true;
		        }
		        catch (AmazonServiceException ex) 
		        {
		        	if(ex.getStatusCode()==400){
		        		try
		        		{
		        			 Region REGION = Region.getRegion(Regions.US_WEST_2);
		 		            client.setRegion(REGION);
		 		           client.sendEmail(request);  
				            logger.info("Email has successfully sent to "+m_to);
				            return true;
		        		}catch(Exception e){
		        		logger.error(e.getMessage(),e);
		        		}
		        	}else{
		        		logger.error(ex.getMessage());
		        		}
		        	}
		        catch (Exception e) {
		        	logger.error(e.getMessage(),e);
			
		        }

	            
			}else {
				emailSend = false;
			}
		} catch(Exception e){
			e.printStackTrace();
		}
	
		return emailSend;
	}
	
 

}
