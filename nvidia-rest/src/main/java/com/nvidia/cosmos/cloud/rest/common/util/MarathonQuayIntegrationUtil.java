package com.nvidia.cosmos.cloud.rest.common.util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

public class MarathonQuayIntegrationUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(MarathonQuayIntegrationUtil.class);

	/*public static void main(String[] args) throws Exception{
		
			generateDocker();
    }*/
	
	/**
	 
    	method to integrate Marathon with Quay
		NVCloud should create URI with user credentials which Marathon uses to pull the image from quay repository in order to run the docker container. The URI should be available at the location reachable from the appliance.
		Launch a docker container located at Quay for a user created at LDAP server
		Following steps are performed at NVCloud before creating job definition JSON for the Marathon instance. The main idea is to create an URI with user credentials which are further passed as part of job definition JSON
         
	 * @param oAuthTocken customer oAuthTocken
	 * @param password  customer password
	 * @param mail      customer email
	 * @param quayDockerLogin2 
	 * @param userID    cutomerID to create docker image name
	 */
	public static String  generateDocker(String oAuthTocken, String password, String mail, String userName,String dir,String quayDockerLogin) {
		String resopnse=null;
		ClassPathResource shellScript = new ClassPathResource("MarathonQuayIntegrationUtil.sh");
		
		logger.info("Initiated docker creation with parameters oAuthTocken[ "+oAuthTocken+"] password ["+password+"] mail["+mail+"]userID ["+userName+"]" );
		try {
			String shellScriptLocation=shellScript.getFile().getAbsolutePath();
			logger.debug("Found shell script in location["+shellScriptLocation+"]");
			String[] command = {"/bin/bash", shellScriptLocation, oAuthTocken , password,mail,dir,userName,quayDockerLogin};
			logger.info("Docker tar will be create in lcoaction["+dir+"]");
			ProcessBuilder pBuilder = new ProcessBuilder(command);
			Process process= pBuilder.start();
			logger.debug("Docker script execution: ");
			BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
		    String line;
		    while ((line = br.readLine()) != null) {
		    	logger.info(line);
		    	resopnse=line;
		    }
		 logger.info("Docker created successfully.........");
		} catch (IOException e) {
			logger.error("Error while creating docker ",e.getMessage());
		}
		return resopnse;
	}

}
