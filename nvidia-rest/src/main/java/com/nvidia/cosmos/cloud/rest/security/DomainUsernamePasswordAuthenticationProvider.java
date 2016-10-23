package com.nvidia.cosmos.cloud.rest.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.google.common.base.Optional;

public class DomainUsernamePasswordAuthenticationProvider implements AuthenticationProvider   {

	private static Logger logger = LoggerFactory.getLogger(DomainUsernamePasswordAuthenticationProvider.class);
   
    private ServiceAuthenticator serviceAuthenticator;
   
     public DomainUsernamePasswordAuthenticationProvider(ServiceAuthenticator externalServiceAuthenticator) {
          this.serviceAuthenticator = externalServiceAuthenticator;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Optional<String> username = (Optional) authentication.getPrincipal();
        Optional<String> password = (Optional) authentication.getCredentials();

        if (!username.isPresent() || !password.isPresent()) {
        	logger.info("Invalid User Credentials");
            throw new BadCredentialsException("Invalid User Credentials");
        }
          AuthenticationWithToken resultOfAuthentication = serviceAuthenticator.authenticate(username.get(), password.get());
         return resultOfAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
   
}