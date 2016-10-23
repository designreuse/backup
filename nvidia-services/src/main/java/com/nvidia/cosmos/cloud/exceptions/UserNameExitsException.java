package com.nvidia.cosmos.cloud.exceptions;

/**
 * @author bprasad
 */
public class UserNameExitsException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNameExitsException(String message) {
		super(message);
	}

	public UserNameExitsException(String message, Throwable cause) {
		super(message, cause);
	}
}
