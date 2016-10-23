/**
 * 
 */
package com.nvidia.cosmos.cloud.dtos;

import java.util.LinkedList;
import java.util.List;

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
public class CustomerDTO implements BaseDTO{
	public static final int MAX_LENGTH_NAME = 100;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	
	@ApiModelProperty(required = true, value="Mandatory")
	@NotEmpty
    @Length(max = MAX_LENGTH_NAME)
	private String name;
	/**
	 * 
	 */
	@ApiModelProperty(required = true, value="Mandatory")
	@NotEmpty
	private String email;
	/**
	 * 
	 */
	private String phone;
	
	/**
	 * 
	 */
	private List<UserDTO> users = new LinkedList<UserDTO>();
	
	/**
	 * 
	 */
	public CustomerDTO(){
	}
	
	public CustomerDTO(String name, String email, String phone){
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
	 * @return the users
	 */
	public List<UserDTO> getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(List<UserDTO> users) {
		this.users = users;
	}
	
	/**
	 * @param user
	 */
	public void addUser(UserDTO user) {
		this.users.add(user);
	}
	
}
