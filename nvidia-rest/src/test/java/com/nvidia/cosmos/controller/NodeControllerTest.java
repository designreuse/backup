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
import com.nvidia.cosmos.cloud.dtos.ResponseDTO;
import com.nvidia.cosmos.cloud.dtos.node.NodeDTO;
import com.nvidia.cosmos.cloud.dtos.node.NodeResponseDTO;
import com.nvidia.cosmos.cloud.dtos.node.NodeStatusDTO;

public class NodeControllerTest {
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
	public void nodecreate() throws JsonProcessingException{
		
		ObjectMapper objectMapper= new ObjectMapper();
		
		NodeDTO dto=new NodeDTO();
		dto.setKey("pulling0");
		dto.setSerialNumber("pulling1");
		
		NodeStatusDTO nsd=new NodeStatusDTO();
		nsd.setCloudManaged("factory_default");
		nsd.setIsLeader("yes");
		nsd.setClusterId("cluster2");
		nsd.setSerialid("node1");
		nsd.setSubnet("22.33.44.000");
		nsd.setNodeName("pencilPusher2");
		nsd.setFirstBoot("no");
		nsd.setEulaAccepted("no");
		nsd.setBiosVersion("54321");
		nsd.setSerialNumber("pulling1");
		nsd.setCloudStatus("connected");
		nsd.setTags("tiger,lion");
		nsd.setGpuConfiguration("1");
		nsd.setHealthy("1");
		nsd.setIpmi("XXXXXX");
		nsd.setNodeId("node1");
		nsd.setMode("dhcp");
		nsd.setKey("pulling0");
		nsd.setIpAddress("172.31.26.133");
		nsd.setSwVersion("2.0.0");
		nsd.setCloudGroup("na");
		nsd.setClusterGroup("master");
		nsd.setModelName("db2");
		nsd.setMemory("4096");
		nsd.setTotalCpuCores("4");
		nsd.setFwVersion("54321");
		nsd.setUserAdded("yes");
		nsd.setGateway("172.18.240.1");
		nsd.setDiskSpace("50");
		nsd.setCloudUrl("head-dev.tresbu.com");
		nsd.setRepoUrl("http://dgx1-bootstrap-repo-devel1.nvidia.com/");
		nsd.setUpgradeOnBoot("no");
			
		dto.setNodeStatus(nsd);
		
		String jsonObj=objectMapper.writeValueAsString(dto);
		
		
		NodeResponseDTO responseDTO= given()
					.spec(spec)
					.body(jsonObj)
				
				.and().header("Content-Type", "application/json")
				.when()
				.post("/api/node/create")
				.then()
				.extract().as(NodeResponseDTO.class);
							
				if(responseDTO.getStatusCode()==200){
					
				assertEquals(200,responseDTO.getStatusCode());
				assertEquals("Cluster and Node successfully updated",responseDTO.getMessage());
				assert true;
				}
				else{
					assert false;
				}
		}
	
	@Test(priority=2)
	public void getNodeByName(){
		
			//ResponseDTO responseDto = getResource(X_AUTH_TOKEN);		
			ResponseDTO responseDTO =  given()
			         .spec(spec)
			         .when()  	  
			    .header("X-Auth-Token", X_AUTH_TOKEN)
				.and().header("Content-Type", "application/json")
				
			    .get("/api/node/cluster2/pencilPusher2")
			    .then()
				.extract().as(ResponseDTO.class);
			
			if(responseDTO.getStatusCode()==200){
			assertEquals(200,responseDTO.getStatusCode());
			assertEquals(null, responseDTO.getMessage());
		   
		    assertNotNull(responseDTO.getData());

			assert true;
			}
			else{
				assert false;
			}

		}
	
	@Test(priority=3)
	public void getNodeByCluster(){
		
			//ResponseDTO responseDto = getResource(X_AUTH_TOKEN);		
			ResponseDTO responseDTO =  given()
			         .spec(spec)
			         .when()  	  
			    .header("X-Auth-Token", X_AUTH_TOKEN)
				.and().header("Content-Type", "application/json")
				
			    .get("/api/node/cluster2")
			    .then()
				.extract().as(ResponseDTO.class);
			
			if(responseDTO.getStatusCode()==200){
			assertEquals(200,responseDTO.getStatusCode());
			assertEquals("All Nodes found by cluster", responseDTO.getMessage());
		   
		    assertNotNull(responseDTO.getData());

			assert true;
			}
			else{
				assert false;
			}

		}
	
	@Test(priority=4)
	public void getNodeById(){
		
			//ResponseDTO responseDto = getResource(X_AUTH_TOKEN);		
			ResponseDTO responseDTO =  given()
			         .spec(spec)
			         .when()  	  
			    .header("X-Auth-Token", X_AUTH_TOKEN)
				.and().header("Content-Type", "application/json")
				
			    .get("/api/nodedata/pencilPusher2/7")
			    .then()
				.extract().as(ResponseDTO.class);
			
			if(responseDTO.getStatusCode()==200){
			assertEquals(200,responseDTO.getStatusCode());
			assertEquals("Node details found given by node", responseDTO.getMessage());
		   
		    assertNotNull(responseDTO.getData());

			assert true;
			}
			else{
				assert false;
			}

		}
	
	@Test(priority=5)
	public void upgradeOnBootInNode(){
		
			//ResponseDTO responseDto = getResource(X_AUTH_TOKEN);		
			ResponseDTO responseDTO =  given()
			         .spec(spec)
			          	  
			    .header("X-Auth-Token", X_AUTH_TOKEN)
				.and().header("Content-Type", "application/json")
				.when()
			    .get("/api/upgradeONBoot/STG1_VM_b25f7b185d61/1/yes")
			    .then()
				.extract().as(ResponseDTO.class);
			System.out.println(responseDTO.getData());
			if(responseDTO.getStatusCode()==200){
			assertEquals(200,responseDTO.getStatusCode());
			assertEquals("Cluster upgraded successfully", responseDTO.getMessage());
		  
			assert true;
			}
			else{
				assert false;
			}

		}
	
}
