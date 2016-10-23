package com.nvidia.cosmos.cloud.rest.controller;

import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.BEARER;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.CHARACTER_COMMA;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.CHARACTER_DOUBLE_HYPON;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.CHARACTER_DOUBLE_QUOTE;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.CHARACTER_EMPTY;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.CHARACTER_HASH;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.CHARACTER_NEWLINE;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.CHARACTER_SINGLE_FRONT_SLASH;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.CHRONOS_SCHEDULER_GRAPH_CSV;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.CHRONOS_SCHEDULER_ISO8601;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.CHRONOS_SCHEDULER_JOB;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.DEVICE;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.EULAACCEPTED;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.EULAUPDATED;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.JOB_DELETE_SUCCESS;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.JOB_IS_ALREADY_EXIST;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.JOB_NOT_DELETE_SUCCESS;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.JOB_POST_SUCCESS;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.KEY;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.LABEL;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.MARATHON_V2_APPS;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.NVIDIA;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.USER;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.VALUE;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.VOLUME;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.VOLUMES_FROM;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.VOLUME_DRIVER;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.formatString;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.JobApiType.CHRONOS;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.JobApiType.MARATHON;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.JobStatus.NO;
import static com.nvidia.cosmos.cloud.rest.util.JobServiceConstant.JobStatus.YES;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.xml.bind.ValidationException;

import org.apache.commons.lang.SerializationUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.path.json.JsonPath;
import com.nvidia.cosmos.cloud.common.ServicesConstants;
import com.nvidia.cosmos.cloud.dtos.ApiResponseDTO;
import com.nvidia.cosmos.cloud.dtos.DockerImageDTO;
import com.nvidia.cosmos.cloud.dtos.DockerImageRequestDTO;
import com.nvidia.cosmos.cloud.dtos.DockerImagesDTO;
import com.nvidia.cosmos.cloud.dtos.DockerRepoResponseDTO;
import com.nvidia.cosmos.cloud.dtos.DockerRepositoryDTO;
import com.nvidia.cosmos.cloud.dtos.EulaUpdatedOnNodeDTO;
import com.nvidia.cosmos.cloud.dtos.JobDeleteRequestDTO;
import com.nvidia.cosmos.cloud.dtos.JobRequestDataDTO;
import com.nvidia.cosmos.cloud.dtos.JobResponseDTO;
import com.nvidia.cosmos.cloud.dtos.PeelerDTO;
import com.nvidia.cosmos.cloud.dtos.ResponseDTO;
import com.nvidia.cosmos.cloud.dtos.dockerdetail.DockerImageDetailDTO;
import com.nvidia.cosmos.cloud.dtos.job.JobTaskDTO;
import com.nvidia.cosmos.cloud.dtos.job.Parameters;
import com.nvidia.cosmos.cloud.dtos.job.RequestMarathonJobDTO;
import com.nvidia.cosmos.cloud.dtos.job.choronos.RequestChronosJobDTO;
import com.nvidia.cosmos.cloud.dtos.node.NodeDTO;
import com.nvidia.cosmos.cloud.exceptions.BaseException;
import com.nvidia.cosmos.cloud.exceptions.ClusterNotFoundException;
import com.nvidia.cosmos.cloud.exceptions.CustomerNotFoundException;
import com.nvidia.cosmos.cloud.exceptions.EntitlementNotFoundException;
import com.nvidia.cosmos.cloud.exceptions.JobNotFoundException;
import com.nvidia.cosmos.cloud.exceptions.NodeNotFoundException;
import com.nvidia.cosmos.cloud.exceptions.UserAuthNotFoundException;
import com.nvidia.cosmos.cloud.exceptions.UserExistsException;
import com.nvidia.cosmos.cloud.exceptions.UserNotFoundException;
import com.nvidia.cosmos.cloud.rest.common.util.DateUtil;
import com.nvidia.cosmos.cloud.rest.exception.NotAuthorizeException;
import com.nvidia.cosmos.cloud.rest.security.AuthenticationWithToken;
import com.nvidia.cosmos.cloud.rest.util.JobUtil;
import com.nvidia.cosmos.cloud.rest.util.JobsClient;
import com.nvidia.cosmos.cloud.rest.util.NvidiaUtil;
import com.nvidia.cosmos.cloud.rest.util.jobparser.JobRetVal;
import com.nvidia.cosmos.cloud.rest.util.jobparser.WSServerJobResponse;
import com.nvidia.cosmos.cloud.services.cluster.model.Cluster;
import com.nvidia.cosmos.cloud.services.cluster.service.ClusterService;
import com.nvidia.cosmos.cloud.services.customer.model.Customer;
import com.nvidia.cosmos.cloud.services.customer.service.CustomerService;
import com.nvidia.cosmos.cloud.services.entitlement.model.Entitlement;
import com.nvidia.cosmos.cloud.services.entitlement.service.EntitlementService;
import com.nvidia.cosmos.cloud.services.job.model.Job;
import com.nvidia.cosmos.cloud.services.job.service.JobService;
import com.nvidia.cosmos.cloud.services.node.model.Node;
import com.nvidia.cosmos.cloud.services.node.service.NodeService;
import com.nvidia.cosmos.cloud.services.user.model.User;
import com.nvidia.cosmos.cloud.services.user.service.UserService;
import com.nvidia.cosmos.cloud.services.userauth.model.UserAuth;
import com.nvidia.cosmos.cloud.services.userauth.service.UserAuthService;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

/**
 * Api's to manage jobs related operations(Posting job, Deleting job , Getting job status etc..).
 * 
 *
 */
@EnableScheduling
@RestController
public class JobsController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(JobsController.class);

	@Autowired
	Environment environment;
	/**
	 * 
	 */
	ClusterService clusterService;
	/**
	 * 
	 */
	NodeService nodeService;
	/**
	 * 
	 */
	UserService userService;

	/**
	 * 
	 */
	CustomerService customerService;

	EntitlementService entitlementService;
	 private UserAuthService userAuthservice;
	/**
	 * 
	 */
	JobService jobService;
	String marathonUrl;
	String quayHost;
	String chronosUrlPrefix;
	String dockerUri;
	String websocketServerUrl;
	String tomcatUri;
	String nvidiaHost;
	
	/**
	 * 
	 */
	public JobsController() {
		super();
		clusterService = factory.getClusterService();
		nodeService = factory.getNodeService();
		customerService = factory.getCustomerService();
		userService = factory.getUserService();
		jobService = factory.getJobService();
		entitlementService = factory.getEntitlementService();
		 this.userAuthservice=factory.getUserAuthService();
	}

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

			}
			if (environment.getProperty("nvidia.host") != null) {
				nvidiaHost = environment.getProperty("nvidia.host");
			}
		} catch (Exception e) {
			logger.error("Error while reading properties for Marathon", e);

		}
	}

	/**
	 * Method returns docker images for the called user, based on his QAuthtoken
	 * associated for the user Api is allowed to the Customer Admin and Customer
	 * User
	 * 
	 * @param customerId
	 * @param request
	 * @return
	 * @throws NotAuthorizeException
	 * @throws BaseException
	 */
	@Path("/api/docker/images")
	@GET
	@ApiOperation(value = "List all the images", notes = "User can list all the docker images.User with role Customer Admin or Customer User is allowed.", response = JobResponseDTO.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "All the images have been listed") })
	@Transactional(rollbackFor = { NotAuthorizeException.class, BaseException.class })
	@RequestMapping(value = "/api/docker/images", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('Customer User','Customer Admin')")
	public JobResponseDTO listImages(HttpServletRequest request) throws NotAuthorizeException, BaseException {

		String loggedInEmail = getLoggedInUserEmail(SecurityContextHolder.getContext().getAuthentication());
		String roleName = getLoggedInUserRoleName(SecurityContextHolder.getContext().getAuthentication());
		logger.debug("RoleName and loggedinemail are {}, {}", roleName, loggedInEmail);

		User loggedInUser = userService.findUserByEmail(loggedInEmail);
		String userQAuthToken = loggedInUser.getqAuthToken();
		DockerImagesDTO dockerImagesDTO = new DockerImagesDTO();
		if (userQAuthToken == null) {
			throw new NotAuthorizeException(environment.getProperty("job.notauthorized"));
		}

		try {
			String custName = customerService.findCustomer(loggedInUser).getName();
			String url = environment.getProperty("quay.url");

			logger.info("Trying to fetch Docker Repository with fallowing parames{} customerName {}", url, custName);

			DockerImagesDTO nvidiaDockerImages = JobsClient.fetchDockerImages(url, BEARER + userQAuthToken, NVIDIA);
			if (nvidiaDockerImages != null) {
				dockerImagesDTO.addAllRepositories(nvidiaDockerImages.getRepositories());
			}

			DockerImagesDTO customerDockerImages = JobsClient.fetchDockerImages(url, BEARER + userQAuthToken, custName);
			if (customerDockerImages != null) {
				dockerImagesDTO.addAllRepositories(customerDockerImages.getRepositories());
			}

			DockerImagesDTO userDockerImages = JobsClient.fetchDockerImages(url, BEARER + userQAuthToken,
					loggedInUser.getUserName());
			if (userDockerImages != null) {
				dockerImagesDTO.addAllRepositories(userDockerImages.getRepositories());
			}
			List<DockerImageDTO> dockerRepositories = dockerImagesDTO.getRepositories();

			dockerImagesDTO = new DockerImagesDTO();
			for (DockerImageDTO dockerImageDTO : dockerRepositories) {
				DockerImageDetailDTO dockerImageDetailDTO = JobsClient.fetchDockerImageDetail(url,
						BEARER + userQAuthToken, dockerImageDTO.getName(), dockerImageDTO.getNamespace());
				if (dockerImageDetailDTO != null) {
					logger.info(dockerImageDetailDTO.toString());
					dockerImageDTO.setDockerImageDetailDTO(dockerImageDetailDTO);
					dockerImagesDTO.add(dockerImageDTO);
				}
			}

			logger.debug("list of dockerimages" + dockerImagesDTO.toString());
		} catch (MalformedURLException e) {
			logger.error(e.getMessage());
			logger.info(environment.getProperty("job.tomcat.failed"),environment.getProperty("nvidia.host"),environment.getProperty("quay.url"));
			throw new BaseException("error while listing images" + e.getMessage());
			
		}

		JobResponseDTO response = new JobResponseDTO(environment.getProperty("job.dockerimage.retreved"),
				Integer.parseInt(HttpStatus.OK.toString()));
		response.setQuayUrl(quayHost);
		response.setTomcatUrl(tomcatUri);
		response.setNvidiaUrl(nvidiaHost);
		response.setData(dockerImagesDTO);
		return response;
	}

	/**
	 * This method returns list of docker repositories for the called user,
	 * based on his QAuthtoken associated for the user Api is allowed to the
	 * Customer Admin
	 * 
	 * @param request
	 * @return
	 * @throws NotAuthorizeException
	 * @throws BaseException
	 */
	@Path("/api/docker/repository")
	@GET
	@ApiOperation(value = "List all the Docker Repository", notes = "User can list all the docker repositories.User with role Customer Admin only allowed.", response = DockerImagesDTO.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "All the Docker Repository have been listed") })
	@Transactional(rollbackFor = { NotAuthorizeException.class, BaseException.class })
	@RequestMapping(value = "/api/docker/repository", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('Customer Admin')")
	public ResponseDTO listRepository(HttpServletRequest request) throws NotAuthorizeException, BaseException {
		String message = null;
		String statusCode = HttpStatus.OK.toString();
		String loggedInEmail = getLoggedInUserEmail(SecurityContextHolder.getContext().getAuthentication());
		String roleName = getLoggedInUserRoleName(SecurityContextHolder.getContext().getAuthentication());
		logger.debug("RoleName and loggedinemail are {}, {}", roleName, loggedInEmail);

		User loggedInUser = userService.findUserByEmail(loggedInEmail);
		String userQAuthToken = loggedInUser.getqAuthToken();

		if (userQAuthToken == null) {
			throw new NotAuthorizeException(environment.getProperty("job.notauthorized"));
		}

		DockerImagesDTO dockerImagesDTO = new DockerImagesDTO();
		try {
			String custName = customerService.findCustomer(loggedInUser).getName();
			String url = environment.getProperty("quay.url");
			logger.info("Trying to fetch Docker Repository with fallowing parames{} CustomerName {}", url, custName);

			DockerImagesDTO customerDockerImages = JobsClient.fetchDockerImages(url, BEARER + userQAuthToken, custName);
			if (customerDockerImages != null) {
				dockerImagesDTO.addAllRepositories(customerDockerImages.getRepositories());
			}

			DockerImagesDTO userDockerImages = JobsClient.fetchDockerImages(url, BEARER + userQAuthToken,
					loggedInUser.getUserName());
			if (userDockerImages != null) {
				dockerImagesDTO.addAllRepositories(userDockerImages.getRepositories());
			}

			logger.debug("list of docker repository" + dockerImagesDTO.toString());
		} catch (MalformedURLException e) {
			logger.error(e.getMessage());
			logger.info(environment.getProperty("job.tomcat.failed"),environment.getProperty("nvidia.host"),environment.getProperty("quay.url"));
			throw new BaseException("error while listing docker repositories" + e.getMessage());
		}
		message = environment.getProperty("job.dockerrepository.retreved");
		ResponseDTO response = new ResponseDTO(message, Integer.parseInt(statusCode));
		response.setData(dockerImagesDTO);
		return response;
	}

	/**
	 * This method creates docker repository for the called user, based on his
	 * QAuthtoken associated for the user Api is allowed to the Customer Admin
	 * 
	 * @param customerId
	 * @param request
	 * @return
	 * @throws NotAuthorizeException
	 * @throws BaseException
	 * @throws JsonProcessingException
	 * @throws  
	 */
	@Path("/api/repo/create")
	@POST
	@ApiOperation(value = "Create the docker repository", notes = "User can create docker repository.User with role Customer Admin only allowed.", response = ApiResponseDTO.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "The docker repository has been created successfully") })
	@Transactional(rollbackFor = { NotAuthorizeException.class, JsonProcessingException.class, ValidationException.class,BaseException.class })
	@RequestMapping(value = "/api/repo/create", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('Customer Admin')")
	public ApiResponseDTO createRepository(
			@ApiParam(name = "Docker respository object", value = "Docker respository object that needs to be created", required = true) @Valid @RequestBody DockerRepositoryDTO dockerRepositoryDTO,
			HttpServletRequest request) throws NotAuthorizeException, JsonProcessingException, ValidationException, BaseException  {
		String message = null;
		String statusCode = HttpStatus.OK.toString();
		String loggedInEmail = getLoggedInUserEmail(SecurityContextHolder.getContext().getAuthentication());
		String roleName = getLoggedInUserRoleName(SecurityContextHolder.getContext().getAuthentication());
		logger.debug("RoleName and loggedinemail are {}, {}", roleName, loggedInEmail);
		User loggedInUser = userService.findUserByEmail(loggedInEmail);
		String userQAuthToken = loggedInUser.getqAuthToken();
		if (userQAuthToken == null) {
			throw new NotAuthorizeException(environment.getProperty("job.notauthorized"));
		}
		if(StringUtils.containsWhitespace(dockerRepositoryDTO.getRepository()) == true){
			throw new ValidationException(environment.getProperty("repository.name.notallow.space"));
		}
		try {
			String custName = customerService.findCustomer(loggedInUser).getName();
			String url = environment.getProperty("quay.url");

			logger.debug("Crreate Docker Repository with fallowing parmes url {} CustomerName {}", url, custName);

			dockerRepositoryDTO.setNamespace(custName);

			DockerRepoResponseDTO dockerRepoResponse = JobsClient.createDockerRepository(url, BEARER + userQAuthToken,
					dockerRepositoryDTO);
			if (dockerRepoResponse != null) {
				message = dockerRepoResponse.getMessage();
				statusCode = String.valueOf(dockerRepoResponse.getStatusCode());
			}
			logger.info(environment.getProperty("job.repository.create.sucess"),loggedInUser.getUserName(),custName,environment.getProperty("quay.url"));
		} catch (MalformedURLException e) {
			logger.error(e.getMessage());
			logger.info(environment.getProperty("job.tomcat.failed"),environment.getProperty("nvidia.host"),environment.getProperty("quay.url"));
			throw new BaseException("error while creating docker repositories" + e.getMessage());
		}
		
		ApiResponseDTO response = new ApiResponseDTO(message, Integer.parseInt(statusCode));
		return response;
	}

	/**
	 * This method is delete a docker repository for the called user, based on
	 * his QAuthtoken associated for the user Api is allowed to the Customer
	 * Admin
	 * 
	 * @param customerId
	 * @param request
	 * @return
	 * @throws NotAuthorizeException
	 * @throws CustomerNotFoundException
	 * @throws UserExistsException
	 * @throws BaseException
	 */
	@Path("/api/repo/delete")
	@POST
	@ApiOperation(value = "Delete the docker repository", notes = "User can delete docker repository.User with role Customer Admin only allowed.", response = ApiResponseDTO.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "The docker repository has been deleted successfully") })
	@Transactional(rollbackFor = { NotAuthorizeException.class, CustomerNotFoundException.class,
			UserExistsException.class, BaseException.class })
	@RequestMapping(value = "/api/repo/delete", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('Customer Admin')")
	public ApiResponseDTO deleteRepository(
			@ApiParam(name = "Docker respository object", value = "Docker respository object that needs to be deleted", required = true) @Valid @RequestBody DockerImageRequestDTO dockerImageDTO,
			HttpServletRequest request)
			throws NotAuthorizeException, CustomerNotFoundException, UserExistsException, BaseException {
		String message = null;
		String statusCode = HttpStatus.OK.toString();

		String loggedInEmail = getLoggedInUserEmail(SecurityContextHolder.getContext().getAuthentication());
		String roleName = getLoggedInUserRoleName(SecurityContextHolder.getContext().getAuthentication());
		logger.debug("RoleName and loggedinemail are {}, {}", roleName, loggedInEmail);
		User loggedInUser = userService.findUserByEmail(loggedInEmail);
		String userQAuthToken = loggedInUser.getqAuthToken();
		if (userQAuthToken == null) {
			throw new NotAuthorizeException(environment.getProperty("job.notauthorized"));
		}
		try {
			String custName = customerService.findCustomer(loggedInUser).getName();
			String url = environment.getProperty("quay.url");

			logger.debug("Crreate Docker Repository with fallowing parmes url {} CustomerName {}", url, custName);
            
			DockerRepoResponseDTO dockerRepoResponse = JobsClient.deleteDockerRepository(url, BEARER + userQAuthToken,
					dockerImageDTO);
			if (dockerRepoResponse != null) {
			//message = dockerImageDTO.getName() + dockerRepoResponse;
				//message = environment.getProperty("docker.repository.deleted");
				message=dockerRepoResponse.getMessage();
				statusCode = String.valueOf(dockerRepoResponse.getStatusCode());
			}

			logger.debug("Docker Repository Deleted successfully" + dockerImageDTO.toString());
			logger.info(environment.getProperty("job.repository.delete.sucess"),loggedInUser.getUserName(),dockerImageDTO.getName());
		} catch (MalformedURLException e) {
			logger.error(e.getMessage());
			logger.info(environment.getProperty("job.tomcat.failed"),environment.getProperty("nvidia.host"),environment.getProperty("quay.url"));
			throw new BaseException("error while delete docker repositories" + e.getMessage());
		}
		ApiResponseDTO response = new ApiResponseDTO(message, Integer.parseInt(statusCode));
		return response;
	}

	private boolean isCustomerAllowed(String serialNumber, String customerId) {
		try {
			Entitlement entitlement = entitlementService.findEntitlement(serialNumber, customerId);
			if (entitlement != null)
				return true;
		} catch (EntitlementNotFoundException e) {
			logger.debug("Custome not found ");
		}
		return false;
	}

	private boolean isCustomerAllowedForJob(Job job, String customerId) {
		try {
			logger.debug("Finding job for job uniqueid {} and customerid {} ", job.getUniqueId(), customerId);
			Job dbJob = jobService.findJobByCustomer(job.getUniqueId(), customerId);
			if (dbJob != null)
				return true;
		} catch (JobNotFoundException e) {
			logger.debug("Job not found for the customer ");
		}
		return false;
	}

	private boolean isUserAllowedForJob(Job job, String userId) {
		try {
			logger.debug("Finding job for job uniqueid {} and userId {} ", job.getUniqueId(), userId);
			Job dbJob = jobService.findJobByUser(job.getUniqueId(), userId);
			if (dbJob != null)
				return true;
		} catch (JobNotFoundException e) {
			logger.debug("Job not found for the customer user ");
		}
		return false;
	}

	/**
	 * Method to delete job on a given appliance Method is allowed for the roles
	 * Customer User and Customer Admin Customer Admin can delete jobs created
	 * by his organization users Only jobs hosted on appliance owned by the
	 * caller will get deleted. Operation is not allowed for the jobs hosted on
	 * other appliance which are not owned by the caller(NotAuthorizeException)
	 * 
	 * @param jobTaskDTO
	 * @return
	 * @throws NotAuthorizeException
	 * @throws CustomerNotFoundException
	 * @throws NodeNotFoundException
	 * @throws IOException
	 * @throws UserExistsException
	 * @throws BaseException
	 * @throws ValidationException
	 */
	@Path("/api/job/delete")
	@POST
	@ApiOperation(value = "Delete the job", notes = "User can delete a job on his appliance.User with role Customer Admin or Customer User is allowed.", response = ApiResponseDTO.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "The job with the given serial number has been deleted") })
	@Transactional(rollbackFor = { NotAuthorizeException.class, CustomerNotFoundException.class,
			NodeNotFoundException.class, BaseException.class })
	@RequestMapping(value = "/api/job/delete", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('Customer User','Customer Admin')")
	public ApiResponseDTO deleteJob(
			@ApiParam(name = "Job task object", value = "Job task object that needs to be deleted", required = true) @Valid @RequestBody JobDeleteRequestDTO jobTaskDTO,
			HttpServletRequest request)
			throws NotAuthorizeException, JobNotFoundException, BaseException, ValidationException {
		logger.debug("Started JobsController.deleteJob() ");
		String message = null;
		String statusCode = HttpStatus.OK.toString();
		String uri = null;
		String jobName = null;
		// verify user associated with requested appliance
		String loginCustomerId = getLoggedInUserCustomerID(SecurityContextHolder.getContext().getAuthentication());
		String roleName = getLoggedInUserRoleName(SecurityContextHolder.getContext().getAuthentication());
		String loggedInEmail = getLoggedInUserEmail(SecurityContextHolder.getContext().getAuthentication());
		String userQAuthToken = getLoggedInUserAuthToken(SecurityContextHolder.getContext().getAuthentication());
		User loggedInUser = userService.findUserByEmail(loggedInEmail);

		// User is associated with appliance
		if (!isCustomerAllowed(jobTaskDTO.getSerialNumber(), loginCustomerId)) {
			logger.info("Appliance  {}  not owned by user {}", jobTaskDTO.getSerialNumber(), loggedInUser.getName());
			throw new NotAuthorizeException(environment.getProperty("job.delete.operation.notallowed"));
		}

		Job job = createJobTaskDTOToJob(jobTaskDTO);

		// Customer admin can delete jobs created by himself and his
		// organization user jobs
		if (roleName.equalsIgnoreCase(ServicesConstants.CUSTOMER_ADMIN) && !isCustomerAllowedForJob(job, loginCustomerId)) {
			logger.info("Job {} not owned by user {}", job.getName(), loggedInUser.getName());
			throw new NotAuthorizeException(environment.getProperty("job.delete.operation.notallowed"));
		}

		// if user role is customer_user verify job owned by himself
		if (roleName.equalsIgnoreCase(ServicesConstants.CUSTOMER_USER)
				&& !isUserAllowedForJob(job, "" + loggedInUser.getId())) {
			logger.info("Job {} not owned by user {}", job.getName(), loggedInUser.getName());
			throw new NotAuthorizeException(environment.getProperty("job.delete.operation.notallowed"));
		}

		if (jobTaskDTO.getJobApiType() != null || !jobTaskDTO.getJobApiType().isEmpty()) {
			if (jobTaskDTO.getJobApiType().equalsIgnoreCase(MARATHON.toString())) {
				uri = formatString(marathonUrl, MARATHON_V2_APPS);
				jobName = CHARACTER_SINGLE_FRONT_SLASH + jobTaskDTO.getAppId();
			} else if (jobTaskDTO.getJobApiType().equalsIgnoreCase(CHRONOS.toString())) {
				uri = formatString(chronosUrlPrefix, CHRONOS_SCHEDULER_JOB);
				jobName = jobTaskDTO.getAppId();
			}
			try {
                Node node = nodeService.findNodeBySerialNumber(jobTaskDTO.getSerialNumber());
                String nodeName=null;
                if(node != null){
                	nodeName = node.getName();
                }
				job.setIsDeleted(YES.toString());
				jobService.removeJob(job);

				boolean isDeletedSuccess = false;

				WSServerJobResponse wsServerJobResponse = JobsClient.deleteJob(websocketServerUrl,
						jobTaskDTO.getSerialNumber(), jobName, uri, loggedInUser.getUserName(), userQAuthToken);
				JobRetVal jobRetVal = JobsClient.convertWebsocketServerResponse(wsServerJobResponse,
						CHRONOS.toString());
				if (jobRetVal != null && jobRetVal.getStatus() != null
						&& jobRetVal.getStatus().equalsIgnoreCase(statusCode)
						|| jobRetVal != null && jobRetVal.getStatus() != null
								&& jobRetVal.getStatus().equalsIgnoreCase(HttpStatus.NO_CONTENT.toString())) {
					message = formatString(jobTaskDTO.getAppId(), JOB_DELETE_SUCCESS);
					isDeletedSuccess = true;
				} else {
					isDeletedSuccess = false;
					message = formatString(jobTaskDTO.getAppId(), JOB_NOT_DELETE_SUCCESS);
					statusCode = HttpStatus.ACCEPTED.toString();
					job.setIsDeleted(NO.toString());
					jobService.updateJob(job);

				}
				if (isDeletedSuccess == true) {
					jobService.deleteJob(job);
				}
				logger.info(environment.getProperty("job.delete.sucess"),loggedInUser.getUserName(),nodeName);
			} catch (JobNotFoundException e) {
				logger.error(e.getMessage());
				throw new JobNotFoundException(e.getMessage());
			}
		} else {
			logger.error("Deleting a job failed with message {} " + environment.getProperty("job.api.notnull"));
			throw new ValidationException(environment.getProperty("job.api.notnull"));
		}
		ApiResponseDTO response = new ApiResponseDTO(message, Integer.parseInt(statusCode));
		logger.debug("Started JobsController.deleteJob() ");
		return response;
	}

	/**
	 * This API is allowed to post the new marathon job on a given appliance and
	 * is associated with requested user API allowed for Customer Admin and
	 * Customer User
	 * 
	 * @param dto
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 * @throws BaseException
	 * @throws ValidationException
	 */
	@Path("/api/job/save")
	@POST
	@ApiOperation(value = "Post the new marathon job", notes = "User can post a new marathon job on his appliance.User with role Customer Admin or Customer User is allowed.", response = ApiResponseDTO.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "The marathon job has been posted succesfully"),
			@ApiResponse(code = 409, message = "Marathon job already exists"),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 503, message = "Service unreachable") })
	@RequestMapping(value = "/api/job/save", method = RequestMethod.POST)
	@Transactional(rollbackFor = { NotAuthorizeException.class, JsonProcessingException.class, BaseException.class })
	@PreAuthorize("hasAnyAuthority('Customer User','Customer Admin')")
	public ApiResponseDTO getMarathonResubmissionJobApi(
			@ApiParam(name = "Job object", value = "The job object that needs to be saved", required = true) @Valid @RequestBody JobRequestDataDTO jobRequestDTO,
			HttpServletRequest request)
			throws NotAuthorizeException, JsonProcessingException, BaseException, ValidationException {
		logger.debug("Started JobsController.getResubmissionJobApi() ");
		ApiResponseDTO response = null;
		String message = null;
		String statusCode = HttpStatus.OK.toString();
		
		if(jobRequestDTO.getSpaceParameters() !=null){
           if(jobRequestDTO.getSpaceParameters().trim().length() > 256){
             throw new ValidationException(environment.getProperty("spaceparameterlength.marthon.validation")); 
               }
           }
		for (Parameters parameters : jobRequestDTO.getDropDownValues()) {
			if (parameters != null) {

				if (parameters.getKey().equals("ContainerPort") && (!parameters.getValue().isEmpty())) {

					if( !StringUtils.isNumeric(parameters.getValue())){
						logger.debug("ContainerPortValidator:" + environment.getProperty("job.containerport.notnumeric"));
						throw new ValidationException(environment.getProperty("job.containerport.notnumeric"));
						      }
					
					if (Integer.parseInt(parameters.getValue()) > 65535
							|| Integer.parseInt(parameters.getValue()) < 1024) {
						logger.debug("ContainerPortValidator:" + environment.getProperty("job.marathon.containerport"));
						throw new ValidationException(environment.getProperty("job.marathon.containerport"));
					}

				}
				if (parameters.getKey().equals("HostPort") && (!parameters.getValue().isEmpty())) {

					if( !StringUtils.isNumeric(parameters.getValue())){
						logger.debug("HostPortValidator:" + environment.getProperty("job.hostport.notnumeric"));
						throw new ValidationException(environment.getProperty("job.hostport.notnumeric"));
						      }
					if (Integer.parseInt(parameters.getValue()) > 65535
							|| Integer.parseInt(parameters.getValue()) < 1024) {
						logger.debug("HostPortValidator:" + environment.getProperty("job.marathon.hostport"));
						throw new ValidationException(environment.getProperty("job.marathon.hostport"));
					}

				}
			}

		}
		for (Parameters parameters : jobRequestDTO.getParameters()) {
			if(parameters != null && parameters.getKey().equalsIgnoreCase("user")){
				throw new ValidationException(environment.getProperty("key.not.allowed"));
			}
			
		}
		String loginCustomerId = getLoggedInUserCustomerID(SecurityContextHolder.getContext().getAuthentication());
		// Verify user is associated with requested appliance
		if (isCustomerAllowed(jobRequestDTO.getSerialNumber(), loginCustomerId) == false) {
			throw new NotAuthorizeException(environment.getProperty("job.save.operation.notallowed"));
		}

		String loggedInEmail = getLoggedInUserEmail(SecurityContextHolder.getContext().getAuthentication());
		User loggedInUser = userService.findUserByEmail(loggedInEmail);
		String userQAuthToken = loggedInUser.getqAuthToken();
		Node node = nodeService.findNodeBySerialNumber(jobRequestDTO.getSerialNumber());

		if (!JobsClient.getWebSocketStatus(websocketServerUrl, jobRequestDTO.getSerialNumber(),
				loggedInUser.getUserName(), userQAuthToken)) {
			statusCode = HttpStatus.FORBIDDEN.toString();
			response = new ApiResponseDTO("FORBIDDEN", Integer.parseInt(statusCode));
			return response;
		}

		RequestMarathonJobDTO marathonJson = JobUtil.createMarathonJobJson(jobRequestDTO, node, loggedInUser,
				tomcatUri);
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		String jsonInString = mapper.writeValueAsString(marathonJson);
		logger.debug(marathonJson.getId() + "----" + marathonJson.getContainer().getDocker().getImage());

		JsonPath jsonPath = new JsonPath(jsonInString);
		logger.debug("Marathon job creation json from user input {}", jsonPath.prettyPrint());

		String uri = formatString(marathonUrl, MARATHON_V2_APPS);

		if (node != null) {
			Job job = new Job();
			if(StringUtils.containsWhitespace(marathonJson.getId()) == true){
				throw new ValidationException(environment.getProperty("job.name.notallow.space"));
			}
			job.setName(marathonJson.getId());
			job.setServiceType(MARATHON.toString());
			job.setIsDeleted(NO.toString());
			job.setNode(node);
			job.setCustomer(loggedInUser.getCustomer());
			job.setUser(loggedInUser);

			Job jobObje = jobService.findJobByName(job);
			if (jobObje != null && jobObje.getName() != null) {
				message = formatString(jobObje.getName(), JOB_IS_ALREADY_EXIST);
				statusCode = HttpStatus.CONFLICT.toString();
				response = new ApiResponseDTO(message, Integer.parseInt(statusCode));
				return response;
			} else {
				job.setRequestJson(jsonInString);
				this.createJobInputArgs(marathonJson, job);
				job.setCreatedDate(new Date());
				jobService.createJob(job);
				message = formatString(marathonJson.getId(), JOB_POST_SUCCESS);
				logger.info(environment.getProperty("job.launch.sucess"),loggedInUser.getUserName(),node.getName());
			}

			boolean jobPostSuccess = false;

			WSServerJobResponse wsServerJobResponse = JobsClient.getResubmissionJobApi(websocketServerUrl, jsonInString,
					jobRequestDTO.getSerialNumber(), uri, loggedInUser.getUserName(), userQAuthToken);
			JobRetVal jobRetVal = JobsClient.convertWebsocketServerResponse(wsServerJobResponse, MARATHON.toString());
			if (jobRetVal != null && jobRetVal.getStatus() != null
					&& jobRetVal.getStatus().equals(HttpStatus.CREATED.toString())) {
				jobPostSuccess = true;
			} else {
				if (jobRetVal != null && jobPostSuccess == false) {
					message = jobRetVal.getMessage();
					statusCode = jobRetVal.getStatus();
					this.deleteJob(job);
					logger.info(environment.getProperty("job.launch.failed"),loggedInUser.getUserName(),node.getName(),statusCode);
				}
			}
		}

		logger.debug("Ended JobsController.getResubmissionJobApi()");
		response = new ApiResponseDTO(message, Integer.parseInt(statusCode));
		return response;
	}

	private void deleteJob(Job job) throws JobNotFoundException {
		Job jobObject = jobService.findJobByName(job);
		if (jobObject != null) {
			jobService.deleteJob(jobObject);
		}
	}

	private void createJobInputArgs(RequestMarathonJobDTO jobRequestMarathonDTO, Job job) {
		if (jobRequestMarathonDTO != null && job != null) {
			if (jobRequestMarathonDTO.getArgs() != null) {
				job.setApplicationParametrs(String.join(" ", jobRequestMarathonDTO.getArgs()));
			}
			if (jobRequestMarathonDTO.getContainer() != null
					&& jobRequestMarathonDTO.getContainer().getDocker() != null) {
				job.setForcePullImage(jobRequestMarathonDTO.getContainer().getDocker().getForcePullImage());
				try {
					JobsClient.createParameterToString(jobRequestMarathonDTO.getContainer().getDocker(), job);
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			}
			if (jobRequestMarathonDTO.getLabels() != null) {
				job.setOwnerName(jobRequestMarathonDTO.getLabels().getOwnerName());
				job.setSerialNumber(jobRequestMarathonDTO.getLabels().getSerialNumber());
				job.setUniqueId(jobRequestMarathonDTO.getLabels().getLabel());
			}

		}
	}

	/**
	 * This API is allowed to post the new chronos job on a given appliance and
	 * is associated with requested user API allowed for Customer Admin and
	 * Customer User
	 * 
	 * @param dto
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 * @throws BaseException
	 * @throws ValidationException
	 */
	@Path("/api/job/chronos/save")
	@POST
	@ApiOperation(value = "Post the new chronos jobs", notes = "User can post a new chronos job on his appliance.User with role Customer Admin or Customer User is allowed.", response = ApiResponseDTO.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "The chronos jobs have been posted succesfully"),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 503, message = "Service unreachable") })
	@RequestMapping(value = "/api/job/chronos/save", method = RequestMethod.POST)
	@Transactional(rollbackFor = { NotAuthorizeException.class, CustomerNotFoundException.class,
			ClusterNotFoundException.class, NodeNotFoundException.class, BaseException.class })
	@PreAuthorize("hasAnyAuthority('Customer User','Customer Admin')")
	public ApiResponseDTO getChronosResubmissionJobApi(
			@ApiParam(name = "Chronos job object", value = "The chronos job object that needs to be created", required = true) @Valid @RequestBody JobRequestDataDTO jobRequestDTO,
			HttpServletRequest request)
			throws JsonParseException, JsonMappingException, IOException, BaseException, ValidationException {
		logger.debug("Started JobsController.getChronosResubmissionJobApi() ");
		ApiResponseDTO response = null;
		String message = null;
		String statusCode = HttpStatus.OK.toString();
		if(jobRequestDTO.getSpaceParameters() == null || jobRequestDTO.getSpaceParameters().isEmpty()){
			throw new ValidationException(environment.getProperty("spaceparameter.validation"));
		}else if(jobRequestDTO.getSpaceParameters().trim().length() > 256){
			throw new ValidationException(environment.getProperty("spaceparameterlength.chronos.validation")); 
		}
		for (Parameters parameters : jobRequestDTO.getDropDownValues()) {
			if (parameters != null) {

				if (parameters.getKey().equals("ContainerPort") && (!parameters.getValue().isEmpty())) {

					if( !StringUtils.isNumeric(parameters.getValue())){
						logger.debug("ContainerPortValidator:" + environment.getProperty("job.containerport.notnumeric"));
						throw new ValidationException(environment.getProperty("job.containerport.notnumeric"));
						      }
					if (Integer.parseInt(parameters.getValue()) > 65535
							|| Integer.parseInt(parameters.getValue()) < 1024) {
						logger.debug("ContainerPortValidater:" + environment.getProperty("job.chronos.containerport"));
						throw new ValidationException(environment.getProperty("job.chronos.containerport"));
					}

				}
				if (parameters.getKey().equals("HostPort") && (!parameters.getValue().isEmpty())) {

					if( !StringUtils.isNumeric(parameters.getValue())){
						logger.debug("HostPortValidator:" + environment.getProperty("job.hostport.notnumeric"));
						throw new ValidationException(environment.getProperty("job.hostport.notnumeric"));
						      }
					if (Integer.parseInt(parameters.getValue()) > 65535
							|| Integer.parseInt(parameters.getValue()) < 1024) {
						logger.debug("HostPortValidator:" + environment.getProperty("job.chronos.hostport"));
						throw new ValidationException(environment.getProperty("job.chronos.hostport"));

					}

				}
			}

		}
		for (Parameters parameters : jobRequestDTO.getParameters()) {
			if(parameters != null && parameters.getKey().equalsIgnoreCase("user")){
				throw new ValidationException(environment.getProperty("key.not.allowed"));
			}
			
		}
		String loginCustomerId = getLoggedInUserCustomerID(SecurityContextHolder.getContext().getAuthentication());
		// Verify user is associated with requested appliance
		if (isCustomerAllowed(jobRequestDTO.getSerialNumber(), loginCustomerId) == false) {
			throw new NotAuthorizeException(environment.getProperty("job.save.operation.notallowed"));
		}
		String loggedInEmail = getLoggedInUserEmail(SecurityContextHolder.getContext().getAuthentication());
		User loggedInUser = userService.findUserByEmail(loggedInEmail);
		String userQAuthToken = loggedInUser.getqAuthToken();

		Node node = nodeService.findNodeBySerialNumber(jobRequestDTO.getSerialNumber());

		if (!JobsClient.getWebSocketStatus(websocketServerUrl, jobRequestDTO.getSerialNumber(),
				loggedInUser.getUserName(), userQAuthToken)) {
			statusCode = HttpStatus.FORBIDDEN.toString();
			response = new ApiResponseDTO("FORBIDDEN", Integer.parseInt(statusCode));
			return response;
		}

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		RequestChronosJobDTO chronosRequestDto = JobUtil.createChronosJobJson(jobRequestDTO, node, loggedInUser,
				tomcatUri);

		logger.debug(chronosRequestDto.getName() + "----" + chronosRequestDto.getContainer().getImage());

		String jsonString = mapper.writeValueAsString(chronosRequestDto);
		JsonPath jsonPath = new JsonPath(jsonString);
		logger.debug("Chronos job creation json from user input {}", jsonPath.prettyPrint());
		if (node != null) {
			Job job = new Job();
			if(StringUtils.containsWhitespace(chronosRequestDto.getName()) == true){
				throw new ValidationException(environment.getProperty("job.name.notallow.space"));
			}
			job.setName(chronosRequestDto.getName());
			job.setServiceType(CHRONOS.toString());
			job.setIsDeleted(NO.toString());
			job.setNode(node);
			job.setCustomer(loggedInUser.getCustomer());
			job.setUser(loggedInUser);

			Job jobObje = jobService.findJobByName(job);
			if (jobObje != null && jobObje.getName() != null) {
				message = formatString(jobObje.getName(), JOB_IS_ALREADY_EXIST);
				statusCode = HttpStatus.CONFLICT.toString();
				response = new ApiResponseDTO(message, Integer.parseInt(statusCode));
				return response;
			} else {
				job.setRequestJson(jsonString);
				this.createJobChronosInputArgs(chronosRequestDto, job);
				job.setCreatedDate(new Date());
				jobService.createJob(job);
				message = formatString(chronosRequestDto.getName(), JOB_POST_SUCCESS);
				logger.info(environment.getProperty("job.launch.sucess"),loggedInUser.getUserName(),node.getName());
			}

			boolean jobPostSuccess = false;

			JobRetVal jobRetVal = getChronosGraphCsvData(jobRequestDTO.getSerialNumber(), loggedInUser);
			if (jobRetVal != null && jobRetVal.getStatus() != null
					&& jobRetVal.getStatus().equals(HttpStatus.OK.toString())) {
				WSServerJobResponse wsServerJobResponse2 = JobsClient.getResubmissionJobApi(websocketServerUrl,
						jsonString, jobRequestDTO.getSerialNumber(),
						formatString(chronosUrlPrefix, CHRONOS_SCHEDULER_ISO8601), loggedInUser.getUserName(),
						userQAuthToken);
				JobRetVal jobRetVal2 = JobsClient.convertWebsocketServerResponse(wsServerJobResponse2,
						CHRONOS.toString());
				if (jobRetVal2 != null && jobRetVal2.getStatus() != null
						&& jobRetVal2.getStatus().equals(HttpStatus.NO_CONTENT.toString())) {
					jobPostSuccess = true;
				}
			} else {
				if (jobRetVal != null && jobPostSuccess == false) {
					message = jobRetVal.getMessage();
					statusCode = jobRetVal.getStatus();
					this.deleteJob(job);
					logger.info(environment.getProperty("job.launch.failed"),loggedInUser.getUserName(),node.getName(),statusCode);
				}
			}
		}
		logger.debug("Ended JobsController.getChronosResubmissionJobApi()");
		response = new ApiResponseDTO(message, Integer.parseInt(statusCode));
		return response;
	}

	private void createJobChronosInputArgs(RequestChronosJobDTO requestChronosJobDTO, Job job) {
		if (requestChronosJobDTO != null && job != null) {
			if (requestChronosJobDTO.getContainer() != null) {
				job.setForcePullImage(requestChronosJobDTO.getContainer().isForcePullImage());
				job.setApplicationParametrs(requestChronosJobDTO.getCommand());
				try {
					List<String> nfsString = new ArrayList<String>();
					List<String> localValumeString = new ArrayList<String>();
					List<String> argumentString = new ArrayList<String>();
					for (Parameters parameter : requestChronosJobDTO.getContainer().getParameters()) {
						if (parameter.getKey().equalsIgnoreCase(LABEL) == true) {
							job.setUniqueId(parameter.getValue());
						}
						if (!parameter.getKey().equals(VOLUME_DRIVER) && !parameter.getKey().equals(VOLUMES_FROM)
								&& !parameter.getKey().equals(DEVICE) && !parameter.getKey().equals(USER)) {
							if (parameter.getKey() != null && parameter.getKey().equals(VOLUME)) {
								if (parameter.getValue().startsWith(CHARACTER_SINGLE_FRONT_SLASH)) {
									localValumeString.add(parameter.getValue());
								} else {
									nfsString.add(parameter.getValue());
								}
							} else if (parameter.getKey() != null && parameter.getValue() != null
									&& !parameter.getKey().equals(VOLUME) && !parameter.getKey().equals(LABEL)) {
								argumentString
										.add(KEY + parameter.getKey() + CHARACTER_HASH + VALUE + parameter.getValue());
							}
						}
					}
					job.setSchedulerParameters(String.join(CHARACTER_COMMA, argumentString));
					job.setNfsVolume(String.join(CHARACTER_HASH, nfsString));
					job.setLocalVolume(String.join(CHARACTER_HASH, localValumeString));
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
				job.setOwnerName(requestChronosJobDTO.getOwnerName());

			}

		}
	}

	private JobRetVal getChronosGraphCsvData(String serialNumber, User user) throws BaseException {
		WSServerJobResponse wsServerJobResponse = JobsClient.getChronosGraphCsvApi(websocketServerUrl, serialNumber,
				formatString(chronosUrlPrefix, CHRONOS_SCHEDULER_GRAPH_CSV), user.getUserName(), user.getqAuthToken());
		JobRetVal jobRetVal = JobsClient.convertWebsocketServerResponse(wsServerJobResponse, CHRONOS.toString());
		return jobRetVal;
	}

	/**
	 * Method returns list of jobs associated to the called user (User
	 * organization jobs list)
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Path("/api/jobs")
	@GET
	@ApiOperation(value = "Get the list of jobs by Customer", notes = "Logged in user can list all of his jobs.User with role Customer Admin or Customer User is allowed.", response = ResponseDTO.class, responseContainer = "Job List")
	@ApiResponses({ @ApiResponse(code = 200, message = "Job details for the given Customer have been fetched"), })
	@RequestMapping(value = "/api/jobs", method = RequestMethod.GET)
	@Transactional(rollbackFor = { NotAuthorizeException.class, CustomerNotFoundException.class,
			UserExistsException.class, ClusterNotFoundException.class, NodeNotFoundException.class,
			BaseException.class })
	@PreAuthorize("hasAnyAuthority('Customer User','Customer Admin')")
	public ResponseDTO jobsList(HttpServletRequest request) throws Exception {
		logger.debug("Started JobsController.jobsList() ");
		ResponseDTO response = new ResponseDTO();

		String loginCustomerId = getLoggedInUserCustomerID(SecurityContextHolder.getContext().getAuthentication());
		Job job = new Job();
		job.setIsDeleted(NO.toString());

		List<Job> jobList = jobService.findCustomerJobs(loginCustomerId);
		List<JobTaskDTO> jobTaskDataList = new LinkedList<JobTaskDTO>();
		if (jobList != null && !jobList.isEmpty()) {
			try {
				for (Job jobObj : jobList) {
					if (jobObj != null) {
						jobTaskDataList.add(createJobToJobTaskDTO(jobObj));
					}
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		response.setData(jobTaskDataList);
		response.setMessage("Retrieved");
		response.setStatusCode(200);
		logger.debug("Ended JobsController.jobsList() ");
		return response;
	}

	private boolean isClusterAccessable(String clusterID, String customerID) {
		try {
			Cluster cluster = clusterService.findCluster(clusterID, customerID);
			if (cluster != null)
				return true;
		} catch (NumberFormatException | ClusterNotFoundException e) {
			logger.debug("Cluster is not avilable for the given cluster and customer");
		}

		return false;
	}

	/**
	 * This API Get the list of cluster jobs by Customer User or Customer Admin
	 * if is authorized to access given cluster
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Path("/api/cluster/jobs/{clusterId}")
	@GET
	@ApiOperation(value = "Get the list of cluster jobs by Customer", notes = "Logged in user can list jobs by clusterId.User with role Customer Admin or Customer User is allowed.", response = ResponseDTO.class, responseContainer = "Job List")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Job details for the given Customer and cluster have been fetched"), })
	@RequestMapping(value = "/api/cluster/jobs/{clusterId}", method = RequestMethod.GET)
	@Transactional(rollbackFor = { NotAuthorizeException.class, ValidationException.class,
			CustomerNotFoundException.class, UserExistsException.class, ClusterNotFoundException.class,
			NodeNotFoundException.class, BaseException.class })
	@PreAuthorize("hasAnyAuthority('Customer User','Customer Admin')")
	public ResponseDTO clusterJobsList(@PathVariable("clusterId") String clusterId, HttpServletRequest request)
			throws ClusterNotFoundException, ValidationException, Exception {
		logger.debug("Started JobsController.clusterJobsList() ");

		if (clusterId != null && !StringUtils.isNumeric(clusterId)) {
			new ValidationException("ClusterID should be numeric and should not be null");
		}

		String loggedInCustomerID = getLoggedInUserCustomerID(SecurityContextHolder.getContext().getAuthentication());
		if (!isClusterAccessable(clusterId, loggedInCustomerID)) {
			throw new NotAuthorizeException("User not authorized to access given cluster ");
		}
		ResponseDTO response = new ResponseDTO();
		String loggedInEmail = getLoggedInUserEmail(SecurityContextHolder.getContext().getAuthentication());
		User loggedInUser = userService.findUserByEmail(loggedInEmail);
		Customer customer = new Customer();
		customer.setId(loggedInUser.getCustomer().getId());
		Job job = new Job();
		job.setIsDeleted(NO.toString());
		Cluster cluster = new Cluster();
		cluster.setId(Integer.parseInt(clusterId));
		cluster.setCustomer(customer);

		List<Job> jobList = jobService.findAllJobsByCluster(job, cluster);
		List<JobTaskDTO> jobTaskDataList = new LinkedList<JobTaskDTO>();
		if (jobList != null && !jobList.isEmpty()) {
			try {
				for (Job jobObj : jobList) {
					if (jobObj != null) {
						jobTaskDataList.add(createJobToJobTaskDTO(jobObj));
					}
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		response.setData(jobTaskDataList);
		response.setMessage("Retrieved");
		response.setStatusCode(200);
		logger.debug("Ended JobsController.clusterJobsList() ");
		return response;
	}

	boolean isAcceesbleForClusterAndNode(String customerId, String clusterName, String nodeName) {
		try {
			Node node = nodeService.findNodeByNameAndCluster(nodeName, clusterName, Integer.parseInt(customerId));
			if (node != null)
				return true;
		} catch (NumberFormatException | NodeNotFoundException e) {
			logger.debug("user doent have access to the requested cluster {} and node ", clusterName, nodeName);
		}

		return false;
	}

	/**
	 * This API Get the list of jobs by Customer User or Customer Admin if is
	 * authorized to access given cluster and node
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Path("/api/node/jobs/{nodeName}/{clusterName}")
	@GET
	@ApiOperation(value = "Get the list of cluster jobs by Customer", notes = "User can get list of jobs by cluster and node under his organization.User with role Customer Admin or Customer User is allowed.", response = ResponseDTO.class, responseContainer = "Job List")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Job details for the given Customer and cluster have been fetched"), })
	@RequestMapping(value = "/api/node/jobs/{nodeName}/{clusterName}", method = RequestMethod.GET)
	@Transactional(rollbackFor = { NotAuthorizeException.class, CustomerNotFoundException.class,
			UserExistsException.class, ClusterNotFoundException.class, NodeNotFoundException.class,
			BaseException.class })
	@PreAuthorize("hasAnyAuthority('Customer User','Customer Admin')")
	public ResponseDTO nodeJobsList(@PathVariable("nodeName") String nodeName,
			@PathVariable("clusterName") String clusterName, HttpServletRequest request) throws Exception {
		logger.debug("Started JobsController.nodeJobsList() ");
		ResponseDTO response = new ResponseDTO();

		if (StringUtils.isEmpty(nodeName) || StringUtils.isEmpty(clusterName)) {
			throw new ValidationException("Naode name or ClusterName should not be null ");
		}
		String loggedInEmail = getLoggedInUserEmail(SecurityContextHolder.getContext().getAuthentication());
		String cutomerID = getLoggedInUserCustomerID(SecurityContextHolder.getContext().getAuthentication());

		if (!isAcceesbleForClusterAndNode(cutomerID, clusterName, nodeName)) {
			throw new NotAuthorizeException("User not authorized to access given cluster or node ");
		}

		User loggedInUser = userService.findUserByEmail(loggedInEmail);
		Customer customer = new Customer();
		customer.setId(loggedInUser.getCustomer().getId());

		Job job = new Job();
		job.setIsDeleted(NO.toString());

		List<JobTaskDTO> jobTaskDataList = null;
		if (nodeName != null && !nodeName.isEmpty() || clusterName != null && !clusterName.isEmpty()) {

			Node node = nodeService.findNodeByNameAndCluster(nodeName, clusterName, customer.getId());
			job.setNode(node);
			List<Job> jobList = jobService.findAllJobsByNode(job);
			jobTaskDataList = new LinkedList<JobTaskDTO>();
			if (jobList != null && !jobList.isEmpty()) {
				try {
					for (Job jobObj : jobList) {
						if (jobObj != null) {
							jobTaskDataList.add(createJobToJobTaskDTO(jobObj));
						}
					}
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			}
		} else {
			logger.error("Jobs list failed with message {} " + environment.getProperty("nodename.clusterName.notnull"));
			throw new ValidationException(environment.getProperty("nodename.clusterName.notnull"));
		}
		response.setData(jobTaskDataList);
		response.setMessage("Retrieved");
		response.setStatusCode(200);
		logger.debug("Ended JobsController.nodeJobsList() ");
		return response;
	}

	private JobTaskDTO createJobToJobTaskDTO(Job job) throws BaseException {
		logger.debug("JobsController.createJobToJobTaskDTO()");

		JobTaskDTO jobTaskData = new JobTaskDTO();
		jobTaskData.setId(job.getId());
		jobTaskData.setAppId(job.getName());
		jobTaskData.setOwner(job.getOwnerName() != null && !job.getOwnerName().isEmpty() ? job.getOwnerName()
				: CHARACTER_DOUBLE_HYPON);

		NodeDTO nodeDTO = new NodeDTO();
		if (job.getNode() != null) {
			NvidiaUtil.convertNodeToNodeDTO(job.getNode(), nodeDTO, job.getNode().getCluster());
			jobTaskData.setSerialNumber(job.getNode().getSerialNumber());
			jobTaskData.setNodeDato(nodeDTO);
		}

		jobTaskData.setJobApiType(job.getServiceType());

		if (StringUtils.isNotBlank(job.getServiceHelth())) {
			jobTaskData.setStartedAt(job.getServiceHelth());
			// return jobTaskData; commented due to the host and masosURL not
			// received to UI
		}

		jobTaskData.setHost(job.getHost());
		jobTaskData.setSlaveId(job.getSlaveId());
		jobTaskData.setState(job.getState());
		jobTaskData.setTaskId(job.getTaskId());
		jobTaskData.setVersion(job.getVersion());
		String text = job.getMessage();
		text = text != null
				? text.replace(CHARACTER_NEWLINE, CHARACTER_EMPTY).replace(CHARACTER_DOUBLE_QUOTE, CHARACTER_EMPTY)
				: CHARACTER_EMPTY;
		jobTaskData.setMessage(text.trim());
		jobTaskData.setTimestamp(job.getTimestamp());
		jobTaskData.setSpaceSeparatedParam(job.getApplicationParametrs());
		jobTaskData.setNfsVolume(job.getNfsVolume());
		jobTaskData.setLocalVolume(job.getLocalVolume());
		jobTaskData.setForcePullImage(job.getForcePullImage());
		jobTaskData.setHostPort(job.getHostPorts());
		jobTaskData.setContainerPort(job.getContainerPorts());
		jobTaskData.setArguments(job.getSchedulerParameters());
		jobTaskData.setDockerUri(dockerUri);
		jobTaskData.setImage(job.getImage());
		jobTaskData.setUniquename(job.getUniqueId());
		jobTaskData.setIpAddresses(job.getHost());
		jobTaskData.setSerialNumber(job.getSerialNumber());
		jobTaskData.setStartedAt(job.getTimeStarted() != null
				? DateUtil.format(job.getTimeStarted(), DateUtil.YYYY_MM_DD_DATE_TIME_PATTERN)
				: CHARACTER_DOUBLE_HYPON);
		jobTaskData.setStatus(
				job.getStatus() != null && !job.getStatus().isEmpty() ? job.getStatus() : CHARACTER_DOUBLE_HYPON);
		jobTaskData.setMessosSlaveUri(job.getMesosSlaveName());
		jobTaskData.setContainerName(job.getDockerContainerName());
		if (job.getPorts() != null && !job.getPorts().isEmpty()) {
			List<String> portList = new ArrayList<String>(Arrays.asList(job.getPorts().split(CHARACTER_COMMA)));
			jobTaskData.setPortList(portList);
		} else {
			List<String> portList = new ArrayList<String>();
			portList.add(CHARACTER_DOUBLE_HYPON);
			jobTaskData.setPortList(portList);
		}
		jobTaskData.setLongRunningJob(
				jobTaskData.getJobApiType() != null && jobTaskData.getJobApiType().equalsIgnoreCase(MARATHON.toString())
						? true : false);

		logger.debug("JobsController.createJobToJobTaskDTO()");
		return jobTaskData;
	}

	/**
	 * This API update node with EULA accepted value if is authorized to access
	 * given cluster and node
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Path("/api/eulaUpdated/{nodeName}/{clusterId}")
	@GET
	@ApiOperation(value = "Update the node table with EULA accepted value", notes = "User updates eula on node by node and cluster.User with role Customer Admin or Customer User is allowed.", response = EulaUpdatedOnNodeDTO.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Node has been updated with EULA accepted value"), })
	@Transactional(rollbackFor = { NodeNotFoundException.class, NotAuthorizeException.class,
			UserNotFoundException.class, BaseException.class })
	@RequestMapping(value = "/api/eulaUpdated/{nodeName}/{clusterId}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('Customer User','Customer Admin')")
	public ResponseDTO eulaAccepted(
			@ApiParam(name = "Node name", value = "The name of the node whose eula details have to be updated", required = true) @PathVariable("nodeName") String nodeName,
			@ApiParam(name = "Cluster Id", value = "The id of the cluster whose node has to be fetched", required = true) @PathVariable("clusterId") String clusterId,
			HttpServletRequest request)
			throws NodeNotFoundException, NotAuthorizeException, UserNotFoundException, ValidationException {
		logger.debug("eulaAccepted() method enter...");

		if (nodeName == null || nodeName.isEmpty() || clusterId == null || clusterId.isEmpty()) {
			logger.error(
					"Updating eula failed with message {} " + environment.getProperty("nodename.clusterid.notnull"));
			throw new ValidationException(environment.getProperty("nodename.clusterid.notnull"));
		}

		String message = null;
		String statusCode = HttpStatus.OK.toString();
		String loggedInCutomerID = getLoggedInUserCustomerID(SecurityContextHolder.getContext().getAuthentication());

		// verify user allowed for requested cluster and node
		if (!isClusterAccessable(clusterId, loggedInCutomerID)) {
			throw new NotAuthorizeException("User not authorized to access given cluster or node ");
		}

		String loggedInEmail = getLoggedInUserEmail(SecurityContextHolder.getContext().getAuthentication());
		User loggedInUser = userService.findUserByEmail(loggedInEmail);

		EulaUpdatedOnNodeDTO eulaUpdateOnNode = new EulaUpdatedOnNodeDTO();

		Node nodeObje = new Node();
		nodeObje.setName(nodeName);
		Cluster clusterObje = new Cluster();
		try {
			clusterObje.setId(Integer.parseInt(clusterId));
		} catch (NumberFormatException nfe) {
			logger.error("Updating eula failed with message {} " + nfe.getMessage());
			throw new ValidationException(environment.getProperty("input.invalid"));
		}
		Customer customerObje = new Customer();
		customerObje.setId(loggedInUser.getCustomer().getId());
		clusterObje.setCustomer(customerObje);
		nodeObje.setCluster(clusterObje);

		Node entity = nodeService.findNodeByNodeNameAndClusterId(nodeObje);
		if (entity != null) {
			entity.setEulaAccepted(EULAACCEPTED);
			nodeService.updateNodeEulaAccepted(entity);

			eulaUpdateOnNode.setEulaAccepted(entity.getEulaAccepted());
			message = EULAUPDATED;
		}
		ResponseDTO response = new ResponseDTO(message, Integer.parseInt(statusCode));
		response.setData(eulaUpdateOnNode);
		logger.debug("eulaAccepted() method exit...");
		return response;
	}

	@Path("/api/mesosurl/{uniqueId}")
	@GET
	@ApiOperation(value = "Get the peeler URL for the job", notes = "Get the peeler URL for the job", response = ResponseDTO.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Peeler URL constructed successfully"), })
	@Transactional(rollbackFor = { NotAuthorizeException.class, JobNotFoundException.class, BaseException.class })
	@RequestMapping(value = "/api/mesosurl/{uniqueId}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('Customer User','Customer Admin')")
	public ResponseDTO mesosURL(
			@ApiParam(name = "Job Id", value = "The id of the job whose job has to be fetched", required = true) @PathVariable("uniqueId") String uniqueId,
			HttpServletRequest request) throws NodeNotFoundException, NotAuthorizeException, JobNotFoundException,
			UserNotFoundException, ValidationException {
		logger.debug("mesosURL() method enter...");
		String message = "Peeler URL constructed successfully";
		String statusCode = HttpStatus.OK.toString();
		if (uniqueId == null || uniqueId.isEmpty() ) {
			logger.error("Job id should not be empty to fetch the mesos Peeler URL");
			throw new ValidationException(environment.getProperty("job.id.empty"));
		}

		String loggedInEmail = getLoggedInUserEmail(SecurityContextHolder.getContext().getAuthentication());
		String loggedInUserCustomer = getLoggedInUserCustomerID(SecurityContextHolder.getContext().getAuthentication());
		User loggedInUser = userService.findUserByEmail(loggedInEmail);
		Job job = jobService.findJobUiqueID(uniqueId);
		
		if (job == null) {
			logger.info("Job not found with unique id {} ", uniqueId);
			throw new JobNotFoundException("Job not found ");
		}
		
		String role = getLoggedInUserRoleName(SecurityContextHolder.getContext().getAuthentication());
		// Customer admin can delete jobs created by himself and his
		// organization user jobs
				if (role.equalsIgnoreCase(ServicesConstants.CUSTOMER_ADMIN) && !isCustomerAllowedForJob(job, loggedInUserCustomer)) {
					logger.info("Job {} not owned by user {}", job.getName(), loggedInUser.getName());
					throw new NotAuthorizeException("Unauthorized");
				}

				// if user role is customer_user verify job owned by himself
				if (role.equalsIgnoreCase(ServicesConstants.CUSTOMER_USER)
						&& !isUserAllowedForJob(job, "" + loggedInUser.getId())) {
					logger.info("Job {} not owned by user {}", job.getName(), loggedInUser.getName());
					throw new NotAuthorizeException("Unauthorized");
				}
				
				
				
				if(StringUtils.isEmpty(job.getMesosSlaveName()) || StringUtils.isEmpty(job.getHost())){
					logger.info("Job {} doesn't have messos slave dir ", job.getName());
					throw new JobNotFoundException("Internal error, try again later");
				}
				
				PeelerDTO peelerDTO = new PeelerDTO();
				String pailerURI=environment.getProperty("job.pailer.logs.uri") ;
				String pailerHtml=environment.getProperty("job.pailer.protocol")+"://"+job.getHost()+environment.getProperty("job.pailer.html") ;
				String pailerLogs=environment.getProperty("job.pailer.protocol")+"://"+job.getHost()+environment.getProperty("job.pailer.logs.port")+environment.getProperty("job.pailer.logs.uri") ;	
				
				peelerDTO.setPailerUri(pailerURI);
				peelerDTO.setHost(job.getHost());
				peelerDTO.setPailerHtml(pailerHtml);
				peelerDTO.setPailerLogs(pailerLogs);
				peelerDTO.setMessSlaveDir(job.getMesosSlaveName());
				peelerDTO.setDockerContainerName(job.getDockerContainerName());
		        ResponseDTO response = new ResponseDTO(message, Integer.parseInt(statusCode));
		        response.setData(peelerDTO);
				
		/*if (job == null) {
			logger.info("Job not found with unique id {} ", uniqueId);
			throw new JobNotFoundException("Job not found ");
		}
		if (role.equalsIgnoreCase(ServicesConstants.CUSTOMER_USER)) {
			if (job.getUser().getId() != loggedInUser.getgId()) {
				logger.info("user {} not authorized to see job {} ", loggedInEmail, uniqueId);
				throw new NotAuthorizeException("Not Authorized to perform this Action ");
			}
		}

		Node node = nodeService.findById(job.getNode().getId());
		PeelerDTO peelerDTO = new PeelerDTO();
		ResponseDTO response = new ResponseDTO(message, Integer.parseInt(statusCode));
		if (node.getIpAddress() != null && pattern.matcher(node.getIpAddress()).matches()
				&& job.getMesosSlaveName() != null && !job.getMesosSlaveName().isEmpty()) {
			peelerDTO.setUrl(environment.getProperty("job.pailer.protocol") + node.getIpAddress()
					+ environment.getProperty("job.pailer.page.uri"));
			peelerDTO.setLogUrl(environment.getProperty("job.pailer.protocol") + node.getIpAddress()
					+ environment.getProperty("job.pailer.logs.uri") + job.getMesosSlaveName());
			response.setData(peelerDTO);
		} else {
			if (node.getCloudStatus().equalsIgnoreCase(ServicesConstants.NODE_DISCONNCETD)) {
				response.setMessage("Since node is disconnected logs will not be able to shown at this time");
			} else {
				response.setMessage("Logs are not able to shown at this time");
			}

			response.setStatusCode(HttpStatus.BAD_REQUEST.value());
		}*/

		logger.debug("mesosURL() method exit...");
		return response;
	}
	
	
	
	/**
	 * 
	 * @param session Session token of the user 
	 * @param container Container name to validate accessibility
	 * @param request
	 * @return
	 * @throws NodeNotFoundException
	 * @throws NotAuthorizeException
	 * @throws JobNotFoundException
	 * @throws UserNotFoundException
	 * @throws ValidationException
	 */
	
	
	@Path("/api/validatepermissions")
	@GET
	@ApiOperation(value = "Validates user assosition for the ", notes = "Gets the container for the requested session ", response = ResponseDTO.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "OK"), })
	@RequestMapping(value = "/api/validatepermissions", method = RequestMethod.GET)
	public ApiResponseDTO isContainerAccessable(
			HttpServletRequest request,@RequestParam(value = "session", defaultValue = "") String session,@RequestParam(value = "container", defaultValue = "") String container
			) throws NodeNotFoundException, NotAuthorizeException, JobNotFoundException,
			UserNotFoundException, ValidationException {
		logger.debug("isContainerAccessable() method enter...");

		if (StringUtils.isEmpty(session) || StringUtils.isEmpty(container)) {
			 logger.error("Session key {} and container name {} is required", session, container);
			 return new ApiResponseDTO("Session key and container name is required" , Integer.parseInt(HttpStatus.FORBIDDEN.toString()));
		}
		 Authentication authintication=null;
		try {
			UserAuth authValue=  userAuthservice.findUserAuthByAuthToken(session);
			authintication = (AuthenticationWithToken) SerializationUtils.deserialize(authValue.getAuthenticationWithToken());
		} catch (UserAuthNotFoundException e) {
			 return new ApiResponseDTO("FORBIDDEN" , Integer.parseInt(HttpStatus.FORBIDDEN.toString()));
		}
		String loggedInEmail = getLoggedInUserEmail(authintication);
		
		String loginCustomerId = getLoggedInUserCustomerID(authintication);
		String roleName = getLoggedInUserRoleName(authintication);
		User loggedInUser = userService.findUserByEmail(loggedInEmail);
		logger.debug("User {} has access for the given container{}",loggedInUser.getName(),container);
		
		if (roleName.equalsIgnoreCase(ServicesConstants.CUSTOMER_ADMIN) && !isCustomerAllowedForJobContainer(container, loginCustomerId)) {
			logger.info("container {} not owned by user {}", container, loggedInUser.getName());
			 return new ApiResponseDTO("FORBIDDEN" , Integer.parseInt(HttpStatus.FORBIDDEN.toString()));
					
		}

		// if user role is customer_user verify job owned by himself
		if (roleName.equalsIgnoreCase(ServicesConstants.CUSTOMER_USER) && !isUserAllowedForJobContainer(container, "" + loggedInUser.getId())) {
		   logger.info("container {} not owned by user {}", container, loggedInUser.getName());
		return new ApiResponseDTO("FORBIDDEN" , Integer.parseInt(HttpStatus.FORBIDDEN.toString()));
		}
	
		return new ApiResponseDTO("Container is accessable",Integer.parseInt(HttpStatus.OK.toString()));
	}

	
	private boolean isCustomerAllowedForJobContainer(String container, String customerId) {
		try {
			logger.debug("Finding job for job container {} and customerid {} ", container, customerId);
			List<Job> dbJob = jobService.findJobByCustomerContainer(container, customerId);
			if (dbJob != null && dbJob.size() > 0)
				return true;
		} catch (JobNotFoundException e) {
			logger.debug("Job not found for the customer ");
		}
		return false;
	}
	
	
	private boolean isUserAllowedForJobContainer(String container, String customerId) {
		try {
			logger.debug("Finding job for job container {} and customerid {} ", container, customerId);
			List<Job> dbJob = jobService.findJobByCustomerContainer(container, customerId);
			if (dbJob != null && dbJob.size() > 0)
				return true;
		} catch (JobNotFoundException e) {
			logger.debug("Job not found for the customer ");
		}
		return false;
	}
	
	
	
   private Job createJobTaskDTOToJob(JobDeleteRequestDTO jobTaskDTO) {
		Job job = new Job();
		job.setId(jobTaskDTO.getId());
		job.setName(jobTaskDTO.getAppId());
		job.setUniqueId(jobTaskDTO.getUniquename());
		job.setServiceType(jobTaskDTO.getJobApiType());
		/*Node node = new Node();
		node.setId(jobTaskDTO.getNodeDato().getNodeId());
		job.setNode(node);*/
		return job;
	}
}
