package com.nvidia.cosmos.cloud.exceptions;

public class GraphanaFailedException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public GraphanaFailedException(String message) {
		super(message);
	}

	public GraphanaFailedException(String message, Throwable cause) {
		super(message, cause);
	}
}
