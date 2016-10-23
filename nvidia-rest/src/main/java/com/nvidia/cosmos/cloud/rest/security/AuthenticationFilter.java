package com.nvidia.cosmos.cloud.rest.security;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.util.UrlPathHelper;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.nvidia.cosmos.cloud.common.ServicesConstants;
import com.nvidia.cosmos.cloud.exceptions.UserNameExitsException;
import com.nvidia.cosmos.cloud.exceptions.UserNotFoundException;
import com.nvidia.cosmos.cloud.factory.ServicesFactory;
import com.nvidia.cosmos.cloud.rest.controller.BaseController;
import com.nvidia.cosmos.cloud.services.user.model.User;
import com.nvidia.cosmos.cloud.services.user.service.UserService;

public class AuthenticationFilter extends GenericFilterBean {

	@Autowired
	public Environment environment;
	
    private final static Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);
    public static final String TOKEN_SESSION_KEY = "token";
    public static final String USER_SESSION_KEY = "user";
    private AuthenticationManager authenticationManager;
    
    private UserService userService;
    public ServicesFactory factory = null;
    int maxAttempts = 5;
	 int minToDisableAccount = 10;
    
      
    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        try{
    		factory = ServicesFactory.getInstance();
    		
    	}catch(Exception e){
    		//e.printStackTrace();
    		logger.error(e.getMessage());
    	}
       
        this.userService = factory.getUserService();
   }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = asHttp(request);
        HttpServletResponse httpResponse = asHttp(response);
        
        Enumeration<String> headerNames = httpRequest.getHeaderNames();
        while(headerNames.hasMoreElements()) {
          String headerName = (String)headerNames.nextElement();
          logger.debug("{}" , headerName);
          logger.debug("{}" , httpRequest.getHeader(headerName));
        }

        Optional<String> username = Optional.fromNullable(httpRequest.getHeader("X-Auth-Username"));
        Optional<String> password = Optional.fromNullable(httpRequest.getHeader("X-Auth-Password"));
        Optional<String> token = Optional.fromNullable(httpRequest.getHeader("X-Auth-Token"));

        String resourcePath = new UrlPathHelper().getPathWithinApplication(httpRequest);
         logger.debug("requested url {}",resourcePath);
        try {
           
        	
        	if(username.isPresent() && password.isPresent()){
				 String failedErrorMessage= isFailedAttemptsCrossed(username.get(),httpRequest);
				 if(failedErrorMessage != null){
					 throw new BadCredentialsException(failedErrorMessage);
				 }
					
       		}
        	
        	
        	if (postToAuthenticate(httpRequest, resourcePath)) {
                logger.debug("Trying to authenticate user {} by X-Auth-Username method", username);
                processUsernamePasswordAuthentication(httpResponse, username, password);
   				logInSuccess(username.get(),httpRequest);
               }
        	

            if (token.isPresent()) {
                logger.debug("Trying to authenticate user by X-Auth-Token method. Token: {}", token);
                processTokenAuthentication(token);
                
            }
         
            logger.debug("AuthenticationFilter is passing request down the filter chain");
            addSessionContextToLogging();
            chain.doFilter(request, response);
        } catch (InternalAuthenticationServiceException internalAuthenticationServiceException) {
            SecurityContextHolder.clearContext();
            logger.error("Internal authentication service exception", internalAuthenticationServiceException);
            httpResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (AuthenticationException authenticationException) {
        	String errorMessage = authenticationException.getMessage();
        	if(username.isPresent()){
        	verifyFailedCount(username.get(),httpRequest);
        	}
        	SecurityContextHolder.clearContext();
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, errorMessage);
        } finally {
            MDC.remove(TOKEN_SESSION_KEY);
            MDC.remove(USER_SESSION_KEY);
        }
    }
    
    private void logInSuccess(String email, HttpServletRequest request) {
    	User user = null;
		try {
			user = userService.findUserByEmail(email);
		} catch (UserNotFoundException e) {
			logger.error("User not found {}",e.getMessage());
		}
		if(user == null || request  == null || request.getHeader("referer")  == null)
		  return ;
		
		user.setLoginFailedCount(0);
		user.setLastLoginTime(0L);
		try {
			userService.mergeUser(user);
		} catch (UserNotFoundException | UserNameExitsException e) {
			logger.error("User not found {}",e.getMessage());
		}
		
    }
    private String isFailedAttemptsCrossed(String email, HttpServletRequest request) {
    	String errorMessage = null;
    	
    		User user = null;
			try {
				user = userService.findUserByEmail(email);
			} catch (UserNotFoundException e) {
				logger.error("User not found {}",e.getMessage());
			}
			if(user == null || request  == null || request.getHeader("referer")  == null)
			  return errorMessage;
			
			if((user.getRole().getName().equals(ServicesConstants.SUPER_ADMIN) && request.getHeader("referer").contains("admin.html"))
					|| (!user.getRole().getName().equals(ServicesConstants.SUPER_ADMIN) && request.getHeader("referer").contains("main.html"))){
				readFile();
				if ((user.getLoginFailedCount() >= maxAttempts)) {
					if (((System.currentTimeMillis() - user.getLastLoginTime()) / 1000) < (minToDisableAccount * 60)) {
						NumberToWord obj = new NumberToWord();
						String message = obj.convert(maxAttempts+1) + " wrong attempts. Try after "
								+ obj.convert((int) (long)((((minToDisableAccount * 60) - ((System.currentTimeMillis() - user.getLastLoginTime()) / 1000)) / 60) + 1)).toLowerCase()
								+ " minute.";
						//throw new UserLogginAttemptsException(message);
						return errorMessage = message;
						
					} else {
						user.setLoginFailedCount(0);
						user.setLastLoginTime(0L);
						
						try {
							userService.mergeUser(user);
						} catch (UserNotFoundException | UserNameExitsException e) {
							logger.error("User not found {}",e.getMessage());
						}
					}
				}
			}
			/*if(errorMessage ==null){
				verifyFailedCount(email,request);
			}*/
			
			return errorMessage;
		
	}
    
    private void verifyFailedCount(String email,  HttpServletRequest request){
    	User user = null;
    	try {
    		user = userService.findUserByEmail(email);
			
			if(user == null || request  == null || request.getHeader("referer")  == null)
			  return;
			
        	if((user.getRole().getName().equals(ServicesConstants.SUPER_ADMIN) && request.getHeader("referer").contains("admin.html"))
        			|| (!user.getRole().getName().equals(ServicesConstants.SUPER_ADMIN) && request.getHeader("referer").contains("main.html"))){
        		user.setLastLoginTime(System.currentTimeMillis());
    			user.setLoginFailedCount(user.getLoginFailedCount() + 1);
			} 
    		userService.mergeUser(user);
    		
		} catch (UserNotFoundException e) {
			logger.error("User not found with email {}",email);
			
		} catch (UserNameExitsException e) {
			logger.error("UserName exists {}",e.getMessage());
			
		}
		
		
	} 
    
    private void readFile(){
    	Properties prop = new Properties();
		InputStream input = null;

		try {
            String filename = "config.properties";
			input = getClass().getClassLoader().getResourceAsStream(filename);
			if (input == null) {
				return;
			}
            prop.load(input);

			maxAttempts= Integer.parseInt(prop.getProperty("max.failed.login.attempts.allowed"));
			minToDisableAccount = Integer.parseInt(prop.getProperty("minutues.to.disable.account"));
		} catch (IOException ex) {
			logger.error("IOException {}",ex.getMessage());
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					logger.error("IOException {}",e.getMessage());
				}
			}
		}
    }

    private void addSessionContextToLogging() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String tokenValue = "EMPTY";
        if (authentication != null && !Strings.isNullOrEmpty(authentication.getDetails().toString())) {
            MessageDigestPasswordEncoder encoder = new MessageDigestPasswordEncoder("SHA-1");
            tokenValue = encoder.encodePassword(authentication.getDetails().toString(), "not_so_random_salt");
        }
        MDC.put(TOKEN_SESSION_KEY, tokenValue);

        String userValue = "EMPTY";
        if (authentication != null && !Strings.isNullOrEmpty(authentication.getPrincipal().toString())) {
            userValue = authentication.getPrincipal().toString();
        }
        MDC.put(USER_SESSION_KEY, userValue);
    }

    private HttpServletRequest asHttp(ServletRequest request) {
        return (HttpServletRequest) request;
    }

    private HttpServletResponse asHttp(ServletResponse response) {
        return (HttpServletResponse) response;
    }

    private boolean postToAuthenticate(HttpServletRequest httpRequest, String resourcePath) {
        return BaseController.AUTHENTICATE_URL.equalsIgnoreCase(resourcePath) && httpRequest.getMethod().equals("POST");
    }

    private void processUsernamePasswordAuthentication(HttpServletResponse httpResponse, Optional<String> username, Optional<String> password) throws IOException {
        Authentication resultOfAuthentication = tryToAuthenticateWithUsernameAndPassword(username, password);
        SecurityContextHolder.getContext().setAuthentication(resultOfAuthentication);
      
    }

    private Authentication tryToAuthenticateWithUsernameAndPassword(Optional<String> username, Optional<String> password) {
        UsernamePasswordAuthenticationToken requestAuthentication = new UsernamePasswordAuthenticationToken(username, password);
        return tryToAuthenticate(requestAuthentication);
    }

    private void processTokenAuthentication(Optional<String> token) {
        Authentication resultOfAuthentication = tryToAuthenticateWithToken(token);
        SecurityContextHolder.getContext().setAuthentication(resultOfAuthentication);
    }

    private Authentication tryToAuthenticateWithToken(Optional<String> token) {
        PreAuthenticatedAuthenticationToken requestAuthentication = new PreAuthenticatedAuthenticationToken(token, null);
        return tryToAuthenticate(requestAuthentication);
    }

    private Authentication tryToAuthenticate(Authentication requestAuthentication) {
        Authentication responseAuthentication = authenticationManager.authenticate(requestAuthentication);
        if (responseAuthentication == null || !responseAuthentication.isAuthenticated()) {
            throw new InternalAuthenticationServiceException("Unable to authenticate Domain User for provided credentials");
        }
        logger.debug("User successfully authenticated");
        return responseAuthentication;
    }
}

 
