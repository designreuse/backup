package com.nvidia.cosmos.cloud.exceptions;

/**
 * @author bprasad
 */
public class RoleNotFoundException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RoleNotFoundException(String message) {
		super(message);
	}

	public RoleNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
