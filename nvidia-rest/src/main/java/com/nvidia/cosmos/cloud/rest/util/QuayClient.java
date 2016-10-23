package com.nvidia.cosmos.cloud.rest.util;

import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.AUTHORIZATION;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.QUEY_FAILED;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.REGISTRATION_FAILED;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Headers;
import com.jayway.restassured.response.Response;
import com.nvidia.cosmos.cloud.exceptions.QuayFailedException;
import com.nvidia.cosmos.cloud.exceptions.RegistrationFailedException;
import com.nvidia.cosmos.cloud.exceptions.UserNotFoundException;
import com.nvidia.cosmos.cloud.rest.common.util.DeleteAdapter;
import com.nvidia.cosmos.cloud.rest.common.util.IRestAdapter;
import com.nvidia.cosmos.cloud.rest.common.util.PostAdapter;
import com.nvidia.cosmos.cloud.rest.common.util.PutAdapter;
import com.nvidia.cosmos.cloud.rest.common.util.ResponseType;

public class QuayClient {
	private static Logger logger = LoggerFactory.getLogger(QuayClient.class);

	@Autowired
	Environment environment;
	
	/**
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 * 
	 */
	public static Map<String, Object> createCustomer(String url, String authorization, String name, String email, String redirectURI)
			throws RegistrationFailedException{
		logger.debug("Creating customer with url {} and input as {} ", url, name);

		Map<String, Object> object = null;
		if (url != null) {

			// QuayApplication application = new QuayApplication("", "Cosmos",
			// "http://54.183.142.244:9000/", "http://54.183.142.244:9000/",
			// "");

			IRestAdapter response = PostAdapter
					.builder()
					.setEndPoint(url)
					.setHeader(AUTHORIZATION, authorization)
					.setRequestObject(
							"{  \"avatar_email\": \"\",  " + "\"name\": \"DGX-1\",  " + "\"redirect_uri\": \"" + redirectURI + "\", "
									+ " \"application_uri\": \"" + redirectURI + "\", " + "\"description\": \"\" }")
					.setMethodName("/v1/organization/" + name + "/applications").build();

			// JsonPath jsonResponse = response.execute();
			Response jsonResponse = response.executeForAllStatuscode();
			logger.debug("Reponse from quey for applications call {}",jsonResponse);

			if (jsonResponse != null) {
				if (jsonResponse.getStatusCode() == 200) {
					ObjectMapper mapper = new ObjectMapper();
	 				try {
						object = mapper.readValue(jsonResponse.prettyPrint(), Map.class);
						logger.info("Object " + object + "{}");
					} catch (JsonParseException | JsonMappingException e) {
						logger.error("Registration failed with message "+e.getMessage());
						throw new RegistrationFailedException(REGISTRATION_FAILED);
					} catch (IOException e) {
						logger.error("Registration failed with message "+e.getMessage());
						throw new RegistrationFailedException(REGISTRATION_FAILED);
					}
				} else if (jsonResponse.getStatusCode() == 204) {
					logger.error("Registration failed with message {}",jsonResponse.getStatusCode() );
					throw new RegistrationFailedException(REGISTRATION_FAILED);
				} else if (jsonResponse.getStatusCode() == 400 || jsonResponse.getStatusCode() == 401 || jsonResponse.getStatusCode() == 403
						|| jsonResponse.getStatusCode() == 404) {
					logger.error("Registration failed with message {}",jsonResponse.getStatusCode() );
					throw new RegistrationFailedException(REGISTRATION_FAILED);
				}
			}else{
				logger.error("Error while retrving application info from QUAY");
				throw new RegistrationFailedException(REGISTRATION_FAILED);
			}
			return object;

		}
		return null;

	}
	

	/**
	 * authorization - OAuth for super admin
	 * 
	 * @throws MalformedURLException
	 * 
	 */
	public static Map<String, Object> createRolesForSuperAdmin(String url, String authorization) throws QuayFailedException {
		logger.debug("Creating roles for super admin with url {} ", url);

		Map<String, Object> object = null;
		if (url != null) {
			String[] teams = new String[] { "readers", "writers" };
			String[] roles = new String[] { "member", "creator" };
			String[] descriptions = new String[] { "Read only members", "Read/ Write members" };
			for (int i = 0; i < teams.length;) {
				IRestAdapter response = PutAdapter.builder().setEndPoint(url).setHeader(AUTHORIZATION, authorization)
						.setRequestObject("{\"role\":\"" + roles[i] + "\", \"description\":\"" + descriptions[i] + "\"}")
						.setMethodName("/v1/organization/nvidia/team/" + teams[i]).build();

				Response jsonResponse = response.executeForAllStatuscode();
				
				if (jsonResponse != null) {
					if (jsonResponse.getStatusCode() == 200) {
						ObjectMapper mapper = new ObjectMapper();
		 				try {
							object = mapper.readValue(jsonResponse.prettyPrint(), Map.class);
							logger.debug("Created roles {}", jsonResponse);
						} catch (JsonParseException | JsonMappingException e) {
							logger.error("Quey failed with message "+e.getMessage());
							throw new QuayFailedException(QUEY_FAILED);
						} catch (IOException e) {
							logger.error("Quey failed with message "+e.getMessage());
							throw new QuayFailedException(QUEY_FAILED);
						}
					} else if (jsonResponse.getStatusCode() == 204) {
						logger.error("Quey failed with status code {} "+jsonResponse.getStatusCode() );
						throw new QuayFailedException(QUEY_FAILED);
					} else if (jsonResponse.getStatusCode() == 400 || jsonResponse.getStatusCode() == 401 || jsonResponse.getStatusCode() == 403
							|| jsonResponse.getStatusCode() == 404) {
						logger.error("Quey failed with status code {} "+jsonResponse.getStatusCode() );
						throw new QuayFailedException(QUEY_FAILED);
					}
				}
				return object;

			}
			// return jsonResponse.toString();

		}
		return null;

	}

	/**
	 * authorization - OAuth for super admin
	 * 
	 * @throws MalformedURLException
	 * 
	 */
	public static Map<String, Object> assignRoleReaders(String url, String authorization, String name) throws QuayFailedException {
		logger.debug("Assign roles to user with url {} and name {} ", url, name);

		Map<String, Object> object = null;		
		if (url != null) {
			IRestAdapter response = PutAdapter.builder().setEndPoint(url).setHeader(AUTHORIZATION, authorization)
					// .setRequestObject("{\"role\":\"member\",
					// \"description\":\"Read only members\"}")
					.setMethodName("/v1/organization/nvidia/team/readers/members/" + name).build();

			Response jsonResponse = response.executeForAllStatuscode();

			if (jsonResponse != null) {
				if (jsonResponse.getStatusCode() == 200 || jsonResponse.getStatusCode() == 404) {
					ObjectMapper mapper = new ObjectMapper();
	 				try {
						object = mapper.readValue(jsonResponse.prettyPrint(), Map.class);
						logger.debug("Created roles {}", jsonResponse);
					} catch (JsonParseException | JsonMappingException e) {
						logger.error("Quey failed with message "+e.getMessage());
						throw new QuayFailedException(QUEY_FAILED);
					} catch (IOException e) {
						logger.error("Quey failed with message "+e.getMessage());
						throw new QuayFailedException(QUEY_FAILED);
					}
				} else if (jsonResponse.getStatusCode() == 204) {
					logger.error("Quey failed with status code {} "+jsonResponse.getStatusCode() );
					throw new QuayFailedException(QUEY_FAILED);
				} else if (jsonResponse.getStatusCode() == 400 || jsonResponse.getStatusCode() == 401 || jsonResponse.getStatusCode() == 403
						|| jsonResponse.getStatusCode() == 404) {
					logger.error("Quey failed with status code {} "+jsonResponse.getStatusCode() );
					throw new QuayFailedException(QUEY_FAILED);
				}
			}
			return object;

			// return jsonResponse.toString();

		}
		return null;

	}

	/**
	 * authorization - OAuth for customer admin
	 * 
	 * @throws MalformedURLException
	 * 
	 */
	public static Map<String, Object> createRoleWriters(String url, String authorization, String name) throws QuayFailedException {

		logger.debug("Creating role for user with url {} and name {}", url, name);

		Map<String, Object> object = null;
		if (url != null) {
			JsonObject requestJson =new JsonObject();
			
			IRestAdapter response = PutAdapter.builder().setEndPoint(url).setHeader(AUTHORIZATION, authorization)
					.setRequestObject("{\"role\":\"creator\", \"description\":\"Read/ Write members\"}")
					.setMethodName("/v1/organization/" + name + "/team/writers").build();

			Response jsonResponse = response.executeForAllStatuscode();
			
			if (jsonResponse != null) {
				if (jsonResponse.getStatusCode() == 200) {
					ObjectMapper mapper = new ObjectMapper();
	 				try {
						object = mapper.readValue(jsonResponse.prettyPrint(), Map.class);
						logger.debug("Created writer role {}", jsonResponse);
					} catch (JsonParseException | JsonMappingException e) {
						logger.error("Quey failed with message "+e.getMessage());
						throw new QuayFailedException(QUEY_FAILED);
					} catch (IOException e) {
						logger.error("Quey failed with message "+e.getMessage());
						throw new QuayFailedException(QUEY_FAILED);
					}
				} else if (jsonResponse.getStatusCode() == 204) {
					logger.error("Quey failed with status code {} "+jsonResponse.getStatusCode() );
					throw new QuayFailedException(QUEY_FAILED);
				} else if (jsonResponse.getStatusCode() == 400 || jsonResponse.getStatusCode() == 401 || jsonResponse.getStatusCode() == 403
						|| jsonResponse.getStatusCode() == 404) {
					logger.error("Quey failed with status code {} "+jsonResponse.getStatusCode() );
					throw new QuayFailedException(QUEY_FAILED);
				}
			}
			return object;			

		}
		return null;

	}

	/**
	 * Url authorization - OAuth for customer admin
	 * 
	 * @throws MalformedURLException
	 * 
	 */
	public static Map<String, Object> assignRoleWriters(String url, String authorization, String tenantName, String name) throws QuayFailedException {
		logger.debug("Assign roles to user with url {} and name {} tenent{} ", url, name,tenantName);

		Map<String, Object> object = null;
		if (url != null) {
			IRestAdapter response = PutAdapter.builder().setEndPoint(url).setHeader(AUTHORIZATION, authorization)
					// .setRequestObject("{\"role\":\"creator\",
					// \"description\":\"Read/ Write members\"}")
                    			.setMethodName("/v1/organization/" + tenantName + "/team/writers/members/" + name).build();

			Response jsonResponse = response.executeForAllStatuscode("");
			
			if (jsonResponse != null) {
				if (jsonResponse.getStatusCode() == 200 ) {
					ObjectMapper mapper = new ObjectMapper();
	 				try {
						object = mapper.readValue(jsonResponse.prettyPrint(), Map.class);
						logger.debug("Assigned writers role ", jsonResponse);
					} catch (JsonParseException | JsonMappingException e) {
						logger.error("Quey failed with message "+e.getMessage());
						throw new QuayFailedException(QUEY_FAILED);
					} catch (IOException e) {
						logger.error("Quey failed with message "+e.getMessage());
						throw new QuayFailedException(QUEY_FAILED);
					}
				} else if (jsonResponse.getStatusCode() == 204) {
					logger.error("Quey failed with status code {} "+jsonResponse.getStatusCode() );
					throw new QuayFailedException(QUEY_FAILED);
				} else if (jsonResponse.getStatusCode() == 400 || jsonResponse.getStatusCode() == 401 || jsonResponse.getStatusCode() == 403
						|| jsonResponse.getStatusCode() == 404) {
					logger.error("Quey failed with status code {} "+jsonResponse.getStatusCode() );
					throw new QuayFailedException(QUEY_FAILED);
				}
			}
			return object;

			// return jsonResponse.toString();

		}
		return null;

	}
	
	
	
	/**
	 * authorization - OAuth for customer admin
	 * 
	 * @throws MalformedURLException
	 * 
	 */
	public static void deleteUser(String url, String authorization, String name) throws UserNotFoundException {

		logger.debug("delete user with url {} and name {}", url, name);

		
		if (url != null) {
			IRestAdapter request = DeleteAdapter.builder().setEndPoint(url).setHeader(AUTHORIZATION, authorization)
					//.setRequestObject("{\"role\":\"creator\", \"description\":\"Read/ Write members\"}")
					.setMethodName("/v1/superuser/users/" + name ).build();
			try{
			Response jsonResponse = request.executeForAllStatuscode();
            logger.debug("delete operation status {}",jsonResponse.getStatusCode());
			}catch(AssertionError ae){
				//throw new UserNotFoundException(environment.getMessage("user.notfound.email", new String[]{name}, new Locale("en", "US")));
				logger.error("User not found with name "+name);
			}

		}
		

	}
	public static String createUsre(String url, String authorization, String name, String email)
			throws RegistrationFailedException{
		logger.debug("Creating user in quay parameters url {}  input name {} and  email ", url, name);

		Map<String, Object> object = null;
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("username", name);
		jsonObject.addProperty("email", email);
		if (url != null) {
			IRestAdapter request = PostAdapter
					.builder()
					.setEndPoint(url)
					.setContentType(ResponseType.JSON)
					.setHeader(AUTHORIZATION, authorization)
					.setRequestObject(jsonObject)
					.setMethodName("/v1/superuser/users/").build();
			Response jsonResponse = request.executeForAllStatuscode("application/json");
			logger.debug("Reponse from quay for applications call {}",jsonResponse);
    	if (jsonResponse != null) {
				if (jsonResponse.getStatusCode() == 200) {
					ObjectMapper mapper = new ObjectMapper();
	 				try {
						object = mapper.readValue(jsonResponse.prettyPrint(), Map.class);
						logger.info("Object " + object + "{}");
						return object.get("password").toString();
					} catch (Exception e) {
						logger.error("Registration failed with message "+e.getMessage());
						throw new RegistrationFailedException(REGISTRATION_FAILED);
					}
				} else {
					logger.error("Registration failed with message {}",jsonResponse.getStatusCode() );
					throw new RegistrationFailedException(REGISTRATION_FAILED);
				}
			}
		}
		return null;

	}
	
	
	
	
	public static String updateUserPassword(String url, String authorization, String name, String password)
			throws RegistrationFailedException{
		logger.debug("updating user in quay parameters url {}  input authorization {} and  name {} ", url, authorization, name);

		Map<String, Object> object = null;
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("password", password);
		//jsonObject.addProperty("email", email);
		if (url != null) {
			IRestAdapter request = PostAdapter
					.builder()
					.setEndPoint(url)
					.setContentType(ResponseType.JSON)
					.setHeader(AUTHORIZATION, authorization)
					.setRequestObject(jsonObject)
					.setMethodName("/v1/superuser/users/"+name).build();
			Response jsonResponse = request.executeForAllStatuscode("application/json");
			logger.debug("Reponse from quay for applications call {}",jsonResponse);
    	if (jsonResponse != null) {
				if (jsonResponse.getStatusCode() == 200) {
					ObjectMapper mapper = new ObjectMapper();
	 				try {
						object = mapper.readValue(jsonResponse.prettyPrint(), Map.class);
						logger.info("Object " + object + "{}");
						return object.get("password").toString();
					} catch (Exception e) {
						logger.error("Registration failed with message "+e.getMessage());
						throw new RegistrationFailedException(REGISTRATION_FAILED);
					}
				} else {
					logger.error("Registration failed with message {}",jsonResponse.getStatusCode() );
					throw new RegistrationFailedException(REGISTRATION_FAILED);
				}
			}
		}
		return null;

	}
	
	
	
	
	public static String getQuayOAuthTocken(String url, String authorization, String clientId, String redirectUri,String scope)
			throws RegistrationFailedException{
		logger.debug("Retriving auth tocken ");
		//String urlParameters="client_id=DMWFJW6J8SQG2HQT5UG0&redirect_uri=http://54.183.142.244/&scope=super:user";
		                       
		String urlParameters="client_id="+clientId+"&redirect_uri="+redirectUri+"&scope="+scope;
		logger.debug("\nUrl :{}\n, authorization :{}\n clientId :{}\n redirectUri :{}\n scope :{}",url,authorization,clientId,redirectUri,scope);
		
		if (url != null) {
			IRestAdapter request = PostAdapter
					.builder()
					.setEndPoint(url)
					.setContentType(ResponseType.URLENCODE)
					.setHeader(AUTHORIZATION, authorization)
					.setRequestObject(urlParameters)
					.setMethodName("/oauth/authorizeapp").build();
			Response response=null;
			try {
				response = request.executeForAllStatuscode("text/html; charset=utf-8");
			} catch (AssertionError e) {
				logger.error("Error while retriving QAut {}",e.getMessage());
			}
			logger.debug("Reponse from quey for applications call {}",response);

			if (response != null) {
				if (response.getStatusCode() == 302) {
					String location = response.header("Location");
					String completeOAutTocken= StringUtils.substringAfter(location, "access_token=");
					return StringUtils.substringBefore(completeOAutTocken, "&");
	 				
				} 
			}else{
				logger.error("Error while retrving auth token from  QUAY");
				throw new RegistrationFailedException(REGISTRATION_FAILED);
			}
			

		}
		return null;

	}
	
	/*public static void main(String[] args) throws Exception{
	//String authrization ="Basic bnZhZG1pbjpXZTFjb21lIUAj" ;
	//String authrization ="Bearer jGomlC4njrZetksAilfHBxV0n1yamwFAKNbzuaSX";
	String authrization ="Bearer gvtAOBtwhZeShh4yfmix4nG8hERj56MqXr7WF6tk";
	String url="http://52.40.144.237/api";
    String name ="apinewuser9";
    String email= "apinewuser9@yopmail.com";
    
    //String authTocken ="http://52.40.144.237/oauth/authorizeapp?client_id=DMWFJW6J8SQG2HQT5UG0&redirect_uri=http://54.183.142.244/&scope=super:user";
  // String password= createUsre(url,authrization,name,email);
   
  // String credentials =name+":"+password;
   String credentials ="nvadmin"+":"+"We1come!@#";
   
   byte[] encodedBytes = org.apache.commons.codec.binary.Base64.encodeBase64(credentials.getBytes());
	String userCredetials = new String(encodedBytes);
    authrization="Basic "+userCredetials;
    System.out.println("authorization ["+authrization+"]");
    System.out.println("User OAuth is "+getQuayOAuthTocken("http://52.40.144.237",authrization,"DMWFJW6J8SQG2HQT5UG0","http://54.183.142.244/","super:user"));
		//String test="http://54.183.142.244/?scope=super%3Auser#access_token=NHLgEfHhuRzCZCdVvFzeQlhFmZaqWE76EL8aHI1w&token_type=Bearer&expires_in=315576000";
		
	}*/
	
	

}
