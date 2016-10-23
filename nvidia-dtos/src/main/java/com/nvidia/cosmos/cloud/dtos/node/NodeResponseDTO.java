package com.nvidia.cosmos.cloud.dtos.node;

import java.io.Serializable;

public class NodeResponseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NodeResponseDTO() {
	}

	private String message;
	private int statusCode;

	/**
	 * @param message
	 * @param statusCode
	 */
	public NodeResponseDTO(String message, int statusCode) {
		super();
		this.message = message;
		this.statusCode = statusCode;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the statusCode
	 */
	public int getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode
	 *            the statusCode to set
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NodeResponseDTO [message=" + message + ", statusCode=" + statusCode + "]";
	}

}
