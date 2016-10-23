package com.nvidia.cosmos.cloud.exceptions;

/**
 * @author bprasad
 */
public class ClusterExistsException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClusterExistsException(String message) {
		super(message);
	}

	public ClusterExistsException(String message, Throwable cause) {
		super(message, cause);
	}
}
