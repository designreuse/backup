package com.nvidia.cosmos.cloud.rest.controller;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nvidia.cosmos.cloud.common.ServicesConstants;
import com.nvidia.cosmos.cloud.dtos.ApplianceDTO;
import com.nvidia.cosmos.cloud.dtos.ResponseDTO;
import com.nvidia.cosmos.cloud.exceptions.ApplianceExistsException;
import com.nvidia.cosmos.cloud.exceptions.BaseException;
import com.nvidia.cosmos.cloud.exceptions.CustomerNotFoundException;
import com.nvidia.cosmos.cloud.rest.exception.NotAuthorizeException;
import com.nvidia.cosmos.cloud.services.appliance.model.Appliance;
import com.nvidia.cosmos.cloud.services.appliance.service.ApplianceService;
import com.nvidia.cosmos.cloud.services.customer.model.Customer;
import com.nvidia.cosmos.cloud.services.customer.service.CustomerService;
import com.nvidia.cosmos.cloud.services.role.service.RoleService;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

/**
 * This controller will help to register appliances and also CRUD methods for a appliance
 *
 * @author bprasad
 */
@RestController
public class ApplianceController extends BaseController{
/*
    *//**
     * 
     *//*
    private static Logger logger = LoggerFactory.getLogger(ApplianceController.class);
    *//**
     * 
     *//*
    @Autowired
   	Environment environment;
    ApplianceService service;
    CustomerService customerService;
    RoleService roleService;
    *//**
     * 
     *//*
    public ApplianceController(){
    	service = factory.getApplianceService();
    	customerService = factory.getCustomerService();
    	roleService = factory.getRoleService();
    }
    
    *//**
     * @param dto
     * @param request
     * @return
     * @throws ApplianceExistsException
     * @throws BaseException
     *//*
    @Path("/api/appliance/{customerId}")
    @POST
	@ApiOperation( value = "Create appliance", notes = "Create a new appliance", response = Appliance.class)
    @ApiResponses( {
	    @ApiResponse( code = 200, message = "A new appliance has been created" )
	} )
    @Transactional(rollbackFor = {NotAuthorizeException.class, CustomerNotFoundException.class, 
    		ApplianceExistsException.class, BaseException.class})
    @RequestMapping(value = "/api/appliance/{customerId}", method = RequestMethod.POST)
    public ResponseDTO register(@ApiParam(name="Customer id", value="The id of the customer under which the appliance needs to be created", required=true) @PathVariable("customerId") int customerId, 
    		@ApiParam(name="Appliance object", value="The appliance object that needs to be created", required=true) @Valid @RequestBody ApplianceDTO dto, HttpServletRequest request) throws NotAuthorizeException, 
    		CustomerNotFoundException, ApplianceExistsException, BaseException{
    	logger.debug("Adding a new appliance entry with information:"+ new Object[]{dto});
        String message = null;
		String statusCode = HttpStatus.OK.toString();
		String roleName = (String)request.getAttribute(ServicesConstants.LOGGED_IN_ROLE_NAME);
		if(!roleName.equals(ServicesConstants.ROLES[0])){
			//throw new NotAuthorizeException("This operation can not be allowed");
			throw new NotAuthorizeException(environment.getProperty("appliance.operation"));
		}
		Customer customer = customerService.findById(customerId);
		Appliance appliance = new Appliance(dto.getName(), dto.getServiceKey(), customer);
		
		service.saveAppliance(appliance);
		appliance = service.findApplianceByServiceKey(dto.getServiceKey());
		//message = "Appliance with name "+appliance.getName()+" saved.";
		message = environment.getProperty("appliance.name.saved");		
		logger.debug("Appliance is saved with name {}", appliance.getName());
        ResponseDTO response = new ResponseDTO(message, Integer.parseInt(statusCode));
        response.setData(createDTO(appliance));
        return response;
    }
    
    @Path("/api/appliance/{customerId}")
    @GET
	@ApiOperation( value = "Get all the appliance list by customer", notes = "List all the appliances by customer", response = Appliance.class,  responseContainer = "List")
    @ApiResponses( {
	    @ApiResponse( code = 200, message = "All the appliances by the customer have been listed" )
	} ) 
    @Transactional(rollbackFor = {NotAuthorizeException.class, CustomerNotFoundException.class, 
    		ApplianceExistsException.class, BaseException.class})
    @RequestMapping(value = "/api/appliance/{customerId}", method = RequestMethod.GET)
    public ResponseDTO listAppliances(@ApiParam(name="Customer Id", value="The id of the customer whose appliances have to be listed", required=true) @PathVariable("customerId") int customerId,
    		HttpServletRequest request) throws NotAuthorizeException, 
    		CustomerNotFoundException, ApplianceExistsException, BaseException{
    	logger.debug("Fetching all appliances for a given customer "+ new Object[]{customerId});
    	String message = null;
    	String statusCode = HttpStatus.OK.toString();
    	String roleName = (String)request.getAttribute(ServicesConstants.LOGGED_IN_ROLE_NAME);
    	String loggedInEmail = (String)request.getAttribute(ServicesConstants.LOGGED_IN_EMAIL);
    	Customer cus = customerService.findCustomerByEmail(loggedInEmail);
    	if(!roleName.equals(ServicesConstants.ROLES[0]) && cus!=null &&
    			cus.getId() != customerId){
    		//throw new NotAuthorizeException("This operation can not be allowed");
    		throw new NotAuthorizeException(environment.getProperty("appliance.operation"));
    	}
    	List<Appliance> appliances = service.findAllAppliances(customerId);
    	List<ApplianceDTO> dtos = new LinkedList<ApplianceDTO>();
    	for(Appliance appliance : appliances){
    		dtos.add(createDTO(appliance));
    	}
    	//message = "All appliances are retrieved.";
    	message = environment.getProperty("appliance.retrieved");
    	logger.debug("Retrieved all customer appliances for the given customer id ", customerId);
    	ResponseDTO response = new ResponseDTO(message, Integer.parseInt(statusCode));
    	response.setData(dtos);
    	return response;
    }
    
    @Path("/api/appliances")
    @GET
	@ApiOperation( value = "Get all the appliance list", notes = "List all the appliances ", response = Appliance.class,  responseContainer = "List")
    @ApiResponses( {
	    @ApiResponse( code = 200, message = "All the appliances have been listed" )
	} ) 
    @Transactional(rollbackFor = {NotAuthorizeException.class, CustomerNotFoundException.class, 
    		ApplianceExistsException.class, BaseException.class})
    @RequestMapping(value = "/api/appliances", method = RequestMethod.GET)
    public ResponseDTO listAllAppliances(HttpServletRequest request) throws NotAuthorizeException, 
    CustomerNotFoundException, ApplianceExistsException, BaseException{
    	logger.debug("Fetching all appliances");
    	String message = null;
    	String statusCode = HttpStatus.OK.toString();
    	String roleName = (String)request.getAttribute(ServicesConstants.LOGGED_IN_ROLE_NAME);
    	String loggedInEmail = (String)request.getAttribute(ServicesConstants.LOGGED_IN_EMAIL);
    	Customer cus = customerService.findCustomerByEmail(loggedInEmail);
//    	if(!roleName.equals(ServicesConstants.ROLES[0]) && cus!=null &&
//    			cus.getId() != customerId){
//    		throw new NotAuthorizeException("This operation can not be allowed");
//    	}
    	List<Appliance> appliances = service.findAllAppliances();
    	List<ApplianceDTO> dtos = new LinkedList<ApplianceDTO>();
    	for(Appliance appliance : appliances){
    		dtos.add(createDTO(appliance));
    	}
    	//message = "All appliances are retrieved.";
    	message = environment.getProperty("appliance.retrieved");
    	logger.debug("Retrieved all customer appliances");
    	ResponseDTO response = new ResponseDTO(message, Integer.parseInt(statusCode));
    	response.setData(dtos);
    	return response;
    }
    */
    
}
