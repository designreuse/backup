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

public class DashBoardControllerTest {
	  private static final String BASE_URI = "http://localhost:8888"; 
	  //private static final int BASE_PORT = 8080; 
	  private static final String X_ADMIN_TOKEN = "$2a$12$pwiVZqtQ/R/abS.XqTmI5eRWzhxEHgALZOZ62.VPJvgnXi/pGd1x2";

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
	@Test
	public void dashboard(){
		
		ResponseDTO responseDTO= given()
				.spec(spec)
				.header("X-Auth-Token",X_ADMIN_TOKEN)
				.and().header("content-Type","application/json")
				.when()
				.get("/api/dashboard")
				.then()
				.extract().as(ResponseDTO.class);
		if(responseDTO.getStatusCode()==200){
			assertEquals(200,responseDTO.getStatusCode());
			assertEquals("Dashboard Details",responseDTO.getMessage());
		//	assertEquals(null,responseDTO.getQuayUrl());
			assertNotNull(responseDTO.getData());
			
			assert true;
		}
		else{
			assert false;
		}
	}
}


