package com.nvidia.cosmos.cloud.rest.controller;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nvidia.cosmos.cloud.dtos.AlertDTO;
import com.nvidia.cosmos.cloud.dtos.ResponseDTO;
import com.nvidia.cosmos.cloud.dtos.alert.AlertVerifyDTO;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@RestController
public class BosunController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(BosunController.class);
	private static final String FAILED_HTTP_ERROR_CODE = "Failed : HTTP error code : ";
	private static final String CONTENT_TYPE = "content-type";
	private static final String BOSUN_HOST = "bosun.host";

	
	 @Path("/api/bosun/alerts")
	    @GET
		@ApiOperation( value = "Get bosun alerts", notes = "List all bosun alerts", response = AlertDTO.class,  responseContainer = "List")
	    @ApiResponses( {
		    @ApiResponse( code = 200, message = "All the bosun alerts have been listed" )
		} )
	@RequestMapping(value = "/api/bosun/alerts", method = RequestMethod.GET)
	public @ResponseBody ResponseDTO getAlerts() {
		 ResponseDTO response = new ResponseDTO();
		 /*
		logger.debug("Started BosunController.getAlerts() ");
		ResponseDTO response = new ResponseDTO();
		AlertDTO alert = null;
		Client client = null;
		try {

			String bosunHost = environment.getProperty(BOSUN_HOST);
			// String port = (String) properties.get(BOSUN_PORT);
			String bosunEndPoint = bosunHost + "/api/alerts?filter=";
			logger.info("\n Bosun Endpoint is: " + bosunEndPoint);
			client = Client.create();

			WebResource webResource = client.resource(bosunEndPoint);

			ClientResponse clientResponse = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);

			if (clientResponse.getStatus() != 200) {
				throw new RuntimeException(FAILED_HTTP_ERROR_CODE + clientResponse.getStatus());
			}

			String output = clientResponse.getEntity(String.class);
			ObjectMapper objectMapper = new ObjectMapper();
			alert = objectMapper.readValue(output, AlertDTO.class);
			logger.debug("Output from server .....\n");
			logger.debug(alert.toString());
			response.setStatusCode(200);
			response.setData(alert);
		} catch (Exception e) {
			logger.error(e.getMessage());

		} finally {
			if (client != null) {
				client.destroy();
			}
		}
		logger.debug("Ended BosunController.getAlerts()");*/
		return response;
	}

	 
	 @Path("/api/bosun/ack/key")
	    @POST
		@ApiOperation( value = "Get bosun acknowledgement status by alert key", notes = "Get bosun acknowledgement status by alert key", response = String.class)
	    @ApiResponses( {
		    @ApiResponse( code = 200, message = "Bosun acknowledge status by key has been fetched" )
		} )
	@RequestMapping(value = "/api/bosun/ack/key", method = RequestMethod.POST)
	public @ResponseBody ResponseDTO getAcknowledgeStatusByKeyId(
			@ApiParam(name="Alert key", value="The alert key by which bosun acknowledgement status has to be fetched", required=true) @RequestBody String pAlertKey) {/*
		BufferedReader bufferedReader = null;
		URLConnection connection = null;
		String message = null;
		String statusCode = HttpStatus.OK.toString();
		try {

			String bosunHost = environment.getProperty(BOSUN_HOST);
			String bosunEndPoint = bosunHost + "/api/status?ak=" + pAlertKey;

			logger.info("\n Bosun Endpoint is: " + bosunEndPoint);

			URL url = new URL(bosunEndPoint);
			connection = url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestProperty(CONTENT_TYPE, MediaType.APPLICATION_JSON);
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);

			bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line = bufferedReader.readLine();

			while ((bufferedReader.readLine()) != null) {
				line = bufferedReader.readLine();
			}

			JSONObject jsonObj = new JSONObject(line.toString());
			JSONObject alertJsonObject = jsonObj.getJSONObject(pAlertKey.trim());
			message = alertJsonObject.getString("Body");
			logger.debug(message);

			logger.debug("\nBosun REST Service Invoked Successfully..");
		} catch (Exception e) {
			logger.error(e.getMessage());
			message = "No alert found for given alert key";
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}

		}*/
		ResponseDTO response = new ResponseDTO("Not Implemented", Integer.parseInt("200"));
		return response;
	}

	 @Path("/api/bosun/alert/notify")
	    @POST
		@ApiOperation( value = "Get alert status by alert name ", notes = "Get alert status by alert name ", response = String.class)
	    @ApiResponses( {
		    @ApiResponse( code = 200, message = "Alert status by id has been fetched by alert name" )
		} ) 
	@RequestMapping(value = "/api/bosun/alert/notify", method = RequestMethod.POST)
	public @ResponseBody ResponseDTO getAlertStatusByKeyId(
			@ApiParam(name="Alert object", value="The alert object whose status needs to be fetched", required=true) @RequestBody AlertVerifyDTO pAlertVerify) {/*
		logger.debug("BosunController.getAlertStatusByKeyId()");
		String message = "";
		String statusCode = HttpStatus.OK.toString();
		InputStream inputStream = null;
		try {
			ClientConfig clientConfig = new DefaultClientConfig();
			Client client = Client.create(clientConfig);

			String bosunHost = environment.getProperty(BOSUN_HOST);
			String bosunEndPoint = bosunHost + "/api/action";
			logger.info("\n Bosun Endpoint is: " + bosunEndPoint);

			WebResource webResource = client.resource(bosunEndPoint);

			logger.info("ActionId:"+pAlertVerify.getActionId());
			logger.info("ActionKey:"+pAlertVerify.getKeyId());
			JSONObject inputJsonObj = new JSONObject();
			inputJsonObj.put("Type", pAlertVerify.getActionId());
			inputJsonObj.put("User", "null");
			inputJsonObj.put("Message", "hello");

			String[] strArray = new String[] { pAlertVerify.getKeyId() };
			inputJsonObj.put("Keys", strArray);
			inputJsonObj.put("Notify", true);

			inputStream = new ByteArrayInputStream(inputJsonObj.toString().getBytes());

			// put switch, name,priority....
			ClientResponse clientResponse = webResource.accept(MediaType.APPLICATION_JSON_TYPE).post(ClientResponse.class, inputStream);

			if (clientResponse.getStatus() == 500) {
				message = clientResponse.getEntity(String.class);
				statusCode=String.valueOf(clientResponse.getStatus());
			}

			if (clientResponse.getStatus() == 200) {
				message = clientResponse.getEntity(String.class);
				if (pAlertVerify.getActionId().equals("forceClose") == true) {
					message = "Close success :" + pAlertVerify.getKeyId();
				} else {
					message = "Acknowledge success :" + pAlertVerify.getKeyId();
				}
				statusCode = String.valueOf(clientResponse.getStatus());
			}

//			if (pAlertVerify.getActionId() != null && pAlertVerify.getActionId().equals("ack") == true) {
//				message = "Acknowledge success :" + pAlertVerify.getKeyId();
//			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			message = "No alert found for given alert key";
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				logger.error(e.getMessage());

			}
		}*/
		ResponseDTO response = new ResponseDTO("Not Implemented", Integer.parseInt("200"));
		logger.debug("BosunController.getAlertStatusByKeyId()");
		return response;
	}
}
