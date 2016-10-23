package com.nvidia.cosmos.cloud.exceptions;

/**
 * @author bprasad
 */
public class ClusterNotFoundException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClusterNotFoundException(String message) {
		super(message);
	}

	public ClusterNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
