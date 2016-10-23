package com.nvidia.cosmos.cloud.exceptions;

public class OrganizationExistsException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OrganizationExistsException(String message) {
		super(message);
	}

	public OrganizationExistsException(String message, Throwable cause) {
		super(message, cause);
	}
}
