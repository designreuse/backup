package com.nvidia.cosmos.cloud.rest.controller;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nvidia.cosmos.cloud.common.ServicesConstants;
import com.nvidia.cosmos.cloud.dtos.ResponseDTO;
import com.nvidia.cosmos.cloud.dtos.RoleDTO;
import com.nvidia.cosmos.cloud.exceptions.BaseException;
import com.nvidia.cosmos.cloud.exceptions.RoleNotFoundException;
import com.nvidia.cosmos.cloud.exceptions.UserExistsException;
import com.nvidia.cosmos.cloud.rest.exception.NotAuthorizeException;
import com.nvidia.cosmos.cloud.services.role.model.Role;
import com.nvidia.cosmos.cloud.services.role.service.RoleService;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

/**
 * This controller will help to register roles and also CRUD methods for a role
 *
 * @author bprasad
 */
@RestController
public class RoleController extends BaseController{

    /**
     * 
     */
    private static Logger logger = LoggerFactory.getLogger(RoleController.class);
    /**
     * 
     */
    RoleService service;
    /**
     * 
     */
    public RoleController(){
    	service = factory.getRoleService();
    }
    
    /**
     * @param request
     * @return
     * @throws NotAuthorizeException
     * @throws RoleNotFoundException
     * @throws UserExistsException
     * @throws BaseException
     */
  /*  @Path( "/api/role/" )
   	@GET
   	@ApiOperation( value = "Get all role list", notes = "List all the roles",  response=Role.class, responseContainer = "List" )
   	@ApiResponses( {
   	    @ApiResponse( code = 200, message = "All the roles have been listed"),
   	    @ApiResponse( code = 401, message = "Unauthorized" ),
   	    @ApiResponse( code = 403, message = "Forbidden" )
   	  } )
    @Transactional(rollbackFor = {NotAuthorizeException.class, BaseException.class})
    @RequestMapping(value = "/api/role/", method = RequestMethod.GET)
    public ResponseDTO listRoles(HttpServletRequest request) throws NotAuthorizeException, BaseException{
    	logger.debug("Fetching all roles by super admin ");
    	String message = null;
    	String statusCode = HttpStatus.OK.toString();
    	String roleName = (String)request.getAttribute(ServicesConstants.LOGGED_IN_ROLE_NAME);
    	String loggedInEmail = (String)request.getAttribute(ServicesConstants.LOGGED_IN_EMAIL);
    	if(!roleName.equals(ServicesConstants.ROLES[0]) && !loggedInEmail.equals(ServicesConstants.DEFAULT_ADMIN_EMAIL)){
    		throw new NotAuthorizeException("This operation can not be allowed");
    	}
    	List<Role> roles = service.findAllRoles();
    	List<RoleDTO> dtos = new LinkedList<RoleDTO>();
    	for(Role role : roles){
    		dtos.add(createDTO(role));
    	}
    	message = "All roles are retrieved.";
    	logger.debug("Retrieved all roles.");
    	ResponseDTO response = new ResponseDTO(message, Integer.parseInt(statusCode));
    	response.setData(dtos);
    	return response;
    }*/
}
