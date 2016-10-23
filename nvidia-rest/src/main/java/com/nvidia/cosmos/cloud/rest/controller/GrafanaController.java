package com.nvidia.cosmos.cloud.rest.controller;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.nvidia.cosmos.cloud.dtos.ResponseDTO;
import com.nvidia.cosmos.cloud.exceptions.BaseException;
import com.nvidia.cosmos.cloud.exceptions.ClusterNotFoundException;
import com.nvidia.cosmos.cloud.exceptions.NodeNotFoundException;
import com.nvidia.cosmos.cloud.exceptions.UserAuthNotFoundException;
import com.nvidia.cosmos.cloud.rest.exception.NotAuthorizeException;
import com.nvidia.cosmos.cloud.services.cluster.model.Cluster;
import com.nvidia.cosmos.cloud.services.cluster.service.ClusterService;
import com.nvidia.cosmos.cloud.services.customer.model.Customer;
import com.nvidia.cosmos.cloud.services.customer.service.CustomerService;
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

@RestController
public class GrafanaController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(GrafanaController.class);

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
	CustomerService customerService;
	
	UserService userService;
	
	String grafanaUrl=null;
	
	private UserAuthService userAuthservice;

	/**
	 * 
	 */
	public GrafanaController() {
		clusterService = factory.getClusterService();
		nodeService = factory.getNodeService();
		customerService = factory.getCustomerService();
		userService=factory.getUserService();
		userAuthservice = factory.getUserAuthService();
	}
	
	
	

	/**
	 * RBAC and tenant rules
	 * This method is allowed for user with roles Customer Admin, Customer User
	 * Customer Admin, Customer User get list of graph frames for given customer ID and cluster ID under his organization
	 * @param request
	 * @return
	 * @throws ClusterNotFoundException
	 * @throws BaseException
	 */
	@Path("/api/cluster/grafanainfo/{customerId}/{clusterId}")
    @GET
	@ApiOperation( value = "Get list of graph frames for given customer ID and cluster ID", notes = "User can get list of graph frames for given customer ID and cluster ID under his organization. "
			+ "User with role Customer Admin or Customer User is allowed."
			+ "and cluster ID under his organization",  responseContainer = "List")
    @ApiResponses( {
	    @ApiResponse( code = 200, message = "List of graph frames (for given customer ID and cluster ID) are fetched successfully" )
	} )
	@Transactional(rollbackFor = { NotAuthorizeException.class, ClusterNotFoundException.class, BaseException.class })
	@RequestMapping(value = "/api/cluster/grafanainfo/{customerId}/{clusterId}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('Customer User','Customer Admin')")
	public ResponseDTO clusterGrafanaDataByCustomer(@ApiParam(name="Customer Id", value="The id of the customer whose gragh details have to be fetched", required=true) @PathVariable("customerId") String customerId,
			@ApiParam(name="Cluster Id", value="The id of the cluster whose gragh details have to be fetches", required=true) @PathVariable("clusterId") String clusterId,
			HttpServletRequest request) throws ClusterNotFoundException, BaseException {
		logger.debug("GrafanaController.clusterGrafanaDataByCustomer()");
		 grafanaUrl = environment.getProperty("grafana.host");
		 
		String message = null;
		String statusCode = HttpStatus.OK.toString();
        Map<String,List<Object>> grafanaClusterGraphs= new java.util.HashMap<String,List<Object>>();
        String loggedInCustID = getLoggedInUserCustomerID(SecurityContextHolder.getContext().getAuthentication());
        String authtoken = getLoggedInUserAuthToken(SecurityContextHolder.getContext().getAuthentication());
		try {
			Customer customer=customerService.findById(Integer.parseInt(loggedInCustID));
			
			//GrafanaClient.switchOrgnizationContext(customer,grafanaUrl);
			String clusterCurrentDB = grafanaUrl+"/dashboard-solo/db/customer-" +loggedInCustID+"-" +clusterId +"-current";
			//String clusterCurrentDB = grafanaUrl+"/api/dashboards/db/customer-" +loggedInCustID+"-" +clusterId +"-current";
			List<Object> clusterCurGraph = new LinkedList<Object>();
			grafanaClusterGraphs.put("cluster_curr",clusterCurGraph );
			clusterCurGraph.add(clusterCurrentDB+"?panelId=5&fullscreen&theme=light");
			clusterCurGraph.add( clusterCurrentDB+"?panelId=6&fullscreen&theme=light");
			clusterCurGraph.add( clusterCurrentDB+"?panelId=8&fullscreen&theme=light");
			clusterCurGraph.add(clusterCurrentDB+"?panelId=18&fullscreen&theme=light");
			
			String clusetrLast7DB = grafanaUrl+"/dashboard-solo/db/customer-" + loggedInCustID +"-" +clusterId + "-last-7-days";
			//String clusetrLast7DB = grafanaUrl+"/api/dashboards/db/customer-" + loggedInCustID +"-" +clusterId + "-last-7-days";
			List<Object> clusterPreGraph = new LinkedList<Object>();
			grafanaClusterGraphs.put("cluster_pre",clusterPreGraph );
			
			
			clusterPreGraph.add( clusetrLast7DB+"?panelId=6&fullscreen&theme=light");
			clusterPreGraph.add( clusetrLast7DB+"?panelId=5&fullscreen&theme=light");
			clusterPreGraph.add(clusetrLast7DB+"?panelId=8&fullscreen&theme=light");
			clusterPreGraph.add( clusetrLast7DB+"?panelId=18&fullscreen&theme=light");
			
			
			List<Object> customerDetails =new LinkedList<Object>();
			grafanaClusterGraphs.put("cluster_details",customerDetails );
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("user", customer.getName()+"-"+customer.getId());
			jsonObject.addProperty("email", customer.getEmail());
			jsonObject.addProperty("password", customer.getGrafanaAuthToken());
			customerDetails.add(grafanaUrl+"/login");
			customerDetails.add(jsonObject.toString());
			customerDetails.add(grafanaUrl+"/api/user/using/"+customer.getGrafanaOrgId());
			/*customerDetails.add("");
			customerDetails.add("");
			customerDetails.add("");*/
			customerDetails.add("ServerTime:"+Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTimeInMillis());
			//customerDetails.add(getGrafanaAuthToken(authtoken));
			//customerDetails.add(clusterCurrentDB);
			//grafanaClusterGraphs=defaultGrafanaClusterGraphs();
			
		} catch (Exception e) {
			logger.error("Error in retriving cluster graph details ", e);
		}

		ResponseDTO response = new ResponseDTO(message, Integer.parseInt(statusCode));
		response.setData(grafanaClusterGraphs);
		response.setStatusCode(Integer.parseInt(statusCode));
		response.setMessage(environment.getProperty("cluster.graphana.customerid.clusterid"));
		logger.debug("GrafanaController.clusterGrafanaDataByCustomer()");
		return response;

	}
	
	/*private String getGrafanaAuthToken(String token){
		
	try {
		UserAuth userAuth=userAuthservice.findUserAuthByAuthToken(token);
		return userAuth.getGrafanaToken();
	} catch (UserAuthNotFoundException e) {
		logger.debug("grafana Token not available for the user");
	}
	
		return null;
	}*/

	/**
	 * RBAC and tenant rules
	 * This method is allowed for user with roles Customer Admin, Customer User
	 * Customer Admin, Customer User get list of graph frames for given node ID under his organization
	 * @param customerId
	 * @param request
	 * @return
	 * @throws ClusterNotFoundException
	 * @throws NodeNotFoundException
	 * @throws BaseException
	 */
	@Path("/api/node/grafanainfo/{customerId}/{clusterId}/{nodeName}")
    @GET
	@ApiOperation( value = "Get list of graph frames for given node ID", notes = "User can get list of graph frames for given customer ID, cluster ID and node ID under his organization. "
			+ "User with role Customer Admin or Customer User is allowed.",  responseContainer = "List")
    @ApiResponses( {
	    @ApiResponse( code = 200, message = "List of graph frames (for given customer ID and cluster ID) are fetched successfully" )
	} )
	@Transactional(rollbackFor = { NotAuthorizeException.class, ClusterNotFoundException.class, NodeNotFoundException.class, BaseException.class })
	@RequestMapping(value = "/api/node/grafanainfo/{customerId}/{clusterName}/{nodeName}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('Customer User','Customer Admin')")
	public ResponseDTO nodeGrafanaDataByCustomer(@ApiParam(name="Customer Id", value="The id of the customer whose gragh details have to be fetched", required=true) @PathVariable("customerId") String customerId,
			@ApiParam(name="Cluster Id", value="The id of the cluster whose gragh details have to be fetched", required=true) @PathVariable("clusterName") String clusterName,
			@ApiParam(name="Node Id", value="The id of the node whose gragh details have to be fetched", required=true) @PathVariable("nodeName") String nodeName,
			HttpServletRequest request)
			throws ClusterNotFoundException, NodeNotFoundException, BaseException {
		logger.debug("GrafanaController nodeGrafanaDataByCustomer for customer{} cluster {} node {} ",customerId, clusterName, nodeName);
		String message = null;
		String statusCode = HttpStatus.OK.toString();
		
		String authtoken=getLoggedInUserAuthToken(SecurityContextHolder.getContext().getAuthentication());
		String loggedInCustID =getLoggedInUserCustomerID(SecurityContextHolder.getContext().getAuthentication());
        Map<String,List<Object>> grafanaClusterGraphs= new java.util.HashMap<String,List<Object>>();
		grafanaUrl = environment.getProperty("grafana.host");
		try {
			Node node = nodeService.findNodeByNameAndCluster(nodeName, clusterName,Integer.parseInt(loggedInCustID));
			Customer customer=customerService.findById(Integer.parseInt(loggedInCustID));
			
			
			String clusterCurrentDB = grafanaUrl+"/dashboard-solo/db/customer-" +loggedInCustID+"-" +node.getCluster().getId() + "-" +node.getId()+"-current";
			//String clusterCurrentDB = grafanaUrl+"/api/dashboards/db/customer-" +loggedInCustID+"-" +node.getCluster().getId() + "-" +node.getId()+"-current";
			List<Object> nodeCurGraph = new LinkedList<Object>();
			grafanaClusterGraphs.put("cluster_curr",nodeCurGraph );
			nodeCurGraph.add(clusterCurrentDB+"?panelId=5&fullscreen&theme=light");
			nodeCurGraph.add( clusterCurrentDB+"?panelId=6&fullscreen&theme=light");
			nodeCurGraph.add( clusterCurrentDB+"?panelId=8&fullscreen&theme=light");
			nodeCurGraph.add(clusterCurrentDB+"?panelId=18&fullscreen&theme=light");
			
			String clusetrLast7DB = grafanaUrl+"/dashboard-solo/db/customer-" + loggedInCustID +"-" +node.getCluster().getId() + "-" +node.getId() +"-last-7-days";
			//String clusetrLast7DB = grafanaUrl+"/api/dashboards/db/customer-" + loggedInCustID +"-" +node.getCluster().getId() + "-" +node.getId() +"-last-7-days";
			List<Object> nodePreGraph = new LinkedList<Object>();
			grafanaClusterGraphs.put("cluster_pre",nodePreGraph );
			
			
			nodePreGraph.add( clusetrLast7DB+"?panelId=6&fullscreen&theme=light");
			nodePreGraph.add( clusetrLast7DB+"?panelId=5&fullscreen&theme=light");
			nodePreGraph.add(clusetrLast7DB+"?panelId=8&fullscreen&theme=light");
			nodePreGraph.add( clusetrLast7DB+"?panelId=18&fullscreen&theme=light");
			
			List<Object> customerDetails =new LinkedList<Object>();
			grafanaClusterGraphs.put("cluster_details",customerDetails );
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("user", customer.getName()+"-"+customer.getId());
			jsonObject.addProperty("email", customer.getEmail());
			jsonObject.addProperty("password", customer.getGrafanaAuthToken());
			customerDetails.add(grafanaUrl+"/login");
			customerDetails.add(jsonObject.toString());
			customerDetails.add(grafanaUrl+"/api/user/using/"+customer.getGrafanaOrgId());
			/*customerDetails.add("");
			customerDetails.add("");
			customerDetails.add("");*/
			
			customerDetails.add("ServerTime:"+Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTimeInMillis());
			//customerDetails.add(getGrafanaAuthToken(authtoken));
			//customerDetails.add(clusterCurrentDB);
			//grafanaClusterGraphs=defaultGrafanaNodeGraphs();	
		} catch (Exception e) {
			logger.error("Error in retriving node graph details ", e);
		}
		ResponseDTO response = new ResponseDTO(message, Integer.parseInt(statusCode));
		
		 response.setData(grafanaClusterGraphs);
		 response.setStatusCode(Integer.parseInt(statusCode));
		 response.setMessage(environment.getProperty("node.graphana.details.customerid.nodename"));
		logger.debug("GrafanaController.clusterGrafanaDataByCustomer()");
		return response;

	}
	
	
	
	
	
	/**
	 * RBAC and tenant rules
	 * This method is allowed for user with roles Customer Admin, Customer User
	 * Customer Admin, Customer User get grafana login details for the loggedin customer
	 * @param request
	 * @return
	 * @throws ClusterNotFoundException
	 * @throws BaseException
	 */
	@Path("/api/cluster/grafanainfo")
    @GET
	@ApiOperation( value = "Get grafana login details for the loggedin customer", notes = "User can get grafana login details for the loggedin customer. Request should contain loggedin email."
			+ "User with role Customer Admin or Customer User is allowed.",  responseContainer = "List")
    @ApiResponses( {
	    @ApiResponse( code = 200, message = "Grafana login details for the logged in customer" )
	} )
	@Transactional(rollbackFor = { NotAuthorizeException.class, ClusterNotFoundException.class, BaseException.class })
	@RequestMapping(value = "/api/cluster/grafanainfo", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('Customer User','Customer Admin')")
	public ResponseDTO customerGrfanaDetails(HttpServletRequest request) throws ClusterNotFoundException, BaseException {
		logger.debug("GrafanaController.clusterGrafanaDataByCustomer()");
		 grafanaUrl = environment.getProperty("grafana.host");
		 
		String message = null;
		String statusCode = HttpStatus.OK.toString();
        Map<String,List<Object>> grafanaClusterGraphs= new java.util.HashMap<String,List<Object>>();
        
       
        String loggedInEmail = getLoggedInUserEmail(SecurityContextHolder.getContext().getAuthentication());
        String authtoken = getLoggedInUserAuthToken(SecurityContextHolder.getContext().getAuthentication());
         String loggedInCustID = getLoggedInUserCustomerID(SecurityContextHolder.getContext().getAuthentication());
    	logger.debug("RoleName and loggedinemail are {}",  loggedInEmail);
		User loggedInUser = userService.findUserByEmail(loggedInEmail);
		Customer customer=loggedInUser.getCustomer();
		
       if(customer.getGrafanaOrgId()!=null){
		try {
			List<Cluster> cluster = clusterService.findClusterByCustomerId(Integer.parseInt(loggedInCustID));
			//GrafanaClient.switchOrgnizationContext(customer,grafanaUrl);
			String clusterCurrentDB = grafanaUrl+"/dashboard-solo/db/customer-" +loggedInCustID+"-" +cluster.get(0).getId() +"-current";
			//String clusterCurrentDB = grafanaUrl+"/api/dashboards/db/customer-" +loggedInCustID+"-" +cluster.get(0).getId() +"-current";
			List<Object> customerDetails =new LinkedList<Object>();
			grafanaClusterGraphs.put("cluster_details",customerDetails );
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("user", customer.getName()+"-"+customer.getId());
			jsonObject.addProperty("email", customer.getEmail());
			jsonObject.addProperty("password", customer.getGrafanaAuthToken());
			customerDetails.add(grafanaUrl+"/login");
			customerDetails.add(jsonObject.toString());
			customerDetails.add(grafanaUrl+"/api/user/using/"+customer.getGrafanaOrgId());
			//customerDetails.add(getGrafanaAuthToken(authtoken));
			//grafanaClusterGraphs=defaultGrafanaClusterGraphs();
			//customerDetails.add(clusterCurrentDB);
			
			
		} catch (Exception e) {
			logger.error("Error in retriving customerGrfanaDetails ", e);
		}
       }

        ResponseDTO response = new ResponseDTO(message, Integer.parseInt(statusCode));
		response.setData(grafanaClusterGraphs);
		response.setStatusCode(Integer.parseInt(statusCode));
		response.setMessage(environment.getProperty("cluster.graphana.details"));
		logger.debug("GrafanaController.customerGrfanaDetails()");
		return response;

	}
	
	public static void main(String[] args) {
		
	}
	
	
}
