package com.nvidia.cosmos.cloud.rest.util;

import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.CHARACTER_DOUBLE_QUOTE;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.CHARACTER_EMPTY;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.CHARACTER_NEWLINE;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.CHARACTER_SINGLE_FRONT_SLASH;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.CHRONOS_SCHEDULER_GRAPH_CSV;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.CHRONOS_SCHEDULER_JOB;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.CHRONOS_SCHEDULER_JOBS;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.CONTAINER_JSON_LABEL;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.LABEL;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.MARATHON_V2_APPS;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.MESOS_SLAVE_STATE;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.formatString;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.ChronosGraphCsvResponseMethod.FAILURE;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.ChronosGraphCsvResponseMethod.FRESH;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.ChronosGraphCsvResponseMethod.IDLE;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.ChronosGraphCsvResponseMethod.QUEUED;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.ChronosGraphCsvResponseMethod.RUNNING;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.ChronosGraphCsvResponseMethod.SUCCESS;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.ChronosJobResponseStatus.FINISHED;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.JobApiType.CHRONOS;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.JobApiType.MARATHON;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.JobStatus.NO;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.JobStatus.YES;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.MarathonJobResponseStatus.FAILED_TO_LAUNCH;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.MarathonJobResponseStatus.IN_QUEUE;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.MarathonJobResponseStatus.LAUNCHED;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.nvidia.cosmos.cloud.common.ServicesConstants;
import com.nvidia.cosmos.cloud.dtos.job.Parameters;
import com.nvidia.cosmos.cloud.dtos.job.choronos.ChronosGraphCsv;
import com.nvidia.cosmos.cloud.exceptions.BaseException;
import com.nvidia.cosmos.cloud.exceptions.ClusterNotFoundException;
import com.nvidia.cosmos.cloud.exceptions.CustomerNotFoundException;
import com.nvidia.cosmos.cloud.exceptions.JobNotFoundException;
import com.nvidia.cosmos.cloud.exceptions.NodeNotFoundException;
import com.nvidia.cosmos.cloud.factory.ServicesFactory;
import com.nvidia.cosmos.cloud.rest.common.util.DateUtil;
import com.nvidia.cosmos.cloud.rest.exception.NotAuthorizeException;
import com.nvidia.cosmos.cloud.rest.util.jobparser.ChronosJobDetails;
import com.nvidia.cosmos.cloud.rest.util.jobparser.JobDetails;
import com.nvidia.cosmos.cloud.rest.util.jobparser.JobRetVal;
import com.nvidia.cosmos.cloud.rest.util.jobparser.LastTaskFailure;
import com.nvidia.cosmos.cloud.rest.util.jobparser.Task;
import com.nvidia.cosmos.cloud.rest.util.jobparser.WSServerJobResponse;
import com.nvidia.cosmos.cloud.services.cluster.model.Cluster;
import com.nvidia.cosmos.cloud.services.cluster.service.ClusterService;
import com.nvidia.cosmos.cloud.services.customer.service.CustomerService;
import com.nvidia.cosmos.cloud.services.job.model.Job;
import com.nvidia.cosmos.cloud.services.job.service.JobService;
import com.nvidia.cosmos.cloud.services.node.model.Node;
import com.nvidia.cosmos.cloud.services.node.service.NodeService;
import com.nvidia.cosmos.cloud.services.role.model.Role;
import com.nvidia.cosmos.cloud.services.user.model.User;
import com.nvidia.cosmos.cloud.services.user.service.UserService;

@Component
@EnableScheduling
public class ScheduledTasks {

	// private static final String DISCONNECTED = "disconnected";

	private static Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

	private static final String JOB_RUNNING = "TASK_RUNNING";
	private static final String JOB_FAILED = "TASK_FAILED";
	private static final String JOB_QUEUED = "TASK_QUEUED";
	private static final String JOB_KILLED = "TASK_KILLED";
	private static final String JOB_FINISHED = "TASK_FINISHED";

	private static final String PATH_FRAMEWORKS = "frameworks";
	private static final String PATH_COMPLETED_FRAMEWORKS = "completed_frameworks";

	private static final String PATH_EXECUTORS = "executors";
	private static final String PATH_COMPLETED_EXECUTORS = "completed_executors";

	private static final String PATH_TASKS = "tasks";
	private static final String PATH_COMPLETED_TASKS = "completed_tasks";

	/**
	 * Schedule at every 3 min below methods it will updates node cloud status
	 */

	@Autowired
	Environment environment;

	/**
	 * 
	 */
	NodeService nodeService = ServicesFactory.getInstance().getNodeService();

	/**
	 * 
	 */
	CustomerService customerService = ServicesFactory.getInstance().getCustomerService();

	/**
	 * 
	 */
	JobService jobService = ServicesFactory.getInstance().getJobService();

	/**
	 * 
	 */
	UserService userService = ServicesFactory.getInstance().getUserService();

	/**
	 * 
	 */
	ClusterService clusterService = ServicesFactory.getInstance().getClusterService();

	// String marathonUrl = "//v2/app";//we are not using it right know its for
	// exception handiling
	// String quayHost = "52.38.206.109";
	String marathonUrl;
	String quayHost;
	String chronosUrlPrefix;
	String dockerUri;
	String websocketServerUrl;
	String tomcatUri;
	String nvidiaHost;
	String mesosSlvaePrefix = null;

	// private static ConcurrentHashMap<String, List<JobTaskDTO>>
	// CUSTOMER_JOB_DETAILS_MAP = new ConcurrentHashMap<String,
	// List<JobTaskDTO>>();

	@PostConstruct
	private void init() {
		try {
			if (environment.getProperty("marathon.uri") != null)
				marathonUrl = environment.getProperty("marathon.uri");
			if (environment.getProperty("quay.url") != null)
				quayHost = environment.getProperty("quay.url");
			{
				int slashslash = quayHost.indexOf("//") + 2;
				quayHost = quayHost.substring(slashslash, quayHost.indexOf('/', slashslash));
				/*
				 * URL aURL = new URL("quayhost"); quayhost = aURL.getHost();
				 */
			}
			if (environment.getProperty("chronos.uri.prefix") != null) {
				chronosUrlPrefix = environment.getProperty("chronos.uri.prefix");
			}
			if (environment.getProperty("docker.uri.prefix") != null) {
				dockerUri = environment.getProperty("docker.uri.prefix");
			}
			if (environment.getProperty("websocket.retsapi.server.url") != null) {
				websocketServerUrl = environment.getProperty("websocket.retsapi.server.url");
			}
			if (environment.getProperty("tomcat.url") != null) {
				tomcatUri = environment.getProperty("tomcat.url");
				// String myString = tomcatUri;
				// tomcatUri = myString.replace("http://","").replace("http://
				// www.","").replace("www.","");
				// int slashslash = tomcatUri.indexOf("//") + 2;
				// tomcatUri = tomcatUri.substring(slashslash,
				// tomcatUri.indexOf('/', slashslash));
				// System.out.println(a);
			}
			if (environment.getProperty("nvidia.host") != null) {
				nvidiaHost = environment.getProperty("nvidia.host");
			}
			if (environment.getProperty("mesos.slave.prefix") != null) {
				mesosSlvaePrefix = environment.getProperty("mesos.slave.prefix");
			}
		} catch (Exception e) {
			logger.error("Error while reading properties for Marathon", e);

		}
	}

	/*
	 * @Transactional(rollbackFor = { NodeNotFoundException.class,
	 * BaseException.class })
	 * 
	 * @Scheduled(fixedDelayString =
	 * "${node.status.update.time.in.milliseconds}") protected void
	 * updateNodeCloudStatusByTime() { // Default time if we missed in config
	 * file String nodeStatusTime = "3"; try { nodeStatusTime =
	 * environment.getProperty("node.status.update.time.minute"); } catch
	 * (Exception e) { logger.error(
	 * "Unable to Read field from properties file with key:->'node.status.update.time.minute'"
	 * , e); } try {
	 * nodeService.updateNodeStatusByTime(Integer.parseInt(nodeStatusTime)); }
	 * catch (NumberFormatException | NodeNotFoundException e) {
	 * logger.error(e.getMessage()); } }
	 */

	@Scheduled(fixedDelayString = "${job.status.update.time.in.milliseconds}")
	@Transactional(rollbackFor = { NotAuthorizeException.class, CustomerNotFoundException.class,
			ClusterNotFoundException.class, NodeNotFoundException.class, JobNotFoundException.class,
			BaseException.class })
	public void scheduleJobsStatusList() throws Exception {
		logger.debug("Started JobsController.scheduleJobsStatusList() ");
		try {

			Role role = new Role();
			role.setName(ServicesConstants.CUSTOMER_ADMIN);

			List<User> userList = userService.findAllActiveUserList(role);

			if (userList != null && !userList.isEmpty() == true) {
				for (User user : userList) {
					List<Cluster> clustersList = clusterService.findClusterByCustomerId(user.getCustomer().getId());
					if (clustersList != null) {
						for (Cluster cluster : clustersList) {
							List<Node> nodeList = nodeService.findAllNodesByCluster(cluster.getId());
							if (nodeList != null && !nodeList.isEmpty()) {
								for (Node node : nodeList) {
									// update node status for no jobs case.
									try {
										this.updateNodeStatusOnMarthonWebsocketResult(node.getSerialNumber(), user);
									} catch (Exception e) {
										logger.error("Error while updating node status {} for serial {}",
												e.getMessage(), node.getSerialNumber());
									}

									Job job = new Job();
									job.setIsDeleted(NO.toString());
									job.setServiceType(MARATHON.toString());
									job.setNode(node);
									List<Job> nodeMarathonJobList = jobService.findAllJobsByServiceType(job);
									if (nodeMarathonJobList != null && !nodeMarathonJobList.isEmpty()) {
										try {
											this.getMarathonJobAppDetailsFromJsonData(node.getSerialNumber(),
													nodeMarathonJobList, user);
										} catch (Exception e) {
											logger.error(e.getMessage());
										}
									}

									try {
										this.updateNodeStatusOnChronusWebsocketResult(node.getSerialNumber(), user);
									}

									catch (Exception e) {
										logger.error("Error while updating node status {} for serial {}",
												e.getMessage(), node.getSerialNumber());
									}

									job = new Job();
									job.setIsDeleted(NO.toString());
									job.setServiceType(CHRONOS.toString());
									job.setNode(node);
									List<Job> nodeChonosJobList = jobService.findAllJobsByServiceType(job);
									if (nodeChonosJobList != null && !nodeChonosJobList.isEmpty()) {
										try {
											this.getChronosJobAppDetailsFromJsonData(node.getSerialNumber(),
													nodeChonosJobList, user);
										} catch (Exception e) {
											logger.error(e.getMessage());
										}
									}

								}
							}
						}

					}
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		logger.debug("Ended JobsController.scheduleJobsStatusList()");
	}

	/*
	 * @Scheduled(fixedDelayString =
	 * "${job.status.update.time.in.milliseconds}")
	 * 
	 * @Transactional(rollbackFor = { NotAuthorizeException.class,
	 * CustomerNotFoundException.class, ClusterNotFoundException.class,
	 * NodeNotFoundException.class, JobNotFoundException.class,
	 * BaseException.class }) public void addChronosJobsList() throws Exception
	 * { logger.debug("Started JobsController.addChronosJobsListIntoMap() ");
	 * try { List<Customer> customersList = customerService.findAllCustomers();
	 * if (customersList != null && !customersList.isEmpty() == true) { for
	 * (Customer customer : customersList) { List<Cluster> clustersList =
	 * clusterService.findClusterByCustomerId(customer.getId()); if
	 * (clustersList != null) { for (Cluster cluster : clustersList) {
	 * List<Node> nodeList = nodeService.findAllNodesByCluster(cluster.getId());
	 * if (nodeList != null && !nodeList.isEmpty()) { for (Node node : nodeList)
	 * { Job job = new Job(); job.setIsDeleted(NO.toString());
	 * job.setServiceType(CHRONOS.toString()); job.setNode(node); List<Job>
	 * nodeJobList = jobService.findAllJobsByServiceType(job); if (nodeJobList
	 * != null && !nodeJobList.isEmpty()) { try {
	 * this.getChronosJobAppDetailsFromJsonData(node.getSerialNumber(),
	 * nodeJobList); } catch (Exception e) { logger.error(e.getMessage()); } } }
	 * } }
	 * 
	 * } } } } catch (Exception e) { logger.error(e.getMessage()); }
	 * logger.debug("Ended JobsController.addChronosJobsListIntoMap()"); }
	 */

	@Transactional(rollbackFor = { NotAuthorizeException.class, CustomerNotFoundException.class,
			ClusterNotFoundException.class, NodeNotFoundException.class, BaseException.class })
	@Scheduled(fixedDelayString = "${job.status.update.time.in.milliseconds}")
	public void getChronosGraphCsvJobApi() throws JsonParseException, JsonMappingException, IOException, BaseException {
		logger.debug("Started JobsController.getChronosGraphCsvJobApi() ");

		Role role = new Role();
		role.setName(ServicesConstants.CUSTOMER_ADMIN);

		List<User> userList = userService.findAllActiveUserList(role);
		if (userList != null && !userList.isEmpty() == true) {
			for (User user : userList) {
				List<Cluster> clustersList = clusterService.findClusterByCustomerId(user.getCustomer().getId());
				if (clustersList != null) {
					for (Cluster cluster : clustersList) {
						JobRetVal jobRetVal = getChronosGraphCsvData(cluster.getSerialNumber(), user);
						if (jobRetVal != null && jobRetVal.getStatus() != null
								&& jobRetVal.getStatus().equals(HttpStatus.OK.toString())) {
							List<ChronosGraphCsv> chronosGraphCsvsList = JobsClient
									.getChronosGraphCsvList(jobRetVal.getData());
							for (ChronosGraphCsv chronosGraphCsv : chronosGraphCsvsList) {
								if (chronosGraphCsv.getLastResult().equalsIgnoreCase(FRESH.toString())
										&& chronosGraphCsv.getJobState().equalsIgnoreCase(IDLE.toString())) {
									WSServerJobResponse wsServerJobResponse2 = JobsClient.putChronosJobApi(
											websocketServerUrl, cluster.getSerialNumber(),
											formatString(chronosUrlPrefix, CHRONOS_SCHEDULER_JOB),
											chronosGraphCsv.getJobId(), user.getUserName(), user.getqAuthToken());
									JobRetVal jobRetVal2 = JobsClient
											.convertWebsocketServerResponse(wsServerJobResponse2, CHRONOS.toString());
									if (jobRetVal2 != null && jobRetVal2.getStatus() != null) {
										logger.debug("Chronos Graphcsv put method updated successfully!"
												+ ", SerialNumber:" + cluster.getSerialNumber() + ", Job Name:"
												+ chronosGraphCsv.getNode());
									} else {
										logger.debug("Chronos Graphcsv put method Not updated successfully!"
												+ ", SerialNumber:" + cluster.getSerialNumber() + ", Job Name:"
												+ chronosGraphCsv.getNode());
									}
								}
							}

						} else {
							if (jobRetVal != null) {
								logger.debug("Chronos Graphcsv put method Not updated successfully!" + ", SerialNumber:"
										+ cluster.getSerialNumber());
							}
						}
					}
				}
			}
		}
		logger.debug("Ended JobsController.getChronosGraphCsvJobApi()");
	}

	private void updateNodeStatusOnMarthonWebsocketResult(String serialNumber, User user) throws Exception {
		logger.debug("In updateNodeStatusOnMarthonWebsocketResult");
		String uri = formatString(marathonUrl, MARATHON_V2_APPS);
		WSServerJobResponse wsServerJobResponse = JobsClient.listJobsString(websocketServerUrl, serialNumber, uri,
				CHARACTER_EMPTY, user.getUserName(), user.getqAuthToken());
		JobRetVal jobRetVal = JobsClient.convertWebsocketServerResponse(wsServerJobResponse, MARATHON.toString());

		if (jobRetVal != null) {
			logger.debug("Websocket  status {} for serial number", jobRetVal.getStatus(), serialNumber);
		}
		if (jobRetVal != null && StringUtils.equals(jobRetVal.getStatus(), HttpStatus.BAD_GATEWAY.toString()) == true
				|| StringUtils.equals(jobRetVal.getStatus(), HttpStatus.SERVICE_UNAVAILABLE.toString()) == true) {

			if (serialNumber != null && !serialNumber.isEmpty()) {
				try {
					Node node = nodeService.findNodeBySerialNumber(serialNumber);
					if (node != null) {
						node.setCloudStatus(ServicesConstants.NODE_DISCONNCETD);
						node.setIpAddress(ServicesConstants.NODE_DISCONNCETD_IP);
						nodeService.updateNode(node);
					}
				} catch (NodeNotFoundException e) {
					logger.error(e.getMessage());
				}
			}

		}
	}

	private void updateNodeStatusOnChronusWebsocketResult(String serialNumber, User user) throws Exception {
		String uri = formatString(chronosUrlPrefix, CHRONOS_SCHEDULER_JOBS);
		WSServerJobResponse wsServerJobResponse = JobsClient.listJobsString(websocketServerUrl, serialNumber, uri,
				CHARACTER_EMPTY, user.getUserName(), user.getqAuthToken());
		JobRetVal jobRetVal = JobsClient.convertWebsocketServerResponse(wsServerJobResponse, CHRONOS.toString());

		if (jobRetVal != null) {
			logger.debug("Websocket  status {}", jobRetVal.getStatus());
		}
		if (jobRetVal != null && StringUtils.equals(jobRetVal.getStatus(), HttpStatus.BAD_GATEWAY.toString()) == true
				|| StringUtils.equals(jobRetVal.getStatus(), HttpStatus.SERVICE_UNAVAILABLE.toString()) == true
				|| StringUtils.equalsIgnoreCase(jobRetVal.getStatus(),
						"Connection to the appliance is down!") == true) {

			if (serialNumber != null && !serialNumber.isEmpty()) {
				try {
					Node node = nodeService.findNodeBySerialNumber(serialNumber);
					if (node != null) {
						node.setCloudStatus(ServicesConstants.NODE_DISCONNCETD);
						node.setIpAddress(ServicesConstants.NODE_DISCONNCETD_IP);
						nodeService.updateNode(node);
					}
				} catch (NodeNotFoundException e) {
					logger.error(e.getMessage());
				}
			}

		}
	}

	private void getMarathonJobAppDetailsFromJsonData(String serialNumber, List<Job> nodeJobList, User user)
			throws Exception {
		logger.debug("getMarathonJobAppDetailsFromJsonData() method enter...");
		String uri = formatString(marathonUrl, MARATHON_V2_APPS);
		WSServerJobResponse wsServerJobResponse = JobsClient.listJobsString(websocketServerUrl, serialNumber, uri,
				CHARACTER_EMPTY, user.getUserName(), user.getqAuthToken());
		JobRetVal jobRetVal = this.validateWebsocketServerResponse(wsServerJobResponse, nodeJobList,
				MARATHON.toString(), serialNumber);
		if (jobRetVal == null) {
			return;
		} else if (jobRetVal != null && StringUtils.isNotBlank(jobRetVal.getData())
				&& StringUtils.equals(jobRetVal.getStatus(), HttpStatus.OK.toString()) == true) {
			for (Job job : nodeJobList) {
				if (job != null) {
					WSServerJobResponse wsServerJobResponse2 = JobsClient.listJobsString(websocketServerUrl,
							serialNumber, uri, CHARACTER_SINGLE_FRONT_SLASH + job.getName(), user.getUserName(),
							user.getqAuthToken());
					JobRetVal jobRetVal2 = this.validateWebsocketServerResponse(wsServerJobResponse2,
							Collections.singletonList(job), MARATHON.toString(), serialNumber);
					if (jobRetVal2 == null) {
						return;
					} else if (jobRetVal2 != null && StringUtils.isNotBlank(jobRetVal2.getData())
							&& StringUtils.equals(jobRetVal2.getStatus(), HttpStatus.OK.toString()) == true) {
						try {
							job.setResponseJson(jobRetVal2.getData());
							ObjectMapper objectMapper = new ObjectMapper();
							JobDetails jobDetails = objectMapper.readValue(jobRetVal2.getData(), JobDetails.class);
							if (jobDetails != null && jobDetails.getApp() != null) {
								LastTaskFailure lastTaskFailure = jobDetails.getApp().getLastTaskFailure();
								if (lastTaskFailure != null) {
									job.setHost(lastTaskFailure.getHost());
									job.setSlaveId(lastTaskFailure.getSlaveId());
									job.setState(lastTaskFailure.getState());
									job.setTaskId(lastTaskFailure.getTaskId());
									job.setVersion(lastTaskFailure.getVersion());

									String text = lastTaskFailure.getMessage();
									text = text != null ? text.replace(CHARACTER_NEWLINE, CHARACTER_EMPTY)
											.replace(CHARACTER_DOUBLE_QUOTE, CHARACTER_EMPTY) : CHARACTER_EMPTY;
									job.setMessage(text.trim());
									job.setTimestamp(lastTaskFailure.getTimestamp());
								}

								if (jobDetails.getApp().getContainer() != null
										&& jobDetails.getApp().getContainer().getDocker() != null) {
									job.setImage(jobDetails.getApp().getContainer().getDocker().getImage());
									// jobTaskData.setNetwork(app.getContainer().getDocker().getNetwork());

									/*
									 * if (jobDetails.getApp().getLabels() !=
									 * null) {
									 * 
									 * jobTaskData.setUniquename(labels.
									 * getLabel());
									 * 
									 * job.setOwnerName(jobDetails.getApp().
									 * getLabels().getOwnerName());
									 * job.setSerialNumber(jobDetails.getApp ().
									 * getLabels().getSerialNumber());
									 * job.setUniqueId(jobDetails.getApp().
									 * getLabels().getLabel()); }
									 */

								}
								if (jobDetails.getApp().getTasksStaged() == 0
										&& jobDetails.getApp().getTasksRunning() == 0) {
									job.setStatus(FAILED_TO_LAUNCH.toString());
									
								}

								for (Task task : jobDetails.getApp().getTasks()) {
									if (task != null) {

										job.setIpAddress(task.getHost());
										if (task.getPorts() != null && !task.getPorts().isEmpty()) {
											String listString = StringUtils.join(task.getPorts(), ',');
											job.setPorts(listString);
										}

										// Note: If tasks are empty then no
										// need
										// to show
										// job
										// details into ui
										// taskRunning=1
										// status-Launched,taskStaged=1
										// status=In
										// queue,taskRunning=0 status=Failed
										// to
										// launch
										// LastTaskFailure is required to
										// show
										// in UI
										if (jobDetails.getApp().getTasksStaged() == 1) {
											// If Started At is null when
											// job
											// taskStaged=1
											// job.setStartedAt(task.getStagedAt());
											job.setTimeStarted(DateUtil.convertStringToUtilDate(task.getStagedAt()));
											job.setStatus(IN_QUEUE.toString());
											// jobTaskDataList.add(jobTaskData);
										} else if (jobDetails.getApp().getTasksRunning() == 1) {
											job.setTimeStarted(DateUtil.convertStringToUtilDate(task.getStagedAt()));
											// jobTaskData.setStartedAt(task.getStartedAt());
											// jobTaskData.setHost(task.getHost());
											if (task.getHost() != null && !task.getHost().isEmpty()) {
												// job.setIpAddress(task.getHost());
												job.setHost(task.getHost());
											} else {
												logger.error(" no host from the marathon job ");
											}
											job.setStatus(LAUNCHED.toString());
											// To get container name use
											// below
											// url
											try {
												String id = this.getDockerContainerName(serialNumber, job.getUniqueId(),
														MARATHON.toString(), user);
												if (id != null && !id.isEmpty()) {
													job.setDockerContainerName(id);
												} else {
													logger.error(" no id from the mesos job ");
												}
											} catch (Exception e) {
												logger.error(e.getMessage());
											}
										}
									}
								}
								if (jobDetails.getApp().getTasksStaged() == 0
										&& jobDetails.getApp().getTasksRunning() == 0 && lastTaskFailure == null
										&& jobDetails.getApp().getTasks().isEmpty()) {
									job.setStatus(IN_QUEUE.toString());
								}
								if (job.getStatus() != null && !job.getStatus().equals(IN_QUEUE.toString())) {
									try {
										// this.getMesosJobFailedDeatils(job,
										// MARATHON.toString());
										this.getMesosJobInfo(job, MARATHON.toString(), user);
									} catch (Exception e) {
										logger.error("Unable to get Mesos Slave ID {}", e.getMessage());
									}
								}
								job.setServiceHelth(null);
								jobService.mergeJob(job);
							}
						} catch (JobNotFoundException e) {
							logger.error(e.getMessage());
						}
					} else {
						// TODO Discuss with Surendra Sir
						if (job.getStatus() != null && !job.getStatus().isEmpty() && job.getIsDeleted() != null
								&& job.getIsDeleted().equalsIgnoreCase(YES.toString())) {
							logger.error(jobRetVal2.toString());
							job.setIsDeleted(YES.toString());
							jobService.removeJob(job);
						}
					}
				}
			}
		}
		logger.debug("getMarathonJobAppDetailsFromJsonData() method exit...");

	}

	private void getChronosJobAppDetailsFromJsonData(String serialNumber, List<Job> nodeJobList, User user)
			throws Exception {
		logger.debug("getChronosJobAppDetailsFromJsonData() method enter...");

		String uri = formatString(chronosUrlPrefix, CHRONOS_SCHEDULER_JOBS);
		WSServerJobResponse wsServerJobResponse = JobsClient.listJobsString(websocketServerUrl, serialNumber, uri,
				CHARACTER_EMPTY, user.getUserName(), user.getqAuthToken());
		JobRetVal jobRetVal = this.validateWebsocketServerResponse(wsServerJobResponse, nodeJobList, CHRONOS.toString(),
				serialNumber);
		if (jobRetVal == null) {
			return;
		} else if (jobRetVal != null && StringUtils.isNotBlank(jobRetVal.getData())
				&& StringUtils.equals(jobRetVal.getStatus(), HttpStatus.OK.toString()) == true) {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			List<ChronosJobDetails> chronosJobDetailsList = objectMapper.readValue(jobRetVal.getData(),
					objectMapper.getTypeFactory().constructCollectionType(List.class, ChronosJobDetails.class));
			if (chronosJobDetailsList != null && !chronosJobDetailsList.isEmpty()) {
				JobRetVal jobRetValData = getChronosGraphCsvData(serialNumber, user);
				for (ChronosJobDetails chronosJobDetails : chronosJobDetailsList) {
					for (Job job : nodeJobList) {
						try {
							if (chronosJobDetails.getName().equals(job.getName()) == true) {
								ChronosGraphCsv chronosGraphCsv = JobsClient
										.getChronosGraphCsvByJobId(jobRetValData.getData(), job.getName());
								// node,counter,fresh,idle (Job is submitted
								// and
								// waiting
								// for
								// start signal. Can be shown as Queued)
								// node,counter,fresh,queued (Job state
								// Queued)
								// node,counter,fresh,running (Job state
								// Running)
								// node,counter,Success,idle (Job state
								// Finished)
								// node,counter,Failure,idle (Job state
								// Failed)

								String jobStatus = null;
								if (chronosGraphCsv != null && chronosGraphCsv.getIsDeleted() != null
										&& chronosGraphCsv.getIsDeleted() == true && job.getStatus() != null
										&& !job.getStatus().isEmpty() && job.getIsDeleted() != null
										&& job.getIsDeleted().equalsIgnoreCase(YES.toString())) {
									job.setIsDeleted(YES.toString());
									jobService.removeJob(job);
									return;
								}
								if (chronosGraphCsv != null && StringUtils.isNotBlank(chronosGraphCsv.getLastResult())
										&& StringUtils.isNotBlank(chronosGraphCsv.getJobState())) {
									if (chronosJobDetails.getContainer() != null
											&& chronosJobDetails.getContainer().getParameters() != null) {
										for (Parameters parameters : chronosJobDetails.getContainer().getParameters()) {
											if (parameters.getKey() != null && !parameters.getKey().isEmpty()
													&& parameters.getKey().equalsIgnoreCase(LABEL) == true) {
												job.setUniqueId(parameters.getValue());
												break;
											}
										}
									}
									job.setSerialNumber(serialNumber);
									try {
										Node node = nodeService.findNodeBySerialNumber(serialNumber);
										if (node != null && node.getIpAddress() != null) {
											job.setHost(node.getIpAddress());
										}
									} catch (Exception e) {
										logger.equals("No Ipaddress found for given SerialNumber" + e.getMessage());
									}

									if (chronosGraphCsv.getLastResult().equalsIgnoreCase(FRESH.toString())
											&& chronosGraphCsv.getJobState().equalsIgnoreCase(QUEUED.toString())) {
										jobStatus = IN_QUEUE.toString();
									} else if (chronosGraphCsv.getLastResult().equalsIgnoreCase(FRESH.toString())
											&& chronosGraphCsv.getJobState().equalsIgnoreCase(RUNNING.toString())) {
										jobStatus = LAUNCHED.toString();
										if (job.getTimeStarted() == null) {
											job.setTimeStarted(new Date());
										}
										try {
											String dockerName = this.getDockerContainerName(serialNumber,
													job.getUniqueId(), CHRONOS.toString(), user);
											job.setDockerContainerName(dockerName);
										} catch (Exception e) {
											logger.error("Unable to get docker container Name {}", e.getMessage());
										}
									} else if (chronosGraphCsv.getLastResult().equalsIgnoreCase(SUCCESS.toString())
											&& chronosGraphCsv.getJobState().equalsIgnoreCase(IDLE.toString())) {
										jobStatus = FINISHED.toString();
										logger.info(environment.getProperty("job.finished"),
												job.getUser().getUserName(), job.getName(), job.getNode().getName());
									} else if (chronosGraphCsv.getLastResult().equalsIgnoreCase(FAILURE.toString())
											&& chronosGraphCsv.getJobState().equalsIgnoreCase(IDLE.toString())) {
										jobStatus = FAILED_TO_LAUNCH.toString();
									}

									if (chronosJobDetails.getContainer() != null
											&& chronosJobDetails.getContainer().getImage() != null) {
										job.setImage(chronosJobDetails.getContainer().getImage());
									}

									if (job.getStatus() != null && !job.getStatus().equals(IN_QUEUE.toString())) {
										if (job.getMesosSlaveName() == null) {
											try {
												// this.getMesosJobFailedDeatils(job,
												// CHRONOS.toString());
												this.getMesosJobInfo(job, CHRONOS.toString(), user);
											} catch (Exception e) {
												logger.error("Unable to get Mesos Slave ID {}", e.getMessage());
											}
										}
									}

									String chronosJson = objectMapper.writerWithView(ChronosJobDetails.class)
											.writeValueAsString(chronosJobDetails);
									// job.setOwnerName(chronosJobDetails.getOwnerName());
									job.setServiceHelth(null);
									job.setStatus(jobStatus);
									job.setResponseJson(chronosJson);
									jobService.mergeJob(job);
									break;
								}
							}

						} catch (JobNotFoundException e) {
							logger.error(e.getMessage());
						}
					}

				}
			} else {
				logger.error(jobRetVal.toString());
			}
		}
		logger.debug("getChronosJobAppDetailsFromJsonData() method exit...");

	}

	/**
	 * @param wsServerJobResponse
	 * @param serialNumber
	 * @param nodeJobList
	 * @param uri
	 * @return
	 * @throws BaseException
	 */
	private JobRetVal validateWebsocketServerResponse(WSServerJobResponse wsServerJobResponse, List<Job> nodeJobList,
			String serviceApiType, String serialNumber) throws BaseException {
		JobRetVal jobRetVal = JobsClient.convertWebsocketServerResponse(wsServerJobResponse, serviceApiType);

		logger.debug("Websocket request status {} ", jobRetVal.getStatus());

		if (jobRetVal != null && StringUtils.equals(jobRetVal.getStatus(), HttpStatus.BAD_GATEWAY.toString()) == true
				&& StringUtils.equals(jobRetVal.getJobApiType(), serviceApiType) == true) {
			this.updateBadGatewayStatus(serviceApiType, jobRetVal.getMessage(), nodeJobList, serialNumber);
			jobRetVal = null;
		} else if (jobRetVal != null
				&& StringUtils.equals(jobRetVal.getStatus(), HttpStatus.SERVICE_UNAVAILABLE.toString()) == true) {
			this.updateBadGatewayStatus(jobRetVal.getStatus(), jobRetVal.getMessage(), nodeJobList, serialNumber);
			jobRetVal = null;
		}
		return jobRetVal;
	}

	private void updateBadGatewayStatus(String jobServiceType, String message, List<Job> nodeJobList,
			String serialNumber) {
		/*
		 * if (serialNumber != null && !serialNumber.isEmpty()) { try { Node
		 * node = nodeService.findNodeBySerialNumber(serialNumber); if (node !=
		 * null) { node.setCloudStatus(ServicesConstants.NODE_DISCONNCETD);
		 * node.setIpAddress(ServicesConstants.NODE_DISCONNCETD_IP);
		 * nodeService.updateNode(node); } } catch (NodeNotFoundException e) {
		 * logger.error(e.getMessage()); } }
		 */
		for (Job job : nodeJobList) {
			if (StringUtils.equals(job.getServiceType(), jobServiceType) == true
					|| StringUtils.equals(jobServiceType, HttpStatus.SERVICE_UNAVAILABLE.toString()) == true) {
				try {
					job.setServiceHelth(message);
					jobService.mergeJob(job);
				} catch (JobNotFoundException e) {
					logger.error(e.getMessage());
				}
			}

		}

	}

	/**
	 * @param job
	 * @param jobTaskData
	 * @param objectMapper
	 * @throws BaseException
	 * @throws IOException
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 */
	private Job getMesosJobFailedDeatils(Job job, String serviceType, User user)
			throws BaseException, IOException, JsonParseException, JsonMappingException {
		try {
			WSServerJobResponse websocketFailedJobResponse = JobsClient.getAllSlaveTask(websocketServerUrl,
					job.getNode().getSerialNumber(), MESOS_SLAVE_STATE, user.getUserName(), user.getqAuthToken());
			JobRetVal jobRetVal = this.validateWebsocketServerResponse(websocketFailedJobResponse,
					Collections.singletonList(job), serviceType, job.getNode().getSerialNumber());
			if (jobRetVal != null && StringUtils.isNotBlank(jobRetVal.getData())
					&& StringUtils.equals(jobRetVal.getStatus(), HttpStatus.OK.toString()) == true) {
				JsonParser parser = new JsonParser();
				JsonObject mesosslaveRespons = (JsonObject) parser.parse(jobRetVal.getData());
				if (mesosslaveRespons != null) {
					JsonArray completedFrameworks = mesosslaveRespons.getAsJsonArray("frameworks");
					if (completedFrameworks != null) {
						for (int rowsIndex = 0; rowsIndex < completedFrameworks.size(); rowsIndex++) {
							JsonObject framework = completedFrameworks.get(rowsIndex).getAsJsonObject();
							JsonArray completedExecutors = framework.getAsJsonArray("completed_executors");
							if (completedExecutors != null) {
								for (int ceIndex = 0; ceIndex < completedExecutors.size(); ceIndex++) {
									JsonObject executor = completedExecutors.get(rowsIndex).getAsJsonObject();
									String directory = executor.get("directory").toString();
									JsonArray completedTasks = executor.getAsJsonArray("completed_tasks");
									if (completedTasks != null) {
										for (int ctIndex = 0; ctIndex < completedTasks.size(); ctIndex++) {
											JsonObject completedTask = completedTasks.get(ctIndex).getAsJsonObject();
											JsonArray labels = completedTask.getAsJsonArray("labels");
											if (labels != null) {
												for (int labelIndex = 0; labelIndex < labels.size(); labelIndex++) {
													JsonObject label = labels.get(labelIndex).getAsJsonObject();
													if (label.get("value").getAsString()
															.equalsIgnoreCase("c45f6a5f-d5ca-4fc6-adb7-882049def9e3")) {
														logger.debug("Job Failed Mesos Slave Uri" + directory
																+ ", UniqueId:" + job.getUniqueId() + ",ServiceType:"
																+ serviceType + ",Job Name:" + job.getName());
														job.setMesosSlaveName(directory);
														return job;
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			} else {
				logger.error("Unable to get Messos Slave URI" + websocketFailedJobResponse.toString() + ", UniqueId:"
						+ job.getUniqueId() + ",ServiceType:" + serviceType + ",Job Name:" + job.getName());
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return job;
	}

	/**
	 * @param job
	 * @param user
	 * @param jobTaskData
	 * @param objectMapper
	 * @throws BaseException
	 * @throws IOException
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 */
	private void getMesosJobInfo(Job job, String serviceType, User user)
			throws BaseException, IOException, JsonParseException, JsonMappingException {
		try {
			WSServerJobResponse websocketFailedJobResponse = JobsClient.getAllSlaveTask(websocketServerUrl,
					job.getNode().getSerialNumber(), MESOS_SLAVE_STATE, user.getUserName(), user.getqAuthToken());
			JobRetVal jobRetVal = this.validateWebsocketServerResponse(websocketFailedJobResponse,
					Collections.singletonList(job), serviceType, job.getNode().getSerialNumber());
			if (jobRetVal != null && StringUtils.isNotBlank(jobRetVal.getData())
					&& StringUtils.equals(jobRetVal.getStatus(), HttpStatus.OK.toString()) == true) {

				String messos_slavedir = null;
				if (serviceType.equals(CHRONOS.toString()) && job!=null && job.getStatus() != null && !job.getStatus().equals(QUEUED)) {
					logger.debug("Calling getMesosSlaveDirctoryForChronos with parameter job status {} and uiniqueid {}",job.getStatus(),job.getUniqueId());
					messos_slavedir = getMesosSlaveDirctoryForChronos(jobRetVal, job.getUniqueId(), job.getStatus());
				} else if (serviceType.equals(MARATHON.toString()) && job!=null && job.getStatus() != null && !job.getStatus().equals(IN_QUEUE)) {
					logger.debug("Calling getMesosSlaveDirctoryForMarthon with parameter job status {} and uiniqueid {}",job.getStatus(),job.getUniqueId());
					messos_slavedir = getMesosSlaveDirctoryForMarthon(jobRetVal, job.getUniqueId(), job.getStatus());

				}

				if (messos_slavedir != null) {
					String uri = /*JobServiceConstant.MESOS_SLAVE_FILES_READ_PATH_PREFIX +*/ messos_slavedir;
							//+ JobServiceConstant.MESOS_SLAVE_SUFFIX;
					if (uri != null && !uri.isEmpty()) {
						job.setMesosSlaveName(uri);
					}
				}

			} else {
				logger.error("Unable to get Messos Slave URI" + websocketFailedJobResponse.toString() + ", UniqueId:"
						+ job.getUniqueId() + ",ServiceType:" + serviceType + ",Job Name:" + job.getName());
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	public static String getMesosSlaveDirctoryForChronos(JobRetVal jobRetVal, String uniqueID, String jobStatus) {
		if (jobRetVal.getData() == null) {
			logger.error("Messos response is not avilable ");
			return null;
		}
		JsonParser parser = new JsonParser(); //
		JsonObject stateJson = (JsonObject) parser.parse(jobRetVal.getData());
		/*JsonObject stateJson=null;
		 ClassPathResource shellScript = new ClassPathResource("dev-state.json");
		  try {
				 stateJson = (JsonObject)parser.parse(new FileReader(shellScript.getFile()));
			} catch (JsonIOException | JsonSyntaxException | IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}*/
		  
		logger.debug("Parsing messos slave response for chronous slave_directory {}",stateJson.toString());
		JsonArray frameworks = null;
		try {
			if (jobStatus.equalsIgnoreCase(RUNNING.toString())) {
				frameworks = stateJson.getAsJsonArray(PATH_FRAMEWORKS);
			} else {
				frameworks = stateJson.getAsJsonArray(PATH_COMPLETED_FRAMEWORKS);
			}
		} catch (Exception e1) {
			logger.error("Parsing error for job status {} {}", jobStatus, e1.getMessage());
		}
		if (frameworks == null) {
			logger.debug("Tag for the job status {} not found in tags {} or {}", jobStatus, PATH_FRAMEWORKS,
					PATH_COMPLETED_FRAMEWORKS);
			return null;
		}
		logger.debug("Found frameworks {}", frameworks.size());

		for (int frameworksIndex = 0; frameworksIndex < frameworks.size(); frameworksIndex++) {
			JsonObject framework = frameworks.get(frameworksIndex).getAsJsonObject();
			JsonArray executors = null;
			try {
				if (jobStatus.equalsIgnoreCase(RUNNING.toString())) {
					executors = framework.getAsJsonArray(PATH_EXECUTORS);
				} else {
					executors = framework.getAsJsonArray(PATH_COMPLETED_EXECUTORS);
				}

			} catch (Exception e1) {
				logger.error("Parsing error for job status {} {}", jobStatus, e1.getMessage());
			}

			if (executors == null) {
				logger.debug("Tag for the job status {} not found in tags {} or {}", jobStatus, PATH_EXECUTORS,
						PATH_COMPLETED_EXECUTORS);
				continue;
			}

			logger.debug("Found executors with size {}", executors.size());

			for (int executorsIndex = 0; executorsIndex < executors.size(); executorsIndex++) {
				JsonObject executor = executors.get(executorsIndex).getAsJsonObject();
				// completed_tasks moved to tasks
				JsonArray tasks = null;
				try {
					if (jobStatus.equalsIgnoreCase(RUNNING.toString())) {
						tasks = executor.getAsJsonArray(PATH_TASKS);
					} else {
						tasks = executor.getAsJsonArray(PATH_COMPLETED_TASKS);
					}
				} catch (Exception e1) {
					logger.error("Parsing error for job status {} {}", jobStatus, e1.getMessage());
				}

				if (tasks == null) {
					logger.debug("Tag for the job status {} not found in tags {} or {}", jobStatus, PATH_TASKS,
							PATH_COMPLETED_TASKS);
					continue;
				}

				logger.debug("found tasks with size {}", tasks.size());

				String dir = executor.get("directory").getAsString();
				// if(completedTasks==null || completedTasks.size()==0)continue;
				for (int tasksIndex = 0; tasksIndex < tasks.size(); tasksIndex++) {
					JsonObject completedTask = tasks.get(tasksIndex).getAsJsonObject();
					JsonArray parameters = null;
					try {
						JsonObject containerObject = completedTask.getAsJsonObject("container");
						if (containerObject == null)
							continue;
						JsonObject dockerObject = containerObject.getAsJsonObject("docker");
						if (dockerObject == null)
							continue;
						parameters = dockerObject.getAsJsonArray("parameters");
					} catch (Exception e) {
						logger.debug("No Container or doceker element present");
					}

					if (parameters == null)
						continue;
					logger.debug("found parameters with size {}", parameters.size());

					for (int parametersIndex = 0; parametersIndex < parameters.size(); parametersIndex++) {
						JsonObject parameter = parameters.get(parametersIndex).getAsJsonObject();
						logger.debug("Label is {} value is {}", parameter.get("key").getAsString(),
								parameter.get("value").getAsString());
						if (parameter.get("key").getAsString().equals("label")
								&& parameter.get("value").getAsString().equals(uniqueID)) {
							logger.debug("Mesos Directory is {}", dir);
							return dir;
						}
					}
				}
			}
		}

		return null;
	}

	public static String getMesosSlaveDirctoryForMarthon(JobRetVal jobRetVal, String uniqueID, String jobStatus) {
		if (jobRetVal.getData() == null) {
			logger.error("Messos response is not avilable ");
			return null;
		}
		JsonParser parser = new JsonParser(); 
		List<MessosSlave> list = new ArrayList<MessosSlave>();
		// try {
		JsonObject stateJson = (JsonObject) parser.parse(jobRetVal.getData());
		
		/* JsonObject stateJson=null;
		
		 ClassPathResource shellScript = new ClassPathResource("marathonfailed.json");
		  try {
				 stateJson = (JsonObject)parser.parse(new FileReader(shellScript.getFile()));
			} catch (JsonIOException | JsonSyntaxException | IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			  
			*/ 
			 

		logger.debug("Parsing messos slave response for marthon slave_directory");
		JsonArray frameworks = null;
		try {
			if (jobStatus.equalsIgnoreCase(LAUNCHED.toString())) {
				frameworks = stateJson.getAsJsonArray(PATH_FRAMEWORKS);
			} else {
				frameworks = stateJson.getAsJsonArray(PATH_COMPLETED_FRAMEWORKS);
			}
		} catch (Exception e1) {
			logger.error("Parsing error for job status {} {}", jobStatus, e1.getMessage());
		}
		if (frameworks == null) {
			logger.debug("Tag for the job status {} not found in tags {} or {}", jobStatus, PATH_FRAMEWORKS,
					PATH_COMPLETED_FRAMEWORKS);
			return null;
		}
		logger.debug("Found frameworks {}", frameworks.size());
		for (int frameworksIndex = 0; frameworksIndex < frameworks.size(); frameworksIndex++) {
			JsonObject framework = frameworks.get(frameworksIndex).getAsJsonObject();
			JsonArray executors = null;
			try {
				if (jobStatus.equalsIgnoreCase(LAUNCHED.toString())) {
					executors = framework.getAsJsonArray(PATH_EXECUTORS);
				} else {
					executors = framework.getAsJsonArray(PATH_COMPLETED_EXECUTORS);
				}

			} catch (Exception e1) {
				logger.error("Parsing error for job status {} {}", jobStatus, e1.getMessage());
			}

			if (executors == null) {
				logger.debug("Tag for the job status {} not found in tags {} or {}", jobStatus, PATH_EXECUTORS,
						PATH_COMPLETED_EXECUTORS);
				continue;
			}

			logger.debug("Found executors with size {}", executors.size());

			for (int executorsIndex = 0; executorsIndex < executors.size(); executorsIndex++) {
				JsonObject executor = executors.get(executorsIndex).getAsJsonObject();
				// completed_tasks moved to tasks
				JsonArray tasks = null;
				try {
					if (jobStatus.equalsIgnoreCase(LAUNCHED.toString())) {
						tasks = executor.getAsJsonArray(PATH_TASKS);
					} else {
						tasks = executor.getAsJsonArray(PATH_COMPLETED_TASKS);
					}
				} catch (Exception e1) {
					logger.error("Parsing error for job status {} {}", jobStatus, e1.getMessage());
				}

				if (tasks == null) {
					logger.debug("Tag for the job status {} not found in tags {} or {}", jobStatus, PATH_TASKS,
							PATH_COMPLETED_TASKS);
					continue;
				}

				logger.debug("found tasks with size {}", tasks.size());
				String dir = executor.get("directory").getAsString();
				logger.debug("Found directory [" + dir + "]");

				for (int tasksIndex = 0; tasksIndex < tasks.size(); tasksIndex++) {
					JsonObject completedTask = tasks.get(tasksIndex).getAsJsonObject();
					JsonArray labels = null;
					JsonArray statuses = null;
					try {
						labels = completedTask.getAsJsonArray("labels");
						statuses = completedTask.getAsJsonArray("statuses");
					} catch (Exception e) {
						logger.debug("Labels and status tags not found");
					}

					if (labels == null || statuses == null)
						continue;

					if (labels.size() > 0) {
						logger.debug("found parameters with size {}", labels.size());
						for (int labelsIndex = 0; labelsIndex < labels.size(); labelsIndex++) {
							JsonObject label = labels.get(labelsIndex).getAsJsonObject();
							logger.debug("Label is {} value is {}", label.get("key").getAsString(),
									label.get("value").getAsString());
							if (label.get("key").getAsString().equals("label")
									&& label.get("value").getAsString().equals(uniqueID)) {
								MessosSlave ms = new MessosSlave();
								for (int statusesIndex = 0; statusesIndex < statuses.size(); statusesIndex++) {
									JsonObject statuse = statuses.get(statusesIndex).getAsJsonObject();
									ms.setTimeStamp(statuse.get("timestamp").getAsDouble());
									ms.setDirectory(dir);
								}
								list.add(ms);
							}
						}
					}

				}
			}
		}
		// }

		if (list.size() > 0) {
			Collections.sort(list, new MessosSlave());
			logger.debug("Found failed {} status directories latest timestame is {} and directorory is {}", list.size(),
					list.get(0).getTimeStamp(), list.get(0).getDirectory());
			return list.get(0).getDirectory();
		}

		return null;
	}

	private String getDockerContainerName(String serialNumber, String uniqueId, String jobApiType, User user)
			throws Exception {
		logger.debug("getDockerContainerName() method enter...with parameter {} ",
				formatString(dockerUri, CONTAINER_JSON_LABEL + uniqueId));
		String response = JobsClient.getContainerNameFromTheJobResponse(websocketServerUrl,
				formatString(dockerUri, CONTAINER_JSON_LABEL + uniqueId), serialNumber, jobApiType, user.getUserName(),
				user.getqAuthToken(), uniqueId);
		logger.debug("Respnse from getDockerContainerName method call {}", response);
		return response;
	}

	private JobRetVal getChronosGraphCsvData(String serialNumber, User user) throws BaseException {
		WSServerJobResponse wsServerJobResponse = JobsClient.getChronosGraphCsvApi(websocketServerUrl, serialNumber,
				formatString(chronosUrlPrefix, CHRONOS_SCHEDULER_GRAPH_CSV), user.getUserName(), user.getqAuthToken());
		JobRetVal jobRetVal = JobsClient.convertWebsocketServerResponse(wsServerJobResponse, CHRONOS.toString());
		return jobRetVal;
	}

	public static void main(String[] args) {

		//System.out.println(getMesosSlaveDirctoryForMarthon(null, "ea356e9a-e73b-4049-8114-2b24d1d89661", FAILED_TO_LAUNCH.toString()));
		System.out.println(getMesosSlaveDirctoryForChronos(null, "39587347-12d9-42e7-a554-9d4b8f2739ed", RUNNING.toString()));
		

	}

}

class MessosSlave implements Comparator<MessosSlave> {

	String directory;
	Double timeStamp;

	public Double getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Double timeStamp) {
		this.timeStamp = timeStamp;
	}

	MessosSlave() {

	}

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	@Override
	public int compare(MessosSlave o1, MessosSlave o2) {
		if (o2.getTimeStamp() > o1.getTimeStamp())
			return 1;
		else if (o1.getTimeStamp() > o2.getTimeStamp())
			return -1;
		else
			return 0;

	}
}
