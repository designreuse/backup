package com.nvidia.cosmos.cloud.rest.exception;

import com.nvidia.cosmos.cloud.exceptions.BaseException;

/**
 * @author pbatta
 *
 */
public class NotAuthorizeException extends BaseException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotAuthorizeException(String message) {
		super(message);
	}

	public NotAuthorizeException(String message, Throwable cause) {
		super(message, cause);
	}
}
