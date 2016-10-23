package com.nvidia.cosmos.cloud.dtos;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @author bprasad
 *
 */
public class ResetPasswordDTO implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	
	private String userId;
	
	private String password;
	//password used to set in QUAY
	private String passwordTxt;
	private boolean isSentEmail;
	public String getPasswordTxt() {
		return passwordTxt;
	}
	public void setPasswordTxt(String passwordTxt) {
		this.passwordTxt = passwordTxt;
	}
	
	@NotEmpty(message = "Email should not be empty")
	@Size(max=128, message="Email should be maxiumum 128 characters")
	@Pattern(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",message="Email is not valid")
	@ApiModelProperty(required = true, value="Mandatory")
	private String email;
	
	private String oldPwd;

	private String name;
	private boolean verified;
	
	private boolean registartion;
	private String roleName;
	private String userName;
	private String qAuthToken;
	private String clientId;
	private String uId;
	private String gId;
	private boolean registration;
	
	public ResetPasswordDTO(){
		
	}
	public ResetPasswordDTO(String userId){
		this.userId = userId;
	}
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * @return the verified
	 */
	public boolean isVerified() {
		return verified;
	}
	/**
	 * @param verified the verified to set
	 */
	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	/**
	 * @return the registartion
	 */
	public boolean getRegistartion() {
		return registartion;
	}
	/**
	 * @param registartion the registartion to set
	 */
	public void setRegistartion(boolean registartion) {
		this.registartion = registartion;
	}
	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}
	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the qAuthToken
	 */
	public String getqAuthToken() {
		return qAuthToken;
	}
	/**
	 * @param qAuthToken the qAuthToken to set
	 */
	public void setqAuthToken(String qAuthToken) {
		this.qAuthToken = qAuthToken;
	}
	/**
	 * @return the clientId
	 */
	public String getClientId() {
		return clientId;
	}
	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	public String getgId() {
		return gId;
	}
	public void setgId(String gId) {
		this.gId = gId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOldPwd() {
		return oldPwd;
	}
	public boolean isRegistration() {
		return registration;
	}
	public void setRegistration(boolean registration) {
		this.registration = registration;
	}
	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}
	public boolean isSentEmail() {
		return isSentEmail;
	}
	public void setSentEmail(boolean isSentEmail) {
		this.isSentEmail = isSentEmail;
	}
	
}
