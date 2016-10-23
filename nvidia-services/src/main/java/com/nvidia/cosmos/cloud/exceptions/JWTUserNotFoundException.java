package com.nvidia.cosmos.cloud.exceptions;

/**
 * @author bprasad
 */
public class JWTUserNotFoundException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JWTUserNotFoundException(String message) {
		super(message);
	}

	public JWTUserNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
