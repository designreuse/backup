package com.nvidia.cosmos.cloud.exceptions;

/**
 * @author bprasad
 */
public class RoleExistsException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RoleExistsException(String message) {
		super(message);
	}

	public RoleExistsException(String message, Throwable cause) {
		super(message, cause);
	}
}
