package com.nvidia.cosmos.cloud.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DockerRepoResponseDTO implements BaseDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("error_description")
	private String errorDescription;
	@JsonProperty("error_type")
	private String errorType;
	@JsonProperty("message")
	private String message;
	private int statusCode;

	/**
	 * @return the errorDescription
	 */
	public String getErrorDescription() {
		return errorDescription;
	}

	/**
	 * @param errorDescription
	 *            the errorDescription to set
	 */
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	/**
	 * @return the errorType
	 */
	public String getErrorType() {
		return errorType;
	}

	/**
	 * @param errorType
	 *            the errorType to set
	 */
	public void setErrorType(String errorType) {
		this.errorType = errorType;
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

}
