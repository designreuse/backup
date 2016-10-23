package com.nvidia.cosmos.controller;

import static com.jayway.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

import org.osgi.service.useradmin.Authorization;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.specification.RequestSpecification;
import com.nvidia.cosmos.cloud.dtos.ResponseDTO;

public class JETControllerTest {
	  private static final String BASE_URI = "http://localhost:8888"; 
	  //private static final int BASE_PORT = 8080; 
	  private static final String X_AUTH_TOKEN = "sPscK7vcFX8upTBDUvWIu4p6vrmHRcpkDxAWCbEN";

	  private static final String KEY = "nvidia-DEV1_DGX-1.high.ram{}";
	  
	  private static final String Config ="";
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
	
	/*@Test(priority=1)
	public void getJWTResponse(){
		ResponseDTO responseDTO =  given()
		         .spec(spec)
		          	  
		    .auth().form("nvadmin", "We1come!@#", config)
			.and().header("Authorization", "Basic bnZhZG1pbjpXZTFjb21lIUAj")
			.when() 
		    .get("/api/quay/verify")
		    .then()
			.extract().as(ResponseDTO.class);
		
		if(responseDTO.getStatusCode()==200){
		assertEquals(200,responseDTO.getStatusCode());
		assertEquals("User has been verified", responseDTO.getMessage());
	   
	    assertNotNull(responseDTO.getData());

		assert true;
		}
		else{
			assert false;
		}
	}*/

}
