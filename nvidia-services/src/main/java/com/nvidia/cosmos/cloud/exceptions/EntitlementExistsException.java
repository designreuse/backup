package com.nvidia.cosmos.cloud.exceptions;

/**
 * @author bprasad
 */
public class EntitlementExistsException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EntitlementExistsException(String message) {
		super(message);
	}

	public EntitlementExistsException(String message, Throwable cause) {
		super(message, cause);
	}
}
