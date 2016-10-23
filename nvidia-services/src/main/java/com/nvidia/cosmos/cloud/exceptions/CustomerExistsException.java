package com.nvidia.cosmos.cloud.exceptions;

/**
 * @author bprasad
 */
public class CustomerExistsException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomerExistsException(String message) {
		super(message);
	}

	public CustomerExistsException(String message, Throwable cause) {
		super(message, cause);
	}
}
