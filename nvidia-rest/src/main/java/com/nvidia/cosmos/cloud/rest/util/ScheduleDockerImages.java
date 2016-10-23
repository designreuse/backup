package com.nvidia.cosmos.cloud.rest.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.nvidia.cosmos.cloud.common.ServicesConstants;
import com.nvidia.cosmos.cloud.exceptions.UserNameExitsException;
import com.nvidia.cosmos.cloud.exceptions.UserNotFoundException;
import com.nvidia.cosmos.cloud.factory.ServicesFactory;
import com.nvidia.cosmos.cloud.rest.common.util.MarathonQuayIntegrationUtil;
import com.nvidia.cosmos.cloud.services.user.model.User;
import com.nvidia.cosmos.cloud.services.user.service.UserService;

@Component
@EnableScheduling
public class ScheduleDockerImages {/*
	private static Logger logger = LoggerFactory.getLogger(ScheduleDockerImages.class);
	final String dir = System.getProperty("user.dir");
	UserService userService = ServicesFactory.getInstance().getUserService();
	@Autowired
	Environment environment;
	
	@Scheduled(fixedRate = 1000*60)
	public void createDockerImages(){
		List<User> userList = userService.findAllUsers();
		FileOutputStream fileOutputStream = null;
		for (User user : userList) {
			// Dockers needs to be  created only for other then super admin.
			if(user.getRole().getName().equals(ServicesConstants.ROLES[0])) continue;
			String fileName= user.getUserName() + ".tar.gz";
			File file = new File(dir+File.separator+fileName);
				if (!file.exists() && user.getDockerUri()!=null && user.getDockerUri().length!=0) {
					//Docker image is available in database, missing in home directory 
					try {
						file.createNewFile();
						fileOutputStream = new FileOutputStream(file);
						fileOutputStream.write(user.getDockerUri());
						fileOutputStream.flush();
						fileOutputStream.close();
						
					} catch (IOException e) {
						logger.error("Erorr while copying docker image from db to home dir {}",e);
					}
				}/*else{
					//Docker image is not created for the user, create and store in database.
					
					if (user.getqAuthToken() != null && user.getPassword()!=null && user.getUserName()!=null) {
						//final String dir = System.getProperty("user.dir");
						String quayDockerLogin = environment.getProperty("quay.docker.login");
						String response=null;
						try {
							
							response = MarathonQuayIntegrationUtil.generateDocker(user.getqAuthToken(), user.getPassword(), user.getEmail(),
									user.getUserName(),dir, quayDockerLogin);
						} catch (Exception e1) {
							logger.error("Error while creating Docker file");
						}
						if (response != null && !response.isEmpty()) {
							String dockerUriFile=user.getUserName()+".tar.gz";
							File dockerFile=new File(dir+File.separator+dockerUriFile);
							byte[] dockerUriData = new byte[(int) file.length()];
							try {
							    FileInputStream fileInputStream = new FileInputStream(dockerFile);
							    fileInputStream.read(dockerUriData);
							    fileInputStream.close();
							} catch (Exception e) {
							    logger.error("Unable to Read Docker Uri File...",e.getCause());
							}
							user.setDockerUri(dockerUriData);
							try {
								userService.mergeUser(user);
							} catch (UserNotFoundException e) {
								logger.error("Error while adding Docker image for user", e);
							} catch (UserNameExitsException e) {
								logger.error("Error while adding Docker image for user", e);
							}
							logger.info("Docker URI file uploaded into database successfully{}"+dockerUriFile);
						}

					}
				}
		}
		
		
		
	}

*/}
