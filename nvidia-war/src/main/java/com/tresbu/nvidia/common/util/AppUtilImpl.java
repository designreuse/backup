package com.tresbu.nvidia.common.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.ws.rs.core.MediaType;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.tresbu.nvidia.common.AppConstant;
import com.tresbu.nvidia.common.data.LicenceKeyData;
import com.tresbu.nvidia.common.logging.NvidiaAppLogger;
import com.tresbu.nvidia.json.pojo.Alert;
import com.tresbu.nvidia.json.pojo.job.App;
import com.tresbu.nvidia.json.pojo.job.Job;
import com.tresbu.nvidia.json.pojo.job.JobDetails;
import com.tresbu.nvidia.json.pojo.njob.Request;

public class AppUtilImpl implements AppUtilIntrface {

	private static final Logger LOGGER = NvidiaAppLogger.getLogger(AppUtilImpl.class.getName());

	private static final String CONTENT_TYPE = "content-type";

	private static final String UTF_8 = "UTF-8";

	public static final String CHARACTER_DOT_REGEX = "\\.";

	public static final String CHARACTER_DOT = ".";

	private static final String HTTP_PREFIX = "http://";
	private static final String BODY = "Body";

	// private static final String BOSUN_PORT = "bosun.port";
	private static final String BOSUN_HOST = "bosun.host";

	// private static final String JOB_PORT = "job.port";
	private static final String JOB_HOST = "job.host";
	private static final String MARATHON_HOST = "marathon.host";
	private static final String DOCKER_HOST = "docker.host";

	// private static final String JOB_PORT_CONTAINER = "job.container.port";

	// private static final String JOB_CONTAINER_HOST = "job.container.host";
	// private static final String JOB_CONTAINER_URL = "job.container.url";

	private static final String SERVER_IP_ADDRESS = "server.ip.address";

	private static final String NVIDIA_HOST = "nvidia.host";

	private static final String CHARACTER_COLON = ":";

	private static final String GRAFANA_HOST = "grafana.host";

	@Autowired
	private AppConfigIntrface mAppConfigIntrface;

	private static final String FAILED_HTTP_ERROR_CODE = "Failed : HTTP error code : ";
	private static final String CHARACTER_HYPON = "-";

	public static LicenceKeyData getLicenceKeyInfo(String pLicenceKey) {
		LicenceKeyData licenceKeyData = new LicenceKeyData();
		// String licenceKey = "CST123-CLU123-NODE123";
		if (pLicenceKey != null && !pLicenceKey.isEmpty()) {
			String[] strArry = pLicenceKey.split(CHARACTER_HYPON);
			if (strArry != null && strArry.length != 0) {
				licenceKeyData.setCustomerId(strArry[0]);
				licenceKeyData.setClusterId(strArry[1]);
				licenceKeyData.setSerialId(strArry[2]);
			}
		}
		return licenceKeyData;

	}

	@Override
	public String[] getNodeAndClusterIdFromAlertName(String pAlertName) {
		String[] result = new String[3];
		String[] strArray = pAlertName.split(CHARACTER_DOT_REGEX);
		if (strArray != null && strArray.length >= 3) {
			LOGGER.debug("ClusterId:" + strArray[1] + ",SerialId:" + strArray[2]);
			result[0] = strArray[1];
			result[1] = strArray[2];

			StringBuilder stringBuilder = new StringBuilder();
			for (int size = 0; size < strArray.length; size++) {
				if (size >= 3) {
					stringBuilder.append(strArray[size]).append(CHARACTER_DOT);
				}
			}

			if (stringBuilder.length() > 0) {
				stringBuilder.deleteCharAt(stringBuilder.length() - 1);
			}
			result[2] = stringBuilder.toString();
		}

		return result;
	}

	@Override
	public String getNodeFromAlertName(String pNodeName, String pAlertName) {
		LOGGER.debug("getNodeFromAlertName() method enter...");
		String result = null;
		if (pAlertName != null) {
			String[] strArray = pAlertName.split(pNodeName + AppConstant.CHARACTER_DOT);
			if (strArray != null && strArray.length >= 2) {
				result = strArray[1];
			}

		}

		LOGGER.debug("getNodeFromAlertName() method exit...");
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tresbu.nvidia.common.util.AppUtilIntrface#getAlertJsonDataFromBosun()
	 */
	@Override
	public Alert getAlertJsonDataFromBosun(String pNodeName) throws Exception {
		LOGGER.debug("getAlertJsonDataFromBosun() method enter...");
		Alert alert = null;
		Client client = null;
		try {

			Properties properties = mAppConfigIntrface.getProperties();
			String bosunHost = (String) properties.get(BOSUN_HOST);
			// String port = (String) properties.get(BOSUN_PORT);
			String bosunEndPoint = bosunHost + "/api/alerts?filter=";
			LOGGER.info("\n Bosun Endpoint is: " + bosunEndPoint);
			client = Client.create();

			WebResource webResource = client.resource(bosunEndPoint);

			ClientResponse clientResponse = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);

			if (clientResponse.getStatus() != 200) {
				throw new RuntimeException(FAILED_HTTP_ERROR_CODE + clientResponse.getStatus());
			}

			String output = clientResponse.getEntity(String.class);
			ObjectMapper objectMapper = new ObjectMapper();
			alert = objectMapper.readValue(output, Alert.class);
			LOGGER.debug("Output from server .....\n");
			LOGGER.debug(alert);

		} catch (Exception e) {
			LOGGER.error(e);
			throw e;
		} finally {
			if (client != null) {
				client.destroy();
			}
		}
		LOGGER.debug("getAlertJsonDataFromBosun() method exit...");
		return alert;
	}

	public String getAlertActionResponse(String pAlertKey, String pAlertAction) throws Exception {
		String response = "";
		try {
			ClientConfig clientConfig = new DefaultClientConfig();
			Client client = Client.create(clientConfig);

			Properties properties = mAppConfigIntrface.getProperties();
			String bosunHost = (String) properties.get(BOSUN_HOST);
			// String port = (String) properties.get(BOSUN_PORT);

			String bosunEndPoint = bosunHost + "/api/action";
			LOGGER.info("\n Bosun Endpoint is: " + bosunEndPoint);

			WebResource webResource = client.resource(bosunEndPoint);

			JSONObject inputJsonObj = new JSONObject();
			inputJsonObj.put("Type", pAlertAction);
			inputJsonObj.put("User", "null");
			inputJsonObj.put("Message", "hello");

			String[] strArray = new String[] { pAlertKey };
			inputJsonObj.put("Keys", strArray);
			inputJsonObj.put("Notify", true);

			InputStream inputStream = new ByteArrayInputStream(inputJsonObj.toString().getBytes());

			// put switch, name,priority....
			ClientResponse clientResponse = webResource.accept(MediaType.APPLICATION_JSON_TYPE)
					.post(ClientResponse.class, inputStream);

			if (clientResponse.getStatus() == 500) {
				LOGGER.debug(clientResponse.getStatus());
				response = clientResponse.getEntity(String.class);
				LOGGER.debug(response);
			}

			if (clientResponse.getStatus() == 200) {
				LOGGER.debug(clientResponse.getStatus());
				response = clientResponse.getEntity(String.class);
				response = "Close success :" + pAlertKey;
				LOGGER.debug(response);
			}

			if (pAlertAction != null && pAlertAction.equals("ack") == true) {
				response = "Acknowledge success :" + pAlertKey;
			}
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return response;
	}

	@Override
	public String getAcknowledgeStatus(String pAlertKey) {
		String jsonAkStatus = null;
		BufferedReader bufferedReader = null;
		URLConnection connection = null;
		try {

			Properties properties = mAppConfigIntrface.getProperties();
			String bosunHost = (String) properties.get(BOSUN_HOST);
			// String port = (String) properties.get(BOSUN_PORT);
			String bosunEndPoint = bosunHost + "/api/status?ak=" + pAlertKey;

			LOGGER.info("\n Bosun Endpoint is: " + bosunEndPoint);

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
			LOGGER.debug(line);

			JSONObject jsonObj = new JSONObject(line.toString());

			JSONObject alertJsonObject = jsonObj.getJSONObject(pAlertKey.trim());
			jsonAkStatus = alertJsonObject.getString(BODY);
			LOGGER.debug(jsonAkStatus);

			LOGGER.debug("\nBosun REST Service Invoked Successfully..");

		} catch (Exception e) {
			LOGGER.error("\nError while calling Bosun REST Service");
			LOGGER.error(e);
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					LOGGER.error(e);
				}
			}

		}
		return jsonAkStatus;
	}

	@Override
	public List<JobDetails> getJobDetailsFromJsonData() throws Exception {
		LOGGER.debug("getJobDetailsFromJsonData() method enter...");
		List<JobDetails> jobDetailsList = new ArrayList<JobDetails>();
		String result = this.getJobIdFromJsonData("");
		if (result != null) {
			ObjectMapper objectMapper = new ObjectMapper();
			LOGGER.debug(result);
			Job job = objectMapper.readValue(result, Job.class);
			for (App app : job.getApps()) {
				if (app != null) {
					result = this.getJobIdFromJsonData(app.getId());
					objectMapper = new ObjectMapper();
					JobDetails jobDetails = objectMapper.readValue(result, JobDetails.class);
					LOGGER.debug(jobDetails.toString());
					jobDetailsList.add(jobDetails);
				}
			}
		}
		LOGGER.debug("getJobDetailsFromJsonData() method exit...");
		return jobDetailsList;
	}

	@Override
	public String getServerIpAddress() {
		String ipAddress = null;
		try {
			Properties properties = mAppConfigIntrface.getProperties();
			ipAddress = (String) properties.get(SERVER_IP_ADDRESS);
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return ipAddress;
	}

	@Override
	public String getNvidiaHost() {
		String nvidiaHost = null;
		try {
			Properties properties = mAppConfigIntrface.getProperties();
			nvidiaHost = (String) properties.get(NVIDIA_HOST);
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return nvidiaHost;
	}

	@Override
	public String getBosunHost() {
		String bosunHost = null;
		try {
			Properties properties = mAppConfigIntrface.getProperties();
			bosunHost = (String) properties.get(BOSUN_HOST);
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return bosunHost;
	}

	@Override
	public String getGrafanaHost() {
		String grafanaHost = null;
		try {
			Properties properties = mAppConfigIntrface.getProperties();
			grafanaHost = (String) properties.get(GRAFANA_HOST);
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return grafanaHost;
	}

	@Override
	public String getResubmissionJobApi(String pjobHost, Request jobRequest) {
		LOGGER.debug("");
		String responseString = null;
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		try {
			Properties properties = mAppConfigIntrface.getProperties();
			// String host = (String) properties.get(JOB_HOST);
			String port = (String) properties.get(MARATHON_HOST);
			// String jobContainerHost = (String)
			// properties.get(JOB_CONTAINER_HOST);
			String endPoint = "/v2/apps";
			//http://52.24.185.151:8080
			//String jobEndPoint = HTTP_PREFIX + pjobHost + CHARACTER_COLON + port + endPoint;
			String jobEndPoint = port + endPoint;
			// String jobEndPoint = jobContainerHost + endPoint;
			LOGGER.info("\n JOBS Endpoint is: " + jobEndPoint);

			HttpPost request = new HttpPost(jobEndPoint);

			// for digits containerPort:34448 and hostPort:0

			// String jsonString = "{\"id\":\"" + pId
			// +
			// "\",\"cpus\":4,\"mem\":128,\"instances\":1,\"constraints\":[[\"hostname\",\"UNIQUE\",\"\"]],\"container\":{\"type\":\"DOCKER\",\"docker\":{\"image\":\""
			// + pImage
			// +
			// "\",\"network\":\"BRIDGE\",\"portMappings\":[{\"containerPort\":"+pContainerPort+",\"hostPort\":0,\"servicePort\":0,\"protocol\":\"tcp\"}]}},\"forcePullImage\":true,\"uris\":[\"https://compute.nvidia.com/dockercfg.tar.gz\"]}";
			//
			// LOGGER.info("------------------------------");
			// LOGGER.info(jsonString);
			// LOGGER.info("------------------------------");

			// String
			// portJosnString="\",\"portMappings\":[{\"containerPort\":34448,\"hostPort\":0,\"servicePort\":0,\"protocol\":\"tcp\"}]}},\"";
			// String portContainer=pContainerPort!=null &&
			// pContainerPort==0?portJosnString:"\"}},\"";
			//
			// String jsonString = "{\"id\":\"" + pId
			// +
			// "\",\"cpus\":4,\"mem\":128,\"instances\":1,\"constraints\":[[\"hostname\",\"UNIQUE\",\"\"]],\"container\":{\"type\":\"DOCKER\",\"docker\":{\"image\":\""
			// + pImage
			// +
			// "\",\"network\":\"BRIDGE"+portContainer+"forcePullImage\":true,\"uris\":[\"https://compute.nvidia.com/dockercfg.tar.gz\"]}";

			// String jsonString = "{\"id\":\"" + pId+
			// "\",\"env\":{},\"instances\":1,\"cpus\":2,\"mem\":128,\"disk\":0,\"executor\":\"\",\"constraints\":[[\"hostname\",\"UNIQUE\",\"\"]],\"uris\":[],\"fetch\":[],\"storeUrls\":[],\"ports\":[10000],\"requirePorts\":false,\"backoffSeconds\":1,\"backoffFactor\":1.15,\"maxLaunchDelaySeconds\":3600,\"container\":{\"type\":\"DOCKER\",\"volumes\":[],\"docker\":{\"image\":\""+pImage+"\",\"network\":\"BRIDGE\",\"portMappings\":[{\"containerPort\":34448,\"hostPort\":8668,\"servicePort\":10000,\"protocol\":\"tcp\"}],\"privileged\":false,\"parameters\":[],\"forcePullImage\":false}},\"healthChecks\":[],\"dependencies\":[],\"upgradeStrategy\":{\"minimumHealthCapacity\":1,\"maximumOverCapacity\":1},\"labels\":{},\"version\":\"2016-04-06T04:43:44.753Z\",\"versionInfo\":{\"lastScalingAt\":\"2016-04-06T04:43:44.753Z\",\"lastConfigChangeAt\":\"2016-04-06T04:43:44.753Z\"},\"tasksStaged\":0,\"tasksRunning\":1,\"tasksHealthy\":0,\"tasksUnhealthy\":0,\"deployments\":[],\"tasks\":[]}";

			// List<Fetch> fetchs = new LinkedList<Fetch>();
			// List<List<String>> constraints = new LinkedList<List<String>>();
			//// List<String> constraints = new LinkedList<String>();
			//
			//
			// List<PortDefinitions> definitaions = new
			// LinkedList<PortDefinitions>();
			//
			// List<String> uris = new LinkedList<String>();
			// uris.add("https://compute.nvidia.com/dockercfg.tar.gz");
			//
			// Container container = new Container();
			// container.setType("DOCKER");
			// Docker docker = new Docker();
			// docker.setNetwork("BRIDGE");
			// docker.setImage(pImage);
			// container.setDocker(docker);
			//
			// Request jobRequest = new Request(pId, fetchs, definitaions, 4,
			// null, uris.toArray(new String[0]), container, 0, 128, 1,
			// constraints);
			ObjectMapper mapper = new ObjectMapper();
			String jsonString = mapper.writeValueAsString(jobRequest);
			LOGGER.info("----------------New-------------");
			LOGGER.info(jsonString);
			LOGGER.info("---------------New--------------");

			StringEntity params = new StringEntity(jsonString);
			request.addHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON);
			request.setEntity(params);
			HttpResponse response = httpClient.execute(request);
			HttpEntity entity = response.getEntity();

			responseString = EntityUtils.toString(entity, UTF_8);
			LOGGER.debug(responseString);
			try {
				JSONObject jsonObject = new JSONObject(responseString);
				LOGGER.debug(jsonObject.get("id"));
				responseString = "Job posted successfully";
			} catch (Exception e) {
				// Valid one
				LOGGER.debug(responseString);
			}

		} catch (Exception e) {
			LOGGER.error(e);
		} finally {
			try {
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				LOGGER.error(e);
			}

		}
		return responseString;
	}

	@Override
	public String deleteJobApi(String pId) {
		String result = null;
		Client client = null;
		try {
			Properties properties = mAppConfigIntrface.getProperties();

			//String host = (String) properties.get(JOB_HOST);
			String port = (String) properties.get(MARATHON_HOST);
			// String jobContainerHost = (String)
			// properties.get(JOB_CONTAINER_HOST);

			String endPoint = "/v2/apps/" + pId;
            //http://52.24.185.151:8080
			//String jobEndPoint = HTTP_PREFIX + host + CHARACTER_COLON + port + endPoint
			String jobEndPoint = port + endPoint;
			// String jobEndPoint = jobContainerHost + endPoint;
			LOGGER.info("\n JOBS Endpoint is: " + jobEndPoint);

			client = Client.create();

			WebResource webResource = client.resource(jobEndPoint);

			ClientResponse clientResponse = webResource.accept(MediaType.APPLICATION_JSON).delete(ClientResponse.class);

			if (clientResponse.getStatus() != 200) {
				result = "Job aleready deleted :" + pId;
				throw new RuntimeException(FAILED_HTTP_ERROR_CODE + clientResponse.getStatus());
			}

			result = clientResponse.getEntity(String.class);
			LOGGER.debug(result);
		} catch (Exception e) {
			LOGGER.error(e);
		} finally {
			if (client != null) {
				client.destroy();
			}
		}
		return result;
	}

	@Override
	public String getContainerStream(String pjobHost, String pContainerName) throws Exception {
		LOGGER.debug("getContainerStream() method entered.....");
		String response = null;
		// http://${jobTaskData.host}:2375/containers/${jobTaskData.containerName}/attach?stdout=true&stderr=true&stream=true&logs=false
		InputStream responseStream = null;
		InputStreamReader responseStreamReader = null;
		BufferedReader bufferedReader = null;
		HttpURLConnection httpURLConnection = null;
		try {
			Properties properties = mAppConfigIntrface.getProperties();
			String dockerhost = (String) properties.get(DOCKER_HOST);
			String endPoint = "/containers/" + pContainerName
					+ "/attach?stdin=true&stdout=true&stderr=true&stream=true&logs=false";
			//http://52.24.185.151:2375
			//String containerEndpoint = HTTP_PREFIX + pjobHost + CHARACTER_COLON + dockerport + endPoint;
			String containerEndpoint =dockerhost + endPoint;
			LOGGER.info("\n JOBS Containers Console Endpoint is: " + containerEndpoint);

			URL url = new URL(containerEndpoint);
			httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.connect();
			if (httpURLConnection.getResponseCode() != 200) {
				throw new Exception("REST call to " + url.toString() + "failed with HTTP status code: "
						+ httpURLConnection.getResponseCode());
			}
			responseStream = httpURLConnection.getInputStream();
			LOGGER.debug(responseStream);
			responseStreamReader = new InputStreamReader(responseStream);
			bufferedReader = new BufferedReader(responseStreamReader);
			response = bufferedReader.readLine();
			LOGGER.info(response);
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (responseStream != null) {
					responseStream.close();
					responseStream = null;
				}
				if (responseStreamReader != null) {
					responseStreamReader.close();
					responseStreamReader = null;
				}
				if (bufferedReader != null) {
					bufferedReader.close();
					bufferedReader = null;
				}
				if (httpURLConnection != null) {
					httpURLConnection.disconnect();
				}
			} catch (IOException e) {
				throw e;
			}

		}
		return response;
	}

	@Override
	public String getNameFromTheJobResponse(String pIpAddress, String puniqueName) {
		LOGGER.debug("getNameFromTheJobRespons() method entered.....");
		// http://<job host>:2375/containers/json?label=uuid
		String responseString = null;
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		try {
			Properties properties = mAppConfigIntrface.getProperties();
			// String host = (String) properties.get(JOB_HOST);
			// String port = (String) properties.get(JOB_PORT_CONTAINER);
			String dockerhost = (String) properties.get(DOCKER_HOST);

			// String jobContainerUrl = (String)
			// properties.get(JOB_CONTAINER_URL);
			String endPoint = "/containers/json?label=" + puniqueName;
			//http://52.24.185.151:2375
			//String jobEndPoint = HTTP_PREFIX + pIpAddress + CHARACTER_COLON + dockerPort + endPoint;
			String jobEndPoint = dockerhost + endPoint;
			LOGGER.info("\n JOBS Containers Endpoint is: " + jobEndPoint);

			HttpGet request = new HttpGet(jobEndPoint);
			HttpResponse response = httpClient.execute(request);
			HttpEntity entity = response.getEntity();

			responseString = EntityUtils.toString(entity, UTF_8);

			if (responseString != null && !responseString.isEmpty()) {
				String[] strArry1 = responseString.split(",");
				if (strArry1 != null && strArry1.length != 0 && strArry1[1] != null && !strArry1[1].isEmpty()) {
					String[] strArry2 = strArry1[1].split(":");
					if (strArry2 != null && strArry2.length != 0 && strArry2[1] != null && !strArry2[1].isEmpty()) {
						responseString = strArry2[1].replace("[", "").replace("]", "");
					}
				}

				LOGGER.debug(responseString);
				// responseString = "Job posted successfully";
			}
			LOGGER.debug(responseString);
		} catch (Exception e) {
			LOGGER.error(e);
		} finally {
			try {
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				LOGGER.error(e);
			}

		}
		return responseString;
	}

	private String getJobIdFromJsonData(String pEndpoint) throws Exception {
		LOGGER.debug("getJobIdFromJsonData() method enter....");
		String result = null;
		Client client = null;
		try {
			Properties properties = mAppConfigIntrface.getProperties();
			//String host = (String) properties.get(JOB_HOST);
			String port = (String) properties.get(MARATHON_HOST);
			// String jobContainerHost = (String)
			// properties.get(JOB_CONTAINER_HOST);

			String endPoint = "/v2/apps";
			if (pEndpoint != null && !pEndpoint.isEmpty()) {
				endPoint = endPoint + pEndpoint;
			}
			//http://52.24.185.151:8080
			//String jobEndPoint = HTTP_PREFIX + host + CHARACTER_COLON + port + endPoint;
			String jobEndPoint = port + endPoint;
			// String jobEndPoint =jobContainerHost+ endPoint;
			LOGGER.info("\n JOBS Endpoint is: " + jobEndPoint);

			client = Client.create();

			WebResource webResource = client.resource(jobEndPoint);

			ClientResponse clientResponse = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);

			if (clientResponse.getStatus() != 200) {
				throw new RuntimeException(FAILED_HTTP_ERROR_CODE + clientResponse.getStatus());
			}

			result = clientResponse.getEntity(String.class);

		} catch (Exception e) {
			LOGGER.error(e);
			throw e;
		} finally {
			if (client != null) {
				client.destroy();
			}
		}
		LOGGER.debug("getJobIdFromJsonData() method exit....");
		return result;
	}

}
