package com.nvidia.cosmos.controller;

import static com.jayway.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.specification.RequestSpecification;
import com.nvidia.cosmos.cloud.dtos.ApiResponseDTO;
import com.nvidia.cosmos.cloud.dtos.DockerRepositoryDTO;
import com.nvidia.cosmos.cloud.dtos.FetchDTO;

public class JobsControllerTest {
	  private static final String BASE_URI = "http://localhost:8888"; 
	  //private static final int BASE_PORT = 8080; 
	  private static final String X_AUTH_TOKEN = "$2a$12$Hdl35u.8CTVLaiHeiMVaXeoqE.cJnrzvO1iWUqXjqS4LUadcr9782";

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
	public void createRepository()throws JsonProcessingException{
		
		DockerRepositoryDTO createrepoDTO= new DockerRepositoryDTO();
		createrepoDTO.setDescription("kumari");
		createrepoDTO.setRepository("hariprasad");
		createrepoDTO.setNamespace("manish");
		
		ObjectMapper objectMapper= new ObjectMapper();
		String jsonObj=objectMapper.writeValueAsString(createrepoDTO);
		
		ApiResponseDTO responseDTO= given()
				.spec(spec)
				.body(jsonObj)
				.header("X-Auth_Token",X_AUTH_TOKEN)
			.and().header("Content-Type", "application/json")
			.when()
			.post("/api/repo/create")
			.then()
			.extract().as(ApiResponseDTO.class);
			
			if(responseDTO.getStatusCode()==200){
			assertEquals(200,responseDTO.getStatusCode());
			assertEquals("Entitlement key is validated", responseDTO.getMessage());
				 
			assert true;
			}
			else{
				assert false;
			}
	}

}
