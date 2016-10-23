package com.nvidia.cosmos.cloud.dtos;

/**
 * @author bprasad
 *
 */
public class UserAuthDTO implements BaseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 
	 */
	private String authKey;
	private String name;
	private String email;
	private String roleName;
	private String message;
	private String isRegistered;
	private String customerId;
	private String userId;
	private String userName;
	
	
	
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @param authKey
	 * @param name
	 * @param email
	 * @param roleName
	 * @param message
	 * 
	 */
	public UserAuthDTO(String authKey, String name, String email, String roleName, String message, String userName, String customerId, String userId) {
		super();
		
		this.authKey = authKey;
		this.name = name;
		this.email = email;
		this.roleName = roleName;
		this.message = message;
		this.userName = userName;
		this.userId = userId;
		this.customerId = customerId;
		
	}
	
	/**
	 * @return the authKey
	 */
	public String getAuthKey() {
		return authKey;
	}
	/**
	 * @param authKey the authKey to set
	 */
	public void setAuthKey(String authKey) {
		this.authKey = authKey;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the customerId
	 */
	public String getCustomerId() {
		return customerId;
	}
	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getIsRegistered() {
		return isRegistered;
	}

	public void setIsRegistered(String isRegistered) {
		this.isRegistered = isRegistered;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	

}
