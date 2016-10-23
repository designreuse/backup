package com.nvidia.cosmos.cloud.rest.security;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.SerializationUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.transaction.annotation.Transactional;

import com.nvidia.cosmos.cloud.auth.encryptor.EncryptorFactory;
import com.nvidia.cosmos.cloud.common.ServicesConstants;
import com.nvidia.cosmos.cloud.common.UserStatusEnum;
import com.nvidia.cosmos.cloud.dtos.AuthDTO;
import com.nvidia.cosmos.cloud.dtos.UserAuthDTO;
import com.nvidia.cosmos.cloud.exceptions.BaseException;
import com.nvidia.cosmos.cloud.exceptions.UserAuthNotFoundException;
import com.nvidia.cosmos.cloud.exceptions.UserNotFoundException;
import com.nvidia.cosmos.cloud.rest.controller.BaseController;
import com.nvidia.cosmos.cloud.rest.util.GrafanaClient;
import com.nvidia.cosmos.cloud.services.user.model.User;
import com.nvidia.cosmos.cloud.services.user.service.UserService;
import com.nvidia.cosmos.cloud.services.userauth.model.UserAuth;
import com.nvidia.cosmos.cloud.services.userauth.service.UserAuthService;
import com.nvidia.cosmos.cloud.util.EncryptionUtil;
public class NvidiaServiceAuthenticator extends  BaseController implements ServiceAuthenticator {
	private static Logger logger = LoggerFactory.getLogger(NvidiaServiceAuthenticator.class);
   
	 private UserService service;
	 private UserAuthService userAuthservice;
	 
	 private static String grafanaUserName = null;
	private static String grafanaPassword = null;
	private static String grafanaUrl = null;
	
	 
	 NvidiaServiceAuthenticator(){
		this.service = factory.getUserService();
		this.userAuthservice = factory.getUserAuthService();
	}
	 
	 
	 @PostConstruct
		private void init() {
			try {
				if (environment.getProperty("grafana.host") != null)
					grafanaUrl = environment.getProperty("grafana.host");

				grafanaUserName = environment.getProperty(ServicesConstants.grafanaUser);
				grafanaPassword = environment.getProperty(ServicesConstants.grafanaPwd);
				

			} catch (Exception e) {
				logger.error("Error while reading properties for login attemts", e);
			}
		}
	
	@Override
	public AuthenticationWithToken authenticate(String username, String password) {
		AuthenticationWithToken authenticatedNvidiaService = null;

		try {
			UserAuthDTO userDto = getUserAuthDTO(new AuthDTO(username.toLowerCase(), password));
			if(userDto==null){
				logger.info("Invalid credentials attempt for user {}",username);
				throw new BadCredentialsException("Invalid email/password");
			}
			authenticatedNvidiaService = new AuthenticationWithToken(userDto, null,
					AuthorityUtils.commaSeparatedStringToAuthorityList(userDto.getRoleName()));
			authenticatedNvidiaService.setToken(userDto.getAuthKey());
			UserAuth userAuth = userAuthservice.findUserAuthByAuthToken(userDto.getAuthKey());
			userAuth.setAuthenticationWithToken(SerializationUtils.serialize(authenticatedNvidiaService));
			userAuthservice.mergeUserAuth(userAuth);
		} catch (UserNotFoundException | UserAuthNotFoundException e) {
			throw new BadCredentialsException(e.getMessage());
		}

		return authenticatedNvidiaService;
	}
    
	
	
	
	@Transactional(rollbackFor = { UserNotFoundException.class, BaseException.class }) 
    private UserAuthDTO getUserAuthDTO(AuthDTO dto) throws UserNotFoundException{
    	//logger.debug("Authenticating the user with password :" + new Object[] { dto.getEmail() });
    			String message = null;
    			//String statusCode = HttpStatus.OK.toString();
    			UserAuthDTO returndto = null;
    			logger.debug("Validating user from local store");
    			User user = service.findUserByEmail(dto.getEmail());
    				
    			if (user.getStatus().equalsIgnoreCase(UserStatusEnum.ACTIVE.toString())) {
    				try {
    					 if (EncryptorFactory.getBCryptEncryptor().matches(dto.getPassword(), user.getPassword())
    							|| EncryptorFactory.getBCryptEncryptor().matches(EncryptionUtil.getHashCode(dto.getPassword()), user.getPassword())){//.encrypt(dto.getPassword()).equals(user.getPassword())) {
    						String authToken = UUID.randomUUID().toString();//user.getId() + user.getName() + user.getEmail() + System.currentTimeMillis();
    						String authKey = null;
    						try {
    							authKey = generateKey(authToken);
    						} catch (NoSuchAlgorithmException e) {
    							throw new UserNotFoundException(e.getMessage());
    						}
    						String encAuthKey=EncryptorFactory.getBCryptEncryptor().encode(authKey);										
    						
    						UserAuth userAuth = new UserAuth(encAuthKey, user.getName(), user.getEmail(), user.getRole().getName());
    						//get the grafana authkey for customer admin and customer user if grafanad orgID is associated with that user.
    						//if user is already having session, use previous session grfanaAutkey for the new session too
				/*	if ((!user.getRole().equals(ServicesConstants.SUPER_ADMIN)
							&& (!StringUtils.isEmpty(user.getCustomer().getGrafanaOrgId())))) {
						logger.debug("User has role {} retriving grafana authkey for orgID {} for this sesssion ",
								user.getRole(), user.getCustomer().getGrafanaOrgId());
						String grafanaAuthKey = null;
						List<UserAuth> existingSessionAuth = null;

						try {
							existingSessionAuth = userAuthservice.findUserAuthByEmail(user.getEmail());

						} catch (Exception e) {
							logger.debug("User doesn't have logged in session");
						}
						if (existingSessionAuth != null && existingSessionAuth.size() > 0
								&& existingSessionAuth.get(0).getGrafanaToken() != null) {
							grafanaAuthKey = existingSessionAuth.get(0).getGrafanaToken();
						} else {
							grafanaAuthKey = GrafanaClient.createUserAuthKey(user.getUserName(),
									user.getCustomer().getGrafanaOrgId(), grafanaUrl, grafanaUserName, grafanaPassword);
						}
						userAuth.setGrafanaToken(grafanaAuthKey);
						logger.debug("Recived grafana session auth key {} for grafanaOrdID {}", grafanaAuthKey,
								user.getCustomer().getGrafanaOrgId());
					}*/
    						
    						userAuthservice.saveUserAuth(userAuth);
    						userAuth=userAuthservice.findUserAuthByAuthToken(encAuthKey);
    						
    						message = environment.getProperty("user.validate.success");
    						
    						returndto = new UserAuthDTO(encAuthKey, user.getName(), user.getEmail(),user.getRole().getName(),
    								message, user.getUserName(),"" + user.getCustomer().getId(),""+user.getId());
    						if(user.getqAuthToken() != null){
    							returndto.setIsRegistered("yes");
    						}
    	                   /**
    	                    * REST API call to get QAuth tocken
    	                    */
    						
    						user.setLastLogin(new Date());
    						user.setLoginFailedCount(0);
    						user.setLastLoginTime(0L);
    						service.updateUser(user);
    						logger.info("{} authinticated ",user.getName() );
    						
    					} else {
    						//user.setLastLoginTime(System.currentTimeMillis());
    						//service.mergeUser(user);
    						message = environment.getProperty("login.failed");
    						throw new UserNotFoundException(message);
    					}

    				} catch (Exception e) {
    					throw new UserNotFoundException(e.getMessage(), e);
    				}
    			} else {
    				logger.error("User is not in active mode");
    				message = environment.getProperty("user.inactive");
    				throw new UserNotFoundException(message);
    			}

    			logger.debug("User is authenticated with email {}", dto.getEmail());
    			
    			return returndto;
    }
    
    
	
    
    
}



class NumberToWord  
{
   private static final String[] specialNames = {
       "",
       " thousand",
       " million",
       " billion",
       " trillion",
       " quadrillion",
       " quintillion"
   };
   
   private static final String[] tensNames = {
       "",
       " Ten",
       " Twenty",
       " Thirty",
       " Forty",
       " Fifty",
       " Sixty",
       " Seventy",
       " Eighty",
       " Ninety"
   };
   
   private static final String[] numNames = {
       "",
       " One",
       " Two",
       " Three",
       " Four",
       " Five",
       " Six",
       " Seven",
       " Eight",
       " Nine",
       " Ten",
       " Eleven",
       " Twelve",
       " Thirteen",
       " Fourteen",
       " Fifteen",
       " Sixteen",
       " Seventeen",
       " Eighteen",
       " Nineteen"
   };
   
   private String convertLessThanOneThousand(int number) {
       String current;
       
       if (number % 100 < 20){
           current = numNames[number % 100];
           number /= 100;
       }
       else {
           current = numNames[number % 10];
           number /= 10;
           
           current = tensNames[number % 10] + current;
           number /= 10;
       }
       if (number == 0) return current;
       return numNames[number] + " hundred" + current;
   }
   
   public String convert(int number) {

       if (number == 0) { return "zero"; }
       
       String prefix = "";
       
       if (number < 0) {
           number = -number;
           prefix = "negative";
       }
       
       String current = "";
       int place = 0;
       
       do {
           int n = number % 1000;
           if (n != 0){
               String s = convertLessThanOneThousand(n);
               current = s + specialNames[place] + current;
           }
           place++;
           number /= 1000;
       } while (number > 0);
       
       return (prefix + current).trim();
   }
   
  
}



