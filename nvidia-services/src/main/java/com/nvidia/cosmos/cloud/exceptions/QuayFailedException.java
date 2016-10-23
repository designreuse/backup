package com.nvidia.cosmos.cloud.exceptions;

public class QuayFailedException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public QuayFailedException(String message) {
		super(message);
	}

	public QuayFailedException(String message, Throwable cause) {
		super(message, cause);
	}
}
