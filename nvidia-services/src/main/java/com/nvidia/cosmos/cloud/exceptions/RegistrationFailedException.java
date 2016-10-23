package com.nvidia.cosmos.cloud.exceptions;

public class RegistrationFailedException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public RegistrationFailedException(String message) {
		super(message);
	}

	public RegistrationFailedException(String message, Throwable cause) {
		super(message, cause);
	}

}
