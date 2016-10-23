package com.nvidia.cosmos.cloud.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
//@JsonIgnoreProperties(ignoreUnknown=true)
public class ApiResponseDTO implements BaseDTO{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message;
	
	private int statusCode;
	
	public ApiResponseDTO(){
		
	}
	
	public ApiResponseDTO(String message, int statusCode){
		this.setMessage(message);
		this.setStatusCode(statusCode);
	}

//	@JsonProperty("Message")
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

//	@JsonProperty("StatusCode")
	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	@Override
	public String toString() {
		return "ApiResponseDTO [message=" + message + ", statusCode=" + statusCode + "]";
	}
	
	
}
