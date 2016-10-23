package com.nvidia.cosmos.cloud.rest.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nvidia.cosmos.cloud.auth.encryptor.EncryptorFactory;
import com.nvidia.cosmos.cloud.common.ServicesConstants;
import com.nvidia.cosmos.cloud.common.UserStatusEnum;
import com.nvidia.cosmos.cloud.dtos.ApiResponseDTO;
import com.nvidia.cosmos.cloud.dtos.LoginResponseDTO;
import com.nvidia.cosmos.cloud.dtos.ResetDTO;
import com.nvidia.cosmos.cloud.dtos.ResetPasswordDTO;
import com.nvidia.cosmos.cloud.dtos.ResponseDTO;
import com.nvidia.cosmos.cloud.dtos.VerifyResponseDTO;
import com.nvidia.cosmos.cloud.exceptions.BaseException;
import com.nvidia.cosmos.cloud.exceptions.CustomerNotFoundException;
import com.nvidia.cosmos.cloud.exceptions.GraphanaFailedException;
import com.nvidia.cosmos.cloud.exceptions.NotAuthorizedException;
import com.nvidia.cosmos.cloud.exceptions.QuayFailedException;
import com.nvidia.cosmos.cloud.exceptions.RoleNotFoundException;
import com.nvidia.cosmos.cloud.exceptions.UserAuthExistsException;
import com.nvidia.cosmos.cloud.exceptions.UserAuthNotFoundException;
import com.nvidia.cosmos.cloud.exceptions.UserExistsException;
import com.nvidia.cosmos.cloud.exceptions.UserNameExitsException;
import com.nvidia.cosmos.cloud.exceptions.UserNotFoundException;
import com.nvidia.cosmos.cloud.rest.exception.HibernateViolationException;
import com.nvidia.cosmos.cloud.rest.exception.NotAuthorizeException;
import com.nvidia.cosmos.cloud.rest.util.GrafanaClient;
import com.nvidia.cosmos.cloud.rest.util.NvidiaUtil;
import com.nvidia.cosmos.cloud.rest.util.QuayClient;
import com.nvidia.cosmos.cloud.services.customer.model.Customer;
import com.nvidia.cosmos.cloud.services.entitlement.model.Entitlement;
import com.nvidia.cosmos.cloud.services.entitlement.service.EntitlementService;
import com.nvidia.cosmos.cloud.services.user.model.User;
import com.nvidia.cosmos.cloud.services.user.service.UserService;
import com.nvidia.cosmos.cloud.services.userauth.model.UserAuth;
import com.nvidia.cosmos.cloud.services.userauth.service.UserAuthService;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;;

/**
 * This controller will help to register customers and also CRUD methods for a
 * customer
 *
 * 
 */
@RestController
public class LoginController extends BaseController {

	@Autowired
	Environment environment;

	/**
	 * 
	 */
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	/**
	 * 
	 */
	UserService service;
	UserAuthService userAuthservice;
	EntitlementService entitlementService;
	int maxAttempts = 5;
	int minToDisableAccount = 10;
	String quayDockerLogin = null;
	String grafanaUrl = null;
	String influxUrl = null;
	private static String grafanaUserName = null;
	private static String grafanaPassword = null;
	private static String influxdbUserName = null;
	private static String influxdbPassword = null;
	private static String databaseName = "collectd";

	/**
	 * 
	 */

	public LoginController() {
		service = factory.getUserService();
		userAuthservice = factory.getUserAuthService();
		entitlementService = factory.getEntitlementService();

	}

	@PostConstruct
	private void init() {
		try {
			if (environment.getProperty("max.failed.login.attempts.allowed") != null)
				maxAttempts = Integer.parseInt(environment.getProperty("max.failed.login.attempts.allowed"));
			if (environment.getProperty("minutues.to.disable.account") != null)
				minToDisableAccount = Integer.parseInt(environment.getProperty("minutues.to.disable.account"));
			if (environment.getProperty("minutues.to.disable.account") != null)
				quayDockerLogin = environment.getProperty("quay.docker.login");

			if (environment.getProperty("grafana.host") != null)
				grafanaUrl = environment.getProperty("grafana.host");

			if (environment.getProperty("influxdb.url") != null)
				influxUrl = environment.getProperty("influxdb.url");
			
			
			grafanaUserName = environment.getProperty(ServicesConstants.grafanaUser);
			grafanaPassword = environment.getProperty(ServicesConstants.grafanaPwd);
			influxdbUserName = environment.getProperty(ServicesConstants.influxdbUser);
			influxdbPassword = environment.getProperty(ServicesConstants.influxdbPwd);
			
			

		} catch (Exception e) {
			logger.error("Error while reading properties for login attemts {}", e.getMessage());
		}
	}

	private class LoginValidator implements Validator {
		public void validate(ResetPasswordDTO obj, Errors errors, String key) throws NumberFormatException, UserNotFoundException, ValidationException, NotAuthorizeException {
			
			if (obj.getName() != null) {
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",
						environment.getProperty("user.name.notempty"));
				if (obj.getName() != null && obj.getName().trim().length() > 64) {
					errors.rejectValue("name", environment.getProperty("user.name.length"));
				}
			}
			if (obj.getUserName() != null) {
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName",
						environment.getProperty("user.username.notempty"));
				if (obj.getUserName() != null && (obj.getUserName().trim().length() > 30 || obj.getUserName().trim().length() < 4)) {
					errors.rejectValue("userName", environment.getProperty("user.username.length"));
				}
				if(StringUtils.containsWhitespace(obj.getUserName()) == true){
					throw new ValidationException(environment.getProperty("user.name.notallow.space"));
				}
			}
			User user = service.findUserByEmail(obj.getEmail());
			if (user != null) {
				// uid and gid should be validated inly if user does not have uid and gid
				if (user.getuId() == null) {
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "uId", environment.getProperty("user.uid.notempty"));
					if(StringUtils.isEmpty(obj.getuId()) || !StringUtils.isNumeric(obj.getuId())){
						errors.rejectValue("uId", environment.getProperty("user.uid.numeric"));
					}
					if (obj.getuId() != null && !StringUtils.isEmpty(obj.getuId()) && StringUtils.isNumeric(obj.getuId()) && Long.parseLong(obj.getuId()) > 999999) {
						errors.rejectValue("uId", environment.getProperty("user.uid.length"));
					}
					if (obj.getuId() != null && !StringUtils.isEmpty(obj.getuId()) && StringUtils.isNumeric(obj.getuId()) && Long.parseLong(obj.getuId()) <= 0) {
						errors.rejectValue("uId", environment.getProperty("user.uid.value"));
					}
					
				}
				if (user.getgId() == null) {
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gId", environment.getProperty("user.gid.notempty"));
					if(StringUtils.isEmpty(obj.getgId()) || !StringUtils.isNumeric(obj.getgId())){
						errors.rejectValue("uId", environment.getProperty("user.gid.numeric"));
					}
					if (obj.getgId() != null && !StringUtils.isEmpty(obj.getgId()) && StringUtils.isNumeric(obj.getgId()) && Long.parseLong(obj.getgId()) > 999999) {
						errors.rejectValue("gId", environment.getProperty("user.gid.length"));
					}
					if (obj.getgId() != null && !StringUtils.isEmpty(obj.getgId()) && StringUtils.isNumeric(obj.getgId()) &&Long.parseLong(obj.getgId()) <= 0) {
						errors.rejectValue("gId", environment.getProperty("user.gid.value"));
					}
					
				}
			}

			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
					environment.getProperty("user.password.notempty"));
			
			  if (obj.getPassword() != null &&  (obj.getPassword().length() > 64 || obj.getPassword().length() < 8)) {
			  errors.rejectValue("password",
			  environment.getProperty("user.password.length")); 
			  }
			  if(key==null || key.isEmpty()){
					throw new NotAuthorizeException("not.authorized");				  
			  }
			  else  
			  {
					String[] array = validateEmailUrl(key);
					long millis = Long.parseLong(array[2]);
					if (System.currentTimeMillis() > millis) {
						throw new NotAuthorizeException("not.authorized");
					}
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
	 * This method is allowed for user with roles Super Admin, Customer Admin, Customer User
	 * User logout
	 * @param request
	 * @throws UserAuthNotFoundException
	 * @throws BaseException
	 */
	@Path("/api/logout")
	@DELETE
	@ApiOperation(value = "User logout ", notes = "User logs out from active session. User with role Super Admin or Customer Admin or Customer User is allowed.")  /*response = UserAuthDTO.class*/
	@ApiResponses( {
   	    @ApiResponse( code = 200, message = "User logged out sucessfully" ),
	    @ApiResponse( code = 204, message = "No content" ),
	    @ApiResponse( code = 401, message = "UnAuthorized User" ),
	    @ApiResponse( code = 403, message = "User Forbidden" )
	} )
	@Transactional(rollbackFor = { UserAuthNotFoundException.class, BaseException.class })
	@RequestMapping(value = "/api/logout", method = RequestMethod.DELETE)
	public void logout(HttpServletRequest request) throws UserAuthNotFoundException, BaseException {
		String authToken = getLoggedInUserAuthToken(SecurityContextHolder.getContext().getAuthentication());
		String loggedInEmai = getLoggedInUserEmail(SecurityContextHolder.getContext().getAuthentication());
		String message = null;
		String statusCode = HttpStatus.OK.toString();

		UserAuth userAuth = userAuthservice.findUserAuthByAuthToken(authToken);
		
		
		User user = service.findUserByEmail(loggedInEmai);
		
		// delete the grafana authkey for customer admin and customer user if
		// grafanad orgID is associated with that user.
		/*if ((!user.getRole().equals(ServicesConstants.SUPER_ADMIN)
				&& (!StringUtils.isEmpty(user.getCustomer().getGrafanaOrgId())))) {
			logger.debug("User has role {} deleting grafana authkey for orgID {} for this sesssion ");

			List<UserAuth> existingSessionAuth = null;
			try {
				existingSessionAuth = userAuthservice.findUserAuthByEmail(user.getEmail());
			} catch (Exception e) {
				logger.debug("User doesn't have logged in session");
			}
			// if more then one user using using grafanaAuthkey don't delete
			// from grafana
			if (existingSessionAuth != null && existingSessionAuth.size() == 1 &&  existingSessionAuth.get(0).getGrafanaToken()!=null) {
				GrafanaClient.deleteUserAuthKey(user.getUserName(), user.getCustomer().getGrafanaOrgId(), grafanaUrl,
						grafanaUserName, grafanaPassword);
			}
			logger.debug("Deleted session auth key for user {} for grafanaOrdID {}", user.getUserName(),
					user.getCustomer().getGrafanaOrgId());
		}*/
		userAuthservice.deleteUserAuthById(userAuth.getId());

		ResponseDTO response = new ResponseDTO(message, Integer.parseInt(statusCode));
		response.setData(null);

	}

	/**
	 * RBAC and tenant rules
	 * This method is allowed for user with roles Super Admin, Customer Admin, Customer User
	 * Send email to user when user try to recover password 
	 * @param dto
	 * @param request
	 * @throws UserAuthNotFoundException, UserNotFoundException
	 * @throws BaseException
	 */
	@Path("/api/reset")
	@POST
	@ApiOperation(value = "Send email to user when user try to recover password ", notes = "Sends email to user when user try to recover password. "
			+ "User with role Super Admin or Customer Admin or Customer User is allowed", 
	response = ApiResponseDTO.class)
	 @ApiResponses( {
		    @ApiResponse( code = 200, message = "Password reset link has been sent to your email id successfully" ),
		    @ApiResponse( code = 401, message = "Unauthorized User" ),
		    @ApiResponse( code = 403, message = "User forbidden" ),
		    @ApiResponse( code = 404, message = "User corresponding to input email-id is not found" )
		} )
	@Transactional(rollbackFor = { UserNotFoundException.class, BaseException.class })
	@RequestMapping(value = "/api/reset", method = RequestMethod.POST)
	/*@PreAuthorize("hasAnyAuthority('Customer User','Customer Admin')")
*/
	@ResponseBody
	public ApiResponseDTO reset(
			@ApiParam(name = "User object", value = "The user object whose password needs to be reset", required = true) @Valid @RequestBody ResetDTO dto,
			HttpServletRequest request) throws UserNotFoundException, BaseException, UserAuthExistsException,
			RoleNotFoundException, NotAuthorizedException {
		logger.debug("Authenticating with email {} " , dto.getEmail());

		ApiResponseDTO res = null;
		User user = null;

		try {
			user = service.findUserByEmail(dto.getEmail().toLowerCase());

		} catch (UserNotFoundException unfe) {
			logger.info(environment.getProperty("user.passwd.reset.notFound"),dto.getEmail().toLowerCase());
			res = new ApiResponseDTO(environment.getProperty("reset.message.success"),
					Integer.parseInt(HttpStatus.OK.toString()));
			return res;
		}

		if(user.getStatus().equals(UserStatusEnum.REGISTERED.toString())){
			logger.info("User status is inactive for email {} ", dto.getEmail().toLowerCase());
			res = new ApiResponseDTO(environment.getProperty("reset.message.success"),
					Integer.parseInt(HttpStatus.OK.toString()));
			return res;
		}
		if (user.getEmail() != null || user.getEmail() != "") {
			if (user.getRole().getName().equals(ServicesConstants.SUPER_ADMIN)) {
				logger.info(environment.getProperty("user.passwd.reset.NotAuthorized") , user.getUserName());
				res = new ApiResponseDTO(environment.getProperty("reset.message.success"),
						Integer.parseInt(HttpStatus.OK.toString()));
				return res;
			}
			if(user.getPasswordKey()==null || user.getPasswordKey().isEmpty()){

				String encryptEmail = NvidiaUtil.getEncrypt("" + user.getId(), user.getEmail(), false, "1", 
						Integer.parseInt(environment.getProperty("reset.hours")), NvidiaUtil.getCalenderTime(
                          Integer.parseInt(environment.getProperty("reset.hours"))));
					
				try {
					sendAnEmail(user, encryptEmail);
					res = new ApiResponseDTO(environment.getProperty("reset.message.success"),
							Integer.parseInt(HttpStatus.OK.toString()));
					return res;
				} catch (Exception e) {
					logger.error(environment.getProperty("user.passwd.reset.failure"),e.getMessage(), user.getUserName());
					res = new ApiResponseDTO(environment.getProperty("reset.message.success"),
							Integer.parseInt(HttpStatus.OK.toString()));
					return res;
				}
			}


			try {
				String[] decryptedEmail = validateEmailUrl(user.getPasswordKey());
				if(decryptedEmail[4]=="0" || decryptedEmail[4] == null || decryptedEmail[4].isEmpty()){
				String encryptEmail = NvidiaUtil.getEncrypt("" + user.getId(), user.getEmail(), false, "1", 
						Integer.parseInt(environment.getProperty("reset.hours")), NvidiaUtil.getCalenderTime(
                          Integer.parseInt(environment.getProperty("reset.hours"))));
				                  
				sendAnEmail(user, encryptEmail);
				res = new ApiResponseDTO(environment.getProperty("reset.message.success"),
						Integer.parseInt(HttpStatus.OK.toString()));
				return res;
				}else{
					if(Integer.parseInt(decryptedEmail[4]) <= Integer.parseInt(environment.getProperty("reset.max.count"))){
						System.out.println(decryptedEmail[4]);
						int count = Integer.parseInt(decryptedEmail[4]) + 1;
					String encryptEmail = NvidiaUtil.getEncrypt("" + user.getId(), user.getEmail(), false, count+"", 
							Integer.parseInt(environment.getProperty("reset.hours")), Long.parseLong(decryptedEmail[2]));
				
//						String encryptEmail = NvidiaUtil.getEncrypt("" + user.getId(), user.getEmail(), false, count+"1", 
//							Integer.parseInt(environment.getProperty("reset.hours")), NvidiaUtil.getCalenderTime(
//	                          Integer.parseInt(environment.getProperty("reset.hours"))));
					
					sendAnEmail(user, encryptEmail);
					res = new ApiResponseDTO(environment.getProperty("reset.message.success"),
							Integer.parseInt(HttpStatus.OK.toString()));
					return res;
					}else{
						if (System.currentTimeMillis() <= Long.parseLong(decryptedEmail[2])){
							logger.error("Maximum recovery password counts in a day reached by email {} ", user.getEmail());
							res = new ApiResponseDTO(environment.getProperty("reset.message.success"),
									Integer.parseInt(HttpStatus.OK.toString()));
							return res;
						}else{
							String encryptEmail = NvidiaUtil.getEncrypt("" + user.getId(), user.getEmail(), false, "1", 
									Integer.parseInt(environment.getProperty("reset.hours")), NvidiaUtil.getCalenderTime(
					                          Integer.parseInt(environment.getProperty("reset.hours"))));
							
							sendAnEmail(user, encryptEmail);							

							res = new ApiResponseDTO(environment.getProperty("reset.message.success"),
									Integer.parseInt(HttpStatus.OK.toString()));
							return res;
							}
					}
				}

			} catch (Exception e) {
				logger.info(environment.getProperty("user.passwd.reset.failure") , user.getUserName());
				res = new ApiResponseDTO(environment.getProperty("reset.message.success"),
						Integer.parseInt(HttpStatus.OK.toString()));
			}
		}
		
		res = new ApiResponseDTO(environment.getProperty("reset.message.success"),
				Integer.parseInt(HttpStatus.OK.toString()));
		return res;
	}

	public void sendAnEmail(User user, String encryptEmail) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("userName", user.getName());
		
		user.setStatus(UserStatusEnum.ACTIVE.toString());
		user.setSentEmail(true);
		user.setPasswordKey(encryptEmail);
		service.mergeUser(user);
		model.put("resetPasswordLink",
				environment.getProperty("tomcat.url") + "reset/reset-p.html#!/reset/" + encryptEmail);
		model.put("corporateHeader", environment.getProperty("tomcat.url") + "img/NVIDIA-Corporate-Header.png");
		String subject = environment.getProperty("reset.email.subject");

		initMailThread();
		NvidiaUtil.executeSendMailsThread(user.getEmail(), subject, model, "forget.vm");
		logger.info(environment.getProperty("user.passwd.reset.success") + user.getUserName() + user.getEmail());
	}


	/**
	 * RBAC and tenant rules
	 * This method is allowed for user with roles Super Admin, Customer Admin, Customer User
	 * Verify whether the user exists or not
	 * @param request
	 * @throws UserAuthNotFoundException, UserNotFoundException
	 * @throws BaseException
	 */
	@Path("/api/reset/verify/{key}")
	@GET
	@ApiOperation(value = "Verify whether the user exists or not", notes = "Verify whether the user exists or not. "
			+ "User with role Super Admin or Customer Admin or Customer User is allowed.", response = VerifyResponseDTO.class)
	@ApiResponses( {
		 @ApiResponse( code = 200, message = "User has been verified" ),
		    @ApiResponse( code = 401, message = "UnAuthorized User" ),
		    @ApiResponse( code = 403, message = "User Forbidden" ),
		    @ApiResponse( code = 404, message = "User not found" )
		} )
	@Transactional(rollbackFor = { ValidationException.class, UserAuthNotFoundException.class, UserNotFoundException.class })
	@RequestMapping(value = "/api/reset/verify/{key}", method = RequestMethod.GET)
	/*@PreAuthorize("hasAnyAuthority('Customer User','Customer Admin')")
*/
	public VerifyResponseDTO verify(
			@ApiParam(name = "Key", value = "Key that contains user name and password", required = true) @PathVariable("key") String key,
			HttpServletRequest request)
			throws UserAuthNotFoundException, NumberFormatException, UserNotFoundException, UserNameExitsException, ValidationException {
		VerifyResponseDTO dto = new VerifyResponseDTO();

		String[] array = validateEmailUrl(key);
		long millis = Long.parseLong(array[2]);
		
		if (System.currentTimeMillis() <= millis) {
			
			User user = service.findById(Long.parseLong(array[0]));			
			dto.setUserId("" + user.getId());
			dto.setEmail(user.getEmail());
			dto.setRoleName(user.getRole().getName());
			dto.setClientId(user.getCustomer().getClientId());
			
			if (user.getStatus().equals(UserStatusEnum.REGISTERED.toString())) {
				dto.setVerified(true);

			} else if (user.isSentEmail() == true && user.getStatus().equals(UserStatusEnum.ACTIVE.toString())
					&& key.equals(user.getPasswordKey())) {
				dto.setSentEmail(true);
				dto.setVerified(false);

			} else {
				dto.setVerified(false);
				dto.setSentEmail(false);
			}
		} else {
			dto.setVerified(false);
			dto.setSentEmail(false);
		}
		
		dto.setRegistartion(Boolean.parseBoolean(array[3]));
		return dto;
	}

	private String[] validateEmailUrl(String key) throws ValidationException{
		String[] array = NvidiaUtil.getDecrypt(key);
        if(key == null || key.isEmpty()){
        	throw new ValidationException(environment.getProperty("key.not.empty"));
        } 
		return array;
	}	

	/**
	 * RBAC and tenant rules
	 * This method is allowed for user with roles Super Admin, Customer Admin, Customer User
	 * Reset the password by user ID
	 * User can reset only his own 
	 * @param dto
	 * @param request
	 * @return
	 * @throws UserNotFoundException
	 * @throws BaseException
	 */
	@Path("/api/reset/{userId}/{key}")
	@POST
	@ApiOperation(value = "Resets the password by user ID", notes = "User can reset the password using user ID. User with role Super Admin or "
			+ "Customer Admin or Customer User is allowed.", response = LoginResponseDTO.class)
	 @ApiResponses( {
	    	@ApiResponse( code = 200, message = "Password is reset successfully." ),
		    @ApiResponse( code = 401, message = "UnAuthorized User" ),
		    @ApiResponse( code = 403, message = "User Forbidden" ),
		    @ApiResponse( code = 404, message = "User not found" )
		} )
	@Transactional(rollbackFor = { ValidationException.class, UserNotFoundException.class, UserNameExitsException.class,
			CustomerNotFoundException.class, QuayFailedException.class, HibernateViolationException.class,
			GraphanaFailedException.class, UserExistsException.class, BaseException.class })
	@RequestMapping(value = "/api/reset/{userId}/{key}", method = RequestMethod.POST)
	public LoginResponseDTO reset(
			@ApiParam(name = "User object", value = "User object whose details need to be reset", required = true) @Valid @RequestBody ResetPasswordDTO dto,
			@ApiParam(name = "User id", value = "The id of the user whose details need to be reset", required = true) @PathVariable("userId") String userId,
			@ApiParam(name = "key", value = "The key of the user is needed to check a valid user") @PathVariable("key") String key,
			BindingResult result, HttpServletRequest request)
			throws NotAuthorizeException, ValidationException, HibernateViolationException, AssertionError, Exception {
		logger.debug("Resetting the password for user {} " , dto.getUserId());
		
		if(StringUtils.isEmpty(dto.getUserId()) || !StringUtils.isNumeric(dto.getUserId()) 
				|| StringUtils.isEmpty(userId) || !StringUtils.isNumeric(userId)){
			throw new ValidationException(environment.getProperty("user.id.notnull"));
		}
		
		
		User user = service.findById(Long.parseLong(dto.getUserId()));
		User superAdminUser = service.findUserByEmail(ServicesConstants.DEFAULT_ADMIN_EMAIL);
		LoginResponseDTO res = null;

		if(!user.getRole().getName().equals(ServicesConstants.SUPER_ADMIN)){
			if(StringUtils.isEmpty(key) ){
				throw new ValidationException(environment.getProperty("key.not.null"));
			}
		}
		
		if(!user.getRole().getName().equals(ServicesConstants.SUPER_ADMIN)){
		LoginValidator loginValidator = new LoginValidator();
		loginValidator.validate(dto, result, key);
		}

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

		
			if (dto.getUserName() != null) {
				user.setUserName(dto.getUserName().trim());
			}
			try {
				//String md5Pwd= EncryptionUtil.getHashCodePassword(dto.getPassword());
				user.setPassword(EncryptorFactory.getBCryptEncryptor().encode(dto.getPassword()));
			} catch (Exception ex) {
				logger.error("Error while encoding password {}" , ex.getMessage());
			}

			if (dto.getName() != null) {
				user.setName(dto.getName().trim());
			}
			if (dto.getgId() != null) {
				user.setgId(Long.parseLong(dto.getgId()));
			}
			if (dto.getuId() != null) {
				user.setuId(Long.parseLong(dto.getuId()));
			}

			user.setSentEmail(false);
			/**
			 * Rest API CALL
			 */

			if (dto.getqAuthToken() != null) {
				user.setqAuthToken(dto.getqAuthToken());
			}

			logger.info("Updated the user with new pass {}" , user.getName());

			/*
			 * First if - With super admin oauth token creating two roles
			 * writers and readers under nvidia org Second if - With customer
			 * admin oauth token created writers role under that org and using
			 * super admin oauth token assigned customer to readers role of
			 * nvidia org Third if - With customer admin oauth token assigned
			 * user to writers role under that org and using super admin oauth
			 * token assigned customer to readers role of nvidia org
			 * 
			 */

			if(dto.isRegistration() == true && user.getStatus().equals(UserStatusEnum.ACTIVE.toString())){
				logger.info("User already exists with email {} ", user.getEmail());
				throw new ValidationException(environment.getProperty("reset.user.exists"));
			}
			
			if (user.getRole().getName().equals(ServicesConstants.SUPER_ADMIN) && user.getqAuthToken() != null) {

				Map<String, Object> object = null;
				try {
					object = QuayClient.createRolesForSuperAdmin(environment.getProperty("quay.url"),
							"Bearer " + user.getqAuthToken());
				} catch (QuayFailedException qfe) {

					user.setqAuthToken(null);
					service.updateUser(user);
					logger.info(environment.getProperty("Quay.unreachable"), environment.getProperty("quay.url"));
					throw new QuayFailedException(environment.getProperty("quay.failed"));
				}

				if (object != null && !object.isEmpty()) {
					logger.info("Object {}" ,object );
					if (object != null && object.containsKey("client_id")) {
						logger.info(environment.getProperty("user.passwd.update.failure.superadmin") ,user.getUserName());
					}
				}

			} else if (user.getRole().getName().equals(ServicesConstants.CUSTOMER_ADMIN) && user.getqAuthToken() != null
					&& dto.isRegistration() == true && user.getStatus().equals(UserStatusEnum.REGISTERED.toString())) {

				{
					Map<String, Object> object = null;
					try {
						object = QuayClient.createRoleWriters(environment.getProperty("quay.url"),
								"Bearer " + superAdminUser.getqAuthToken(), user.getCustomer().getName());
					} catch (QuayFailedException qfe) {

						user.setqAuthToken(null);
						service.updateUser(user);
						logger.info(environment.getProperty("Quay.unreachable"), environment.getProperty("quay.url"));
						throw new QuayFailedException(environment.getProperty("quay.failed"));
					}
					if (object != null && !object.isEmpty()) {

						try {
							object = QuayClient.assignRoleWriters(environment.getProperty("quay.url"),
									"Bearer " + superAdminUser.getqAuthToken(), user.getCustomer().getName(),
									user.getUserName());
						} catch (QuayFailedException qfe) {

							user.setqAuthToken(null);
							service.updateUser(user);
							logger.info(environment.getProperty("Quay.unreachable"), environment.getProperty("quay.url"));
							throw new QuayFailedException(environment.getProperty("quay.failed"));
						}
						if (object != null && !object.isEmpty()) {
							logger.info(environment.getProperty("user.passwd.update.failure.customeradmin") , user.getUserName());
						}
					}
				}
				{
					Map<String, Object> object = null;
					try {
						object = QuayClient.assignRoleReaders(environment.getProperty("quay.url"),
								"Bearer " + superAdminUser.getqAuthToken(), user.getUserName());
					} catch (Exception e) {

						user.setqAuthToken(null);
						service.updateUser(user);
						logger.info(environment.getProperty("Quay.unreachable"), environment.getProperty("quay.url"));
						throw new QuayFailedException(environment.getProperty("quay.failed"));
					}
					if (object != null && !object.isEmpty()) {
						logger.info(environment.getProperty("user.passwd.update.failure.readrole") , user.getUserName());
					}
				}
			} else if (user.getRole().getName().equals(ServicesConstants.CUSTOMER_USER) && user.getqAuthToken() != null
					&& dto.isRegistration() == true && user.getStatus().equals(UserStatusEnum.REGISTERED.toString())) {

				{
					Customer cus = user.getCustomer();

					Map<String, Object> object = null;
					try {
						object = QuayClient.assignRoleWriters(environment.getProperty("quay.url"),
								"Bearer " + superAdminUser.getqAuthToken(), cus.getName(), user.getUserName());
					} catch (Exception qfe) {

						user.setqAuthToken(null);
						service.updateUser(user);
						logger.info(environment.getProperty("Quay.unreachable"), environment.getProperty("quay.url"));
						throw new QuayFailedException(environment.getProperty("quay.failed"));
					}

					if (object != null && !object.isEmpty()) {
						logger.info(environment.getProperty("user.passwd.update.failure.writerole") , user.getUserName());
					}

				}
				{
					Map<String, Object> object = null;
					try {
						object = QuayClient.assignRoleReaders(environment.getProperty("quay.url"),
								"Bearer " + superAdminUser.getqAuthToken(), user.getUserName());
					} catch (Exception qfe) {

						user.setqAuthToken(null);
						service.updateUser(user);
						logger.info(environment.getProperty("Quay.unreachable"), environment.getProperty("quay.url"));
						throw new QuayFailedException(environment.getProperty("quay.failed"));
					}
					if (object != null && !object.isEmpty()) {
						logger.info(environment.getProperty("user.passwd.update.failure.readrole") + user.getUserName());
					}
				}
			}

			if (user.getRole().getName().equals(ServicesConstants.CUSTOMER_ADMIN) && user.getqAuthToken() != null
					&& user.getCustomer().getGrafanaOrgId() == null && user.getStatus().equals(UserStatusEnum.REGISTERED.toString())) {

				try {
					
					Customer customer = user.getCustomer();
					if (customer != null) {
						List<Entitlement> entitlements = entitlementService.findEntitlementByName(customer.getName());
						Entitlement entitlement = null;
						if (entitlements != null && entitlements.size() > 0)
							entitlement = entitlements.get(0);
						if (entitlement != null && entitlement.getInfluxdbUrl() != null
								&& entitlement.getInfluxdbUser() != null && entitlement.getDatabaseName() !=null && !entitlement.getDatabaseName().isEmpty()) {
							GrafanaClient.createCustomerDetailsInGrafana(user, grafanaUrl, entitlement.getInfluxdbUrl(),
									grafanaUserName, grafanaPassword, entitlement.getInfluxdbUser(),
									entitlement.getInfluxdbPassword(),entitlement.getDatabaseName());
						} else {
							GrafanaClient.createCustomerDetailsInGrafana(user, grafanaUrl, influxUrl, grafanaUserName,
									grafanaPassword, influxdbUserName, influxdbPassword,databaseName);
						}
					}
				} catch (Exception qfe) {

					user.setqAuthToken(null);
					service.updateUser(user);
					logger.info(environment.getProperty("graphana.unreachable"), grafanaUrl);
					throw new GraphanaFailedException(environment.getProperty("graphana.failed"));
				}

			}

			if ((user.getqAuthToken() != null && user.getCustomer().getGrafanaOrgId() != null)
					&& user.getStatus().equals(UserStatusEnum.REGISTERED.toString())
					|| (user.getRole().getName().equals(ServicesConstants.SUPER_ADMIN))) {
				user.setStatus(UserStatusEnum.ACTIVE.toString());
			}

			//Key of the reset email is changed to disable the reset link
			if(user.getPasswordKey()!=null && !user.getRole().getName().equals(ServicesConstants.SUPER_ADMIN)){
		        String[] array = validateEmailUrl(key);
		       String key1 =  NvidiaUtil.getEncrypt("" + user.getId(), user.getEmail(), false, array[4], Integer.parseInt(environment.getProperty("reset.hours")),
		    		   Long.parseLong(array[2]));

			user.setPasswordKey(key1);
			}
			
			service.updateUser(user);
			logger.info(environment.getProperty("user.passwd.updated.success") , user.getUserName());

			res = new LoginResponseDTO(environment.getProperty("reset.success"), HttpStatus.ACCEPTED.value());
			res.setDecryptedEmail(dto.getEmail());
		
		return res;
	}
}

class NumberToWord {
	private static final String[] specialNames = { "", " thousand", " million", " billion", " trillion", " quadrillion",
			" quintillion" };

	private static final String[] tensNames = { "", " Ten", " Twenty", " Thirty", " Forty", " Fifty", " Sixty",
			" Seventy", " Eighty", " Ninety" };

	private static final String[] numNames = { "", " One", " Two", " Three", " Four", " Five", " Six", " Seven",
			" Eight", " Nine", " Ten", " Eleven", " Twelve", " Thirteen", " Fourteen", " Fifteen", " Sixteen",
			" Seventeen", " Eighteen", " Nineteen" };

	private String convertLessThanOneThousand(int number) {
		String current;

		if (number % 100 < 20) {
			current = numNames[number % 100];
			number /= 100;
		} else {
			current = numNames[number % 10];
			number /= 10;

			current = tensNames[number % 10] + current;
			number /= 10;
		}
		if (number == 0)
			return current;
		return numNames[number] + " hundred" + current;
	}

	public String convert(int number) {

		if (number == 0) {
			return "zero";
		}

		String prefix = "";

		if (number < 0) {
			number = -number;
			prefix = "negative";
		}

		String current = "";
		int place = 0;

		do {
			int n = number % 1000;
			if (n != 0) {
				String s = convertLessThanOneThousand(n);
				current = s + specialNames[place] + current;
			}
			place++;
			number /= 1000;
		} while (number > 0);

		return (prefix + current).trim();
	}

}
