package com.nvidia.cosmos.cloud.exceptions;

/**
 * @author bprasad
 */
public class ApplianceExistsException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ApplianceExistsException(String message) {
		super(message);
	}

	public ApplianceExistsException(String message, Throwable cause) {
		super(message, cause);
	}
}
