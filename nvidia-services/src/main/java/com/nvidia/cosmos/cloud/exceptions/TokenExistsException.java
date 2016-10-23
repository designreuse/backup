package com.nvidia.cosmos.cloud.exceptions;

/**
 * @author bprasad
 */
public class TokenExistsException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TokenExistsException(String message) {
		super(message);
	}

	public TokenExistsException(String message, Throwable cause) {
		super(message, cause);
	}
}
