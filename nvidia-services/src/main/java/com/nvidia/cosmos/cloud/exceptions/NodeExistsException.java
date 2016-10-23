package com.nvidia.cosmos.cloud.exceptions;

/**
 * @author bprasad
 */
public class NodeExistsException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NodeExistsException(String message) {
		super(message);
	}

	public NodeExistsException(String message, Throwable cause) {
		super(message, cause);
	}
}
