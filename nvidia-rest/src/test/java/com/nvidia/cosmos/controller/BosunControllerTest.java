package com.nvidia.cosmos.controller;

import static org.testng.AssertJUnit.assertNotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.testng.AssertJUnit.assertEquals;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.specification.RequestSpecification;
import com.nvidia.cosmos.cloud.dtos.ResponseDTO;
import com.nvidia.cosmos.config.Response;
import static com.jayway.restassured.RestAssured.given;

public class BosunControllerTest {

	  private static final String BASE_URI = "http://localhost:8888"; 
	  //private static final int BASE_PORT = 8080; 
	  private static final String X_AUTH_TOKEN = "sPscK7vcFX8upTBDUvWIu4p6vrmHRcpkDxAWCbEN";
	  private static Logger logger = LoggerFactory.getLogger(BosunControllerTest.class);
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

	}/*

	//getting alerts by passing proper X-Auth_Token 
	@Test (priority=1)
	public void getAlertsTest1(){

		//ResponseDTO responseDto = getResource(X_AUTH_TOKEN);		
		JobResponseDTO responseDTO =  given()
		         .spec(spec)
		    .header("X-Auth-Token", X_AUTH_TOKEN)
			.and().header("Content-Type", "application/json")
			.when()
		    .get("/api/bosun/alerts")
		    .then()
			.extract().as(ResponseDTO.class);
		
		if(responseDTO.getStatusCode()==200){
		assertEquals(200,responseDTO.getStatusCode());
		assertEquals(null, responseDTO.getMessage());
	    assertEquals(null, responseDTO.getQuayUrl());
	    assertNotNull(responseDTO.getData());

		assert true;
		}
		else{
			assert false;
		}
}

	//getting alerts by passing empty X-Auth_Token 	
	@Test (priority=2)
	public void getAlertsTest2(){

		Response response = given()
		         .spec(spec)
		    .header("X-Auth-Token", "")
			.and().header("Content-Type", "application/json")
			.when()
		    .get("/api/bosun/alerts").
		    then()
	        .contentType(ContentType.JSON)
	        .extract().as(Response.class);  //.response();

		if(response.getStatus()==500){
		assertEquals(500,response.getStatus());
		assertEquals("Token is not found in the request ", 
				response.getMessage());
		
		assert true;
		}
		else{
			assert false;
			
		}
  }

	//getting acknowledge status by passing proper key and X-Auth_Token 
	@Test (priority=3)
	public void getAcknowledgeStatusByKeyId1(){

		//ResponseDTO responseDto = getResource(X_AUTH_TOKEN);		
		ResponseDTO responseDTO =  given()
		         .spec(spec)
		         .param("pAlertKey", KEY)
		         .header("X-Auth-Token", X_AUTH_TOKEN)
			.and().header("Content-Type", "application/json")
			.when()
		    .post("/api/bosun/ack/key")
		    .then()
			.extract().as(ResponseDTO.class);

		if(responseDTO.getStatusCode()==200){
		assertEquals(200,responseDTO.getStatusCode());
		assertEquals("No alert found for given alert key", responseDTO.getMessage());
	   // assertEquals(null, responseDTO.getQuayUrl());
	    assertEquals(null,responseDTO.getData());

		assert true;
		}
		else{
			assert false;
		}
}

	//getting acknowledge status by passing invalid key and X-Auth_Token 	
	@Test (priority=4)
	public void getAcknowledgeStatusByKeyId2(){

		//ResponseDTO responseDto = getResource(X_AUTH_TOKEN);		
		
		ResponseDTO responseDTO =  given()
		         .spec(spec)
		         .param("pAlertKey", "Nokey")
		    .header("X-Auth-Token", X_AUTH_TOKEN)
			.and().header("Content-Type", "application/json")
			.when()
		    .post("/api/bosun/ack/key")
		    .then()
			.extract().as(ResponseDTO.class);

		if(responseDTO.getStatusCode()==200){
		assertEquals(200,responseDTO.getStatusCode());
		assertEquals("No alert found for given alert key", responseDTO.getMessage());
	    //assertEquals(null, responseDTO.getQuayUrl());
	    assertEquals(null,responseDTO.getData());

		assert true;
		}
		else{
			assert false;
		}
}	
	
	//getting acknowledge status by passing proper key and invalid X-Auth_Token
	@Test (priority=5)
	public void getAcknowledgeStatusByKeyId3(){

		//ResponseDTO responseDto = getResource(X_AUTH_TOKEN);		
		Response response =  given()
		         .spec(spec)
		         .param("pAlertKey", KEY)
		    .header("X-Auth-Token", "WrongToken")
			.and().header("Content-Type", "application/json")
			.when()
		    .post("/api/bosun/ack/key")
		    .then()
			.extract().as(Response.class);

		if(response.getStatus()==500){
		assertEquals(500,response.getStatus());
		assertEquals("Token is not found in the request ", 
				response.getMessage());

		assert true;
		}
		else{
			assert false;
		}
}

	//getting acknowledge status by passing proper key, action and X-Auth_Token	
	@Test (priority=6)
	public void getAlertStatusByKeyId1(){

		//ResponseDTO responseDto = getResource(X_AUTH_TOKEN);		
		ResponseDTO responseDTO =  given()
		         .spec(spec)
		         .param("key_id", "nvidia-DEV1_DGX-1.high.ram{}")
		         .param("action_id", "ack")
		         .header("X-Auth-Token", X_AUTH_TOKEN)
			.and().header("Content-Type", "application/json")
			.when()
		    .post("/api/bosun/alert/notify")
		    .then()
			.extract().as(ResponseDTO.class);

		logger.info("Response in getAlertStatusByKeyId1 : "+responseDTO.toString());

		if(responseDTO.getStatusCode()==200){
		assertEquals(200,responseDTO.getStatusCode());
		assertEquals("No alert found for given alert key", responseDTO.getMessage());
	   // assertEquals(null, responseDTO.getQuayUrl());
	    assertEquals(null,responseDTO.getData());

		assert true;
		}
		else{
			assert false;
		}
}

	//getting acknowledge status by passing proper key, action and invalid X-Auth_Token	
	@Test (priority=7)
	public void getAlertStatusByKeyId2(){

		//ResponseDTO responseDto = getResource(X_AUTH_TOKEN);		
		ResponseDTO responseDTO =  given()
		         .spec(spec)
		         .param("key_id", "nvidia-DEV1_DGX-1.high.ram{}")
		         .param("action_id", "forceClose")
		         .header("X-Auth-Token", X_AUTH_TOKEN)
			.and().header("Content-Type", "application/json")
			.when()
		    .post("/api/bosun/alert/notify")
		    .then()
			.extract().as(ResponseDTO.class);

		logger.info("Response in getAlertStatusByKeyId2 : "+responseDTO.toString());
		if(responseDTO.getStatusCode()==200){
		assertEquals(200,responseDTO.getStatusCode());
		assertEquals("Close success :nvidia-DEV1_DGX-1.high.ram{}", responseDTO.getMessage());
	   // assertEquals(null, responseDTO.getQuayUrl());
	    assertEquals(null,responseDTO.getData());

		assert true;
		}
		else{
			assert false;
		}
}

	//getting acknowledge status by passing proper key and invalid X-Auth_Token	and invalid action	
	@Test (priority=8)
	public void getAlertStatusByKeyId3(){

		//ResponseDTO responseDto = getResource(X_AUTH_TOKEN);		
		Response response =  given()
		         .spec(spec)
		         .param("key_id", "nvidia-DEV1_DGX-1.high.ram{}")
		         .param("action_id", "a")
		         .header("X-Auth-Token", X_AUTH_TOKEN)
			.and().header("Content-Type", "application/json")
			.when()
		    .post("/api/bosun/alert/notify")
		    .then()
			.extract().as(Response.class);

		logger.info("Response in getAlertStatusByKeyId3 : "+response.toString());
		if(response.getStatus()==500){
		assertEquals(500,response.getStatus());
		assertEquals("map[nvidia-DEV1_DGX-1.high.ram{}:unknown action type: none]\n", 
				response.getMessage());

		assert true;
		}
		else{
			assert false;
		}
}

	//nice reusable method
	private String createResource(String path, Object bodyPayload) {
	    return given()
	            .spec(spec)
	            .body(bodyPayload)
	            .when()
	            .post(path)
	            .then()
	            .statusCode(200)
	            .extract().header("location");
	}

	//nice reusable method
	private <T> ResponseDTO getResource(String token) { //String locationHeader, Class<T> responseClass
	    return given()
	                .spec(spec)
	                .when()
	                .get(locationHeader)
	                .then()
	                .statusCode(200)
	                .extract().as(responseClass);

		return given()
         .spec(spec)
    .header("X-Auth-Token", token)
	.and().header("Content-Type", "application/json")
	.when()
    .get("/api/bosun/alerts")
    .then()
	.statusCode(200)
	.extract().as(ResponseDTO.class);
}
*/
}