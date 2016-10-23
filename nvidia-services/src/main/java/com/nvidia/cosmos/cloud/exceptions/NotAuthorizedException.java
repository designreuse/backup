package com.nvidia.cosmos.cloud.exceptions;

/**
 * @author bprasad
 */
public class NotAuthorizedException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotAuthorizedException(String message) {
		super(message);
	}

	public NotAuthorizedException(String message, Throwable cause) {
		super(message, cause);
	}
}
