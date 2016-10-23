/**
 * 
 */
package com.nvidia.cosmos.cloud.rest.util;


import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author prbatta
 *
 */
public class EmailThread implements Runnable {

	private static Logger logger = LoggerFactory.getLogger(EmailThread.class);
    private String mailTos;
    private String subject;
    private Map<String, Object> model;
	private String templateName;
    
    public EmailThread(String mailTos, String subject, Map<String, Object> model, String templateName){
        this.mailTos=mailTos;
        this.subject=subject;
        this.model=model;
        this.templateName=templateName;
    }

    @Override
    public void run() {
    	try{
	      
	        logger.info(Thread.currentThread().getName()+" Start. EmailThread Command = "+mailTos);
	        EmailSenderFactory.getInstance().sendEmail(mailTos, subject, model, templateName);
	       logger.info(Thread.currentThread().getName()+" End EmailThread .");
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }

   
}
