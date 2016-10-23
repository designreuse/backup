/**
 * 
 */
package com.nvidia.cosmos.cloud.dtos;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @author bprasad
 *
 */
@ApiModel
public class UserDTO implements BaseDTO{
	public static final int MAX_LENGTH_NAME = 100;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	@ApiModelProperty(required = true, value="Mandatory")	
	@NotEmpty(message = "Name should not be empty")
	@Size(max=32, message="Full Name should be maxiumum 32 characters")
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
	private String phone;
	/**
	 * 
	 */
	private String pasWd;
	private String userName;
	private Long uId;
	private Long gId;
	private RoleDTO role;
	/**
	 * 
	 */
	private CustomerDTO customer;
	/**
	 * 
	 */
	public UserDTO(){
	}
	
	private byte[]  dockerUri;
	
	 public String getqAuthToken() {
		return qAuthToken;
	}


	public void setqAuthToken(String qAuthToken) {
		this.qAuthToken = qAuthToken;
	}

	private String qAuthToken;
	
	public byte[] getDockerUri() {
		return dockerUri;
	}


	public void setDockerUri(byte[] dockerUri) {
		this.dockerUri = dockerUri;
	}


	public UserDTO(String name, String email, String phone){
		this.name = name;
		this.email = email;
		this.phone = phone;
	}


	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
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
	 * @return the pasWd
	 */
	public String getPasWd() {
		return pasWd;
	}


	/**
	 * @param pasWd the pasWd to set
	 */
	public void setPasWd(String pasWd) {
		this.pasWd = pasWd;
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
	 * @return the customer
	 */
	public CustomerDTO getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(CustomerDTO customer) {
		this.customer = customer;
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
	 * @return the uId
	 */
	public Long getuId() {
		return uId;
	}

	/**
	 * @param uId the uId to set
	 */
	public void setuId(Long uId) {
		this.uId = uId;
	}

	/**
	 * @return the gId
	 */
	public Long getgId() {
		return gId;
	}

	/**
	 * @param gId the gId to set
	 */
	public void setgId(Long gId) {
		this.gId = gId;
	}
	
	public RoleDTO getRole() {
		return role;
	}


	public void setRole(RoleDTO role) {
		this.role = role;
	}
}
