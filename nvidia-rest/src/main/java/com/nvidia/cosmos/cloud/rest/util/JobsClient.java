package com.nvidia.cosmos.cloud.rest.util;

import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.APPLIANCE_UNREACHABLE;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.AUTHORIZATION;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.CHARACTER_COMMA;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.CHARACTER_HASH;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.CHARACTER_NEWLINE;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.CHARACTER_SINGLE_FRONT_SLASH;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.CONTENT_TYPE;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.CREDS;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.DEVICE;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.ID;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.LABELS;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.INTERNAL_ERROR_PLEASE_TRY_AGAIN_LATER;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.KEY;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.PRIVATE;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.REPOSITORY_ALREADY_EXISTS;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.REPOSITORY_NOT_SUCCESSFULLY_CREATION;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.REPOSITORY_NOT_SUCCESSFULLY_DELETED;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.REPOSITORY_SUCCESSFULLY_CREATION;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.REPOSITORY_SUCCESSFULLY_DELETED;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.SERIAL_NUMBER_IS_NOT_AUTHORIZED_TO_CONNECT;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.SERVICE_IS_UNREACHABLE;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.USER;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.UTF8;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.V1_REPOSITORY;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.V1_REPOSITORY_NAMESPACE;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.VALUE;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.VOLUME;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.VOLUMES_FROM;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.VOLUME_DRIVER;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.WEB_SOCKET_APPLIANCE_IS_DOWN;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.WebsocketRequestMethod.DELETE;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.WebsocketRequestMethod.GET;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.WebsocketRequestMethod.POST;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.WebsocketRequestMethod.PUT;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.WebsocketServerRequest.COMMAND;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.WebsocketServerRequest.FORWARD_DATA;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.WebsocketServerRequest.OPERATION;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.WebsocketServerRequest.PAYLOAD;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.WebsocketServerRequest.SERIAL_NUMBER;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.WebsocketServerRequest.URI;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.WebsocketServerRequest.USERCREDS;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.core.MediaType;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import com.nvidia.cosmos.cloud.dtos.DockerImageRequestDTO;
import com.nvidia.cosmos.cloud.dtos.DockerImagesDTO;
import com.nvidia.cosmos.cloud.dtos.DockerRepoResponseDTO;
import com.nvidia.cosmos.cloud.dtos.DockerRepositoryDTO;
import com.nvidia.cosmos.cloud.dtos.dockerdetail.DockerImageDetailDTO;
import com.nvidia.cosmos.cloud.dtos.job.Docker;
import com.nvidia.cosmos.cloud.dtos.job.Parameters;
import com.nvidia.cosmos.cloud.dtos.job.PortDefinitions;
import com.nvidia.cosmos.cloud.dtos.job.choronos.ChronosGraphCsv;
import com.nvidia.cosmos.cloud.exceptions.BaseException;
import com.nvidia.cosmos.cloud.rest.common.util.DeleteAdapter;
import com.nvidia.cosmos.cloud.rest.common.util.GetAdapter;
import com.nvidia.cosmos.cloud.rest.common.util.IRestAdapter;
import com.nvidia.cosmos.cloud.rest.common.util.PostAdapter;
import com.nvidia.cosmos.cloud.rest.util.jobparser.JobRetVal;
import com.nvidia.cosmos.cloud.rest.util.jobparser.WSServerJobResponse;
import com.nvidia.cosmos.cloud.services.job.model.Job;
/**
 * 
 *
 */
public class JobsClient {

	private static Logger logger = LoggerFactory.getLogger(JobsClient.class);

	/**
	 * 
	 */
	public JobsClient() {

	}

	/**
	 * @throws MalformedURLException
	 * 
	 */
	public static DockerImagesDTO fetchDockerImages(String url, String authorization, String customername) throws MalformedURLException {
		logger.debug("In fetching docker images from the url {} and with authorization key.", url);
		DockerImagesDTO dockerImagesDTO = new DockerImagesDTO();
		if (url != null) {
			IRestAdapter nvidiaResponse = GetAdapter.builder().setEndPoint(url).setHeader(AUTHORIZATION, authorization)
					.setMethodName(V1_REPOSITORY_NAMESPACE + customername).build();
			DockerImagesDTO nvidiaDockerImages = nvidiaResponse.execute(DockerImagesDTO.class);
			if (nvidiaDockerImages != null) {
				logger.debug("In fetching nvidia docker images{}", nvidiaDockerImages.toString());
				dockerImagesDTO.addAllRepositories(nvidiaDockerImages.getRepositories());
			}
			return dockerImagesDTO;

		}
		return null;

	}

	/**
	 * 
	 * @param url
	 * @param authorization
	 * @param dockerImageName
	 * @param dockerNamespace
	 * @return
	 * @throws MalformedURLException
	 */
	public static DockerImageDetailDTO fetchDockerImageDetail(String url, String authorization, String dockerImageName, String dockerNamespace)
			throws MalformedURLException {
		IRestAdapter dockerImageDetailResponse = GetAdapter.builder().setEndPoint(url).setHeader(AUTHORIZATION, authorization)
				.setMethodName(V1_REPOSITORY + CHARACTER_SINGLE_FRONT_SLASH + dockerNamespace + CHARACTER_SINGLE_FRONT_SLASH + dockerImageName)
				.build();
		return dockerImageDetailResponse.execute(DockerImageDetailDTO.class);
	}

	/**
	 * @throws MalformedURLException
	 * @throws JsonProcessingException
	 * 
	 */
	public static DockerRepoResponseDTO createDockerRepository(String url, String authorization, DockerRepositoryDTO dockerRepoDto)
			throws MalformedURLException, JsonProcessingException {
		DockerRepoResponseDTO dockerResponseDTO = new DockerRepoResponseDTO();
		logger.debug("In creating docker images using the url {} and with authorization key.", url);
		if (url != null) {
			// Remove hard coded value private
			dockerRepoDto.setVisibility(PRIVATE);
			dockerRepoDto.setDescription(dockerRepoDto.getDescription() != null ? dockerRepoDto.getDescription() : "");
			ObjectMapper objectMapper = new ObjectMapper();
			String dockerRequest = objectMapper.writeValueAsString(dockerRepoDto);
			IRestAdapter dockerRepsponce = PostAdapter.builder().setEndPoint(url).setHeader(AUTHORIZATION, authorization).setMethodName(V1_REPOSITORY)
					.setRequestObject(dockerRequest).build();
			Response jsonResponse = dockerRepsponce.executeForAllStatuscode();
			if (jsonResponse != null) {
				logger.debug("Docker Repository Created successfully{}", jsonResponse.prettyPrint());
				if (jsonResponse.getStatusCode() == 201) {
					dockerResponseDTO.setMessage(dockerRepoDto.getRepository() + REPOSITORY_SUCCESSFULLY_CREATION);
					dockerResponseDTO.setStatusCode(200);
				} else if (jsonResponse.getStatusCode() == 400) {
					dockerResponseDTO.setMessage(dockerRepoDto.getRepository() + REPOSITORY_ALREADY_EXISTS);
					dockerResponseDTO.setStatusCode(jsonResponse.getStatusCode());
				} else if (jsonResponse.getStatusCode() == 401 || jsonResponse.getStatusCode() == 403 || jsonResponse.getStatusCode() == 404) {
					dockerResponseDTO.setMessage(dockerRepoDto.getRepository() + REPOSITORY_NOT_SUCCESSFULLY_CREATION);
					dockerResponseDTO.setStatusCode(jsonResponse.getStatusCode());
				} else {
					dockerResponseDTO.setMessage(dockerRepoDto.getRepository() + REPOSITORY_NOT_SUCCESSFULLY_CREATION);
					dockerResponseDTO.setStatusCode(jsonResponse.getStatusCode());
					logger.debug(dockerResponseDTO.getMessage());
				}
			} else {
				dockerResponseDTO.setMessage(INTERNAL_ERROR_PLEASE_TRY_AGAIN_LATER);
				dockerResponseDTO.setStatusCode(503);
			}
		}
		return dockerResponseDTO;

	}

	/**
	 * @throws MalformedURLException
	 * 
	 */
	public static DockerRepoResponseDTO deleteDockerRepository(String url, String authorization, DockerImageRequestDTO requestDcokerRepo)
			throws MalformedURLException {
		DockerRepoResponseDTO dockerResponseDTO = new DockerRepoResponseDTO();
		logger.debug("In deleting docker images from the url {} and with authorization key.", url);
		if (url != null && requestDcokerRepo != null) {
			IRestAdapter dockerRepsponce = DeleteAdapter.builder().setEndPoint(url).setHeader(AUTHORIZATION, authorization)
					.setMethodName(V1_REPOSITORY + CHARACTER_SINGLE_FRONT_SLASH + requestDcokerRepo.getNamespace() + CHARACTER_SINGLE_FRONT_SLASH
							+ requestDcokerRepo.getName())
					.build();
			Response jsonResponse = dockerRepsponce.executeForAllStatuscode();
			if (jsonResponse != null) {
				logger.debug("Docker Repository deleted successfully{}", jsonResponse.prettyPrint());
				if (jsonResponse.getStatusCode() == 204) {
					dockerResponseDTO.setMessage(requestDcokerRepo.getName() + REPOSITORY_SUCCESSFULLY_DELETED);
					dockerResponseDTO.setStatusCode(200);
				} else if (jsonResponse.getStatusCode() == 400 || jsonResponse.getStatusCode() == 401 || jsonResponse.getStatusCode() == 403
						|| jsonResponse.getStatusCode() == 404) {
					dockerResponseDTO.setMessage(requestDcokerRepo.getName() + REPOSITORY_NOT_SUCCESSFULLY_DELETED);
					dockerResponseDTO.setStatusCode(jsonResponse.getStatusCode());
				}
			} else {
				dockerResponseDTO.setMessage(INTERNAL_ERROR_PLEASE_TRY_AGAIN_LATER);
				dockerResponseDTO.setStatusCode(503);
			}

		}
		return dockerResponseDTO;

	}

	public static WSServerJobResponse getResubmissionJobApi(String pWebsocketUrl, String jobRequest, String serialNumber, String uri,String pUserName,String pQAuthToken)
			throws BaseException {
		WSServerJobResponse websocketResponse = null;
		/*
		 * IRestAdapter response = PostAdapter.builder()
		 * .setEndPoint("http://52.24.185.151:9090/scheduler")
		 * .setMethodName("/iso8601") .setRequestObject(jobRequest) .build();
		 * JsonPath jsonPath = response.execute();
		 */

		// String uri = "/v2/apps";

		logger.debug("JOBS Endpoint is {} ", uri);

		logger.debug("JOBS Request Json is {} ", jobRequest);

		websocketResponse = callWebSocketServer(pWebsocketUrl, uri, POST.toString(), serialNumber, jobRequest,pUserName,pQAuthToken);
		return websocketResponse;
	}

	public static WSServerJobResponse getChronosGraphCsvApi(String pWebsocketUrl, String serialNumber, String uri,String pUserName,String pQAuthToken) throws BaseException {
		WSServerJobResponse websocketResponse = null;
		logger.debug("JOBS Endpoint is {} ", uri);

		websocketResponse = callWebSocketServer(pWebsocketUrl, uri, GET.toString(), serialNumber, "",pUserName,pQAuthToken);

		return websocketResponse;
	}

	public static WSServerJobResponse putChronosJobApi(String pWebsocketUrl, String serialNumber, String uri, String pJobId,String pUserName,String pQAuthToken) throws BaseException {
		WSServerJobResponse websocketResponse = null;
		if (pJobId != null && !pJobId.isEmpty()) {
			uri = uri + pJobId;
		}
		logger.debug("JOBS Endpoint is {} ", uri);

		websocketResponse = callWebSocketServer(pWebsocketUrl, uri, PUT.toString(), serialNumber, "",pUserName,pQAuthToken);

		return websocketResponse;
	}

	public static ChronosGraphCsv getChronosGraphCsvByJobId(String graphCsvStr, String pJobId) {

		ChronosGraphCsv chronosGraphCsv = null;
		if (graphCsvStr != null && !graphCsvStr.isEmpty()) {
			String[] graphCsvStrArray = graphCsvStr.split(CHARACTER_NEWLINE);
			if (graphCsvStrArray != null && graphCsvStrArray.length != 0) {
				for (String chronosResp : graphCsvStrArray) {
					if (chronosResp != null && !chronosResp.isEmpty()) {
						String[] chronosRespArray = chronosResp.split(CHARACTER_COMMA);
						if (chronosRespArray != null && chronosRespArray.length == 4) {
							String jobId = chronosRespArray[1];
							if (pJobId != null && pJobId.equalsIgnoreCase(jobId) == true) {
								chronosGraphCsv = new ChronosGraphCsv(chronosRespArray[0], chronosRespArray[1], chronosRespArray[2],
										chronosRespArray[3]);
								return chronosGraphCsv;
							}
						}
					}
				}
			}
		}
		if (chronosGraphCsv == null) {
			chronosGraphCsv = new ChronosGraphCsv();
			chronosGraphCsv.setIsDeleted(true);
		}
		return chronosGraphCsv;
	}

	public static List<ChronosGraphCsv> getChronosGraphCsvList(String graphCsvStr) {
		List<ChronosGraphCsv> chronosGraphCsvList = new ArrayList<ChronosGraphCsv>();
		if (graphCsvStr != null && !graphCsvStr.isEmpty()) {
			String[] graphCsvStrArray = graphCsvStr.split(CHARACTER_NEWLINE);
			if (graphCsvStrArray != null && graphCsvStrArray.length != 0) {
				for (String chronosResp : graphCsvStrArray) {
					if (chronosResp != null && !chronosResp.isEmpty()) {
						String[] chronosRespArray = chronosResp.split(CHARACTER_COMMA);
						if (chronosRespArray != null && chronosRespArray.length == 4) {
							ChronosGraphCsv chronosGraphCsv = new ChronosGraphCsv(chronosRespArray[0], chronosRespArray[1], chronosRespArray[2],
									chronosRespArray[3]);
							chronosGraphCsvList.add(chronosGraphCsv);
						}
					}
				}
			}
		}
		return chronosGraphCsvList;
	}

	public static WSServerJobResponse deleteJob(String webSocketUrl, String serialNumber, String jobId, String uri,String pUserName,String pQAuthToken) throws BaseException {
		WSServerJobResponse websocketResponse = null;
		// String fpath = "/v2/apps";

		if (jobId != null && !jobId.isEmpty()) {
			uri = uri + jobId;
		}

		// String uri = jobUrl + fpath;
		logger.debug("JOBS Endpoint is {} ", uri);
		// No need to pass jobrequest json
		websocketResponse = callWebSocketServer(webSocketUrl, uri, DELETE.toString(), serialNumber, "",pUserName,pQAuthToken);

		// IRestAdapter response =
		// DeleteAdapter.builder().setEndPoint(url).setMethodName(fpath).build();
		// JsonPath jsonPath = response.execute();

		return websocketResponse;
	}

	public static WSServerJobResponse listJobsString(String pWebSocketUrl, String pSerialNumber, String uri, String jobId,String pUserName,String pQAuthToken) throws BaseException {
		WSServerJobResponse websocketResponse = null;
		// String fpath = "/v2/apps";

		if (jobId != null && !jobId.isEmpty()) {
			uri = uri + jobId;
		}

		logger.debug("JOBS listJobsString is {} ", uri);
		websocketResponse = callWebSocketServer(pWebSocketUrl, uri, GET.toString(), pSerialNumber, "",pUserName,pQAuthToken);

		// IRestAdapter response =
		// GetAdapter.builder().setEndPoint(url).setMethodName(fpath).build();
		// JsonPath jsonPath = response.execute();
		return websocketResponse;
	}

	public static WSServerJobResponse getAllSlaveTask(String webSocketUrl, String serialNumber, String uri,String pUserName,String pQAuthToken) throws BaseException {
		WSServerJobResponse websocketResponse = null;
		logger.debug("JOBS Endpoint is {} ", uri);

		// No need to pass jobrequest json
		websocketResponse = callWebSocketServer(webSocketUrl, uri, GET.toString(), serialNumber, "",pUserName,pQAuthToken);

		// IRestAdapter response =
		// DeleteAdapter.builder().setEndPoint(url).setMethodName(fpath).build();
		// JsonPath jsonPath = response.execute();

		return websocketResponse;
	}

	/*
	 * public static String getContainerNameFromTheJobResponse(String
	 * websocketServerUrl, String uri, String serialNumber, String jobApiType)
	 * throws Exception { logger.info(
	 * "JOBS getContainerNameFromTheJobResponse is {} ", websocketServerUrl +
	 * uri); String containersName = null; try { WSServerJobResponse
	 * websocketResponse = callWebSocketServer(websocketServerUrl, uri,
	 * GET.toString(), serialNumber, ""); if (websocketResponse != null) {
	 * JobRetVal jobRetVal = convertWebsocketServerResponse(websocketResponse,
	 * jobApiType); if (jobRetVal != null) { ObjectMapper objectMapper = new
	 * ObjectMapper(); List<ChronosJobDetails> chronosJobDetailsList =
	 * objectMapper.readValue(jobRetVal.getData(),
	 * objectMapper.getTypeFactory().constructCollectionType(List.class,
	 * ChronosJobDetails.class)); if (chronosJobDetailsList != null) { for
	 * (ChronosJobDetails chronosJobDetails : chronosJobDetailsList) { if
	 * (chronosJobDetails.getAdditionalProperties().containsKey(NAMES)) {
	 * List<String> containers = (List<String>)
	 * chronosJobDetails.getAdditionalProperties().get(NAMES); if (containers !=
	 * null && !containers.isEmpty()) { containersName =
	 * containers.get(0).substring(1, containers.get(0).length()); break; } } }
	 * } } } } catch (Exception e) { throw e; } return containersName; }
	 */

	public static String getContainerNameFromTheJobResponse(String websocketServerUrl, String uri, String serialNumber, String jobApiType,String pUserName,String pQAuthToken, String uniqueId)
			throws Exception {
		logger.debug("JOBS getContainerNameFromTheJobResponse is {} ", websocketServerUrl + uri);
		String id = null;
		try {
			WSServerJobResponse websocketResponse = callWebSocketServer(websocketServerUrl, uri, GET.toString(), serialNumber, "",pUserName,pQAuthToken);
			if (websocketResponse != null) {
				JobRetVal jobRetVal = convertWebsocketServerResponse(websocketResponse, jobApiType);

				if (jobRetVal != null) {
					String jsonData = jobRetVal.getData();
					JsonParser parser = new JsonParser();
					JsonArray rowsArry = (JsonArray) parser.parse(jsonData);
					for (int rowsIndex = 0; rowsIndex < rowsArry.size(); rowsIndex++) {
						JsonObject container = rowsArry.get(rowsIndex).getAsJsonObject();
						JsonObject lable= container.getAsJsonObject(LABELS);
						Set<Map.Entry<String, JsonElement>> entries = lable.entrySet();//will return members of your object
						for (Map.Entry<String, JsonElement> entry: entries) {
							if(entry.getKey().equals(uniqueId)){
								return container.get(ID).getAsString();
							}
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("Error while getContainerNameFromTheJobResponse {}", e.getMessage());
			throw e;
		}
		return id;
	}

	public static JobRetVal convertWebsocketServerResponse(WSServerJobResponse websocketResponse, String jobApiType) throws BaseException {
		JobRetVal jobRetVal = null;
		try {
			if (websocketResponse != null && websocketResponse.getWaiting() == null && websocketResponse.getRetval() != null) {
				ObjectMapper objectMapper = new ObjectMapper();
				jobRetVal = objectMapper.readValue(websocketResponse.getRetval(), JobRetVal.class);
			} else if (websocketResponse != null && websocketResponse.getStatus() != null
					&& (websocketResponse.getStatus().startsWith(WEB_SOCKET_APPLIANCE_IS_DOWN))) 
			 {
				jobRetVal = new JobRetVal();
				jobRetVal.setMessage(APPLIANCE_UNREACHABLE);
				jobRetVal.setStatus(HttpStatus.SERVICE_UNAVAILABLE.toString());
				return jobRetVal;
			} else if (websocketResponse != null && websocketResponse.getStatus() != null
					&& websocketResponse.getStatus().equalsIgnoreCase(SERIAL_NUMBER_IS_NOT_AUTHORIZED_TO_CONNECT)) {
				jobRetVal = new JobRetVal();
				jobRetVal.setMessage(websocketResponse.getStatus());
				jobRetVal.setStatus(HttpStatus.SERVICE_UNAVAILABLE.toString());
				return jobRetVal;
			}
			if (jobRetVal != null && jobRetVal.getStatus() != null && jobRetVal.getStatus().equals(HttpStatus.BAD_GATEWAY.toString())) {
				jobRetVal.setJobApiType(jobApiType);
				jobRetVal.setMessage(jobApiType + SERVICE_IS_UNREACHABLE);
			} else if (jobRetVal != null && jobRetVal.getStatus() != null && jobRetVal.getStatus().equals(HttpStatus.BAD_REQUEST.toString())) {
				jobRetVal.setMessage("Some thing went wrong,verify input parameters");
			} else if (jobRetVal != null && jobRetVal.getStatus() != null && jobRetVal.getStatus().equals(HttpStatus.NOT_FOUND.toString())) {
				jobRetVal.setMessage(jobRetVal.getData());
			}
		} catch (ParseException e) {
			logger.error("Unable to Parse Websocket json Response", e.getMessage());
		} catch (IOException e) {
			logger.error("Unable to Read Websocket json Response", e.getMessage());
		}
		return jobRetVal;
	}
	
	
	public static boolean getWebSocketStatus(String pWebSocketUrl, String pSerialNumber,String pUserName,String pQAuthToken){
		
		WSServerJobResponse websocketResponse = null;
		CloseableHttpClient httpClient = null;
		
		JSONObject websocketJsonRequest = new JSONObject();
		websocketJsonRequest.put(COMMAND.toString(), FORWARD_DATA.toString());
		JSONObject userCredJsonRequest = new JSONObject();
		userCredJsonRequest.put(USER, pUserName);
		userCredJsonRequest.put(CREDS, pQAuthToken);
		websocketJsonRequest.put(USERCREDS.toString(), userCredJsonRequest.toString());
		websocketJsonRequest.put(SERIAL_NUMBER.toString(), new String(pSerialNumber));
		websocketJsonRequest.put(PAYLOAD.toString(), "CONTROL_CMD:USER_CREDS");
		websocketJsonRequest.put(OPERATION.toString(), "GET");
		websocketJsonRequest.put(URI.toString(), "/api");
		JsonPath jsonPath = new JsonPath(websocketJsonRequest.toString());
		logger.debug("Sending the Request to WebSocket server Json is {}", jsonPath.prettyPrint());

	try{	
		
		HttpPost request = new HttpPost(pWebSocketUrl);

		StringEntity params = new StringEntity(websocketJsonRequest.toString(), UTF8);
		request.addHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON);
		request.setEntity(params);

		httpClient = HttpClientBuilder.create().build();
		CloseableHttpResponse httpResponse = httpClient.execute(request);
		HttpEntity entity = httpResponse.getEntity();

		String responseString = EntityUtils.toString(entity, UTF8);

		if (responseString != null && !responseString.isEmpty()) {
			jsonPath = new JsonPath(responseString);
			logger.debug("Received Response from WebSocket Server is {}", jsonPath.prettyPrint());
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			websocketResponse = objectMapper.readValue(responseString, WSServerJobResponse.class);
			if(websocketResponse.getRetval()!=null && websocketResponse.getRetval().equalsIgnoreCase("success")){
				return true;
			}
				
		}
	} catch (ParseException e) {
		logger.error("Unable to Parse Websocket json Response", e.getMessage());
	} catch (UnsupportedEncodingException e) {
		websocketResponse = new WSServerJobResponse();
		websocketResponse.setStatus(WEB_SOCKET_APPLIANCE_IS_DOWN);
		logger.error("Unable to connect Websocket server,Please contact administrator! :" + pWebSocketUrl, e.getMessage());
	} catch (ClientProtocolException e) {
		websocketResponse = new WSServerJobResponse();
		websocketResponse.setStatus(WEB_SOCKET_APPLIANCE_IS_DOWN);
		logger.error("Unable to connect Websocket server,Please contact administrator! :" + pWebSocketUrl, e.getMessage());
	} catch (IOException e) {
		websocketResponse = new WSServerJobResponse();
		websocketResponse.setStatus(WEB_SOCKET_APPLIANCE_IS_DOWN);
		logger.error("Unable to connect Websocket server,Please contact administrator! :" + pWebSocketUrl, e.getMessage());
	} catch (Exception e) {
		websocketResponse = new WSServerJobResponse();
		websocketResponse.setStatus(WEB_SOCKET_APPLIANCE_IS_DOWN);
		logger.error("Unable to connect Websocket server,Please contact administrator!", e.getMessage());
	} finally {
		try {
			if (httpClient != null) {
				httpClient.close();
			}
		} catch (IOException e) {
			logger.error("Unable to Read Websocket json Response", e);
		}
	}
	logger.debug("JobsClient.callWebSocketServer method Exit");
		
		
	
	
		return false;
	}

	public static WSServerJobResponse callWebSocketServer(String pWebSocketUrl, String pUri, String pOperation, String pSerialNumber,
			String pJobRequest,String pUserName,String pQAuthToken) throws BaseException {
		logger.debug("JobsClient.callWebSocketServer method Enter");

		WSServerJobResponse websocketResponse = null;
		CloseableHttpClient httpClient = null;

		try {
			
			
			JSONObject userCredJsonRequest = new JSONObject();
			userCredJsonRequest.put(USER, pUserName);
			userCredJsonRequest.put(CREDS, pQAuthToken);
			
			JSONObject websocketJobJsonRequest = new JSONObject();
			websocketJobJsonRequest.put(COMMAND.toString(), FORWARD_DATA.toString());
			websocketJobJsonRequest.put(SERIAL_NUMBER.toString(), new String(pSerialNumber));
			websocketJobJsonRequest.put(URI.toString(), new String(pUri));
			websocketJobJsonRequest.put(OPERATION.toString(), new String(pOperation));
			websocketJobJsonRequest.put(PAYLOAD.toString(), pJobRequest);
			websocketJobJsonRequest.put(USERCREDS.toString(), userCredJsonRequest.toString());

			logger.debug("Websocket server operation {}", pOperation);

			JsonPath jsonPath = new JsonPath(websocketJobJsonRequest.toString());
			logger.debug("Sending the Request to WebSocket server Json is {}", jsonPath.prettyPrint());

			HttpPost request = new HttpPost(pWebSocketUrl);

			StringEntity params = new StringEntity(websocketJobJsonRequest.toString(), UTF8);
			request.addHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON);
			request.setEntity(params);

			httpClient = HttpClientBuilder.create().build();
			CloseableHttpResponse httpResponse = httpClient.execute(request);
			HttpEntity entity = httpResponse.getEntity();

			String responseString = EntityUtils.toString(entity, UTF8);

			if (responseString != null && !responseString.isEmpty()) {
				jsonPath = new JsonPath(responseString);
				logger.debug("Received Response from WebSocket Server is {}", jsonPath.prettyPrint());
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				websocketResponse = objectMapper.readValue(responseString, WSServerJobResponse.class);
				logger.debug("Websocket response Object is {}",websocketResponse.toString());
			}
		} catch (ParseException e) {
			logger.error("Unable to Parse Websocket json Response", e.getMessage());
		} catch (UnsupportedEncodingException e) {
			websocketResponse = new WSServerJobResponse();
			websocketResponse.setStatus(WEB_SOCKET_APPLIANCE_IS_DOWN);
			logger.error("Unable to connect Websocket server,Please contact administrator! :" + pWebSocketUrl, e.getMessage());
		} catch (ClientProtocolException e) {
			websocketResponse = new WSServerJobResponse();
			websocketResponse.setStatus(WEB_SOCKET_APPLIANCE_IS_DOWN);
			logger.error("Unable to connect Websocket server,Please contact administrator! :" + pWebSocketUrl, e.getMessage());
		} catch (IOException e) {
			websocketResponse = new WSServerJobResponse();
			websocketResponse.setStatus(WEB_SOCKET_APPLIANCE_IS_DOWN);
			logger.error("Unable to connect Websocket server,Please contact administrator! :" + pWebSocketUrl, e.getMessage());
		} catch (Exception e) {
			websocketResponse = new WSServerJobResponse();
			websocketResponse.setStatus(WEB_SOCKET_APPLIANCE_IS_DOWN);
			logger.error("Unable to connect Websocket server,Please contact administrator!", e.getMessage());
		} finally {
			try {
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				logger.error("Unable to Read Websocket json Response", e);
			}
		}
		logger.debug("JobsClient.callWebSocketServer method Exit");
		return websocketResponse;
	}

	public static void createParameterToString(final Docker docker, Job job) {
		job.setForcePullImage(docker.getForcePullImage());
		if (docker.getParameters() != null && !docker.getParameters().isEmpty()) {
			List<String> nfsString = new ArrayList<String>();
			List<String> localValumeString = new ArrayList<String>();
			List<String> argumentString = new ArrayList<String>();
			for (Parameters parameter : docker.getParameters()) {
				if (!parameter.getKey().equals(VOLUME_DRIVER) && !parameter.getKey().equals(VOLUMES_FROM) && !parameter.getKey().equals(DEVICE)
						&& !parameter.getKey().equals(USER)) {
					if (parameter.getKey() != null && parameter.getKey().equals(VOLUME)) {
						if (parameter.getValue().startsWith(CHARACTER_SINGLE_FRONT_SLASH)) {
							localValumeString.add(parameter.getValue());
						} else {
							nfsString.add(parameter.getValue());
						}
					} else if (parameter.getKey() != null && parameter.getValue() != null && !parameter.getKey().equals(VOLUME)) {
						argumentString.add(KEY + parameter.getKey() + CHARACTER_HASH + VALUE + parameter.getValue());
					}
				}
			}
			job.setSchedulerParameters(String.join(CHARACTER_COMMA, argumentString));
			job.setNfsVolume(String.join(CHARACTER_HASH, nfsString));
			job.setLocalVolume(String.join(CHARACTER_HASH, localValumeString));
		}

		if (docker.getPortMappings() != null && !docker.getPortMappings().isEmpty())

		{
			List<String> containerPortString = new ArrayList<String>();
			List<String> hostPortString = new ArrayList<String>();
			for (PortDefinitions definitions : docker.getPortMappings()) {
				if (definitions.getContainerPort() != 0) {
					containerPortString.add(String.valueOf(definitions.getContainerPort()));
				}
				if (definitions.getHostPort() != 0) {
					hostPortString.add(String.valueOf(definitions.getHostPort()));
				}
			}

			job.setContainerPorts(String.join(CHARACTER_COMMA, containerPortString));
			job.setHostPorts(String.join(CHARACTER_COMMA, hostPortString));
		}
	}

	/*
	 * public static void main(String[] args) throws JsonParseException,
	 * JsonMappingException, IOException { // String quayhost =
	 * "http://52.38.206.109/api"; // int slashslash = quayhost.indexOf("//") +
	 * 2; // String domain = quayhost.substring(slashslash,
	 * quayhost.indexOf('/', // slashslash)); // System.out.println(domain); //
	 * String res = JobsClient.createCustomer("http://52.38.206.109/api", //
	 * "Bearer 6T64MALPpdQ1059JgkI2U9mlfzeDwoovFByI6Ltl", "tenant1", //
	 * "tenant1@yopmail.com"); // //Process the response and retrieves the
	 * client_id clientCU_id // System.out.println("Response "+res); }
	 */
	
	
	public static void main(String[] args) {
		
		//websocketResponse websocketResponse = objectMapper.readValue(responseString, WSServerJobResponse.class);
	}
}
