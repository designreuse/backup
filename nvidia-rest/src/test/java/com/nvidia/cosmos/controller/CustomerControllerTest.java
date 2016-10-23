package com.nvidia.cosmos.controller;

import static com.jayway.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.specification.RequestSpecification;
import com.nvidia.cosmos.cloud.dtos.ApiResponseDTO;
import com.nvidia.cosmos.cloud.dtos.CustomerExistDTO;
import com.nvidia.cosmos.cloud.dtos.FetchDTO;
import com.nvidia.cosmos.cloud.dtos.RegisterDTO;
import com.nvidia.cosmos.cloud.dtos.ResponseDTO;
import com.nvidia.cosmos.cloud.services.customer.model.Customer;
import com.nvidia.cosmos.cloud.services.entitlement.model.Entitlement;
import com.nvidia.cosmos.cloud.services.user.model.User;

public class CustomerControllerTest {

	  private static final String BASE_URI = "http://localhost:8888"; 

	  private static final String X_AUTH_TOKEN = "$2a$12$1tNPYGdPJlNzu7seaBqzOuWvA40zazZ3bEMCL/III3KHA0SbZjtU6";

	  private static final String X_ADMIN_TOKEN = "$2a$12$fgR0yY7C6D0e8DAtQVmilew5OZyQz1Izk3/tVCFuG4sqsvFk.iiIy";

	  private static Logger logger = LoggerFactory.getLogger(CustomerControllerTest.class);
	  
	//  private RestAssured restAssured;
	  private static RequestSpecification spec;

	  @BeforeClass
	  public static void initSpec(){
	      spec = new RequestSpecBuilder()
	              .setContentType(ContentType.JSON)
	              .setBaseUri(BASE_URI)
	              .build();
/*          .addFilter(new ResponseLoggingFilter())//log request and response for better debugging. You can also only log if a requests fails.
          .addFilter(new RequestLoggingFilter())
*/
	      
	  }
	  
	@AfterClass
	public void cleanUp() {

	}
	
	@Test(priority=1)
	public void fetch()throws JsonProcessingException{
		
		FetchDTO fetchDTO= new FetchDTO();
		fetchDTO.setName("lacky1234");
		
		ObjectMapper objectMapper= new ObjectMapper();
		String jsonObj=objectMapper.writeValueAsString(fetchDTO);
		
		ResponseDTO responseDTO= given()
				.spec(spec)
				.body(jsonObj)
				.header("X-Auth_Token",X_ADMIN_TOKEN)
			.and().header("Content-Type", "application/json")
			.when()
			.post("/api/fetch")
			.then()
			.extract().as(ResponseDTO.class);
		System.out.println(responseDTO.toString());
			
			if(responseDTO.getStatusCode()==200){
			assertEquals(200,responseDTO.getStatusCode());
			assertEquals("Entitlement key is validated", responseDTO.getMessage());
			
		    assertNotNull(responseDTO.getData());

			assert true;
			}
			else{
				assert false;
			}
	}
	
	@Test(priority=2)
	public void customerExit()throws JsonProcessingException{
		
		CustomerExistDTO customerExitDTO= new CustomerExistDTO();
		customerExitDTO.setKey("lacky1234");
		customerExitDTO.setEmail("lackytresbu@yopmail.com");
		
		ObjectMapper objectMapper= new ObjectMapper();
		String jsonObj=objectMapper.writeValueAsString(customerExitDTO);
		
		ResponseDTO responseDTO= given()
				.spec(spec)
				.body(jsonObj)
				.header("X-Auth_Token",X_ADMIN_TOKEN)
			.and().header("Content-Type", "application/json")
			.when()
			.post("/api/customerExist")
			.then()
			.extract().as(ResponseDTO.class);
			
			if(responseDTO.getStatusCode()==200){
			assertEquals(200,responseDTO.getStatusCode());
			assertEquals("This email is acceptable", responseDTO.getMessage());
			   
			assert true;
			}
			else{
				assert false;
			}
	}
	
	@Test(priority=3)
	public void register() throws JsonProcessingException{
		
		RegisterDTO registerDTO= new RegisterDTO();
		registerDTO.setName("lacky");
		registerDTO.setEmail("lackytresbu@yopmail.com");
		
		registerDTO.setKey("lacky1234");
		registerDTO.setEulaAccepted("yes");
		
		ObjectMapper objectMapper= new ObjectMapper();
		String jsonObj=objectMapper.writeValueAsString(registerDTO);
		System.out.println("Custome object: "+jsonObj);
		
		ApiResponseDTO responseDTO =  given()
		         .spec(spec)
		         .body(jsonObj)
		    
			.and().header("Content-Type", "application/json")
			.when()
		    .post("/api/register")
		    .then()
			.extract().as(ApiResponseDTO.class);
		
		if(responseDTO.getStatusCode()==200){
			assertEquals(200,responseDTO.getStatusCode());
			assertEquals("Customer registered successfully.", responseDTO.getMessage());
			assert true;
			}
			else{
				assert false;
			}
				
	}
	
	@Test(priority=4)
	public void listCCustomers(){

		//ResponseDTO responseDto = getResource(X_AUTH_TOKEN);		
		ResponseDTO responseDTO =  given()
		         .spec(spec)
		         .when()  	  
		    .header("X-Auth-Token", X_ADMIN_TOKEN)
			.and().header("Content-Type", "application/json")
			
		    .get("/api/customer")
		    .then()
			.extract().as(ResponseDTO.class);
		
		if(responseDTO.getStatusCode()==200){
		assertEquals(200,responseDTO.getStatusCode());
		assertEquals("All customers are retrieved", responseDTO.getMessage());
	   
	    assertNotNull(responseDTO.getData());

		assert true;
		}
		else{
			assert false;
		}

	}
	
	@Test(priority=5)
	public void customerDelete(){
		
		ResponseDTO responseDTO =  given()
		         .spec(spec)
		         .param("id","6")
		         .header("X-Auth-Token", X_ADMIN_TOKEN)
					.and().header("Content-Type", "application/json")
					.when()
				    .delete("/api/deletecustomer/6")
				    .then()
					.extract().as(ResponseDTO.class);
				
				if(responseDTO.getStatusCode()==200){
				assertEquals(200,responseDTO.getStatusCode());
				assertEquals("Customer Deleted Successfully", responseDTO.getMessage());
			 //   assertEquals(null, responseDTO.getQuayUrl());
			    assertNotNull(responseDTO.getData());

				assert true;
				}
				else{
					assert false;
				}
	}

}
