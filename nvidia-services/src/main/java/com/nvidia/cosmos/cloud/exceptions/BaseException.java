package com.nvidia.cosmos.cloud.exceptions;

public class BaseException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param message
	 */
	public BaseException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}
}
