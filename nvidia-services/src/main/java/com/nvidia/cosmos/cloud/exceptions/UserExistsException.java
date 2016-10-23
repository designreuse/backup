package com.nvidia.cosmos.cloud.exceptions;

/**
 * @author bprasad
 */
public class UserExistsException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserExistsException(String message) {
		super(message);
	}

	public UserExistsException(String message, Throwable cause) {
		super(message, cause);
	}
}
