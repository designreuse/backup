package com.nvidia.cosmos.cloud.exceptions;

/**
 * @author bprasad
 */
public class SerialNumberExitsException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SerialNumberExitsException(String message) {
		super(message);
	}

	public SerialNumberExitsException(String message, Throwable cause) {
		super(message, cause);
	}
}