package com.nvidia.cosmos.cloud.rest.controller;

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
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nvidia.cosmos.cloud.auth.encryptor.EncryptorFactory;
import com.nvidia.cosmos.cloud.common.ServicesConstants;
import com.nvidia.cosmos.cloud.dtos.ApiResponseDTO;
import com.nvidia.cosmos.cloud.dtos.CreateUserDTO;
import com.nvidia.cosmos.cloud.dtos.CreateUserResponseDTO;
import com.nvidia.cosmos.cloud.dtos.DeleteUserDTO;
import com.nvidia.cosmos.cloud.dtos.ProfileDTO;
import com.nvidia.cosmos.cloud.dtos.ResponseDTO;
import com.nvidia.cosmos.cloud.dtos.UpdateUserDTO;
import com.nvidia.cosmos.cloud.dtos.UpdateUserPwdDTO;
import com.nvidia.cosmos.cloud.dtos.ValidateUserDTO;
import com.nvidia.cosmos.cloud.exceptions.BaseException;
import com.nvidia.cosmos.cloud.exceptions.CustomerNotFoundException;
import com.nvidia.cosmos.cloud.exceptions.UserExistsException;
import com.nvidia.cosmos.cloud.exceptions.UserNotFoundException;
import com.nvidia.cosmos.cloud.rest.exception.NotAuthorizeException;
import com.nvidia.cosmos.cloud.rest.util.NvidiaUtil;
import com.nvidia.cosmos.cloud.rest.util.QuayClient;
import com.nvidia.cosmos.cloud.services.customer.model.Customer;
import com.nvidia.cosmos.cloud.services.customer.service.CustomerService;
import com.nvidia.cosmos.cloud.services.role.model.Role;
import com.nvidia.cosmos.cloud.services.role.service.RoleService;
import com.nvidia.cosmos.cloud.services.user.model.User;
import com.nvidia.cosmos.cloud.services.user.service.UserService;
import com.nvidia.cosmos.cloud.util.EncryptionUtil;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

/**
 * This controller will help to register users and also CRUD methods for a user
 *
 * 
 */
@RestController
public class UserController extends BaseController {

	/**
	 * 
	 */
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	/**
	 * 
	 */
	@Autowired
	MessageSource messageSource;
	/**
	 * 
	 */
	UserService service;
	CustomerService customerService;
	RoleService roleService;

	/**
	 * 
	 */
	public UserController() {
		service = factory.getUserService();
		customerService = factory.getCustomerService();
		roleService = factory.getRoleService();
	}

	private class UserValidator implements Validator {
		public void validate(CreateUserDTO obj, Errors errors) throws NumberFormatException, UserNotFoundException {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",environment.getProperty("user.name.notempty"));
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "uId", environment.getProperty("user.uid.notempty"));
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gId", environment.getProperty("user.gid.notempty"));
			
			if (obj.getName().trim().length() > 64) {
				errors.rejectValue("name", environment.getProperty("user.name.length"));
			}
			
			if(StringUtils.isEmpty(obj.getuId()) || !StringUtils.isNumeric(obj.getuId())){
				errors.rejectValue("uId", environment.getProperty("user.uid.numeric"));
			}
			if(StringUtils.isEmpty(obj.getgId()) || !StringUtils.isNumeric(obj.getgId())){
				errors.rejectValue("uId", environment.getProperty("user.gid.numeric"));
			}
			
			if (obj.getuId() != null && !StringUtils.isEmpty(obj.getuId()) && StringUtils.isNumeric(obj.getuId()) && Long.parseLong(obj.getuId()) > 999999) {
				errors.rejectValue("uId", environment.getProperty("user.uid.length"));
			}
			if (obj.getuId() != null && !StringUtils.isEmpty(obj.getuId()) && StringUtils.isNumeric(obj.getuId()) && Long.parseLong(obj.getuId()) <= 0) {
				errors.rejectValue("uId", environment.getProperty("user.uid.value"));
			}
			if (obj.getgId() != null && !StringUtils.isEmpty(obj.getgId()) && StringUtils.isNumeric(obj.getgId()) && Long.parseLong(obj.getgId()) > 999999) {
				errors.rejectValue("gId", environment.getProperty("user.gid.length"));
			}
			if (obj.getgId() != null && !StringUtils.isEmpty(obj.getgId()) && StringUtils.isNumeric(obj.getgId()) &&Long.parseLong(obj.getgId()) <= 0) {
				errors.rejectValue("gId", environment.getProperty("user.gid.value"));
			}
		}
		public void validateUpdate(UpdateUserDTO obj, Errors errors) throws NumberFormatException, UserNotFoundException {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "uId", environment.getProperty("user.uid.notempty"));
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gId", environment.getProperty("user.gid.notempty"));
			if(StringUtils.isEmpty(obj.getuId()) || !StringUtils.isNumeric(obj.getuId())){
				errors.rejectValue("uId", environment.getProperty("user.uid.numeric"));
			}
			if(StringUtils.isEmpty(obj.getgId()) || !StringUtils.isNumeric(obj.getgId())){
				errors.rejectValue("uId", environment.getProperty("user.gid.numeric"));
			}
			
			if (obj.getuId() != null && !StringUtils.isEmpty(obj.getuId()) && StringUtils.isNumeric(obj.getuId()) && Long.parseLong(obj.getuId()) > 999999) {
				errors.rejectValue("uId", environment.getProperty("user.uid.length"));
			}
			if (obj.getuId() != null && !StringUtils.isEmpty(obj.getuId()) && StringUtils.isNumeric(obj.getuId()) && Long.parseLong(obj.getuId()) <= 0) {
				errors.rejectValue("uId", environment.getProperty("user.uid.value"));
			}
			if (obj.getgId() != null && !StringUtils.isEmpty(obj.getgId()) && StringUtils.isNumeric(obj.getgId()) && Long.parseLong(obj.getgId()) > 999999) {
				errors.rejectValue("gId", environment.getProperty("user.gid.length"));
			}
			if (obj.getgId() != null && !StringUtils.isEmpty(obj.getgId()) && StringUtils.isNumeric(obj.getgId()) &&Long.parseLong(obj.getgId()) <= 0) {
				errors.rejectValue("gId", environment.getProperty("user.gid.value"));
			}
		}

		public void validateResetPwd(UpdateUserPwdDTO obj, Errors errors)
				throws NumberFormatException, UserNotFoundException {

			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
					environment.getProperty("user.password.notempty"));
			
			 if(obj.getPassword() != null && (obj.getPassword().length() > 64 || obj.getPassword().length() < 8)) {
			  errors.rejectValue("password", environment.getProperty("user.password.length"));
			  }
		}
		public void validateUpdateProfile(ProfileDTO obj, Errors errors)
				throws UserNotFoundException {

			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",environment.getProperty("user.name.notempty"));
			if (obj.getName().trim().length() > 64) {
				errors.rejectValue("name", environment.getProperty("user.name.length"));
			}
		}
		
		
		@Override
		public boolean supports(Class<?> arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void validate(Object arg0, Errors arg1) {
			// TODO Auto-generated method stub

		}
	}

	/**
	 * RBAC and tenant rules
	 * This API is allowed to Customer Admin
	 * User with role Customer Admin can create users
	 * @param dto
	 * @param request
	 * @return
	 * @throws UserExistsException
	 * @throws BaseException
	 */
	@Path("/api/user/{customerId}")
	@POST
	@ApiOperation(value = "Create new user", notes = "User can create a new user under his organization. User with role Customer Admin is allowed.", response = CreateUserResponseDTO.class)
	@ApiResponses( {
	    @ApiResponse( code = 200, message = "User created successfully" ),
	    @ApiResponse( code = 401, message = "Unauthorized User" ),
	    @ApiResponse( code = 403, message = "User Forbidden" ),
	    @ApiResponse( code = 404, message = "Customer not found" )
	} )
	@Transactional(rollbackFor = { ValidationException.class, NotAuthorizeException.class,
			CustomerNotFoundException.class, UserExistsException.class, BaseException.class })
	@RequestMapping(value = "/api/user/{customerId}", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('Customer Admin')")
	public ResponseDTO register(
			@ApiParam(name = "Customer Id", value = "The id of the customer under which the user needs to be created", required = true) @PathVariable("customerId") String customerId,
			@ApiParam(name = "User object", value = "The user object that needs to be created", required = true) @Valid @RequestBody CreateUserDTO dto,
			BindingResult result, HttpServletRequest request) throws ValidationException, NotAuthorizeException,
			CustomerNotFoundException, UserExistsException, BaseException, Exception {
		logger.debug("Adding a new user entry with information:{}" , dto.getEmail());
		
		UserValidator userValidator = new UserValidator();
		userValidator.validate(dto, result);
		List<ObjectError> resulterrors = result.getAllErrors();

		if (result.hasErrors()) {
			for (ObjectError error : resulterrors) {

				if (error.getDefaultMessage() == null) {

					throw new ValidationException(error.getCode());
				} else {

					throw new ValidationException(error.getDefaultMessage());
				}

			}

		} 
		if(StringUtils.isEmpty(customerId) || !StringUtils.isNumeric(customerId)){
			throw new ValidationException(environment.getProperty("customer.id.notnull"));
		}
		String message = null;
		String statusCode = HttpStatus.OK.toString();
		
		
		ResponseDTO response = null;
			Customer customer = customerService.findById(Integer.parseInt(customerId));
			Role role = roleService.findRoleByName(ServicesConstants.CUSTOMER_USER);

			User user = new User(dto.getName().trim(), dto.getEmail().toLowerCase(), role, customer);
			user.setgId(Long.parseLong(dto.getgId()));
			user.setuId(Long.parseLong(dto.getuId()));
			service.saveUser(user);
			
			
			try {
				
				user  = service.findUserByEmail(dto.getEmail().toLowerCase());				
				String encryptEmail = NvidiaUtil.getEncrypt("" + user.getId(), customer.getEmail(), true, "0", 
						Integer.parseInt(environment.getProperty("reset.hours")), NvidiaUtil.getCalenderTime(
		                          Integer.parseInt(environment.getProperty("reset.hours"))));

			    user.setPasswordKey(encryptEmail);
				service.mergeUser(user);
				
				String subject = "Your Account has been registered with NVIDIA";
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("userName", user.getName());
				model.put("resetPasswordLink",
						environment.getProperty("tomcat.url") + "reset/reset-p.html#!/reset/" + encryptEmail);
				model.put("loginLink", environment.getProperty("tomcat.url") + "login.html");
				model.put("userEmail", user.getEmail());
				model.put("message", "Your account has been provisioned on NVIDIA");
				model.put("corporateHeader", environment.getProperty("tomcat.url") + "img/NVIDIA-Corporate-Header.png");
				initMailThread();
				NvidiaUtil.executeSendMailsThread(user.getEmail(), subject, model, "account.vm");
			} catch (Exception e) {
				
			}
			logger.info(environment.getProperty("user.create.success"),user.getUserName(),customer.getName());
			message = environment.getProperty("user.saved.name");
			logger.debug("User is saved with name {}", user.getName());
			response = new ResponseDTO(message, Integer.parseInt(statusCode));
			response.setData(createUserResponseDTO(user));			
			
		return response;
	}

	/**
	 * RBAC and tenant rules
	 * User with role Customer Admin and Super Admin can edit the user GID, UID
	 * Super Admin can edit all users in the repository
	 * Customer Admin can edit only users under his organization
	 * @param dto
	 * @param request
	 * @return
	 * @throws UserExistsException
	 * @throws BaseException
	 */
	@Path("/api/user")
	@POST
	@ApiOperation(value = "Update user details", notes = "Update user's GID and UID. User with role Customer Admin or Super Admin is allowed "
			+ "User with role Customer Admin can edit the GID, UID of users under his organization. Super Admin can edit all users in the repository", 
			response = CreateUserResponseDTO.class)
	@ApiResponses( {
	    @ApiResponse( code = 200, message = "User details have been updated successfully" ),
	    @ApiResponse( code = 401, message = "Unauthorized User" ),
	    @ApiResponse( code = 403, message = "User Forbidden" ),
	    @ApiResponse( code = 404, message = "User not found" )
	} )
	@Transactional(rollbackFor = { ValidationException.class, NotAuthorizeException.class,
			CustomerNotFoundException.class, UserExistsException.class, BaseException.class })
	@RequestMapping(value = "/api/user", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('Super Admin','Customer Admin')")
	public ResponseDTO updateUser(
			@ApiParam(name = "User object", value = "The user object that needs to be updated", required = true) @Valid @RequestBody UpdateUserDTO dto,
			BindingResult result, HttpServletRequest request)
			throws NotAuthorizeException, ValidationException, UserExistsException, BaseException {
		logger.debug("Adding a new user entry with information:{}" ,dto.getId());
		

		UserValidator userValidator = new UserValidator();
		userValidator.validateUpdate(dto, result);
		List<ObjectError> resulterrors = result.getAllErrors();

		if (result.hasErrors()) {
			for (ObjectError error : resulterrors) {

				if (error.getDefaultMessage() == null) {
					throw new ValidationException(error.getCode());
               } else {
					throw new ValidationException(error.getDefaultMessage());
               }
			}

		} 
		
		String loggedInEmail = getLoggedInUserEmail(SecurityContextHolder.getContext().getAuthentication());
		String roleName = getLoggedInUserRoleName(SecurityContextHolder.getContext().getAuthentication());
		User user = service.findUserByEmail(loggedInEmail);
		if(StringUtils.isEmpty(dto.getId()) || !StringUtils.isNumeric(dto.getId())){
			throw new ValidationException(environment.getProperty("user.id.notnull"));
		}
		User updateUser = service.findById(Long.parseLong(dto.getId()));
		
		ResponseDTO response = null;
			if (updateUser == null) {
				return new ResponseDTO(environment.getProperty("user.not.found"), Integer.parseInt(HttpStatus.OK.toString()));
			}	
				
				// If user role is customer admin only users under his organization can be edited
				if (roleName.equals(ServicesConstants.CUSTOMER_ADMIN)) {
					User dbUser= service.findByIdAndCustomerId(Long.parseLong(dto.getId()), user.getCustomer().getId());
					if(dbUser == null){
						throw new NotAuthorizeException(environment.getProperty("user.operation.notallowed"));
					}
				}
				
				
				updateUser.setgId(Long.parseLong(dto.getgId()));
				updateUser.setuId(Long.parseLong(dto.getuId()));
				service.mergeUser(updateUser);

				user = service.findById(updateUser.getId());
				logger.info(environment.getProperty("user.update.success"),updateUser.getUserName(),updateUser.getCustomer().getName());
				response = new ResponseDTO(environment.getProperty("user.updated.name"), Integer.parseInt(HttpStatus.OK.toString()));
				logger.debug("User is saved with name {}", user.getName());
				response.setData(createUserResponseDTO(user));
			
		return response;

	}
	
	
	/**
	 * RBAC and tenant rules
	 * This method was used for docker images. This is not required now.
	 * User with role Super Admin can get list of all users
	 * @return
	 * @throws UserExistsException
	 * @throws BaseException
	 *//*
	@Path("/api/users")
	@GET
	@ApiOperation(value = "Get all user list", notes = "List all users", response = User.class, responseContainer = "List")
	@ApiResponses( {
        @ApiResponse( code = 200, message = "All the users have been listed" ),
	    @ApiResponse( code = 401, message = "Unauthorized User" ),
	    @ApiResponse( code = 403, message = "User Forbidden" ),
	    @ApiResponse( code = 404, message = "Users not found" )
	} )
	@Transactional(rollbackFor = { NotAuthorizeException.class, CustomerNotFoundException.class,
			UserExistsException.class, BaseException.class })
	@RequestMapping(value = "/api/users", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('Super Admin')")
	public ResponseDTO listAllUsers(HttpServletRequest request)
			throws NotAuthorizeException, CustomerNotFoundException, UserExistsException, BaseException {
		logger.debug("Fetching all users all users");
		List<User> users = service.findAllUsers();
		List<UserDTO> dtos = new LinkedList<UserDTO>();
		for (User user : users) {
			dtos.add(createDTO(user));
		}
		
		ResponseDTO response = new ResponseDTO(environment.getProperty("users.all.retrived"), Integer.parseInt(HttpStatus.OK.toString()));
		response.setData(dtos);
		return response;
	}*/

	
	/**
	 * RBAC and tenant rules
	 * This API is allowed to Super Admin, Customer Admin
	 * User with role Customer Admin and Super Admin can get list of users based on customer ID
	 * Super Admin can get list of all users for the given custome ID
	 * Customer Admin can get list of users only under his organization
	 * @param request
	 * @return
	 * @throws UserExistsException
	 * @throws BaseException
	 */
	@Path("/api/user/{customerId}")
	@GET
	@ApiOperation(value = "Get all the user list by customer", notes = "List all the users by given customer ID. User with role Customer Admin or Super Admin is allowed. "
			+ "Super Admin can get list of all users for the given customer ID. Customer Admin can get list of users only under his organization", response = CreateUserResponseDTO.class, responseContainer = "List")
	 @ApiResponses( {
	    	@ApiResponse( code = 200, message = "All the users by the customer have been listed" ),
		    @ApiResponse( code = 401, message = "Unauthorized User" ),
		    @ApiResponse( code = 403, message = "User Forbidden" ),
		    @ApiResponse( code = 404, message = "Customer not found" )
		} )
	@Transactional(rollbackFor = {ValidationException.class, NotAuthorizeException.class,
			CustomerNotFoundException.class, UserExistsException.class, BaseException.class })
	@RequestMapping(value = "/api/user/{customerId}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('Super Admin','Customer Admin')")
	public ResponseDTO listUsers(
			@ApiParam(name = "Customer Id", value = "The id of the customer whose users have to be listed", required = true) @PathVariable("customerId") String customerId,
			HttpServletRequest request) throws ValidationException, NotAuthorizeException, CustomerNotFoundException,
			UserExistsException, BaseException {
		logger.debug("Fetching all users for a given customer {}" , customerId);
		
		String loggedInEmail = getLoggedInUserEmail(SecurityContextHolder.getContext().getAuthentication());
		String roleName = getLoggedInUserRoleName(SecurityContextHolder.getContext().getAuthentication());
		Customer cus = customerService.findCustomerByEmail(loggedInEmail);
		if(StringUtils.isEmpty(customerId) || !StringUtils.isNumeric(customerId)){
			throw new ValidationException(environment.getProperty("customer.id.notnull"));
		}
		//If requested customer id and logged in customer does not match, user is not authorized to do this operation on other customer users
		// If role is customer Admin
		if (roleName.equals(ServicesConstants.CUSTOMER_ADMIN) && cus != null && cus.getId() != Integer.parseInt(customerId)) {
			throw new NotAuthorizeException(environment.getProperty("user.operation.notallowed"));
		}
		
		List<User> users = service.findAllUsers(Integer.parseInt(customerId));
		List<CreateUserResponseDTO> dtos = new LinkedList<CreateUserResponseDTO>();
		for (User user : users) {
			dtos.add(createUserResponseDTO(user));
		}

		logger.debug("Retrieved all customer users for the given customer id {}", customerId);
		ResponseDTO response = new ResponseDTO(environment.getProperty("users.all.retrived"), Integer.parseInt(HttpStatus.OK.toString()));
		response.setData(dtos);
		return response;
	}

	
	/**
	 * RBAC and tenant rules
	 * This method is allowed for user with all roles(Super Admin, Customer Admin, Customer User)
	 * Details of the logged in user will be displayed
	 * Always it returns logged in user information
	 * @param request
	 * @return
	 * @throws UserNotFoundException
	 * @throws BaseException
	 */
	@Path("api/user/profile")
	@GET
	@ApiOperation(value = "Get logged in user profile details", notes = "User can get only logged in user profile details. User with all role Super Admin, "
			+ "Customer Admin or Customer User is allowed.", response = CreateUserResponseDTO.class)
	@ApiResponses( {
	    @ApiResponse( code = 200, message = "User profile details retrieved successfully" ),
	    @ApiResponse( code = 401, message = "Unauthorized User" ),
	    @ApiResponse( code = 403, message = "User Forbidden" ),
	    @ApiResponse( code = 404, message = "User not found" )
	} )
	@Transactional(rollbackFor = { NotAuthorizeException.class, UserNotFoundException.class, BaseException.class })
	@RequestMapping(value = "/api/user/profile", method = RequestMethod.GET)
	public ResponseDTO findUserProfile(HttpServletRequest request)
			throws NotAuthorizeException, UserNotFoundException, BaseException {
		
		String loggedInEmail = getLoggedInUserEmail(SecurityContextHolder.getContext().getAuthentication());
		logger.debug("Fetching user");
		User user = service.findUserByEmail(loggedInEmail);
		ResponseDTO response = new ResponseDTO(environment.getProperty("user.retrived"), Integer.parseInt(HttpStatus.OK.toString()));
		response.setData(createUserResponseDTO(user));
		return response;
	}

	
	/**
	 * RBAC and tenant rules
	 * This method is allowed for user with all roles(Super Admin, Customer Admin, Customer User)
	 * Logged in user can update his own password, operation is not allowed to update other users
	 * @param dto
	 * @param request
	 * @return
	 * @throws UserNotFoundException
	 * @throws BaseException
	 */
	@Path("/api/profile/resetpwd")
	@POST
	@ApiOperation(value = "Update the logged in user's password", notes = "Update the logged in user's password. User with all role Super Admin or "
			+ "Customer Admin or Customer User is allowed. Logged in user can update his own password, operation is not allowed to update other users", response = ApiResponseDTO.class)
	@ApiResponses( {
	    @ApiResponse( code = 200, message = "Password updated successfully" ),
	    @ApiResponse( code = 203, message = "Please enter correct current password." ),
	    @ApiResponse( code = 401, message = "Unauthorized User" ),
	    @ApiResponse( code = 403, message = "User Forbidden" ),
	    @ApiResponse( code = 404, message = "User not found" )
	} )
	@Transactional(rollbackFor = { ValidationException.class, NotAuthorizeException.class,
			CustomerNotFoundException.class, UserExistsException.class, BaseException.class })
	@RequestMapping(value = "/api/profile/resetpwd", method = RequestMethod.POST)
	public ApiResponseDTO updateUserProfile(
			@ApiParam(name = "User object", value = "The user object that needs to be updated,md5 encoded password parameter needs to be sent", required = true) @Valid @RequestBody UpdateUserPwdDTO dto,
			BindingResult result, HttpServletRequest request) throws ValidationException, NotAuthorizeException,
			UserNotFoundException, UserExistsException, BaseException {

		
		UserValidator userValidator = new UserValidator();
		userValidator.validateResetPwd(dto, result);
		List<ObjectError> resulterrors = result.getAllErrors();
	    if (result.hasErrors()) {
			for (ObjectError error : resulterrors) {
				if (error.getDefaultMessage() == null) {
					throw new ValidationException(error.getCode());
				} else {
					throw new ValidationException(error.getDefaultMessage());
				}
			}

		} 
	    String loggedInEmail = getLoggedInUserEmail(SecurityContextHolder.getContext().getAuthentication());
		User user = service.findUserByEmail(loggedInEmail);
		ApiResponseDTO response =null;
		User updateuser = service.findUserByEmail(dto.getEmail());
		// If logged in user and requested opearation on user does not match, user is not allowed to do operation
			if (updateuser == null || !(user.getId().equals(updateuser.getId()))) {
				throw new NotAuthorizeException(environment.getProperty("user.operation.notallowed"));
			}
										
				boolean match = false;
				try {
					match = (EncryptorFactory.getBCryptEncryptor().matches(dto.getOldPwd(), updateuser.getPassword())
							|| EncryptorFactory.getBCryptEncryptor().matches(EncryptionUtil.getHashCode(dto.getOldPwd()), updateuser.getPassword()));
					if (match) {
	                     updateuser.setPassword(EncryptorFactory.getBCryptEncryptor().encode(dto.getPassword()));
						 service.mergeUser(updateuser);
						 logger.info(environment.getProperty("User.passwd.reset.success"),updateuser.getUserName(),updateuser.getEmail());
						response = new ApiResponseDTO(environment.getProperty("user.password.success"), Integer.parseInt(HttpStatus.OK.toString()));

					} else {
						 logger.info(environment.getProperty("user.passwd.updateFailure.incorrectpasswd"),updateuser.getUserName());
						response = new ApiResponseDTO(environment.getProperty("user.password.incorrect"), Integer.parseInt(HttpStatus.NON_AUTHORITATIVE_INFORMATION.toString()));
						
					}
				} catch (Exception e) {
					logger.error("Error while encoding passsword {}",e.getMessage());
					return new ApiResponseDTO(environment.getProperty("error.password.encode"), Integer.parseInt(HttpStatus.NON_AUTHORITATIVE_INFORMATION.toString()));
				}

			return response;

	}

	
	/**
	 * RBAC and tenant rules
	 * This method is allowed for user with all roles(Super Admin, Customer Admin, Customer User)
	 * Logged in user can validate his password
	 * @param dto
	 * @param request
	 * @return
	 * @throws UserNotFoundException
	 * @throws BaseException
	 */
	@Path("/api/profile/validateuser")
	@POST
	@ApiOperation(value = "Validate user", notes = "Logged in user can validate his own password. User with all role Super Admin or Customer Admin or Customer User is allowed. ", response = ApiResponseDTO.class)
	@ApiResponses( {
	    @ApiResponse( code = 200, message = "User has been validated successfully" ),
	    @ApiResponse( code = 203, message = "User has not been validated" ),
	    @ApiResponse( code = 401, message = "Unauthorized User" ),
	    @ApiResponse( code = 403, message = "User Forbidden" ),
	    @ApiResponse( code = 404, message = "User not found" )
	} )
	@Transactional(rollbackFor = {ValidationException.class, NotAuthorizeException.class, CustomerNotFoundException.class,
			UserExistsException.class, BaseException.class })
	@RequestMapping(value = "/api/profile/validateuser", method = RequestMethod.POST)
	public ApiResponseDTO userValidate(
			@ApiParam(name = "User object", value = "The user object that needs to be validated,md5 encoded password parameter needs to be sent", required = true) @Valid @RequestBody ValidateUserDTO dto,
			HttpServletRequest request)
			throws ValidationException,NotAuthorizeException, UserNotFoundException, UserExistsException, BaseException {
		String loggedInEmail = getLoggedInUserEmail(SecurityContextHolder.getContext().getAuthentication());
		User loggedInUser = service.findUserByEmail(loggedInEmail);
		ApiResponseDTO response = null;
		
		User user = service.findUserByEmail(dto.getEmail());
		if(user ==null || !(loggedInUser.getId().equals(user.getId()))){
			throw new NotAuthorizeException(environment.getProperty("user.operation.notallowed"));
		}
			
         boolean match = false;
		try {
			match = (EncryptorFactory.getBCryptEncryptor().matches(dto.getOldPwd(), user.getPassword())
					|| EncryptorFactory.getBCryptEncryptor().matches(EncryptionUtil.getHashCode(dto.getOldPwd()), user.getPassword()));
			if (match) {
				logger.info(environment.getProperty("user.passwd.validate.success"),user.getUserName());
	             response = new ApiResponseDTO(environment.getProperty("user.password.validate"), Integer.parseInt(HttpStatus.OK.toString()));
				
			} else {
				 logger.info(environment.getProperty("user.passwd.validateFailure.incorrectpasswd"),user.getUserName());
				response = new ApiResponseDTO(environment.getProperty("user.password.incorrect"), Integer.parseInt(HttpStatus.OK.toString()));
			}
		} catch (Exception e) {
			logger.error("Error while encoding passsword {}",e.getMessage());
			return new ApiResponseDTO(environment.getProperty("error.password.encode"), Integer.parseInt(HttpStatus.NON_AUTHORITATIVE_INFORMATION.toString()));
		}

	
		return response;
		
		
	}

	/**
	 * RBAC and tenant rules
	 * This method is allowed for user with role Super Admin, Customer Admin
	 * Super Admin can delete all users in the repository
	 * Customer Admin can delete only users under his organization
	 * @param dto
	 * @param request
	 * @return
	 * @throws UserNotFoundException
	 * @throws BaseException
	 */
	@Path("/api/deleteuser/{id}")
	@DELETE
	@ApiOperation(value = "Delete the user", notes = "Delete the user. User with role Super Admin or Customer Admin is allowed. "
			+ "Super Admin can delete all users in the resposirory. Customer Admin can delete only users under his organization. Logged in user can not delete himself", response = ApiResponseDTO.class)
	@ApiResponses( {
   	 @ApiResponse( code = 200, message = "User deleted successfully successfully." ),
	    @ApiResponse( code = 401, message = "Unauthorized User" ),
	    @ApiResponse( code = 403, message = "User Forbidden" ),
	    @ApiResponse( code = 404, message = "User not found" )
	} )
	@Transactional(rollbackFor = { ValidationException.class, NotAuthorizeException.class, UserNotFoundException.class, BaseException.class })
	@RequestMapping(value = "/api/deleteuser/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasAnyAuthority('Super Admin','Customer Admin')")
	public ApiResponseDTO userDelete(
			@ApiParam(name = "User id", value = "The user id that needs to be deleted", required = true)  @PathVariable("id") String id,
			HttpServletRequest request)
			throws  ValidationException, NotAuthorizeException, UserNotFoundException, BaseException {
		

		
		if(StringUtils.isEmpty(id) || !StringUtils.isNumeric(id)){
			throw new ValidationException(environment.getProperty("user.id.notnull"));
		}
		String loggedInEmail = getLoggedInUserEmail(SecurityContextHolder.getContext().getAuthentication());
		String roleName = getLoggedInUserRoleName(SecurityContextHolder.getContext().getAuthentication());
		User loggedInUser = service.findUserByEmail(loggedInEmail);
		ApiResponseDTO response = null;
			User user = service.findById(Long.parseLong(id));
			if (user == null) {
				response = new ApiResponseDTO(environment.getProperty("user.not.found"), Integer.parseInt(HttpStatus.NOT_FOUND.toString()));
			}	
				
				// User himself can not be deleted
				if(loggedInUser.getId() == user.getId()){
					throw new NotAuthorizeException(environment.getProperty("loggedinuser.not.delete"));
				}
				// User with the role Cusomer Admin can not be deleted here, he should be deleted from customer details API
				if(user.getRole().getName().equals(ServicesConstants.CUSTOMER_ADMIN)){
					throw new NotAuthorizeException(environment.getProperty("customeradmin.not.delete"));
				}
				
				// If user role is customer admin only users under his organization can be edited
				if (roleName.equals(ServicesConstants.CUSTOMER_ADMIN)) {
					User dbUser= service.findByIdAndCustomerId(Long.parseLong(id), loggedInUser.getCustomer().getId());
					if(dbUser == null){
						throw new NotAuthorizeException(environment.getProperty("user.operation.notallowed"));
					}
					
				}
				
				if (user.getqAuthToken() != null && !user.getqAuthToken().isEmpty()) {
					User superAdminUser = service.findUserByEmail(ServicesConstants.DEFAULT_ADMIN_EMAIL);
					if (superAdminUser.getqAuthToken() != null && !superAdminUser.getqAuthToken().isEmpty()) {
						try {
							QuayClient.deleteUser(environment.getProperty("quay.url"),
									"Bearer " + superAdminUser.getqAuthToken(), user.getUserName());
						} catch (Exception e) {
							logger.error("Error in deleting user in quay {} " , e.getMessage());
						}
					}
				}

				service.deleteUser(user);
				logger.info(environment.getProperty("user.delete.success"),user.getUserName(),loggedInUser.getCustomer().getName());
				logger.debug("Deleted a user with id:{}" , id);
				response = new ApiResponseDTO(environment.getProperty("user.deleted.success"), Integer.parseInt(HttpStatus.OK.toString()));

		return response;
	}
	
	
	/**
	 * RBAC and tenant rules
	 * This method is allowed for user with roles Super Admin, Customer Admin, Customer User
	 * Logged in user can update his name
	 * @param dto
	 * @param request
	 * @return
	 * @throws UserNotFoundException
	 * @throws BaseException
	 */
	@Path("/api/updateprofile")
	@POST
	@ApiOperation(value = "Update the user details", notes = "Logged in user can update his name. User with role Super Admin or Customer Admin or Customer User is allowed. ", response = CreateUserResponseDTO.class)
	@ApiResponses( {
	    @ApiResponse( code = 200, message = "User details have been updated successfully" ),
	    @ApiResponse( code = 401, message = "Unauthorized User" ),
	    @ApiResponse( code = 403, message = "User Forbidden" ),
	    @ApiResponse( code = 404, message = "User not found" )
	} )
	@Transactional(rollbackFor = { ValidationException.class, NotAuthorizeException.class,
			CustomerNotFoundException.class, UserExistsException.class, BaseException.class })
	@RequestMapping(value = "/api/updateprofile", method = RequestMethod.POST)
	public ResponseDTO profileUpdate(
			@ApiParam(name = "User object", value = "The user object that needs to be updated", required = true) @Valid @RequestBody ProfileDTO dto,
			BindingResult result, HttpServletRequest request)
			throws ValidationException,NotAuthorizeException, ValidationException, UserExistsException, BaseException {
		logger.debug("Adding a new user entry with information:{}" , dto.getId());
		UserValidator userValidator = new UserValidator();
		userValidator.validateUpdateProfile(dto, result);
		List<ObjectError> resulterrors = result.getAllErrors();

		if (result.hasErrors()) {
			for (ObjectError error : resulterrors) {
				if (error.getDefaultMessage() == null) {
					throw new ValidationException(error.getCode());
				} else {
					throw new ValidationException(error.getDefaultMessage());
				}
			}
		} 
		
		
		if(StringUtils.isEmpty(dto.getId()) || !StringUtils.isNumeric(dto.getId())){
			throw new ValidationException(environment.getProperty("user.id.notnull"));
		}
		String loggedInEmail = getLoggedInUserEmail(SecurityContextHolder.getContext().getAuthentication());
		User user = service.findUserByEmail(loggedInEmail);
		User updateUser = service.findById(Long.parseLong(dto.getId()));
		ResponseDTO response = null;
			if (updateUser == null) {
				return new ResponseDTO(environment.getProperty("user.not.found"), Integer.parseInt(HttpStatus.OK.toString()));
			}
				// Logged in user can edit his own details, not other user details				
				if (!user.getId().equals(updateUser.getId())) {
					throw new NotAuthorizeException(environment.getProperty("user.operation.notallowed"));
				}
				updateUser.setName(dto.getName().trim());
				service.mergeUser(updateUser);
				logger.info(environment.getProperty("User.updateprofile.success"),updateUser.getUserName(),user.getCustomer().getName());
				user = service.findById(updateUser.getId());

				response = new ResponseDTO(environment.getProperty("user.updated.name"), Integer.parseInt(HttpStatus.OK.toString()));
				logger.debug("User is saved with name {}", user.getName());
				response.setData(createUserResponseDTO(user));
			
		
		return response;

	}

}
