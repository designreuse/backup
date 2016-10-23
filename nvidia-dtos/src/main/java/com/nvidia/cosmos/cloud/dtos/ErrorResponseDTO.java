package com.nvidia.cosmos.cloud.dtos;

public class ErrorResponseDTO implements BaseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int statusCode;
	private String message;
	
	public ErrorResponseDTO(String message, int statusCode){
		this.message = message;
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
}
