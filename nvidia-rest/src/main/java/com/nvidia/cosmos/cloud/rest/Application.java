// tag::runner[]
package com.nvidia.cosmos.cloud.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.nvidia.cosmos.cloud.rest.security.AuthenticationFilter;


@Configuration
@ComponentScan(basePackages = { "com.nvidia.cosmos" })
@EnableAutoConfiguration(exclude={MultipartAutoConfiguration.class})
@PropertySource(value = { "classpath:email.properties", "classpath:config.properties",
		"classpath:messages_en_US.properties" })
public class Application {

	private static Logger logger = LoggerFactory.getLogger(Application.class);
	@Bean
	CommandLineRunner init() {
		return null;
	}

	private @Autowired AutowireCapableBeanFactory beanFactory;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
		//getMesosSlaveDirctoryForChronos("");
		//System.out.println("===============>"+getMesosSlaveDirctoryForMarthon(""));
	}

	/*@PostConstruct
	public void intitDockerUri() throws MalformedURLException {
		final String dir = System.getProperty("user.dir");
		UserService userService = ServicesFactory.getInstance().getUserService();
		List<User> userList = userService.findAllUsers();
		FileOutputStream fileOutputStream = null;
		for (User user : userList) {
			String fileName= user.getUserName() + ".tar.gz";
			File file = new File(dir+File.separator+fileName);
				if (!file.exists() && user.getDockerUri()!=null && user.getDockerUri().length!=0) {
					try {
						file.createNewFile();
						fileOutputStream = new FileOutputStream(file);
						fileOutputStream.write(user.getDockerUri());
						fileOutputStream.flush();
						fileOutputStream.close();
						
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
		}
	}*/

	/*@Bean
	public FilterRegistrationBean myFilter() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		AuthenticationTokenProcessingFilter myFilter = new AuthenticationTokenProcessingFilter();
		beanFactory.autowireBean(myFilter);
		registration.setFilter(myFilter);
		return registration;
	}*/
	
	
	
	
	@Bean(name="commonsMultipartResolver")
    public CommonsMultipartResolver commonsMultipartResolver() {
         CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        return commonsMultipartResolver;
    }
	
	
/*	
private static String getMesosSlaveDirctoryForChronos(String serialNumber){
		
		JsonParser parser = new JsonParser();
		ClassPathResource shellScript = new ClassPathResource("state.json");
		
		try {
			JsonObject stateJson = (JsonObject) parser.parse(new FileReader(shellScript.getFile()));
			logger.debug("Parsing completed_frameworks from mesos json for chronous job status");
			JsonArray completedFrameworks= stateJson.getAsJsonArray("completed_frameworks");
			if(completedFrameworks != null){
				logger.debug("found completed_frameworks with size {}",completedFrameworks.size());
				for (int completedFrameworksIndex = 0; completedFrameworksIndex < completedFrameworks.size(); completedFrameworksIndex++) {
					JsonObject completedFramework = completedFrameworks.get(completedFrameworksIndex).getAsJsonObject();
					JsonArray completedExecutors= completedFramework.getAsJsonArray("completed_executors");
					logger.debug("found completed_executors with size {}",completedExecutors.size());
					for (int completedExecutorsIndex = 0; completedExecutorsIndex < completedExecutors.size(); completedExecutorsIndex++) {
						JsonObject completedExecutor = completedExecutors.get(completedExecutorsIndex).getAsJsonObject();
						JsonArray completedTasks = completedExecutor.getAsJsonArray("completed_tasks");
						logger.debug("found completed_tasks with size {}",completedTasks.size());
						String dir=completedExecutor.get("directory").getAsString();
						for (int completedTasksIndex = 0; completedTasksIndex < completedTasks.size(); completedTasksIndex++) {
							JsonObject completedTask = completedTasks.get(completedTasksIndex).getAsJsonObject();
							JsonObject containerObject = completedTask.getAsJsonObject("container");
							JsonObject dockerObject=containerObject.getAsJsonObject("docker");
							JsonArray parameters = dockerObject.getAsJsonArray("parameters");
							logger.debug("found parameters with size {}",completedTasks.size());
							for (int parametersIndex = 0; parametersIndex < parameters.size(); parametersIndex++) {
								JsonObject parameter = parameters.get(parametersIndex).getAsJsonObject();
								logger.debug("Label is {} value is {}",parameter.get("key").getAsString(),parameter.get("value").getAsString());
								if(parameter.get("key").getAsString().equals("label") && parameter.get("value").getAsString().equals("87ed41eb-ccd9-4d95-8afb-210fa5fb0898")){
									System.out.println("Directory is["+dir+"]");
								}
							}
						}
					}
				}
			}
		
		} catch (IOException e) {
			logger.error("Error while parsing messos json");
		}
		return null;
	}



private static String getMesosSlaveDirctoryForMarthon(String serialNumber){
	
	JsonParser parser = new JsonParser();
	ClassPathResource shellScript = new ClassPathResource("state.json");
	
	List<MessosSlave> list = new ArrayList<MessosSlave>();
	
	try {
		JsonObject stateJson = (JsonObject) parser.parse(new FileReader(shellScript.getFile()));
		logger.debug("Parsing frameworks from messos json for marthon messos slave");
		JsonArray frameworks= stateJson.getAsJsonArray("frameworks");
		if(frameworks != null){
			logger.debug("found completed_frameworks with size {}",frameworks.size());
			for (int frameworksIndex = 0; frameworksIndex < frameworks.size(); frameworksIndex++) {
				JsonObject completedFramework = frameworks.get(frameworksIndex).getAsJsonObject();
				JsonArray completedExecutors= completedFramework.getAsJsonArray("completed_executors");
				logger.debug("found completed_executors with size {}",completedExecutors.size());
				for (int completedExecutorsIndex = 0; completedExecutorsIndex < completedExecutors.size(); completedExecutorsIndex++) {
					JsonObject completedExecutor = completedExecutors.get(completedExecutorsIndex).getAsJsonObject();
					JsonArray completedTasks = completedExecutor.getAsJsonArray("completed_tasks");
					logger.debug("found completed_tasks with size {}",completedTasks.size());
					String dir=completedExecutor.get("directory").getAsString();
					logger.debug("found directory ["+dir+"]");
					for (int completedTasksIndex = 0; completedTasksIndex < completedTasks.size(); completedTasksIndex++) {
						JsonObject completedTask = completedTasks.get(completedTasksIndex).getAsJsonObject();
						String taskState = completedTask.get("state").getAsString();
						if(taskState!=null && taskState.equals("TASK_FAILED")){
							
							JsonArray labels = completedTask.getAsJsonArray("labels");
							JsonArray statuses = completedTask.getAsJsonArray("statuses");
							logger.debug("found parameters with size {}",labels.size());
							for (int labelsIndex = 0; labelsIndex < labels.size(); labelsIndex++) {
								JsonObject label = labels.get(labelsIndex).getAsJsonObject();
								logger.debug("Label is {} value is {}",label.get("key").getAsString(),label.get("value").getAsString());
								if(label.get("key").getAsString().equals("label") && label.get("value").getAsString().equals("b4376d6d-dcb9-4df6-ac35-a055ef47a421")){
									MessosSlave ms=new MessosSlave();
									for (int statusesIndex = 0; statusesIndex < statuses.size(); statusesIndex++){
										JsonObject statuse = statuses.get(statusesIndex).getAsJsonObject();
										ms.setTimeStamp(statuse.get("timestamp").getAsDouble());
										ms.setDirectory(dir);
									}
									list.add(ms);
									//System.out.println("Directory is["+dir+"]");
								}
							}
							
							
							
						}
					}
				}
			}
		}
	
	} catch (IOException e) {
		logger.error("Error while parsing messos json");
	}
	if(list.size()>0){
		Collections.sort(list, new MessosSlave());
		logger.debug("Found failed {} status directories latest timestame is {} and directorory is {}",list.size(),list.get(0).getTimeStamp(),list.get(0).getDirectory());
		return list.get(0).getDirectory();
	}
	return null;
}*/
	
}


/*class MessosSlave implements Comparator<MessosSlave>{
	
	String directory;
	Double timeStamp;
	
	public Double getTimeStamp() {
		return timeStamp;
	}


	public void setTimeStamp(Double timeStamp) {
		this.timeStamp = timeStamp;
	}


	MessosSlave(){
		
	}
	
	
	public String getDirectory() {
		return directory;
	}
	public void setDirectory(String directory) {
		this.directory = directory;
	}
	
	
	@Override
	public int compare(MessosSlave o1, MessosSlave o2) {
		if (o2.getTimeStamp() > o1.getTimeStamp())
		    return 1;
		    else if (o1.getTimeStamp() > o2.getTimeStamp())
		    return -1;
		    else
		    return 0;
		
	}

	
	
}*/
// end::runner[]
