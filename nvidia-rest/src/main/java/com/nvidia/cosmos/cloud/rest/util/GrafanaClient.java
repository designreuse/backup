package com.nvidia.cosmos.cloud.rest.util;

import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.GRAPHANA_FAILED;

import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import com.nvidia.cosmos.cloud.common.UserStatusEnum;
import com.nvidia.cosmos.cloud.exceptions.CustomerNotFoundException;
import com.nvidia.cosmos.cloud.exceptions.GraphanaFailedException;
import com.nvidia.cosmos.cloud.exceptions.UserNameExitsException;
import com.nvidia.cosmos.cloud.exceptions.UserNotFoundException;
import com.nvidia.cosmos.cloud.factory.ServicesFactory;
import com.nvidia.cosmos.cloud.rest.common.util.DeleteAdapter;
import com.nvidia.cosmos.cloud.rest.common.util.GetAdapter;
import com.nvidia.cosmos.cloud.rest.common.util.IRestAdapter;
import com.nvidia.cosmos.cloud.rest.common.util.PostAdapter;
import com.nvidia.cosmos.cloud.rest.common.util.ResponseType;
import com.nvidia.cosmos.cloud.services.cluster.model.Cluster;
import com.nvidia.cosmos.cloud.services.customer.model.Customer;
import com.nvidia.cosmos.cloud.services.customer.service.CustomerService;
import com.nvidia.cosmos.cloud.services.node.model.Node;
import com.nvidia.cosmos.cloud.services.user.model.User;
import com.nvidia.cosmos.cloud.services.user.service.UserService;;

public  class GrafanaClient  {
	private static Logger logger = LoggerFactory.getLogger(GrafanaClient.class);
	//public static  String GRAFAN_HOST_URL=null;
	//private static final String CUSTOMER_DASHBOARD = "CustomerDashBoard.json";
	//private static final String CUSTOMER_SEVENDAYS_DASHBOARD = "SevenDaysDashBoard.json";
	
	private static final String CLUSTERCURRENT = "ClusterCurrent.json";
	private static final String CLUSTERLASTSEVENDAYS = "ClusterLastSevenDays.json";
	
	private static final String NODECURRENT = "NodeCurrent.json";
	private static final String NODELASTSEVENDAYS = "NodeLastSevenDays.json";
	
	private static String seperator =":";
	private static String credentials =null;
	
	
	private static CustomerService customerService = null;
	private static String adminCredetials = null;
	private static UserService userService = null;
	
	 static {
		ServicesFactory factory = null;
		try {
			factory = ServicesFactory.getInstance();
			customerService = factory.getCustomerService();
			userService = factory.getUserService();
			
		} catch (Exception e) {
			logger.error("Error while intializing services");
		}
		
		
	}
	 
	

	public static void createCustomerDetailsInGrafana( User user, String grfanaUrl,String influxUrl,String gUserName,String gUserPasssword,String influxdbUserName,String influxdbPassword,String databaseName) throws CustomerNotFoundException, GraphanaFailedException, JsonParseException, JsonMappingException, AssertionError, IOException, UserNotFoundException, UserNameExitsException {
		//create user 
		credentials=gUserName+seperator+gUserPasssword;
		
		byte[] encodedBytes = org.apache.commons.codec.binary.Base64.encodeBase64(credentials.getBytes());
		adminCredetials = new String(encodedBytes);
		String organizationName = user.getCustomer().getName() + "-" + user.getCustomer().getId();
		String grafanaOrgId = user.getCustomer().getGrafanaOrgId();
		Long orgId = null;
		if (grafanaOrgId != null) {
			orgId = Long.parseLong(grafanaOrgId);
		} else {
	 			orgId = createOrg(organizationName, grfanaUrl);
			
		}
		if(orgId!=null){
			
		switchOrgnizationContext(orgId, grfanaUrl);
		if(NvidiaUtil.checkInfluxDBForCustomer(user.getCustomer())){
		createInfluxDBDatabase(influxUrl,databaseName);
		}
		createInfluxDataSource(grfanaUrl,influxUrl,influxdbUserName,influxdbPassword,databaseName);
		String orgAuthKey = createOrgAuthKey(organizationName + "Key", grfanaUrl);
     	user.getCustomer().setGrafanaOrgId(String.valueOf(orgId));

		user.getCustomer().setGrafanaAuthToken(orgAuthKey);
		Long orgUserId =createOrgUser(user, grfanaUrl);
		user.getCustomer().setGrafanaOrgUserId(String.valueOf(orgUserId));
		customerService.mergeCustomer(user.getCustomer());
			//logger.error("Error while persisting cutomer gAuth to db");
			
		switchOrgnizationContext(1L,grfanaUrl);
		}
		
	}
	
	
	
	
	public static void  createInfluxDBDatabase(String influxUrl,String databaseName) throws GraphanaFailedException{
		
		String dbUrl=null;
		try {
			dbUrl = influxUrl+"/query?q="+URLEncoder.encode("CREATE DATABASE "+databaseName,"UTF-8")+"&db=_internal";
			HttpClient client = HttpClientBuilder.create().build();
			logger.debug("\nSending 'GET' request to URL : " + dbUrl);
			HttpGet request = new HttpGet(dbUrl);
		    HttpResponse response = client.execute(request);
			int responseCode = response.getStatusLine().getStatusCode();
			logger.debug("Response Code : " + responseCode);
			if(responseCode==200){
				String retUrl= influxUrl+"/query?q="+URLEncoder.encode("CREATE RETENTION POLICY default ON " +databaseName+ " DURATION 168h REPLICATION 1 DEFAULT","UTF-8")+"&db="+databaseName;
				client = HttpClientBuilder.create().build();
				logger.debug("\nSending 'GET' request to URL : " + retUrl);
				 request = new HttpGet(retUrl);
			     response = client.execute(request);
				 responseCode = response.getStatusLine().getStatusCode();
				 logger.debug("Retention policy addtion response is {}",responseCode);
			}
		} catch (Exception e) {
			logger.error("Error while creating influx db database",e);
			throw new GraphanaFailedException(GRAPHANA_FAILED);
		}
	}
	
	
	
public static void  deleteInfluxDBDatabase(String influxUrl,String databaseName) throws GraphanaFailedException{
		
		String dbUrl=null;
		try {
			dbUrl = influxUrl+"/query?q="+URLEncoder.encode("DROP DATABASE "+databaseName,"UTF-8")+"&db="+databaseName;
			HttpClient client = HttpClientBuilder.create().build();
			logger.debug("\nSending 'GET' request to URL : " + dbUrl);
			HttpGet request = new HttpGet(dbUrl);
		    HttpResponse response = client.execute(request);
			int responseCode = response.getStatusLine().getStatusCode();
			logger.debug("Response Code : " + responseCode);
			
		} catch (Exception e) {
			logger.error("Error while creating influx db database",e);
			throw new GraphanaFailedException(GRAPHANA_FAILED);
		}
	}

	
	public static void createInfluxDataSource(String grfanaUrl,String influxUrl,String influxdbUserName,String influxdbPassword,String databaseName) {
		
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("name", "CollectD");
		jsonObject.addProperty("type", "influxdb");
		jsonObject.addProperty("url", influxUrl);
		jsonObject.addProperty("isDefault", true);
		jsonObject.addProperty("access", "proxy");
		jsonObject.addProperty("database", databaseName);
		jsonObject.addProperty("user", influxdbUserName);
		jsonObject.addProperty("password", influxdbPassword);
		
		IRestAdapter request = PostAdapter.builder().setEndPoint(grfanaUrl)
				.setHeader("Authorization", "Basic " + adminCredetials).setContentType(ResponseType.JSON)
				.setRequestObject(jsonObject).setMethodName("/api/datasources").build();
		logger.debug("Create datasource for org request {}", request.toString());

		JsonPath jsonResponse = request.execute();
		logger.debug("Data source org {}", jsonResponse.prettyPrint());
		
				
	}
	
	private static Long createOrgUser(User user, String grfanaUrl) throws GraphanaFailedException, UserNotFoundException, UserNameExitsException {
		Long orgUserId=null;
		String organizationName = user.getCustomer().getName() + "-" + user.getCustomer().getId();
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("name", organizationName);
		jsonObject.addProperty("email", user.getCustomer().getEmail());
		jsonObject.addProperty("login", organizationName);
		jsonObject.addProperty("password", user.getCustomer().getGrafanaAuthToken());
		
		IRestAdapter request = PostAdapter.builder().setEndPoint(grfanaUrl)
				.setHeader("Authorization", "Basic " + adminCredetials).setContentType(ResponseType.JSON)
				.setRequestObject(jsonObject).setMethodName("/api/admin/users").build();
		logger.debug("Create global user for org {}", request.toString());

		try{
		JsonPath jsonResponse = request.execute();
		logger.debug("Creation of users response {}", request.toString());
		
		
		if (jsonResponse != null) {
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> values=null;
			try {
				values = mapper.readValue(jsonResponse.prettyPrint(), Map.class);
			} catch (Exception e) {
				logger.error("Error while retriving grfana userID {}",e);
			}
			logger.info("Organization created for a user ", jsonResponse);
				orgUserId =new Long(String.valueOf(values.get("id")));	
				
	  }
			
		jsonObject = new JsonObject();
		jsonObject.addProperty("name", organizationName);
		//jsonObject.addProperty("email", customer.getEmail());
		jsonObject.addProperty("loginOrEmail", organizationName);
		jsonObject.addProperty("skipEmails", true);
		jsonObject.addProperty("role", "Viewer");
		
		request = PostAdapter.builder().setEndPoint(grfanaUrl)
				.setHeader("Authorization", "Basic " + adminCredetials)
				.setContentType(ResponseType.JSON)
				.setRequestObject(jsonObject).setMethodName("/api/org/invites").build();
		logger.debug("Adding user to org {}", request.toString());

		 jsonResponse = request.execute("text/plain; charset=utf-8");
		//String jsonResponse = request.executes();
		logger.debug("Adding user to org {}", jsonResponse.prettyPrint());
		}catch(java.lang.AssertionError ae){
			user.setqAuthToken(null);
			user.setStatus(UserStatusEnum.REGISTERED.toString());
			
			userService.mergeUser(user);
			throw new GraphanaFailedException(GRAPHANA_FAILED);
		}
		return orgUserId;
	}

	public static void switchOrgnizationContext(Long orgId, String grfanaUrl) {
		IRestAdapter request = PostAdapter.builder().setEndPoint(grfanaUrl)
		.setHeader("Authorization", "Basic " + adminCredetials).setContentType(ResponseType.JSON)
		.setMethodName("/api/user/using/" + orgId).build();
		logger.debug("Switching to main org request {}", request.toString());
		Response jsonResponse = request.executeForAllStatuscode("");
		logger.debug("Switching main org result{} ", jsonResponse.toString());
	}
	
	
	public static void switchOrgnizationContext(Customer customer, String grfanaUrl) {
		String customerLogin = customer.getName() + "-" + customer.getId()+":"+customer.getGrafanaAuthToken();
		//byte[] encodedBytes = org.apache.commons.codec.binary.Base64.encodeBase64("admin:admin".getBytes());
		byte[] encodedBytes = org.apache.commons.codec.binary.Base64.encodeBase64(customerLogin.getBytes());
		String customerCredetials = new String(encodedBytes);
		IRestAdapter request = PostAdapter.builder().setEndPoint(grfanaUrl)
		.setHeader("Authorization", "Basic " + customerCredetials).setContentType(ResponseType.JSON)
		.setMethodName("/api/user/using/" + customer.getGrafanaOrgId()).build();
		logger.debug("Switching to main org request {}", request.toString());
		JsonPath jsonResponse = request.execute("text/plain; charset=utf-8");
		logger.debug("Switching main org result{} ", jsonResponse.toString());
	}

	
	public static void crateClusterDashBoard(Customer customer,Cluster clusetr,String grfanaUrl,String nodeName){
		switchOrgnizationContext(new Long(customer.getGrafanaOrgId()),grfanaUrl);
		String customerCurrentDB = "Customer " +customer.getId()+"-" +clusetr.getId() + " Current";
		String customerLast7DB = "Customer " + +customer.getId()+"-" +clusetr.getId()  + " Last 7 Days";
		
		JsonObject jsonObjcet = createDashBordJson(CLUSTERCURRENT, customerCurrentDB,nodeName);
		createDasahBoard(jsonObjcet, customer, grfanaUrl);
		jsonObjcet = createDashBordJson(CLUSTERLASTSEVENDAYS, customerLast7DB,nodeName);
		createDasahBoard(jsonObjcet, customer, grfanaUrl);
		switchOrgnizationContext(1L,grfanaUrl);
	}
	
	public static void crateNodeDashBoard(Customer customer,Cluster clusetr,Node node, String grfanaUrl){
		switchOrgnizationContext(new Long(customer.getGrafanaOrgId()),grfanaUrl);
		String customerCurrentDB = "Customer " +customer.getId()+"-" +clusetr.getId()+"-" +node.getId() + " Current";
		String customerLast7DB = "Customer " + +customer.getId()+"-" +clusetr.getId()+"-" +node.getId()  + " Last 7 Days";
		JsonObject jsonObjcet = createDashBordJson(NODECURRENT, customerCurrentDB,node.getName());
		createDasahBoard(jsonObjcet, customer, grfanaUrl);
		jsonObjcet = createDashBordJson(NODELASTSEVENDAYS, customerLast7DB,node.getName());
		createDasahBoard(jsonObjcet, customer,grfanaUrl);
		switchOrgnizationContext(1L,grfanaUrl);
	}
	
	private static void createDasahBoard(JsonObject jsonObject, Customer customer, String grfanaUrl) {
		IRestAdapter request = PostAdapter.builder().setEndPoint(grfanaUrl)
				.setHeader("Authorization", "Bearer " + customer.getGrafanaAuthToken())
				.setContentType(ResponseType.JSON).setRequestObject(jsonObject).setMethodName("/api/dashboards/db")
				.build();
		logger.debug("Creating DashBoard request {}", request.toString());
		JsonPath jsonResponse = request.execute();
		logger.debug("DasahBoard creation result{} ", jsonResponse.toString());
	}

	/**
	 * Method to create AuthKey for org
	 * 
	 * @param orgId
	 *            organization Id
	 * @return AuthKey
	 * @throws GraphanaFailedException 
	 */
	private static String createOrgAuthKey(String custKeyName, String grfanaUrl) throws GraphanaFailedException {

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("name", custKeyName);
		jsonObject.addProperty("role", "Editor");
		IRestAdapter request = PostAdapter.builder().setEndPoint(grfanaUrl)
				.setHeader("Authorization", "Basic " + adminCredetials).setContentType(ResponseType.JSON)
				.setRequestObject(jsonObject).setMethodName("/api/auth/keys").build();
		logger.debug("Auth Key creation request {}", request.toString());
		
		JsonPath jsonResponse = request.execute();

		Map<String, Object> object = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			object = mapper.readValue(jsonResponse.prettyPrint(), Map.class);
		} catch (Exception e) {
			logger.error("Error while creating organization auth key", e);
			throw new GraphanaFailedException(GRAPHANA_FAILED);
		}
		String authKey = (String) object.get("key");

		return authKey;
	}

	private static Long createOrg(String orgName,String grfanaUrl) throws GraphanaFailedException,java.lang.AssertionError, JsonParseException, JsonMappingException, IOException{
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("name", orgName);
		IRestAdapter request = PostAdapter.builder().setEndPoint(grfanaUrl)
				.setHeader("Authorization", "Basic " + adminCredetials).setContentType(ResponseType.JSON)
				.setRequestObject(jsonObject).setMethodName("/api/orgs/").build();
		logger.debug("Orgnization creation request {}", request.toString());

		Response jsonResponse = request.executeForAllStatuscode();
		
		logger.debug("Response from grafana orgnization creation{}",jsonResponse);
		if(jsonResponse.getStatusCode() == 400){
			throw new GraphanaFailedException("Orgnization already exists with name ["+orgName+"]");
		}
		
		logger.debug("Orgnization creation Response {}", jsonResponse);		
		
		Map<String, Object> object = null;

		if (jsonResponse != null) {
				ObjectMapper mapper = new ObjectMapper();
					object = mapper.readValue(jsonResponse.prettyPrint(), Map.class);
					logger.info("Organization created for a user ", jsonResponse);
		}

		Integer orgId = (Integer) object.get("orgId");		

		Long orggId=null;
		if(orgId!=null){
		orggId = new  Long(orgId);
		}
		
		return orggId;
	}
	
	/*	 private static String getEncodedCredentials(String gUserName, String gUserPasssword){
		 String credentials=gUserName+seperator+gUserPasssword;
		  byte[] encodedBytes = org.apache.commons.codec.binary.Base64.encodeBase64(credentials.getBytes());
		  String	adminCredetials = new String(encodedBytes);
		  return adminCredetials;
	 }
	
	
public static String createUserAuthKey(String userName, String orgID,String grfanaUrl,String gUserName,String gUserPasssword) throws GraphanaFailedException {
		 String credentials=getEncodedCredentials(gUserName,gUserPasssword);
		
		switchOrgnizationContext(new Long(orgID), grfanaUrl);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("name", userName);
		jsonObject.addProperty("role", "Viewer");
		IRestAdapter request = PostAdapter.builder().setEndPoint(grfanaUrl)
				.setHeader("Authorization", "Basic " + credentials).setContentType(ResponseType.JSON)
				.setRequestObject(jsonObject).setMethodName("/api/auth/keys").build();
		logger.debug("Auth Key creation request {}", request.toString());
		
		Response jsonResponse = request.executeForAllStatuscode("");

		Map<String, Object> object = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			object = mapper.readValue(jsonResponse.prettyPrint(), Map.class);
		} catch (Exception e) {
			logger.error("Error while creating organization auth key", e);
			throw new GraphanaFailedException(GRAPHANA_FAILED);
		}
		String authKey = (String) object.get("key");
		switchOrgnizationContext(1L, grfanaUrl);
		return authKey;
	}
	
	
	
	public static void deleteUserAuthKey(String userName, String orgID, String grfanaUrl,String gUserName,String gUserPasssword) throws GraphanaFailedException {
		 String credentials=getEncodedCredentials(gUserName,gUserPasssword);
		
		switchOrgnizationContext(new Long(orgID), grfanaUrl);
			IRestAdapter request = GetAdapter.builder().setEndPoint(grfanaUrl)
				.setHeader("Authorization", "Basic " + credentials).setContentType(ResponseType.JSON)
				.setMethodName("/api/auth/keys").build();
		logger.debug("Auth Keys list request {}", request.toString());
		
		Response jsonResponse = request.executeForAllStatuscode("");
		String usersKeys=jsonResponse.getBody().asString();
		if(StringUtils.isEmpty(usersKeys)){
			logger.debug("User keys not avilable in Grafana");
			switchOrgnizationContext(1L, grfanaUrl);
			return;
		}
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		List<GrafanaUserKey> grafanaUserKeys = null;
		try {
			 grafanaUserKeys = objectMapper.readValue(usersKeys,
					objectMapper.getTypeFactory().constructCollectionType(List.class, GrafanaUserKey.class));
		} catch (IOException e1) {
			logger.error("Error while retriving user keys from grafana",e1);
		}
		String userId=null;
		if(grafanaUserKeys!=null){
		for (Iterator<GrafanaUserKey> iterator = grafanaUserKeys.iterator(); iterator.hasNext();) {
			GrafanaUserKey grafanaUserKey = (GrafanaUserKey) iterator.next();
			logger.debug("Name {} and ID {}",grafanaUserKey.getName(),grafanaUserKey.getId());
			if(grafanaUserKey.getName().equals(userName)){
				userId = grafanaUserKey.getId();
				break;
			}
			
		}
		}
		if(StringUtils.isEmpty(userId)){
			logger.debug("User ID not found for the user {} in Grafana",userName);
			switchOrgnizationContext(1L, grfanaUrl);
			return;
		}
		
		request = DeleteAdapter.builder().setEndPoint(grfanaUrl)
				.setHeader("Authorization", "Basic " + credentials).setContentType(ResponseType.JSON)
				.setMethodName("/api/auth/keys/"+userId).build();
		logger.debug("Auth Key creation request {}", request.toString());
		
		 jsonResponse = request.executeForAllStatuscode("");
		 
		 logger.debug("Delete operation response {}",jsonResponse.getStatusCode());
		
		 switchOrgnizationContext(1L, grfanaUrl);
	}
	
	*/
	
	
public static void deleteOrg(Customer  customer,String grfanaUrl,String gUserName,String gUserPasssword) throws GraphanaFailedException,java.lang.AssertionError, JsonParseException, JsonMappingException, IOException{
	credentials=gUserName+seperator+gUserPasssword;
	byte[] encodedBytes = org.apache.commons.codec.binary.Base64.encodeBase64(credentials.getBytes());
	adminCredetials = new String(encodedBytes);	
	if(customer.getGrafanaOrgId() != null && !customer.getGrafanaOrgId().isEmpty()){
		if(customer.getGrafanaOrgUserId()!=null && !customer.getGrafanaOrgUserId().isEmpty()){
			//delete grafana global user 
			String uri=	"/api/admin/users/"+customer.getGrafanaOrgUserId();
			IRestAdapter request = DeleteAdapter.builder().setEndPoint(grfanaUrl)
					.setHeader("Authorization", "Basic " + adminCredetials).setContentType(ResponseType.JSON)
					.setMethodName(uri).build();
			Response jsonResponse =request.executeForAllStatuscode("");
			logger.debug("Delete user api response {}", jsonResponse.getStatusCode());
		}
			
		
		IRestAdapter request = DeleteAdapter.builder().setEndPoint(grfanaUrl)
				.setHeader("Authorization", "Basic " + adminCredetials).setContentType(ResponseType.JSON)
				.setMethodName("/api/orgs/"+customer.getGrafanaOrgId()).build();
				
		logger.debug("Orgnization delete request {}", request.toString());
		Response jsonResponse = request.executeForAllStatuscode("");//("text/plain; charset=utf-8");
		
		
		if(jsonResponse.getStatusCode() != 200){
			logger.error("Orgnization delete Response {}", jsonResponse);	
			throw new GraphanaFailedException("Customer delete failed in grafana. Name: "+customer.getName()+" .Contact admin");
		}
		
		int custId1=Integer.parseInt(customer.getGrafanaOrgId())+1;
		IRestAdapter request1 = DeleteAdapter.builder().setEndPoint(grfanaUrl)
				.setHeader("Authorization", "Basic " + adminCredetials).setContentType(ResponseType.JSON)
				.setMethodName("/api/orgs/"+custId1).build();
				
		logger.debug("Orgnization delete request {}", request1.toString());
		Response jsonResponse1 = request1.executeForAllStatuscode("");
		
		
		if(jsonResponse1.getStatusCode() != 200){
			logger.error("Orgnization delete Response {}", jsonResponse1);	
			throw new GraphanaFailedException("Customer delete failed in grafana. Id: "+custId1+" .Contact admin");
		}
		logger.debug("Orgnization delete Response {}", jsonResponse);
		logger.debug("Orgnization delete Response {}", jsonResponse1);	
		
		}
	}

	private static JsonObject createDashBordJson(String fileName, String customerName,String nodeName) {
		JsonObject dasHBoardObject = null;
		JsonParser parser = new JsonParser();
		try {
			// create customer dashboard
			ClassPathResource shellScript = new ClassPathResource(fileName);
			dasHBoardObject = (JsonObject) parser.parse(new FileReader(shellScript.getFile()));
			logger.debug("Before Modificetaion: {}", dasHBoardObject.toString());
			JsonObject dashBorad = dasHBoardObject.getAsJsonObject("dashboard");
			dashBorad.addProperty("title", customerName);
			dashBorad.addProperty("originalTitle", customerName);
			
			
			JsonArray rowsArry= dashBorad.getAsJsonArray("rows");
			//System.out.println("Number of Rows ["+rowsArry.size());
			for (int rowsIndex = 0; rowsIndex < rowsArry.size(); rowsIndex++) {
				JsonObject panels = rowsArry.get(rowsIndex).getAsJsonObject();
				JsonArray panelArry=  panels.getAsJsonArray("panels");
				//System.out.println("Panel Size=====>"+panelArry.size());
				for (int panelsIndex = 0; panelsIndex < panelArry.size(); panelsIndex++) {
					JsonObject targets = panelArry.get(panelsIndex).getAsJsonObject();
					JsonArray targetArry = targets.getAsJsonArray("targets");
					//System.out.println("Size of targets["+targetArry.size()+"]");
					
					for (int targetIndex = 0; targetIndex < targetArry.size(); targetIndex++) {
						JsonObject target = targetArry.get(targetIndex).getAsJsonObject();
						if(target.get("query")!=null){
						//System.out.println(target.get("query").getAsString().replace("nvidia", "Surendra"));
						if(nodeName!=null)
							target.addProperty("query", target.get("query").getAsString().replace("node-name", nodeName));
						}
					}
					
				}
				
			}
			
			logger.debug("After Modificetaion: {}", dasHBoardObject.toString());

		} catch (Exception e) {
			logger.error("Error while creating dash board for customer ", e);
		}
		return dasHBoardObject;
	}
	
	
	
}
