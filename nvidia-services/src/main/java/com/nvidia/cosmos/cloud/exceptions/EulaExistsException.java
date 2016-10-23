package com.nvidia.cosmos.cloud.exceptions;

public class EulaExistsException extends BaseException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EulaExistsException(String message) {
		super(message);
	}

	public EulaExistsException(String message, Throwable cause) {
		super(message, cause);
	}

}
