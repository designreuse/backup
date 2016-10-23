package com.nvidia.cosmos.cloud.exceptions;

/**
 * @author bprasad
 */
public class CustomerNotFoundException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomerNotFoundException(String message) {
		super(message);
	}

	public CustomerNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
