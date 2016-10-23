package com.nvidia.cosmos.cloud.rest.controller;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.xml.bind.ValidationException;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.NoSuchMessageException;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nvidia.cosmos.cloud.dtos.ApiResponseDTO;
import com.nvidia.cosmos.cloud.dtos.EntitlementDTO;
import com.nvidia.cosmos.cloud.dtos.EntitlementNamesRespoponseDTO;
import com.nvidia.cosmos.cloud.dtos.EntitlementResponseDTO;
import com.nvidia.cosmos.cloud.dtos.ResponseDTO;
import com.nvidia.cosmos.cloud.exceptions.BaseException;
import com.nvidia.cosmos.cloud.exceptions.EntitlementExistsException;
import com.nvidia.cosmos.cloud.exceptions.EntitlementNotFoundException;
import com.nvidia.cosmos.cloud.exceptions.SerialNumberExitsException;
import com.nvidia.cosmos.cloud.exceptions.UserExistsException;
import com.nvidia.cosmos.cloud.rest.exception.NotAuthorizeException;
import com.nvidia.cosmos.cloud.services.customer.model.Customer;
import com.nvidia.cosmos.cloud.services.customer.service.CustomerService;
import com.nvidia.cosmos.cloud.services.entitlement.model.Entitlement;
import com.nvidia.cosmos.cloud.services.entitlement.service.EntitlementService;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

/**
 * This controller will help to register entitlements and also CRUD methods for
 * a entitlement
 *
 * 
 */
@RestController
public class EntitlementController extends BaseController {

	/**
	 * 
	 */
	private static Logger logger = LoggerFactory.getLogger(EntitlementController.class);
	/**
	 * 
	 */
	@Autowired
	Environment environment;

	EntitlementService service;
	CustomerService customerService;

	/**
	 * 
	 */
	public EntitlementController() {
		service = factory.getEntitlementService();
		customerService = factory.getCustomerService();
	}

	/**
	 * RBAC and tenant rules This API is allowed to Super Admin Super Admin can
	 * create entitlement
	 * 
	 * @param dto
	 * @param request
	 * @return
	 * @throws SerialNumberExitsException
	 * @throws EntitlementExistsException
	 * @throws EntitlementNotFoundException
	 * @throws ValidationException
	 * @throws Exception
	 * @throws NoSuchMessageException
	 */
	@Path("/api/entitlement")
	@POST
	@ApiOperation(value = "Create a new entitlement", notes = "Create a new entitlement. User with role Super Admin is allowed.", response = ApiResponseDTO.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Entitlemenet has been created successfully"),
			@ApiResponse(code = 201, message = "Entitlemenet Created"),
			@ApiResponse(code = 401, message = "User is unauthorized to create Entitlement"),
			@ApiResponse(code = 403, message = "User forbidden"),
			@ApiResponse(code = 404, message = "Entitlement not Found") })
	@Transactional(rollbackFor = { EntitlementExistsException.class, SerialNumberExitsException.class,
			BaseException.class })
	@RequestMapping(value = "/api/entitlement", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('Super Admin')")
	public ApiResponseDTO register(
			@ApiParam(name = "Entitlement object", value = "The entitlement object that needs to be created", required = true) @Valid @RequestBody EntitlementDTO dto,
			BindingResult result, HttpServletRequest request) throws EntitlementExistsException,
			SerialNumberExitsException, EntitlementNotFoundException, BaseException, ValidationException {

		if (result.hasErrors()) {
			List<ObjectError> resulterrors = result.getAllErrors();
			for (ObjectError error : resulterrors) {
				throw new ValidationException(error.getDefaultMessage());
			}
		}
		logger.debug("Adding a new entitlement entry with information:" + new Object[] { dto });
		String message = null;
		String statusCode = HttpStatus.OK.toString();

		Entitlement entitlement = new Entitlement(dto.getName(), dto.getKey(), dto.getSerialNumber());
		entitlement.setInfluxdbUrl(dto.getInfluxdbUrl());
		entitlement.setInfluxdbUser(dto.getInfluxdbUser());
		entitlement.setInfluxdbPassword(dto.getInfluxdbPassword());
	    entitlement.setDatabaseName(dto.getDatabaseName());
		List<Entitlement> existingEntitlements = service.findEntitlementByName(dto.getName());
		if (existingEntitlements.size() > 0) {
			Customer customer = existingEntitlements.get(0).getCustomer();
			if (customer != null) {
				String customerEmail = customer.getEmail();
				Customer customer1 = customerService.findCustomerByEmail(customerEmail);
				entitlement.setCustomer(customer1);
			}
		}

		service.saveEntitlement(entitlement);
		entitlement = service.findEntitlementByKey(dto.getKey());
		// message = "Entitlement with name "+entitlement.getCustomerName()+"
		// saved.";
		message = environment.getProperty("entitlement.name.saved");
		logger.debug("Entitlement is saved with name {}", entitlement.getCustomerName());
		ApiResponseDTO response = new ApiResponseDTO(message, Integer.parseInt(statusCode));
		return response;
	}

	/**
	 * RBAC and tenant rules This API is allowed to Super Admin Super Amdin can
	 * get list of entitlements in the repository
	 * 
	 * @param request
	 * @return
	 * @throws NotAuthorizeException
	 * @throws EntitlementNotFoundException
	 * @throws UserExistsException
	 * @throws BaseException
	 */
	@Path("/api/entitlement")
	@GET
	@ApiOperation(value = "Get all the entitlement list ", notes = "User can get list of entitlements in the repository . User with role Super Admin is allowed.", response = ResponseDTO.class, responseContainer = "List")
	@ApiResponses({ @ApiResponse(code = 200, message = "All the entitlements have been listed"),
			@ApiResponse(code = 401, message = "User is unauthorized"),
			@ApiResponse(code = 403, message = "User forbidden"),
			@ApiResponse(code = 404, message = "Entitlements not Found") })

	@RequestMapping(value = "/api/entitlement", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('Super Admin')")
	public ResponseDTO listCEntitlements(HttpServletRequest request) throws NotAuthorizeException, BaseException {
		logger.debug("Fetching all entitlements by super admin ");
		String message = null;
		String statusCode = HttpStatus.OK.toString();
		List<Entitlement> entitlements = service.findAllEntitlements();
		List<EntitlementResponseDTO> dtos = new LinkedList<EntitlementResponseDTO>();
		for (Entitlement entitlement : entitlements) {
			dtos.add(createDTO(entitlement));
		}
		// message = "All entitlements are retrieved.";
		message = environment.getProperty("entitlement.retrieved");
		logger.debug("Retrieved all entitlements.");
		ResponseDTO response = new ResponseDTO(message, Integer.parseInt(statusCode));
		response.setData(dtos);
		return response;
	}

	/**
	 * RBAC and tenant rules This API is allowed to Super Admin Super Amdin can
	 * get list of entitlement names in the repository based on given
	 * entitlement name
	 * 
	 * @param request
	 * @return
	 * @throws NotAuthorizeException
	 * @throws BaseException
	 */
	@Path("/api/entitlement/{entitlementName}")
	@GET
	@ApiOperation(value = "Get the entitlement list of entitlement names", notes = "User can get list of entitlement names in the repository based on given entitlement name . User with role Super Admin is allowed. ", response = ResponseDTO.class, responseContainer = "List")
	@ApiResponses({ @ApiResponse(code = 200, message = "All the entitlement names have been listed"),
			@ApiResponse(code = 401, message = "User is unauthorized"),
			@ApiResponse(code = 403, message = "User forbidden"),
			@ApiResponse(code = 404, message = "Entitlements not Found") })
	@Transactional(rollbackFor = { ValidationException.class,NotAuthorizeException.class, BaseException.class })
	@RequestMapping(value = "/api/entitlement/{entitlementName}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('Super Admin')")
	public ResponseDTO listEntitlementNames(
			@ApiParam(name = "Entitlement object", value = "Organization name to fetch entitlements", required = true) @Valid @NotBlank @NotEmpty @NotNull @PathVariable("entitlementName") String entitlementName,
			HttpServletRequest request) throws ValidationException,NotAuthorizeException, BaseException {

		logger.debug("Fech all entitlement names");
		if(entitlementName.trim().length() < 4 || entitlementName.trim().length() >50 || StringUtils.containsWhitespace(entitlementName.trim())){
			throw new ValidationException(environment.getProperty("entitlement.name.notnull"));
		}

		List<Entitlement> entitlements = service.findEntitlementNames(entitlementName);
		List<EntitlementNamesRespoponseDTO> dtos = new LinkedList<EntitlementNamesRespoponseDTO>();
		for (Entitlement entitlement : entitlements) {
			dtos.add(createEntitlementNamesDTO(entitlement));
		}

		logger.debug("Listed all entitlement names");
		ResponseDTO response = new ResponseDTO(environment.getProperty("entitlement.entitlementnames"),
				Integer.parseInt(HttpStatus.OK.toString()));
		response.setData(dtos);
		return response;
	}
	
	/**
	 * RBAC and tenant rules This API is allowed to Super Admin Super Amdin can
	 * get entitlement in the repository based on given
	 * entitlement name
	 * 
	 * @param request
	 * @return
	 * @throws NotAuthorizeException
	 * @throws BaseException
	 */
	@Path("/api/fetchentitlement/{entitlementName}")
	@GET
	@ApiOperation(value = "Get the entitlement entitlement by entitlement name", notes = "User can get entitlement in the repository based on given entitlement name . User with role Super Admin is allowed. ", response = ResponseDTO.class, responseContainer = "List")
	@ApiResponses({ @ApiResponse(code = 200, message = "Entitlement object id fetched"),
			@ApiResponse(code = 401, message = "User is unauthorized"),
			@ApiResponse(code = 403, message = "User forbidden"),
			@ApiResponse(code = 404, message = "Entitlements not Found") })
	@Transactional(rollbackFor = {ValidationException.class, NotAuthorizeException.class, BaseException.class })
	@RequestMapping(value = "/api/fetchentitlement/{entitlementName}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('Super Admin')")
	public ResponseDTO findEntitlementByName(
			@ApiParam(name = "Entitlement object", value = "Organization name to fetch entitlements", required = true) @Valid @NotBlank @NotEmpty @NotNull @PathVariable("entitlementName") String entitlementName,
			HttpServletRequest request) throws ValidationException,NotAuthorizeException, BaseException {

		logger.debug("Fech entitlement name by name");
		ResponseDTO response =null;
		if(entitlementName.trim().length() < 4 || entitlementName.trim().length() >50 || StringUtils.containsWhitespace(entitlementName.trim())){
			throw new ValidationException(environment.getProperty("entitlement.name.notnull"));
		}
		
		List<Entitlement> existingEntitlements = service.findEntitlementByName(entitlementName);
		if (existingEntitlements.size() > 0) {
			response = new ResponseDTO(environment.getProperty("entitlement.retrived"),Integer.parseInt(HttpStatus.OK.toString()));
			response.setData(entitlementObjectResponseDTO(existingEntitlements.get(0)));		
		}
		else{
			response = new ResponseDTO(environment.getProperty("entitlement.not.present"),Integer.parseInt(HttpStatus.OK.toString()));
		}
        logger.debug("Feched entitlement by entitlement name");
		return response;
	}
}
