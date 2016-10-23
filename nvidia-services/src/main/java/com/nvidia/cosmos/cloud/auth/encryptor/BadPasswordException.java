/**
 *  BadPasswordException.java
 */
package com.nvidia.cosmos.cloud.auth.encryptor;

import java.io.Serializable;

/**
 * BadPasswordException will be used by the Encryptor class.
 * @author bprasad
 *
 */
public class BadPasswordException extends Exception implements Serializable {

	/**
	 *  long:serialVersionUID
	 */
	private static final long serialVersionUID = 1418113464007877014L;

	/**
	 * 
	 */
	public BadPasswordException() {
		super();
	}
	/**
	 * @param message
	 * @param cause
	 */
	public BadPasswordException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public BadPasswordException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public BadPasswordException(Throwable cause) {
		super(cause);
	}
	
	
	

	
}
