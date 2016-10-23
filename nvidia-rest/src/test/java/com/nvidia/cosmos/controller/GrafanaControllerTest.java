package com.nvidia.cosmos.controller;

import static com.jayway.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.specification.RequestSpecification;
import com.nvidia.cosmos.cloud.dtos.ResponseDTO;

public class GrafanaControllerTest {
	  private static final String BASE_URI = "http://localhost:8888"; 
	  //private static final int BASE_PORT = 8080; 
	  private static final String X_AUTH_TOKEN = "$2a$12$1tNPYGdPJlNzu7seaBqzOuWvA40zazZ3bEMCL/III3KHA0SbZjtU6";

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
	public void clusterGrafanaDataByCustomer(){
		
		ResponseDTO responseDTO= given()
					.spec(spec)
					.param("customerId", "20")
					.param("clusterId", "7")
				.header("X-Auth-Token",X_AUTH_TOKEN)
				.and().header("Content-Type","application/json")
				.when()
				.get("/api/cluster/grafanainfo/20/7")
				.then()
				.extract().as(ResponseDTO.class);
		if(responseDTO.getStatusCode()==200){
			assertEquals(200,responseDTO.getStatusCode());
			assertEquals("Get list of graph frames for given customer ID and cluster ID",responseDTO.getMessage());
			assertEquals(null,responseDTO.getQuayUrl());
			assertNotNull(responseDTO.getData());
			
			assert true;
		}
		else{
			assert false;
		}
	
	}
	
	@Test(priority=2)
	public void nodeGrafanaDataByCustomer(){
		
		ResponseDTO responseDTO= given()
					.spec(spec)
					.param("customerId", "20")
					.param("clusterId", "7")
					.param("nodeName", "pencilPusher2")
				.header("X-Auth-Token",X_AUTH_TOKEN)
				.and().header("Content-Type","application/json")
				.when()
				.get("/api/node/grafanainfo/20/7/pencilPusher2")
				.then()
				.extract().as(ResponseDTO.class);
		if(responseDTO.getStatusCode()==200){
			assertEquals(200,responseDTO.getStatusCode());
			assertEquals("Get list of graph frames for given customer ID, cluster ID and node Name",responseDTO.getMessage());
			assertEquals(null,responseDTO.getQuayUrl());
			assertNotNull(responseDTO.getData());
			
			assert true;
		}
		else{
			assert false;
		}
	
	}
	
	@Test(priority=3)
	public void customerGrfanaDetails(){
		
		ResponseDTO responseDTO= given()
					.spec(spec)
					
				.header("X-Auth-Token",X_AUTH_TOKEN)
				.and().header("Content-Type","application/json")
				.when()
				.get("/api/cluster/grafanainfo")
				.then()
				.extract().as(ResponseDTO.class);
		if(responseDTO.getStatusCode()==200){
			assertEquals(200,responseDTO.getStatusCode());
			assertEquals("Get grafana login details for the loggedin customer",responseDTO.getMessage());
			assertEquals(null,responseDTO.getQuayUrl());
			assertNotNull(responseDTO.getData());
			
			assert true;
		}
		else{
			assert false;
		}
	
	}

}
