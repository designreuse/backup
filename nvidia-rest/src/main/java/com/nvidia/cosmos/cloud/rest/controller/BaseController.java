package com.nvidia.cosmos.cloud.rest.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

import com.nvidia.cosmos.cloud.dtos.ApplianceDTO;
import com.nvidia.cosmos.cloud.dtos.CreateUserResponseDTO;
import com.nvidia.cosmos.cloud.dtos.CustomerDTO;
import com.nvidia.cosmos.cloud.dtos.CustomerResponseDTO;
import com.nvidia.cosmos.cloud.dtos.EntitlementNamesRespoponseDTO;
import com.nvidia.cosmos.cloud.dtos.EntitlementObjectResponseDTO;
import com.nvidia.cosmos.cloud.dtos.EntitlementResponseDTO;
import com.nvidia.cosmos.cloud.dtos.RoleDTO;
import com.nvidia.cosmos.cloud.dtos.UserAuthDTO;
import com.nvidia.cosmos.cloud.dtos.UserDTO;
import com.nvidia.cosmos.cloud.factory.ServicesFactory;
import com.nvidia.cosmos.cloud.rest.util.EmailSenderFactory;
import com.nvidia.cosmos.cloud.services.appliance.model.Appliance;
import com.nvidia.cosmos.cloud.services.customer.model.Customer;
import com.nvidia.cosmos.cloud.services.entitlement.model.Entitlement;
import com.nvidia.cosmos.cloud.services.role.model.Role;
import com.nvidia.cosmos.cloud.services.user.model.User;

/**
 * @author pbatta
 *
 */


@RestController
public class BaseController {
	
	private static final String API_PATH = "/api";

	public static final String AUTHENTICATE_URL = API_PATH + "/authenticate";
	private static final String IPADDRESS_PATTERN =
			"^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
			"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
			"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
			"([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
	public Pattern pattern = Pattern.compile(IPADDRESS_PATTERN);	
	@Autowired
	public Environment environment;
	/**
	 * 
	 */
	public ServicesFactory factory = null;
	/**
	 * 
	 */
	public BaseController(){
		try{
			factory = ServicesFactory.getInstance();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * @param model
	 * @return
	 */
	protected UserDTO createDTO(User model) {
    	UserDTO dto = new UserDTO();

        dto.setId(""+model.getId());
        dto.setName(model.getName());
        dto.setPhone(""+model.getPhone());
        dto.setEmail(model.getEmail());

        dto.setuId(model.getuId());
        dto.setgId(model.getgId());

        dto.setUserName(model.getUserName());
        dto.setCustomer(createDTO(model.getCustomer()));
        dto.setDockerUri(model.getDockerUri());
        dto.setqAuthToken(model.getqAuthToken());
        dto.setRole(createDTO(model.getRole()));
        
        return dto;
    }
	
	
	protected CreateUserResponseDTO createUserResponseDTO(User model) {
		CreateUserResponseDTO dto = new CreateUserResponseDTO();
        
		dto.setId(""+model.getId());
        dto.setName(model.getName());
        dto.setEmail(model.getEmail());

        dto.setuId(model.getuId());
        dto.setgId(model.getgId());

        dto.setUserName(model.getUserName());
        dto.setCustomerName(model.getCustomer().getName());
        dto.setRoleName(model.getRole().getName());
        return dto;
    }
	
	protected EntitlementObjectResponseDTO entitlementObjectResponseDTO(Entitlement model) {
		EntitlementObjectResponseDTO dto = new EntitlementObjectResponseDTO();
        
		dto.setId(""+model.getId());
		dto.setName(model.getCustomerName());
        dto.setInfluxdbUrl(model.getInfluxdbUrl());
        dto.setDatabaseName(model.getDatabaseName());
        dto.setInfluxdbUser(model.getInfluxdbUser());
        dto.setInfluxdbPassword(model.getInfluxdbPassword());
        return dto;
    }
	
	/**
	 * @param model
	 * @return
	 */
	protected ApplianceDTO createDTO(Appliance model) {
		ApplianceDTO dto = new ApplianceDTO();
		
		dto.setId(""+model.getId());
		dto.setName(model.getName());
		dto.setServiceKey(""+model.getServiceKey());
		dto.setCustomer(createDTO(model.getCustomer()));
		
		return dto;
	}
	/**
	 * @param model
	 * @return
	 */
	protected RoleDTO createDTO(Role model) {
		RoleDTO dto = new RoleDTO();
		
		dto.setId(""+model.getId());
		dto.setName(model.getName());
		dto.setDescription(model.getDescription());
		
		return dto;
	}
	/**
	 * @param model
	 * @return
	 */
	protected CustomerDTO createDTO(Customer model) {
		CustomerDTO dto = new CustomerDTO();
		
		dto.setId(""+model.getId());
		dto.setName(model.getName());
		dto.setPhone(""+model.getPhone());
		dto.setEmail(""+model.getEmail());
//		for(User user : model.getUsers()){
//			dto.addUser(createDTO(user));
//		}
		return dto;
	}
	//CustomerResponseDTO
	
	/**
	 * @param model
	 * @return
	 */
	protected CustomerResponseDTO createCustomerResponseDTO(Customer model) {
		CustomerResponseDTO dto = new CustomerResponseDTO();
		
		dto.setId(""+model.getId());
		dto.setName(model.getName());
		dto.setEmail(""+model.getEmail());
		return dto;
	}
	
	/**
	 * @param model
	 * @return
	 */
	protected EntitlementResponseDTO createDTO(Entitlement model) {
		EntitlementResponseDTO dto = new EntitlementResponseDTO();
		
		dto.setId(""+model.getId());
		dto.setName(model.getCustomerName());
		dto.setKey(model.getKey());
		dto.setSerialNumber(model.getSerialNumber());
		if(model.getCustomer()!=null){
			dto.setCustomer(model.getCustomer().getName());
		}
//		for(User user : model.getUsers()){
//			dto.addUser(createDTO(user));
//		}
		return dto;
	}
	protected EntitlementNamesRespoponseDTO createEntitlementNamesDTO(Entitlement model) {

		EntitlementNamesRespoponseDTO dto = new EntitlementNamesRespoponseDTO();
		
		dto.setName(model.getCustomerName());
//		for(User user : model.getUsers()){
//			dto.addUser(createDTO(user));
//		}
		return dto;
	}
	/**
	 * @return
	 */
	protected Date now() {
		Calendar cal = Calendar.getInstance();
        return cal.getTime();
    }
	/**
	 * @param authToken
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	protected String generateKey(String authToken) throws NoSuchAlgorithmException {
		MessageDigest md5Handle = MessageDigest.getInstance("MD5");

		md5Handle.reset();
		md5Handle.update(authToken.getBytes());

		byte md5Digest[] = md5Handle.digest();

		StringBuffer authKey = new StringBuffer();

		for (int position = 0; position < md5Digest.length; position++) {
			authKey.append(Integer.toHexString(0xFF & md5Digest[position]));
		}
		return authKey.toString();
	}
	
	protected static String getLoggedInUserEmail(Authentication auth){
		
		UserAuthDTO loggedInUser=	(UserAuthDTO)auth.getPrincipal();
		return loggedInUser.getEmail();
	}
	
	
	protected static String getLoggedInUserRoleName(Authentication auth){
		
		UserAuthDTO loggedInUser=	(UserAuthDTO)auth.getPrincipal();
		return loggedInUser.getRoleName();
	}
	
	
   protected static String getLoggedInUserAuthToken(Authentication auth){
		
		UserAuthDTO loggedInUser=	(UserAuthDTO)auth.getPrincipal();
		return loggedInUser.getAuthKey();
	}

	protected static String getLoggedInUserCustomerID(Authentication auth){
		UserAuthDTO loggedInUser=	(UserAuthDTO)auth.getPrincipal();
		return loggedInUser.getCustomerId();
	}
	

	protected void initMailThread() throws ServletException {
		
		String EMAILS_YES_NO = environment.getProperty("emails.yes.no");
		String ACCESS_KEY = environment.getProperty("nvidia.access.id");
		String SECRET_KEY = environment.getProperty("nvidia.secret.key");
		String INVITE_FROM = environment.getProperty("nvidia.invite.email");
		String REPLY = environment.getProperty("nvidia.reply.email");

		try {
			EmailSenderFactory.init(EMAILS_YES_NO, ACCESS_KEY, INVITE_FROM, REPLY, SECRET_KEY);
		} catch (Exception e) {
			//logger.error("Error while initilizing email thread {}",e);
		}
	}
	
}
