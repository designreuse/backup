package com.nvidia.cosmos.cloud.exceptions;

/**
 * @author bprasad
 */
public class ApplianceNotFoundException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ApplianceNotFoundException(String message) {
		super(message);
	}

	public ApplianceNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
