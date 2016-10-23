package com.nvidia.cosmos.cloud.exceptions;

public class DBUniqueResultException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DBUniqueResultException(String message) {
		super(message);
	}

	public DBUniqueResultException(String message, Throwable cause) {
		super(message, cause);
	}

}
