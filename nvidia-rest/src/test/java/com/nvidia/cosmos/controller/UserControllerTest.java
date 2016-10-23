package com.nvidia.cosmos.controller;

import static com.jayway.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.specification.RequestSpecification;
import com.nvidia.cosmos.cloud.dtos.ApiResponseDTO;
import com.nvidia.cosmos.cloud.dtos.ApplianceDTO;
import com.nvidia.cosmos.cloud.dtos.CreateUserDTO;
import com.nvidia.cosmos.cloud.dtos.CustomerDTO;
import com.nvidia.cosmos.cloud.dtos.ProfileDTO;
import com.nvidia.cosmos.cloud.dtos.ResponseDTO;
import com.nvidia.cosmos.cloud.dtos.UpdateUserDTO;
import com.nvidia.cosmos.cloud.dtos.UpdateUserPwdDTO;
import com.nvidia.cosmos.cloud.dtos.UserDTO;
import com.nvidia.cosmos.cloud.dtos.ValidateUserDTO;
import com.nvidia.cosmos.config.Response;


public class UserControllerTest {

	  private static final String BASE_URI = "http://localhost:8888"; 
	  //private static final int BASE_PORT = 8080; 
	  private static final String X_AUTH_TOKEN = "$2a$12$d8U6rErndnXC3aHkjw1WTugHBTQzo2fobhiJeS7YZA.aDSBTVcAM2";
	  private static final String X_ADMIN_TOKEN = "$2a$12$ocSELiZm9uYwkSDQQutJbeETtcK25RlpVSx2H7xOaXDy4RC8aS67i";
	  private static final String KEY = "nvidia-DEV1_DGX-1.high.ram{}";
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
	public void register() throws JsonProcessingException {

		
		ObjectMapper objectMapper = new ObjectMapper();
		//ResponseDTO responseDto = getResource(X_AUTH_TOKEN);	
		CustomerDTO customerDTO=new CustomerDTO();
		CreateUserDTO userDTO = new CreateUserDTO();
		userDTO.setName("ganiesh");
		userDTO.setEmail("jagaswh@yopmail.com");
		userDTO.setgId("345");
		userDTO.setuId("348");
		userDTO.setCustomer(customerDTO);
		
		String objJson = objectMapper.writeValueAsString(userDTO);
		System.out.println("JSON : "+objJson);

		ResponseDTO userResponseDTO =  given()
		        .spec(spec)
		        
		        .body(objJson)
		.when()	
		   .header("X-Auth-Token", X_AUTH_TOKEN)
		.and().header("Content-Type", "application/json")	
		   .post("/api/user/2")
		   .then()
		.extract().as(ResponseDTO.class);

		/*if(userResponseDTO.getStatusCode()==200){
		assertEquals(200,userResponseDTO.getStatusCode());
		assertEquals("User saved successfully", userResponseDTO.getMessage());
		   assertEquals(null, userResponseDTO.getQuayUrl());
		   assertNotNull(userResponseDTO.getData());

		assert true;
		}
		else{
		assert false;
		}*/
		
	}

	@Test(priority=2)
	public void updateuser() throws JsonProcessingException {

		
		ObjectMapper objectMapper = new ObjectMapper();
		//ResponseDTO responseDto = getResource(X_AUTH_TOKEN);	
		
		UpdateUserDTO updateDTO = new UpdateUserDTO();
		updateDTO.setId("8");
		updateDTO.setgId("54");
		updateDTO.setuId("38");
		
		String objJson = objectMapper.writeValueAsString(updateDTO);
		System.out.println("JSON : "+objJson);

		ResponseDTO userResponseDTO =  given()
		        .spec(spec)
		        
		        .body(objJson)
		.when()	
		   .header("X-Auth-Token", X_AUTH_TOKEN)
		.and().header("Content-Type", "application/json")	
		   .post("/api/user")
		   .then()
		.extract().as(ResponseDTO.class);
		
		if(userResponseDTO.getStatusCode()==200){
		assertEquals(200,userResponseDTO.getStatusCode());
		assertEquals("User updated successfully", userResponseDTO.getMessage());
		   assertEquals(null, userResponseDTO.getQuayUrl());
		   assertNotNull(userResponseDTO.getData());

		assert true;
		}
		else{
		assert false;
		}
		
	}
	
	@Test (priority=3)
	public void  listUsers(){

		//ResponseDTO responseDto = getResource(X_AUTH_TOKEN);		
		ResponseDTO responseDTO =  given()
		         .spec(spec)
		    .header("X-Auth-Token", X_AUTH_TOKEN)
			.and().header("Content-Type", "application/json")
	        
			.when()
		    .get("/api/user/2")
		    .then()
			.extract().as(ResponseDTO.class);
		
		if(responseDTO.getStatusCode()==200){
		assertEquals(200,responseDTO.getStatusCode());
		assertEquals("All Users Are Retrieved", responseDTO.getMessage());
	    
		assert true;
		}
		else{
			assert false;
		}
       }
	@Test (priority=4)
	public void  findUserProfile(){

		//ResponseDTO responseDto = getResource(X_AUTH_TOKEN);		
		ResponseDTO response =  given()
		         .spec(spec)
		    .header("X-Auth-Token", X_AUTH_TOKEN)
			.and().header("Content-Type", "application/json")
	       	.when()
		    .get("api/user/profile")
		    .then()
			.extract().as(ResponseDTO.class);
		
		if(response.getStatusCode()==200){
		assertEquals(200,response.getStatusCode());
		assertEquals("User Retrieved", response.getMessage());

		assert true;
		}
		else{
			assert false;
		}
       }
	
	
	@Test(priority=5)
	public void userValidate() throws JsonProcessingException {
	
		ObjectMapper objectMapper = new ObjectMapper();
		//ResponseDTO responseDto = getResource(X_AUTH_TOKEN);	
		
		ValidateUserDTO validateuserDTO = new ValidateUserDTO();
		validateuserDTO.setEmail("ttadmin@yopmail.com");
		validateuserDTO.setOldPwd("We1come!@#");
		
		String objJson = objectMapper.writeValueAsString(validateuserDTO);
		System.out.println("JSON : "+objJson);

		ApiResponseDTO userResponseDTO =  given()
		        .spec(spec)
		        
		        .body(objJson)
		.when()	
		   .header("X-Auth-Token", X_AUTH_TOKEN)
		.and().header("Content-Type", "application/json")	
		   .post("/api/profile/validateuser")
		   .then()
		.extract().as(ApiResponseDTO.class);

		if(userResponseDTO.getStatusCode()==200){
		assertEquals(200,userResponseDTO.getStatusCode());
		assertEquals("User validated successfully", userResponseDTO.getMessage());
		assert true;
		}
		else{
		assert false;
		}
	}
	
	@Test(priority=6)
	public void profileUpdate() throws JsonProcessingException {
	
		ObjectMapper objectMapper = new ObjectMapper();
		//ResponseDTO responseDto = getResource(X_AUTH_TOKEN);	
		
		ProfileDTO profileDTO = new ProfileDTO();
		profileDTO.setId("2");
		profileDTO.setName("TT Admin");
		
		String objJson = objectMapper.writeValueAsString(profileDTO);
		System.out.println("JSON : "+objJson);

		ResponseDTO userResponseDTO =  given()
		        .spec(spec)
		        
		        .body(objJson)
		.when()	
		   .header("X-Auth-Token", X_AUTH_TOKEN)
		.and().header("Content-Type", "application/json")	
		   .post("/api/updateprofile")
		   .then()
		.extract().as(ResponseDTO.class);

		if(userResponseDTO.getStatusCode()==200){
		assertEquals(200,userResponseDTO.getStatusCode());
		assertEquals("User updated successfully", userResponseDTO.getMessage());
		assert true;
		}
		else{
		assert false;
		}
	}
	
	@Test(priority=7)
	public void userDelete() throws JsonProcessingException {

		//ResponseDTO responseDto = getResource(X_AUTH_TOKEN);	
		
		ApiResponseDTO userResponseDTO =  given()
		        .spec(spec)
		        .param("id","20")
		        
		.when()	
		   .header("X-Auth-Token", X_AUTH_TOKEN)
		.and().header("Content-Type", "application/json")	
		   .delete("/api/deleteuser/20")
		   .then()
		.extract().as(ApiResponseDTO.class);

		if(userResponseDTO.getStatusCode()==200){
		assertEquals(200,userResponseDTO.getStatusCode());
		assertEquals("User Deleted Successfully", userResponseDTO.getMessage());
		assert true;
		}
		else{
		assert false;
		}
	}
	
	
	
	@Test(priority=8)
	public void updateUserProfile() throws JsonProcessingException {

		
		ObjectMapper objectMapper = new ObjectMapper();
		//ResponseDTO responseDto = getResource(X_AUTH_TOKEN);	
		
		UpdateUserPwdDTO updateuserpwdDTO = new UpdateUserPwdDTO();
		updateuserpwdDTO.setEmail("ttadmin@yopmail.com");
		updateuserpwdDTO.setOldPwd("We1come!@#");
		updateuserpwdDTO.setPassword("We1come!@#");
		
		String objJson = objectMapper.writeValueAsString(updateuserpwdDTO);
		System.out.println("JSON : "+objJson);

		ApiResponseDTO userResponseDTO =  given()
		        .spec(spec)
		        
		        .body(objJson)
		.when()	
		   .header("X-Auth-Token", X_AUTH_TOKEN)
		.and().header("Content-Type", "application/json")	
		   .post("/api/profile/resetpwd")
		   .then()
		.extract().as(ApiResponseDTO.class);
		System.out.println(userResponseDTO.toString());
		if(userResponseDTO.getStatusCode()==200){
		assertEquals(200,userResponseDTO.getStatusCode());
		assertEquals("Password updated successfully", userResponseDTO.getMessage());
		  

		assert true;
		}
		else{
		assert false;
		}
		
	}
	
	
}


