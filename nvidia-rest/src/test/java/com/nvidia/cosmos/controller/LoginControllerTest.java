package com.nvidia.cosmos.controller;

import static com.jayway.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

import org.junit.runner.Request;
import org.springframework.web.context.request.RequestAttributes;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.specification.RequestSpecification;
import com.nvidia.cosmos.cloud.dtos.ApiResponseDTO;
import com.nvidia.cosmos.cloud.dtos.LoginResponseDTO;
import com.nvidia.cosmos.cloud.dtos.RegisterDTO;
import com.nvidia.cosmos.cloud.dtos.ResetDTO;
import com.nvidia.cosmos.cloud.dtos.ResetPasswordDTO;
import com.nvidia.cosmos.cloud.dtos.ResponseDTO;
import com.nvidia.cosmos.cloud.dtos.VerifyResponseDTO;
import com.nvidia.cosmos.cloud.services.userauth.model.UserAuth;

public class LoginControllerTest {
	  private static final String BASE_URI = "http://localhost:8888"; 
	  //private static final int BASE_PORT = 8080; 
	  private static final String X_AUTH_TOKEN = "$2a$12$tGy1qvhpuJkaANXHCvKdR.E3V54c7dDWzYWsa.lMmG8nYSYkXF.hm";

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
	public void reset() throws JsonProcessingException{
		
		ObjectMapper objectMapper= new ObjectMapper();
		
		ResetDTO reset= new ResetDTO("lackytresbu@yopmail.com");
				
		String jsonObj=objectMapper.writeValueAsString(reset);
		
		ApiResponseDTO responseDTO =  given()
		         .spec(spec)
		         .body(jsonObj)
		    .header("X-Auth-Token", X_AUTH_TOKEN)
			.and().header("Content-Type", "application/json")
			.when()
		    .post("/api/reset")
		    .then()
			.extract().as(ApiResponseDTO.class);
		System.out.println(responseDTO.toString());
		if(responseDTO.getStatusCode()==200){
		assertEquals(200,responseDTO.getStatusCode());
		assertEquals("Your password has been reset. Use the link in the email you received to complete this process", responseDTO.getMessage());
		
	   

		assert true;
		}
		else{
			assert false;
		}
	}
	
	@Test(priority=2)
	public void resetverify() throws JsonProcessingException{
		
		
		VerifyResponseDTO responseDTO =  given()
		         .spec(spec)
		        
		    .header("X-Auth-Token", X_AUTH_TOKEN)
			.and().header("Content-Type", "application/json")
			.when()
		    .get("/api/reset/verify/Fh7hqlG0WaxML8Vs7331NL5lEqRPPLwR38xxXwtoxDEBCG3DXn21BJYpLf1fwKWZ")
		    .then()
			.extract().as(VerifyResponseDTO.class);
		if(responseDTO.isVerified()==true){
		}else{
			
		}
		
		
		}

	
	@Test(priority=3)
	public void resetuser() throws JsonProcessingException{
		
		ObjectMapper objectMapper= new ObjectMapper();
		ResetPasswordDTO resetpwdDTO=new ResetPasswordDTO();
		resetpwdDTO.setEmail("yaswanth@yopmail.com");
		resetpwdDTO.setName("kala");
		resetpwdDTO.setgId("5");
		resetpwdDTO.setuId("4");
		resetpwdDTO.setUserName("kasi");
		resetpwdDTO.setUserId("41");
		resetpwdDTO.setPasswordTxt("We1come!@#");
		resetpwdDTO.setPassword("We1come!@#");
		resetpwdDTO.setqAuthToken("BgAGYXDx3g8u57hGQ6OwaPoTzi5R6wfb6D7wBoka");
		
		String json=objectMapper.writeValueAsString(resetpwdDTO);
		
		LoginResponseDTO responseDTO =  given()
		         .spec(spec)
		        .body(json)
		    .header("X-Auth-Token", X_AUTH_TOKEN)
			.and().header("Content-Type", "application/json")
			.when()
		    .post("/api/reset/41/2q4ZrHu5hARGrTdQ7MkYtudyTj0jYZjSs0AVvuOAKi~RwvV35WJmkTnELZgN2AZh")
		    .then()
			.extract().as(LoginResponseDTO.class);
		
		if(responseDTO.getStatusCode()==202){
			
			assertEquals(202,responseDTO.getStatusCode());
			assertEquals("Password set successfully",responseDTO.getMessage());
			assert true;
			}
			else{
				assert false;
			}
		}
	
	/*@Test(priority=4)
	public void logout(Object request) throws JsonProcessingException{
		
		ObjectMapper objectMapper= new ObjectMapper();
	
		
		UserAuth userAuth=new UserAuth();
		userAuth.setAuthToken("$2a$12$XcuUpB7RRbXkgEcjMC5VoOPtrCKlORW7L7rPik9la7Flk3eBr3XoC");
		userAuth.setEmail("yaswanth@yopmail.com");
		
		String json=objectMapper.writeValueAsString(request);
		
		ResponseDTO responseDTO=given()
				.spec(spec)
				
				.header("X-Auth-Token",X_AUTH_TOKEN)
				.and().header("Content-Type", "application/json")
				.when()
				.delete("/api/logout")
				.then()
				.extract().as(ResponseDTO.class);
		
	}*/
	
}
