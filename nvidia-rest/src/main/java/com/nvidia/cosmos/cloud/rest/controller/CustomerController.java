
package com.nvidia.cosmos.cloud.rest.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.DELETE;
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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.nvidia.cosmos.cloud.common.ServicesConstants;
import com.nvidia.cosmos.cloud.common.UserStatusEnum;
import com.nvidia.cosmos.cloud.dtos.ApiResponseDTO;
import com.nvidia.cosmos.cloud.dtos.CustomerExistDTO;
import com.nvidia.cosmos.cloud.dtos.CustomerResponseDTO;
import com.nvidia.cosmos.cloud.dtos.DeleteCustomerDTO;
import com.nvidia.cosmos.cloud.dtos.FetchDTO;
import com.nvidia.cosmos.cloud.dtos.RegisterDTO;
import com.nvidia.cosmos.cloud.dtos.ResponseDTO;
import com.nvidia.cosmos.cloud.exceptions.BaseException;
import com.nvidia.cosmos.cloud.exceptions.CustomerExistsException;
import com.nvidia.cosmos.cloud.exceptions.CustomerNotFoundException;
import com.nvidia.cosmos.cloud.exceptions.EntitlementNotFoundException;
import com.nvidia.cosmos.cloud.exceptions.GraphanaFailedException;
import com.nvidia.cosmos.cloud.exceptions.RegistrationFailedException;
import com.nvidia.cosmos.cloud.exceptions.UserExistsException;
import com.nvidia.cosmos.cloud.exceptions.UserNotFoundException;
import com.nvidia.cosmos.cloud.rest.exception.NotAuthorizeException;
import com.nvidia.cosmos.cloud.rest.util.GrafanaClient;
import com.nvidia.cosmos.cloud.rest.util.NvidiaUtil;
import com.nvidia.cosmos.cloud.rest.util.QuayClient;
import com.nvidia.cosmos.cloud.services.customer.model.Customer;
import com.nvidia.cosmos.cloud.services.customer.service.CustomerService;
import com.nvidia.cosmos.cloud.services.entitlement.model.Entitlement;
import com.nvidia.cosmos.cloud.services.entitlement.service.EntitlementService;
import com.nvidia.cosmos.cloud.services.user.model.User;
import com.nvidia.cosmos.cloud.services.user.service.UserService;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

/**
 * This controller will help to register customers and also CRUD methods for a customer
 *
 *
 */
@RestController
public class CustomerController extends BaseController{
		
	@Autowired
	Environment environment;
	
    /**
     * 
     */
    private static Logger logger = LoggerFactory.getLogger(CustomerController.class);
    /**
     * 
     */
    CustomerService service;
    /**
     * 
     */
    UserService userservice;
    /**
     * 
     */
     EntitlementService entlservice;
    private static String grafanaUrl = null;
    private static String influxUrl=null;
    private static String grafanaUser = null; 
    private static String grafanaPassword = null; 
    
	private static String databaseName = "collectd";
    /**
     * 
     */
    public CustomerController(){
    	service = factory.getCustomerService();
    	entlservice = factory.getEntitlementService();
    	userservice = factory.getUserService();
    }
    
    /**
     * RBAC and tenant rules
     * This is an open API
     * Register a new customer
     * @param dto
     * @param request
     * @return
     * @throws CustomerExistsException
     * @throws BaseException
     */
	@Path("/api/register")
	@POST
	@ApiOperation(value = "Create new customer", notes = "User can register a new customer. This is an open API. Who has valid entitlement key can register himself", response = ApiResponseDTO.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Customer registered successfully."),
			@ApiResponse(code = 404, message = "Customer with this e-mail already exists"), 
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 417, message = "Registration requires the entitlement key, please contact administrator"),
			@ApiResponse(code = 417, message = "Entitlement key has already been used"),
			@ApiResponse(code = 417, message = "Entitlement key is not found"),
			@ApiResponse(code = 417, message = "Super admin does not have right quay authentication token to create the application, try later"), })
	@Transactional(rollbackFor = { CustomerExistsException.class, RegistrationFailedException.class, BaseException.class })
	@RequestMapping(value = "/api/register", method = RequestMethod.POST)
	public ApiResponseDTO register(
			@ApiParam(name = "Customer object", value = "The customer object that needs to be registered", required = true) @Valid @RequestBody RegisterDTO dto,
			HttpServletRequest request) throws RegistrationFailedException,CustomerExistsException, BaseException, Exception{
		logger.debug("Adding a new customer entry with information:{}" , dto.getEmail());
		String message = null;
		String statusCode = HttpStatus.OK.toString();

		if (dto.getKey() != null) {
			Entitlement entitlement = entlservice.findEntitlementByKey(dto.getKey());
			if (entitlement != null) {
				if (entitlement.getCustomer() == null) {
					User superAdminUser = userservice.findUserByEmail(ServicesConstants.DEFAULT_ADMIN_EMAIL);
					if (superAdminUser.getqAuthToken() != null && !superAdminUser.getqAuthToken().isEmpty()) {
						
					/*Map<String, Object> object = QuayClient.createCustomer(environment.getProperty("quay.url"), "Bearer " + superAdminUser.getqAuthToken(),
								dto.getName(), dto.getEmail().toLowerCase(), environment.getProperty("tomcat.url"));*/
						
						Customer customer = service.findCustomerByEmail(dto.getEmail().toLowerCase());
						if(customer==null){
							customer = new Customer(dto.getName(), dto.getEmail().toLowerCase());
							customer.setEulaAccepted(dto.getEulaAccepted());
							service.saveCustomer(customer);
						}
						
						List<Entitlement> existingEntitlements = entlservice.findEntitlementByName(entitlement.getCustomerName());
						if(existingEntitlements.size() > 0){
							for(Entitlement entl:existingEntitlements){
								entl.setCustomer(customer);
								entlservice.updateEntitlement(entl);
							}
						}

						User user = userservice.findUserByEmail(customer.getEmail());
							if(user!=null){
							user.setStatus(UserStatusEnum.REGISTERED.toString());
							}

							try {
							
								String encryptEmail = NvidiaUtil.getEncrypt("" + user.getId(), customer.getEmail(), true, "0", 
										Integer.parseInt(environment.getProperty("reset.hours")), NvidiaUtil.getCalenderTime(
						                          Integer.parseInt(environment.getProperty("reset.hours"))));

								
							logger.debug("Encripted mail url {}",encryptEmail);

							user.setPasswordKey(encryptEmail);
							userservice.mergeUser(user);
							
							Map<String, Object> model = new HashMap<String, Object>();
							model.put("userName", customer.getName());
							model.put("resetPasswordLink", environment.getProperty("tomcat.url") + "reset/reset-p.html#!/reset/"
									+ encryptEmail);
							model.put("loginLink", environment.getProperty("tomcat.url") + "login.html");
							model.put("userEmail", customer.getEmail());
							
							model.put("message", environment.getProperty("email.message"));
							
							model.put("corporateHeader", environment.getProperty("tomcat.url") + "img/NVIDIA-Corporate-Header.png");
							
							initMailThread();
							NvidiaUtil.executeSendMailsThread(customer.getEmail(), environment.getProperty("email.subject"), model, "account.vm");
						} catch (Exception e) {
							logger.error("Error while sending and email {} ",e.getMessage());
						}

						/*if (object != null && !object.isEmpty()) {
								logger.info("Object {}", object );
								if (object != null && object.containsKey("client_id")) {
									customer.setClientId((String) object.get("client_id"));

									service.updateCustomer(customer);
									message = environment.getProperty("email.message.success");
									logger.debug("Response {}" , object);
									logger.debug("Customer is saved with name {}", customer.getName());
								}
							} */

					} else {
						logger.error("Super admin not having right quay auth token to create application, try later");
						message=environment.getProperty("registration.failed");
						statusCode = HttpStatus.EXPECTATION_FAILED.toString();
					}
				} else {
					logger.error("Entitlement key is already been used.");
					message=environment.getProperty("registration.failed");					
					statusCode = HttpStatus.EXPECTATION_FAILED.toString();
				}
			} else {
				logger.error("Entitlement key is not found.");
				message=environment.getProperty("registration.failed");
				statusCode = HttpStatus.EXPECTATION_FAILED.toString();
			}
		} else {
			logger.error("Registration requires the entitlement key, please contact administrator.");
			message=environment.getProperty("registration.failed");
			statusCode = HttpStatus.EXPECTATION_FAILED.toString();
		}
		ApiResponseDTO response = new ApiResponseDTO(message, Integer.parseInt(statusCode));
		return response;
	}
    
    /**
     * RBAC and tenant rules
     * This is open API
     * Check whether the customer exists or not
     * @param dto
     * @param request
     * @return
     * @throws BaseException
     */
    @Path( "/api/customerExist" )
	@POST
	@ApiOperation( value = "Check whether the customer exists or not", notes = "Check whether the customer exists or not in "
			+ "repository. This is an open API.",response=ApiResponseDTO.class )
    @ApiResponses( {
	    @ApiResponse( code = 200, message = "Customer with this e-mail already exists" ),
	    @ApiResponse( code = 401, message = "Unauthorized" ),
	    @ApiResponse( code = 403, message = "Forbidden" ),
	    @ApiResponse( code = 404, message = "Customer with this e-mail not found" )
	} )
    @Transactional(rollbackFor = {BaseException.class})
    @RequestMapping(value = "/api/customerExist", method = RequestMethod.POST)
    public ApiResponseDTO isCustomerExist(@ApiParam(name="Customer object", value="The customer object that needs to be registered", required=true) @Valid @RequestBody CustomerExistDTO dto, HttpServletRequest request) throws CustomerExistsException, BaseException{
    	logger.debug("Adding a new customer entry with information: {}",dto.getEmail());
		String statusCode = null;
		ApiResponseDTO response = new ApiResponseDTO();
		if(dto.getKey()!=null){
			Entitlement entitlement = entlservice.findEntitlementByKey(dto.getKey());
			if (entitlement != null) {
			 if(service.findCustomerByEmail(dto.getEmail().toLowerCase())==null){
				 statusCode = HttpStatus.OK.toString();
				 response.setStatusCode(Integer.parseInt(statusCode));
				 response.setMessage(environment.getProperty("email.accept"));
			 }else{
					statusCode = HttpStatus.OK.toString();
					response.setStatusCode(Integer.parseInt(statusCode));
					response.setMessage(environment.getProperty("email.already.exists"));
				}
			 }else {
					logger.error("Entitlement key is not found.");
					response.setMessage(environment.getProperty("registration.failed"));
					response.setStatusCode(Integer.parseInt(HttpStatus.EXPECTATION_FAILED.toString()));
				}
		}
		return response;
		}
    
    
    
    /**
     * RBAC and tenant rules
     * This is an open API
     * Get the oragnization name
     * @param dto
     * @param request
     * @return
     * @throws CustomerExistsException
     * @throws BaseException
     */
    @Path( "/api/fetch" )
   	@POST
   	@ApiOperation( value = "Get the oragnization name", notes = "Get the oragnization name. This is an open API.", response=ResponseDTO.class )
   	@ApiResponses( {
   	    @ApiResponse( code = 200, message = "Entitlement key is validated, customer name is fetched"  ),
   	    @ApiResponse( code = 401, message = "Unauthorized" ),
   	    @ApiResponse( code = 403, message = "Forbidden" ),
   	    @ApiResponse( code = 417, message = "Entitlement key is not found" ),
   	    @ApiResponse( code = 417, message = "Registration requires the entitlement key, please contact administrator" )
   	  } )
    @Transactional(rollbackFor = {EntitlementNotFoundException.class, CustomerExistsException.class, BaseException.class})
    @RequestMapping(value = "/api/fetch", method = RequestMethod.POST)
    public ResponseDTO fetchCustomerName(@ApiParam(name="Customer object", value="The customer object whose name has to be fetched", required=true) @Valid @RequestBody FetchDTO dto, 
    		HttpServletRequest request) throws EntitlementNotFoundException, CustomerExistsException, BaseException{
    	logger.debug("Adding a new customer entry with information:{}", dto.getName());
		String name = null;
		if(dto.getName()!=null){
			Entitlement entitlement = entlservice.findEntitlementByKey(dto.getName());
			if(entitlement!=null && entitlement.getCustomer()==null){
				name = entitlement.getCustomerName();
			} else {
				throw new EntitlementNotFoundException(environment.getProperty("entitlment.notfound"));
			}
		} else {
			throw new EntitlementNotFoundException(environment.getProperty("entitlment.required"));			
		}
        ResponseDTO response = new ResponseDTO(environment.getProperty("entitlment.valid"), Integer.parseInt(HttpStatus.OK.toString()));
        response.setData(name);
        return response;
    }
    
    /**
     * RBAC and tenant rules
     * This API is allowed for Super Admin
     * Get the list of customers
     * @param request
     * @return
     * @throws NotAuthorizeException
     * @throws CustomerNotFoundException
     * @throws UserExistsException
     * @throws BaseException
     */
    @Path( "/api/customer" )
   	@GET
   	@ApiOperation( value = "Get all the customer list", notes = "User can get the list of customers in the respository. User with role Super Admin is allowed.",  response=CustomerResponseDTO.class, responseContainer = "List" )
   	@ApiResponses( {
   	    @ApiResponse( code = 200, message = "All the customers have been listed"),
   	    @ApiResponse( code = 401, message = "Unauthorized" ),
   	    @ApiResponse( code = 403, message = "Forbidden" ),
   	    @ApiResponse( code = 404, message = "Input customer not found" )
   	  } )
    @RequestMapping(value = "/api/customer", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('Super Admin')")
    public ResponseDTO listCCustomers(HttpServletRequest request) throws NotAuthorizeException, BaseException{
    	logger.debug("Fetching all customers by super admin ");
    	List<Customer> customers = service.findAllCustomers();
    	List<CustomerResponseDTO> dtos = new LinkedList<CustomerResponseDTO>();
    	for(Customer customer : customers){
    		dtos.add(createCustomerResponseDTO(customer));
    	}
    	logger.debug("Retrieved all customers.");
    	ResponseDTO response = new ResponseDTO(environment.getProperty("customers.retrived"), Integer.parseInt(HttpStatus.OK.toString()));
    	response.setData(dtos);
    	return response;
    }
    
    /**
     * RBAC and tenant rules
     * This API is allowed for Super Admin
     * Delete the customer with all data associated with that customer
     * @param request
     * @return
     * @throws NotAuthorizeException
     * @throws CustomerNotFoundException
     * @throws BaseException
     */
    @Path("/api/deletecustomer/{id}")
    @DELETE
	@ApiOperation( value = "Delete the customer", notes = "User can delete the customer which in turn deletes all peer information like users, nodes, entitlements etc . User with role Super Admin only allowed. "
			+ " Super Admin can not delete himself", response = ApiResponseDTO.class)
    @ApiResponses( {
	    @ApiResponse( code = 200, message = "Customer deleted successfully successfully." ),
	    @ApiResponse( code = 401, message = "Unauthorized User" ),
   	    @ApiResponse( code = 403, message = "User Forbidden" ),
	    @ApiResponse( code = 404, message = "Customer not found." )
	} )
    @Transactional(rollbackFor = {ValidationException.class, NotAuthorizeException.class, CustomerNotFoundException.class, UserNotFoundException.class, BaseException.class})
    @RequestMapping(value = "/api/deletecustomer/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAuthority('Super Admin')")
    public ApiResponseDTO customerDelete(@ApiParam( name="Customer id", value="The customer id that needs to be deleted", required=true ) @PathVariable("id") String id, HttpServletRequest request) 
    		throws ValidationException, NotAuthorizeException, CustomerNotFoundException, 
    		GraphanaFailedException, java.lang.AssertionError, JsonParseException, JsonMappingException, IOException, BaseException{
       
        String loggedInEmail = getLoggedInUserEmail(SecurityContextHolder.getContext().getAuthentication());
		User loggedInUser = userservice.findUserByEmail(loggedInEmail);
		ApiResponseDTO response = null;
		
		if(StringUtils.isEmpty(id) || !StringUtils.isNumeric(id)){
			throw new ValidationException(environment.getProperty("customer.id.notnull"));
		}
		
		if (environment.getProperty("grafana.host") != null)
			grafanaUrl = environment.getProperty("grafana.host");
		
		if (environment.getProperty("influxdb.url") != null)
			influxUrl = environment.getProperty("influxdb.url");
		
		if (environment.getProperty("grafana.username") != null)
			grafanaUser = environment.getProperty("grafana.username");
		
		if (environment.getProperty("grafana.password") != null)
			grafanaPassword = environment.getProperty("grafana.password");
		
    	Customer customer =  service.findById(Integer.parseInt(id));
    	if(customer == null){
    		response = new ApiResponseDTO(environment.getProperty("customer.delete.notfound"), Integer.parseInt(HttpStatus.NOT_FOUND.toString()));
    	}
    		User customerUser= userservice.findUserByEmail(customer.getEmail());
    		if(customerUser == null){
    			response = new ApiResponseDTO(environment.getProperty("user.not.found"), Integer.parseInt(HttpStatus.NOT_FOUND.toString()));
    		}
    		if(loggedInUser.getId() == customerUser.getId()){
				throw new NotAuthorizeException(environment.getProperty("loggedincustomer.not.delete"));
			}
    		
    		GrafanaClient.deleteOrg(customer, grafanaUrl,grafanaUser,grafanaPassword);
    		
    		if(NvidiaUtil.checkInfluxDBForCustomer(customer)){
    			Entitlement entitlement= getEntitlementForCustomer(customer);
        		if(entitlement == null){
        			GrafanaClient.deleteInfluxDBDatabase(influxUrl, databaseName);
        		}else{
        			GrafanaClient.deleteInfluxDBDatabase(entitlement.getInfluxdbUrl(), entitlement.getDatabaseName());

        		}
    		}
    		
    		List<User> userList = userservice.findAllUsers(customer.getId());
    		for(User u: userList){
    			User user = userservice.findUserByEmail(u.getEmail());
    			if(user !=null){
        			if(user.getqAuthToken()!=null && !user.getqAuthToken().isEmpty()){
        	    		User superAdminUser = userservice.findUserByEmail(ServicesConstants.DEFAULT_ADMIN_EMAIL);
        	    		if (superAdminUser.getqAuthToken() != null && !superAdminUser.getqAuthToken().isEmpty()) {
        	    			try{
        		    		QuayClient.deleteUser(environment.getProperty("quay.url"), "Bearer " + superAdminUser.getqAuthToken(),user.getUserName());
        	    			}catch(Exception e){
        	        			logger.error("Error in deleting user in quay {}",e.getMessage());
        	        		}
        	    		}
            		}
        			
        		}
    		}
    		service.deleteCustomer(customer);	
        	logger.debug("Deleted a customer with id:{}",id);
    
    	 response = new ApiResponseDTO(environment.getProperty("customer.deleted.success"), Integer.parseInt(HttpStatus.OK.toString()));
		 return response;
    }
    
private Entitlement getEntitlementForCustomer(Customer customer) {
    	
		if (customer != null) {
			List<Entitlement> entitlements=null;
			try {
				entitlements = entlservice.findEntitlementByName(customer.getName());
			} catch (EntitlementNotFoundException e) {
				logger.error("Error while fetching entitlement {}"+e.getMessage());
			}
			Entitlement entitlement = null;
			if (entitlements != null && entitlements.size() > 0)
				entitlement = entitlements.get(0);
			if (entitlement != null && entitlement.getInfluxdbUrl() != null
					&& entitlement.getInfluxdbUser() != null && entitlement.getDatabaseName() !=null && !entitlement.getDatabaseName().isEmpty()) {
				return entitlement;
			} 
       }
		return null;
    }



}
