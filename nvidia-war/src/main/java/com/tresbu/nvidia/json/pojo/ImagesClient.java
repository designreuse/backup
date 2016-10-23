package com.tresbu.nvidia.json.pojo;

import com.jayway.restassured.RestAssured;
import com.tresbu.nvidia.common.GetAdapter;
import com.tresbu.nvidia.common.IRestAdapter;

/**
 * @author pbatta
 *
 */
public class ImagesClient {
	/**
	 * 
	 */
	public ImagesClient(){
		
	}
	/**
	 * 
	 */
	public static DockerImages fetchIDockermages() {
		//https://csms0-dev.nvidia.com/api/v1/repository?namespace=nvidia
		RestAssured.port = 443;
		RestAssured.basePath = "/api";
		RestAssured.baseURI = "https://csms0-dev.nvidia.com"; 
		
		System.out.println("********fetchIDockermages********");
		IRestAdapter response = GetAdapter.builder()
				.setHeader("Authorization", "Bearer 4mpgPakjo13aA25kfxrFQTyKLlAVc6HKOxco8xAD")
				.setMethodName("/v1/repository?namespace=nvidia").build();
		return response.execute(DockerImages.class);
//		Systems.out.println(dockerImages);
//		try {
//			DockerImages dockerImages = mapper.readValue(jsonPath.toString(), DockerImages.class);
//			
//		} catch (JsonParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (JsonMappingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	public static void main(String[] args) {
		System.out.println(fetchIDockermages());
//		String jsonString = "{\"id\":\"" + "SD"
//				+ "\",\"cpus\":4,\"mem\":128,\"instances\":1,\"constraints\":[[\"hostname\",\"UNIQUE\",\"\"]],\"container\":{\"type\":\"DOCKER\",\"docker\":{\"image\":\""
//				+ "SDSD"
//				+ "\",\"network\":\"BRIDGE\",\"portMappings\":[{\"containerPort\":"+80+",\"hostPort\":0,\"servicePort\":0,\"protocol\":\"tcp\"}]}},\"forcePullImage\":true,\"uris\":[\"https://compute.nvidia.com/dockercfg.tar.gz\"]}";
//		
//		System.out.println("------------------------------");
//		System.out.println(jsonString);
//		System.out.println("------------------------------");
//		
////		String portJosnString="\",\"portMappings\":[{\"containerPort\":34448,\"hostPort\":0,\"servicePort\":0,\"protocol\":\"tcp\"}]}},\"";
////		String portContainer=pContainerPort!=null && pContainerPort==0?portJosnString:"\"}},\"";
////		
////		String jsonString = "{\"id\":\"" + pId
////				+ "\",\"cpus\":4,\"mem\":128,\"instances\":1,\"constraints\":[[\"hostname\",\"UNIQUE\",\"\"]],\"container\":{\"type\":\"DOCKER\",\"docker\":{\"image\":\""
////				+ pImage
////				+ "\",\"network\":\"BRIDGE"+portContainer+"forcePullImage\":true,\"uris\":[\"https://compute.nvidia.com/dockercfg.tar.gz\"]}";
//		
//		// String jsonString = "{\"id\":\"" + pId+
//		// "\",\"env\":{},\"instances\":1,\"cpus\":2,\"mem\":128,\"disk\":0,\"executor\":\"\",\"constraints\":[[\"hostname\",\"UNIQUE\",\"\"]],\"uris\":[],\"fetch\":[],\"storeUrls\":[],\"ports\":[10000],\"requirePorts\":false,\"backoffSeconds\":1,\"backoffFactor\":1.15,\"maxLaunchDelaySeconds\":3600,\"container\":{\"type\":\"DOCKER\",\"volumes\":[],\"docker\":{\"image\":\""+pImage+"\",\"network\":\"BRIDGE\",\"portMappings\":[{\"containerPort\":34448,\"hostPort\":8668,\"servicePort\":10000,\"protocol\":\"tcp\"}],\"privileged\":false,\"parameters\":[],\"forcePullImage\":false}},\"healthChecks\":[],\"dependencies\":[],\"upgradeStrategy\":{\"minimumHealthCapacity\":1,\"maximumOverCapacity\":1},\"labels\":{},\"version\":\"2016-04-06T04:43:44.753Z\",\"versionInfo\":{\"lastScalingAt\":\"2016-04-06T04:43:44.753Z\",\"lastConfigChangeAt\":\"2016-04-06T04:43:44.753Z\"},\"tasksStaged\":0,\"tasksRunning\":1,\"tasksHealthy\":0,\"tasksUnhealthy\":0,\"deployments\":[],\"tasks\":[]}";
//		
//		List<Fetch> fetchs = new LinkedList<Fetch>();
//		List<List<String>> constraints = new LinkedList<List<String>>();
//		List<String> constraint = new LinkedList<String>();
//		constraint.add("hostname");
//		constraint.add("UNIQUE");
//		constraint.add("");
//		constraints.add(constraint);
//		
//		List<PortDefinitions> definitaions = new LinkedList<PortDefinitions>();
//		List<PortDefinitions> portMappings = new LinkedList<PortDefinitions>();
//		
//		List<String> uris = new LinkedList<String>();
//		uris.add("https://compute.nvidia.com/dockercfg.tar.gz");
//		
//		Container container = new Container();
//		container.setType("DOCKER");
//		Docker docker = new Docker();
//		docker.setNetwork("BRIDGE");
//		docker.setImage("SDSD");
//		List<Parameters> ps = new LinkedList<Parameters>();
//		{
//			Parameters p = new Parameters();
//			p.setKey("k");
//			p.setKey("v");
//			ps.add(p);
//		}
//		{
//			Parameters p = new Parameters();
//			p.setKey("k1");
//			p.setKey("v1");
//			ps.add(p);
//		}
//		docker.setParameters(ps.toArray(new Parameters[0]));
//		container.setDocker(docker);
//		
//		PortDefinitions portMapping=new PortDefinitions();
//		portMapping.setContainerPort(80);
//		portMapping.setHostPort(0);
//		portMappings.add(portMapping);
//		
//		Request jobRequest = new Request("SD", fetchs, definitaions, 4, null, uris.toArray(new String[0]), container, 0, 128, 1, constraints, portMappings);
//		ObjectMapper mapper = new ObjectMapper();
//		System.out.println("----------------New-------------");
//		try {
//			System.out.println(mapper.writeValueAsString(jobRequest));
//		} catch (JsonProcessingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("---------------New--------------");
	}
	
}
