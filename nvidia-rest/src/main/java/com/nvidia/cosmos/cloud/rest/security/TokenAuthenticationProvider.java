package com.nvidia.cosmos.cloud.rest.security;

import org.apache.commons.lang.SerializationUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import com.google.common.base.Optional;
import com.nvidia.cosmos.cloud.exceptions.UserAuthNotFoundException;
import com.nvidia.cosmos.cloud.rest.controller.BaseController;
import com.nvidia.cosmos.cloud.services.userauth.model.UserAuth;
import com.nvidia.cosmos.cloud.services.userauth.service.UserAuthService;

public class TokenAuthenticationProvider extends BaseController implements AuthenticationProvider {

    //private TokenService tokenService;
      private UserAuthService userAuthservice;
    public TokenAuthenticationProvider() {
        this.userAuthservice=factory.getUserAuthService();
    	//this.tokenService = tokenService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    	 Authentication authintication=null;
        Optional<String> token = (Optional) authentication.getPrincipal();
        if (!token.isPresent() || token.get().isEmpty()) {
            throw new BadCredentialsException("Invalid token");
        }
        try {
			UserAuth authValue=  userAuthservice.findUserAuthByAuthToken(token.get());
			authintication = (AuthenticationWithToken) SerializationUtils.deserialize(authValue.getAuthenticationWithToken());
		} catch (UserAuthNotFoundException e) {
			throw new BadCredentialsException("Invalid token");
		}
        
        		
       if (authintication==null) {
            throw new BadCredentialsException("Invalid token or token expired");
        }
        return authintication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(PreAuthenticatedAuthenticationToken.class);
    }
}
