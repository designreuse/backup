package com.nvidia.cosmos.cloud.exceptions;

/**
 * @author bprasad
 */
public class UserAuthNotFoundException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserAuthNotFoundException(String message) {
		super(message);
	}

	public UserAuthNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
