package com.nvidia.cosmos.cloud.exceptions;

/**
 * @author bprasad
 */
public class TokenNotFoundException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TokenNotFoundException(String message) {
		super(message);
	}

	public TokenNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
