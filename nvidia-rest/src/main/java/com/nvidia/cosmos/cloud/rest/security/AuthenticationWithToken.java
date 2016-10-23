package com.nvidia.cosmos.cloud.rest.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.io.Serializable;
import java.util.Collection;

public class AuthenticationWithToken extends PreAuthenticatedAuthenticationToken implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1123456789L;

	public AuthenticationWithToken(Object aPrincipal, Object aCredentials) {
        super(aPrincipal, aCredentials);
    }

    public AuthenticationWithToken(Object aPrincipal, Object aCredentials, Collection<? extends GrantedAuthority> anAuthorities) {
        super(aPrincipal, aCredentials, anAuthorities);
    }

    public void setToken(String token) {
        setDetails(token);
    }

    public String getToken() {
        return (String)getDetails();
    }
}
