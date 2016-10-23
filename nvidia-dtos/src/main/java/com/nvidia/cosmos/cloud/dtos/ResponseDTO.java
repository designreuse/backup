package com.nvidia.cosmos.cloud.dtos;

public class ResponseDTO implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ResponseDTO(){
		
	}
	private String message;
	private int statusCode;
	private Object data;
	
	
	
	public ResponseDTO(String message, int statusCode){
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
	 * @param message the message to set
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
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}

	public Object getQuayUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return "ResponseDTO [message=" + message + ", statusCode=" + statusCode + ", data=" + data + "]";
	}
	

	
	
	
	
}
