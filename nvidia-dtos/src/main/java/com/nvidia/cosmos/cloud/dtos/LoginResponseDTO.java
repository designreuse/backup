package com.nvidia.cosmos.cloud.dtos;

public class LoginResponseDTO implements java.io.Serializable {
	/**
	 * 
	 */
	
	public LoginResponseDTO(String message, int statusCode){
		this.message = message;
		this.statusCode = statusCode;
	}
	public LoginResponseDTO(){
		
	}
	private static final long serialVersionUID = 1L;
	private String message;
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

	public String getDecryptedEmail() {
		return decryptedEmail;
	}
	public void setDecryptedEmail(String decryptedEmail) {
		this.decryptedEmail = decryptedEmail;
	}
	private int statusCode;
	//private Object data;
	private String decryptedEmail;
	
	
}
