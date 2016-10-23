package com.nvidia.cosmos.cloud.dtos;

public class JobResponseDTO implements java.io.Serializable {
	private String message;
	private int statusCode;
	private Object data;
	private String decryptedEmail;
	private String quayUrl;
	private String tomcatUrl;
	private String nvidiaUrl;
	private boolean updateStatus;
	
	
	public JobResponseDTO(String message, int statusCode){
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
	/**
	 * @return the decryptedEmail
	 */
	public String getDecryptedEmail() {
		return decryptedEmail;
	}
	/**
	 * @param decryptedEmail the decryptedEmail to set
	 */
	public void setDecryptedEmail(String decryptedEmail) {
		this.decryptedEmail = decryptedEmail;
	}
	public String getQuayUrl() {
		return quayUrl;
	}
	public void setQuayUrl(String quayUrl) {
		this.quayUrl = quayUrl;
	}
	public boolean isUpdateStatus() {
		return updateStatus;
	}
	public void setUpdateStatus(boolean updateStatus) {
		this.updateStatus = updateStatus;
	}
	@Override
	public String toString() {
		return "ResponseDTO [message=" + message + ", statusCode=" + statusCode
				+ ", data=" + data + ", decryptedEmail=" + decryptedEmail
				+ ", quayUrl=" + quayUrl + ", updateStatus=" + updateStatus
				+ "]";
	}

	public String getTomcatUrl() {
		return tomcatUrl;
	}

	public void setTomcatUrl(String tomcatUrl) {
		this.tomcatUrl = tomcatUrl;
	}

	public String getNvidiaUrl() {
		return nvidiaUrl;
	}

	public void setNvidiaUrl(String nvidiaUrl) {
		this.nvidiaUrl = nvidiaUrl;
	}
}
