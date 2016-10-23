/**
 * 
 */
package com.nvidia.cosmos.cloud.dtos;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @author bprasad
 *
 */
public class RegisterDTO implements BaseDTO{
	public static final int MAX_LENGTH_NAME = 100;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(required = true, value="Mandatory")
	@NotEmpty
    @Length(max = MAX_LENGTH_NAME)
	private String name;
	
	
	/**
	 * 
	 */
	@ApiModelProperty(required = true, value="Mandatory")
	@NotEmpty(message = "Email should not be empty")
	@Size(max=128, message="Email should be maxiumum 128 characters")
	@Pattern(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",message="Email is not valid")
	private String email;
	/**
	 * 
	 */
	@Digits(fraction = 0, integer = 10)
	private String phone;
	/**
	 * 
	 */
	@ApiModelProperty(required = true, value="Mandatory")
	@NotEmpty
	@Pattern(regexp = "^[a-zA-Z0-9._-]{4,50}$")
	private String key;
	
	/**
	 * 
	 */
	private String eulaAccepted;
	
	/**
	 * 
	 */
	private String gAuthToken;
	
	/**
	 * 
	 */
	private String gOrgId;
	
	
	public RegisterDTO(){
	}
	
	public RegisterDTO(String name, String email, String phone, String key){
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.key = key;
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
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}


	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
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
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	public String getEulaAccepted() {
		return eulaAccepted;
	}

	public void setEulaAccepted(String eulaAccepted) {
		this.eulaAccepted = eulaAccepted;
	}

	public String getgAuthToken() {
		return gAuthToken;
	}

	public void setgAuthToken(String gAuthToken) {
		this.gAuthToken = gAuthToken;
	}

	public String getgOrgId() {
		return gOrgId;
	}

	public void setgOrgId(String gOrgId) {
		this.gOrgId = gOrgId;
	}

	@Override
	public String toString() {
		return "RegisterDTO [name=" + name + ", email=" + email + ", phone="
				+ phone + ", key=" + key + ", eulaAccepted=" + eulaAccepted
				+ ", gAuthToken=" + gAuthToken + ", gOrgId=" + gOrgId + "]";
	}

	
	
	
	
}
