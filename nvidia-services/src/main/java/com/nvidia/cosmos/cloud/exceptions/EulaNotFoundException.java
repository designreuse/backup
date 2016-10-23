package com.nvidia.cosmos.cloud.exceptions;

public class EulaNotFoundException extends BaseException {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EulaNotFoundException(String message) {
		super(message);
	}

	public EulaNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
