package com.nvidia.cosmos.cloud.rest.security;

public interface ServiceAuthenticator {
	 AuthenticationWithToken authenticate(String username, String password);
}
