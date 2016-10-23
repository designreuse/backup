package com.nvidia.cosmos.cloud.exceptions;

/**
 * @author bprasad
 */
public class EntitlementNotFoundException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EntitlementNotFoundException(String message) {
		super(message);
	}

	public EntitlementNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
