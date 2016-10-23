package com.nvidia.cosmos.controller;

import static com.jayway.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.specification.RequestSpecification;
import com.nvidia.cosmos.cloud.dtos.CustomerDTO;
import com.nvidia.cosmos.cloud.dtos.EntitlementDTO;
import com.nvidia.cosmos.cloud.dtos.ResponseDTO;
import com.nvidia.cosmos.cloud.dtos.UserDTO;
import com.nvidia.cosmos.cloud.services.customer.model.Customer;
import com.nvidia.cosmos.cloud.services.entitlement.model.Entitlement;
import com.nvidia.cosmos.cloud.services.user.model.User;
import com.nvidia.cosmos.config.Response;

public class EntitilementControllerTest {
	
	public static Reporter rep=null;

	  private static final String BASE_URI = "http://localhost:8888"; 
	  //private static final int BASE_PORT = 8080; 
	
	  private static final String X_ADMIN_TOKEN = "$2a$12$pwiVZqtQ/R/abS.XqTmI5eRWzhxEHgALZOZ62.VPJvgnXi/pGd1x2";
	//  private RestAssured restAssured;

	  private static RequestSpecification spec;
	  
	  private static Logger logger = LoggerFactory.getLogger(CustomerControllerTest.class);
	  
	  @BeforeClass
	  public static void initSpec(){
	      spec = new RequestSpecBuilder()
	              .setContentType(ContentType.JSON)
	              .setBaseUri("http://localhost:8888")
	              .build();
	      
/*          .addFilter(new ResponseLoggingFilter())//log request and response for better debugging. You can also only log if a requests fails.
        .addFilter(new RequestLoggingFilter())
*/
	      rep=new Reporter();
	  }
	  
	@AfterClass
	public void cleanUp() {

	}
	
	@Test(priority=1)
	public void saveEntitilement1() throws JsonProcessingException{

		ObjectMapper objectMapper = new ObjectMapper();
		UserDTO user1 = new UserDTO();
		UserDTO user2= new UserDTO();
		List<UserDTO> users = new ArrayList<UserDTO>();	
		users.add(user1);
		users.add(user2);
		CustomerDTO customer = new CustomerDTO();
		customer.setUsers(users);
		EntitlementDTO entitlement = new EntitlementDTO("lacky", "lacky1234", "lacky4321", null);
		entitlement.setInfluxdbUrl("https://head-dev.tresbu.com:8086");
		entitlement.setDatabaseName("collectd");
		entitlement.setInfluxdbUser("admin");
		entitlement.setInfluxdbPassword("admin");
		entitlement.setCustomer(customer);
		String json = objectMapper.writeValueAsString(entitlement);

		logger.info("Entitlement : "+json);

		given().spec(spec)
        		.body(json)
    		    .header("X-Auth-Token", X_ADMIN_TOKEN)
    			.and().header("Content-Type", "application/json")
        		.when()
		    .post("/api/entitlement")
		    .then()
			.extract().as(ResponseDTO.class);

		logger.info("ResponseDTO : SUccessfully");
        
		
	}

	@Test (priority=2)
	public void listCEntitlements1(){

		//ResponseDTO responseDto = getResource(X_AUTH_TOKEN);		
		ResponseDTO responseDTO =  given()
		         .spec(spec)
		    .header("X-Auth-Token", X_ADMIN_TOKEN)
			.and().header("Content-Type", "application/json")
			.when()
		    .get("/api/entitlement")
		    .then()
			.extract().as(ResponseDTO.class);

       if(responseDTO.getStatusCode()==200){
		assertEquals(200,responseDTO.getStatusCode());
		assertEquals("All Entitlements are retrieved", responseDTO.getMessage());
	   // assertEquals(null, responseDTO.getQuayUrl());
	    assertNotNull(responseDTO.getData());

		assert true;
       }
       else{
    	   assert false;
       }
       
    }

	@Test (priority=3)
	public void listByEntitlementName(){

		//ResponseDTO responseDto = getResource(X_AUTH_TOKEN);		
		ResponseDTO response =  given()
		         .spec(spec)
		    .header("X-Auth-Token", X_ADMIN_TOKEN)
			.and().header("Content-Type", "application/json")
			.when()
		    .get("/api/entitlement/lacky")
		    .then()
			.extract().as(ResponseDTO.class);

    		if(response.getStatusCode()==200){
    			assertEquals(200,response.getStatusCode());
    			assertEquals("Listed all Entitlement names ",response.getMessage());
    			assert true;
    		       }
       else{
    	  assert false;
       }
	}
	
	@Test (priority=4)
	public void fetchByEntitlementName(){

		//ResponseDTO responseDto = getResource(X_AUTH_TOKEN);		
		ResponseDTO response =  given()
		         .spec(spec)
		    .header("X-Auth-Token", X_ADMIN_TOKEN)
			.and().header("Content-Type", "application/json")
			.when()
		    .get("/api/fetchentitlement/lacky")
		    .then()
			.extract().as(ResponseDTO.class);

    		if(response.getStatusCode()==200){
    			assertEquals(200,response.getStatusCode());
    			assertEquals("Entitlement details retrieved", response.getMessage());
    			
    			assert true;
		  }
    		else{
    			assert false;
    	  
       }
    		
	}
	
}
