package com.nvidia.cosmos.cloud.exceptions;

/**
 * @author bprasad
 */
public class UserAuthExistsException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserAuthExistsException(String message) {
		super(message);
	}

	public UserAuthExistsException(String message, Throwable cause) {
		super(message, cause);
	}
}
