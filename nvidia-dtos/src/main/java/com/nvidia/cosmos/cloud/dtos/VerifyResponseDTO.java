package com.nvidia.cosmos.cloud.dtos;

public class VerifyResponseDTO {

	private String userId;	
	private boolean isSentEmail;
	private String email;
	private boolean verified;	
	private boolean registartion;
	private boolean registration;
	private String roleName;
	private String clientId;
	
	public VerifyResponseDTO() {
		// TODO Auto-generated constructor stub
	}
	public VerifyResponseDTO(String userId){
		this.userId = userId;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public boolean isSentEmail() {
		return isSentEmail;
	}
	public void setSentEmail(boolean isSentEmail) {
		this.isSentEmail = isSentEmail;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isVerified() {
		return verified;
	}
	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	public boolean isRegistartion() {
		return registartion;
	}
	public void setRegistartion(boolean registartion) {
		this.registartion = registartion;
	}
	public boolean isRegistration() {
		return registration;
	}
	public void setRegistration(boolean registration) {
		this.registration = registration;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	@Override
	public String toString() {
		return "VerifyResponseDTO [userId=" + userId + ", isSentEmail=" + isSentEmail + ", email=" + email
				+ ", verified=" + verified + ", registartion=" + registartion + ", registration=" + registration
				+ ", roleName=" + roleName + ", clientId=" + clientId + "]";
	}
	
	

}
