package com.nvidia.cosmos.cloud.exceptions;

public class JobNotFoundException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JobNotFoundException(String message) {
		super(message);
	}

	public JobNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
