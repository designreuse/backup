package com.nvidia.cosmos.cloud.rest.controller;

import static com.nvidia.cosmos.cloud.rest.util.NvidiaUtil.convertNodeToNodeDTO;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.xml.bind.ValidationException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nvidia.cosmos.cloud.dtos.ApiResponseDTO;
import com.nvidia.cosmos.cloud.dtos.ResponseDTO;
import com.nvidia.cosmos.cloud.dtos.cluster.ClusterDTO;
import com.nvidia.cosmos.cloud.dtos.node.NodeDTO;
import com.nvidia.cosmos.cloud.exceptions.BaseException;
import com.nvidia.cosmos.cloud.exceptions.ClusterNotFoundException;
import com.nvidia.cosmos.cloud.rest.exception.NotAuthorizeException;
import com.nvidia.cosmos.cloud.services.cluster.model.Cluster;
import com.nvidia.cosmos.cloud.services.cluster.service.ClusterService;
import com.nvidia.cosmos.cloud.services.node.model.Node;
import com.nvidia.cosmos.cloud.services.node.service.NodeService;
import com.nvidia.cosmos.cloud.services.user.model.User;
import com.nvidia.cosmos.cloud.services.user.service.UserService;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;


@RestController
public class ClusterController extends BaseController {
		
	/**
	 * 
	 */
	private static Logger logger = LoggerFactory.getLogger(ClusterController.class);

	/**
	 * 
	 */
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
	public ClusterController() {
		clusterService = factory.getClusterService();
		nodeService = factory.getNodeService();
		userService = factory.getUserService();
	}
	
	boolean isClusterAccessble(String serialNumber, String customerId){
		
		try {
			Cluster cluster = clusterService.findClusterBYSerialNumber(serialNumber, customerId);
			if(cluster != null) return true;
		} catch (ClusterNotFoundException e) {
			logger.debug("Customer Id {} not assosiated with serialNumber {} ",customerId,serialNumber);
		}
		
		return false;
	}

	/**
	 * RBAC and tenant rules
	 * This method is allowed to Customer Admin
	 * Customer Admin can update nfs details of the cluster under his organization
	 * @param clusterDTO
	 * @param request
	 * @return
	 * @throws ClusterNotFoundException
	 * @throws BaseException
	 * @throws ValidationException 
	 *  
	 */
	@Path("/api/cluster/updatenfs")
	@POST
	@ApiOperation(value = "Update the cluster Nfs details", notes = "User will update the cluster Nfs details of the cluster under his organization.  "
			+ "User with role Customer Admin is allowed. ", response = ApiResponseDTO.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Cluster Nfs details have been updated") })
	@Transactional(rollbackFor = {ValidationException.class, NotAuthorizeException.class, ClusterNotFoundException.class, BaseException.class })
	@RequestMapping(value = "/api/cluster/updatenfs", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('Customer Admin')")
	public ApiResponseDTO updateClusterNfsDetails(
			@ApiParam(name = "List of cluster objects", value = "The list of cluster objects whose Nfs details have to be upadted", required = true)@Valid @RequestBody List<ClusterDTO> clusterDTOList,
			HttpServletRequest request) throws ClusterNotFoundException,ValidationException, BaseException {
		logger.debug("ClusterController.updateClusterNfsDetails()");
		
		String statusCode = HttpStatus.OK.toString();
		
		String loggedInEmail = getLoggedInUserEmail(SecurityContextHolder.getContext().getAuthentication());
		String custmerId= getLoggedInUserCustomerID(SecurityContextHolder.getContext().getAuthentication());
		
		User loggedInUser = userService.findUserByEmail(loggedInEmail);

		for (ClusterDTO clusterDTO : clusterDTOList) {
			if(!isClusterAccessble(clusterDTO.getSerialNumber(),custmerId)){
				logger.info("User {} not accessble for the appliance  ");
				continue;
			}
	
			Cluster cluster = clusterService.findClusterByClusterNameAndCustomerId(clusterDTO.getName(), loggedInUser.getCustomer().getId());
			if (cluster != null && clusterDTO.getNfsDetails() != null && !clusterDTO.getNfsDetails().isEmpty()) {
				cluster.setNfsDetails(clusterDTO.getNfsDetails());
				clusterService.updateCluster(cluster);
			}else{
				logger.error("Updating nfs failed with message {} "+environment.getProperty("cluster.notempty"));
				throw new ValidationException(environment.getProperty("cluster.notempty"));
			}
		}
		ApiResponseDTO response = new ApiResponseDTO(environment.getProperty("cluster.nfs.updated"), Integer.parseInt(statusCode));
		logger.debug("ClusterController.updateClusterNfsDetails()");
		return response;

	}

	/**
	 * RBAC and tenant rules
	 * This method is allowed to Customer Admin and Customer User
	 * Customer Admin and Customer User can get list of cluster under his organization
	 * @param request
	 * @return
	 * @throws ClusterNotFoundException
	 * @throws BaseException
	 */
	@Path("/api/cluster/customer")
	@GET
	@ApiOperation(value = "Get all the cluster list by customer", notes = "List all the clusters by customer under his organization. User with role Customer Admin or Customer User is allowed. ", response = ResponseDTO.class, responseContainer = "List")
	@ApiResponses({ @ApiResponse(code = 200, message = "All the clusters by the customer have been listed") })
	@Transactional(rollbackFor = {NotAuthorizeException.class, ClusterNotFoundException.class, BaseException.class })
	@RequestMapping(value = "/api/cluster/customer", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('Customer User','Customer Admin')")
	public ResponseDTO findAllClustersDetails(HttpServletRequest request) throws ClusterNotFoundException, BaseException {
		logger.debug("ClusterController.findAllClustersDetails()");
		String message = null;
		String statusCode = HttpStatus.OK.toString();
		String loggedInEmail = getLoggedInUserEmail(SecurityContextHolder.getContext().getAuthentication());
		User loggedInUser = userService.findUserByEmail(loggedInEmail);
		List<ClusterDTO> clusterDTOList = new LinkedList<ClusterDTO>();
		List<Cluster> clustersList = clusterService.findClusterByCustomerId(loggedInUser.getCustomer().getId());
		if (clustersList != null && !clustersList.isEmpty()) {
			for (Cluster cluster : clustersList) {
				ClusterDTO clusterDTO = new ClusterDTO();
				clusterDTO.setId(String.valueOf(cluster.getId()));
				clusterDTO.setIpAddress(cluster.getIpAddress());
				clusterDTO.setName(cluster.getName());
				clusterDTO.setNfsDetails(cluster.getNfsDetails());
				clusterDTO.setSerialNumber(cluster.getSerialNumber());
				clusterDTO.setUpgradeOnBoot(cluster.getUpgradeOnBoot());
				List<Node> nodeList = nodeService.findAllNodesByCluster(cluster.getId());
				if (nodeList != null && !nodeList.isEmpty()) {
					for (Node node : nodeList) {
						NodeDTO nodeDTO = new NodeDTO();
						clusterDTO.addNodeDTO(convertNodeToNodeDTO(node, nodeDTO, cluster));
					}
				}
				clusterDTOList.add(clusterDTO);
			}
			message = environment.getProperty("cluster.all.details");
		} else {
			message = environment.getProperty("cluster.notfound.clusterid");
		}
		ResponseDTO response = new ResponseDTO(message, Integer.parseInt(statusCode));
		response.setData(clusterDTOList);
		logger.debug("ClusterController.findAllClustersDetails()");
		return response;

	}

	/**
	 * RBAC and tenant rules
	 * This method is allowed to Customer Admin and Customer User
	 * Get cluster by cluster name and customer name
	 * Customer Admin and Customer User can get list of clusters by cluster name and customer name
	 * @param request
	 * @return
	 * @throws ClusterNotFoundException
	 * @throws BaseException
	 */
	@Path("/api/cluster/{clusterName}")
	@GET
	@ApiOperation(value = "Get cluster by cluster name and customer name", notes = "User can get list of clusters by cluster name and customer name. "
			+ "User with role Customer Admin or Customer User is allowed.", response = ResponseDTO.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Cluster with the given cluster name and customer name has been fetched") })
	@Transactional(rollbackFor = { NotAuthorizeException.class, ClusterNotFoundException.class, BaseException.class })
	@RequestMapping(value = "/api/cluster/{clusterName}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('Customer User','Customer Admin')")
	public ResponseDTO getClusterByName(
			@ApiParam(name = "Cluster name", value = "The name of the cluster which needs to be fetched", required = true) @PathVariable("clusterName") String clusterName,
			HttpServletRequest request) throws ClusterNotFoundException, BaseException {
		logger.debug("ClusterController.getClusterByName()");
		String message = null;
		String statusCode = HttpStatus.OK.toString();
			String loggedInEmail = getLoggedInUserEmail(SecurityContextHolder.getContext().getAuthentication());
		User loggedInUser = userService.findUserByEmail(loggedInEmail);
		ClusterDTO clusterDTO = new ClusterDTO();
		Cluster clusterEntity = clusterService.findClusterByClusterNameAndCustomerId(clusterName, loggedInUser.getCustomer().getId());
		if (clusterEntity != null) {
			clusterDTO.setId(String.valueOf(clusterEntity.getId()));
			clusterDTO.setIpAddress(clusterEntity.getIpAddress());
			clusterDTO.setName(clusterEntity.getName());
			clusterDTO.setNfsDetails(clusterEntity.getNfsDetails());
			clusterDTO.setSerialNumber(clusterEntity.getSerialNumber());

			List<Node> nodeList = nodeService.findAllNodesByCluster(clusterEntity);
			if (nodeList != null) {
				for (Node node : nodeList) {
					NodeDTO nodeDTO = new NodeDTO();
					clusterDTO.addNodeDTO(convertNodeToNodeDTO(node, nodeDTO, clusterEntity));
				}
				message = environment.getProperty("cluster.found.for.clustername");
			} else {
				message = environment.getProperty("cluster.notfound.for.clustername");
			}
		} else {
			message = environment.getProperty("cluster.notfound.for.clustername");
		}
		ResponseDTO response = new ResponseDTO(message, Integer.parseInt(statusCode));
		response.setData(clusterDTO);
		logger.debug("ClusterController.getClusterByName()");
		return response;

	}
	
	boolean isAccessAllowedforCluster(String clusterID, String customerID){
		   try {
			Cluster cluster = clusterService.findCluster(clusterID, customerID);
			if(cluster != null) return true;
		} catch (ClusterNotFoundException e) {
			logger.debug("Cluster {} not accessble for customer{} ",clusterID,customerID);
		}
		   return false;
	}

	/**
	 * RBAC and tenant rules
	 * This method is allowed to Customer Admin and Customer User
	 * Get the node details for the cluster
	 * Customer Admin and Customer User can get the node details for the cluster under his organization
	 * @param clusterName
	 * @return
	 * @throws ValidationException 
	 */
	@Path("/api/nodeInfo/{clusterId}")
	@GET
	@Transactional(rollbackFor = {ValidationException.class, ClusterNotFoundException.class, BaseException.class })
	@ApiOperation(value = "Get the node details for the cluster", notes = "User can get the node details for the cluster under his organizarion. User with role Customer Admin or "
			+ "Customer User is allowed", response = ResponseDTO.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Cluster along with node details have been fetched") })
	@RequestMapping(value = "/api/nodeInfo/{clusterId}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('Customer User','Customer Admin')")
	public ResponseDTO getNodeInfo(
			@ApiParam(name = "Cluster id", value = "The id of the cluster whose details needs to be fetched", required = true) @PathVariable("clusterId") String clusterId)
					throws ValidationException,BaseException {
		logger.debug("ClusterController.getNodeInfo()");
		
		if(StringUtils.isEmpty(clusterId) && !StringUtils.isNumeric(clusterId)) throw new ValidationException("Invalid cluster ID");
		
		
		String loggedInCustomerId = getLoggedInUserCustomerID(SecurityContextHolder.getContext().getAuthentication());
		
		if(!isAccessAllowedforCluster(clusterId,loggedInCustomerId)){
			logger.debug("Customer {} not assossiated with cluster {}",loggedInCustomerId,clusterId);
			throw new NotAuthorizeException("User not authorized to access given cluster  ");
		}
		
		int clusterIds = Integer.parseInt(clusterId);
		
		String message = null;
		String statusCode = HttpStatus.OK.toString();
		ResponseDTO response = null;
		
		ClusterDTO clusterDTO = new ClusterDTO();
		try {
			
			
			Cluster clusterEntity = clusterService.findById(clusterIds);

			if (clusterEntity != null) {
				clusterDTO.setId(String.valueOf(clusterEntity.getId()));
				clusterDTO.setIpAddress(clusterEntity.getIpAddress());
				clusterDTO.setName(clusterEntity.getName());
				clusterDTO.setNfsDetails(clusterEntity.getNfsDetails());
				clusterDTO.setSerialNumber(clusterEntity.getSerialNumber());
				clusterDTO.setUpgradeOnBoot(clusterEntity.getUpgradeOnBoot());
				List<Node> nodeList = nodeService.findAllNodesByCluster(clusterEntity);
				if (nodeList != null && !nodeList.isEmpty()) {
					for (Node node : nodeList) {
						NodeDTO nodeDTO = new NodeDTO();
						clusterDTO.addNodeDTO(convertNodeToNodeDTO(node, nodeDTO, clusterEntity));
						message = environment.getProperty("node.all.details");
					}
				} else {
					message = environment.getProperty("cluster.not.found");
				}
			}
		} catch (Exception e) {
			 response = new ResponseDTO();
			response.setMessage(environment.getProperty("cluster.clusterid.notfound"));
			return response;
			
		}
		logger.debug("ClusterController.getNodeInfo()");
		 response = new ResponseDTO(message, Integer.parseInt(statusCode));
		response.setData(clusterDTO);
		return response;

	}

}
