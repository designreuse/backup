package com.nvidia.cosmos.cloud.dtos;

public class JWTResponseDTO implements java.io.Serializable{
	
	/**
	 * 
	 */
	
	
	private static final long serialVersionUID = 1L;
	public JWTResponseDTO(String token,int status) {
		super();
		this.token = token;
		this.statusCode=status;
	}
	private String token;
	private int statusCode;
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public JWTResponseDTO(String tk){
		this.token=tk;
	}
	
		
	
	

	
	
	
}
