package com.nvidia.cosmos.cloud.exceptions;

/**
 * @author bprasad
 */
public class NodeNotFoundException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NodeNotFoundException(String message) {
		super(message);
	}

	public NodeNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
