package com.nvidia.cosmos.cloud.services.user.model;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.nvidia.cosmos.cloud.common.ServicesConstants;
import com.nvidia.cosmos.cloud.common.UserStatusEnum;
import com.nvidia.cosmos.cloud.common.model.AbstractModel;
import com.nvidia.cosmos.cloud.services.customer.model.Customer;
import com.nvidia.cosmos.cloud.services.role.model.Role;

/**
 * @author pbatta
 *
 */
@Entity
@Table(name = ServicesConstants.USER_TABLE_NAME)
public class User extends AbstractModel {

	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	
	@Column(name = "NAME", nullable = false)
	private String name;

	/**
	 * 
	 */
	@NotEmpty(message = "Email should not be empty")
	@Column(name = "EMAIL", nullable = false)
	private String email;

	/**
	 * 
	 */
	@Column(name = "DOCKER_URI")
	@Lob
	private byte[] dockerUri;

	@Column(name = "UID")
	private Long uId;

	@Column(name = "GID")
	private Long gId;

	@Column(name = "LOGINFAILEDCOUNT", nullable = true)
	private int loginFailedCount = 0;
	@Column(name = "LASTLOGINTIME", nullable = true)
	private Long lastLoginTime = 0L;

	@Column(name = "IS_SENT_EMAIL")
	private boolean isSentEmail=false;

	@Column(name = "PASSWORD_KEY", nullable= true)
	private String passwordKey;	

/*	@Column(name = "DAY_EMAIL_TIME", nullable= true)
	private String dayEmailTime;
	
	public String getDayEmailTime() {
		return dayEmailTime;
	}

	public void setDayEmailTime(String dayEmailTime) {
		this.dayEmailTime = dayEmailTime;
	}*/

	public Long getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Long lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	
	public int getLoginFailedCount() {
		return loginFailedCount;
	}
	
	public void setLoginFailedCount(int loginFailedCount) {
		this.loginFailedCount = loginFailedCount;
	}

	/**
	 * 
	 */
	// @NotEmpty(message="Phone should not be empty")
	// @Size(min=10, max=14, message="Phone should be minimum 10 and maximum
	// 14")
	@Column(name = "PHONE", nullable = true)
	private String phone;

	@Column(name = "USERNAME", nullable = true, unique = true)
	private String userName;

	@Column(name = "Q_AUTH_TOKEN", nullable = true)
	private String qAuthToken;

	/**
	 * 
	 */
	@Column(name = "CREATED_DATE", nullable = false)
	private Date createdDate;

	/**
	 * 
	 */
	@Column(name = "UPDATED_DATE", nullable = true)
	private Date updatedDate;

	@Column(name = "LAST_LOGIN", nullable = true)
	private Date lastLogin;
	/**
	* 
	*/
	@ManyToOne
	@JoinColumn(name = "ROLE_ID")
	private Role role;
	/**
	 * 
	 */
	@ManyToOne
	@JoinColumn(name = "CUSTOMER_ID")
	private Customer customer;

	/**
	 * 
	 */
	@Column(name = "PASSWORD", nullable = true)
	private String password;
	/**
	 * 
	 */
	@Column(name = "STATUS", nullable = false)
	private String status;

	/**
	 * 
	 */
	public User() {

	}

	/**
	 * @param id
	 * @param name
	 * @param email
	 * @param phone
	 */
	public User(String name, String email, String phone, Role role, Customer customer) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.createdDate = new Date();
		this.role = role;
		this.customer = customer;
		this.status = UserStatusEnum.REGISTERED.toString();

	}
	
	public User(String name, String email, Role role, Customer customer) {
		super();
		this.name = name;
		this.email = email;
		this.createdDate = new Date();
		this.role = role;
		this.customer = customer;
		this.status = UserStatusEnum.REGISTERED.toString();

	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate
	 *            the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the updatedDate
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * @param updatedDate
	 *            the updatedDate to set
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer
	 *            the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the qAuthToken
	 */
	public String getqAuthToken() {
		return qAuthToken;
	}

	/**
	 * @param qAuthToken
	 *            the qAuthToken to set
	 */
	public void setqAuthToken(String qAuthToken) {
		this.qAuthToken = qAuthToken;
	}

	/**
	 * @return the lastLogin
	 */
	public Date getLastLogin() {
		return lastLogin;
	}

	/**
	 * @param lastLogin
	 *            the lastLogin to set
	 */
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	/**
	 * @return the dockerUri
	 */
	public byte[] getDockerUri() {
		return dockerUri;
	}

	/**
	 * @param dockerUri
	 *            the dockerUri to set
	 */
	public void setDockerUri(byte[] dockerUri) {
		this.dockerUri = dockerUri;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */


	public Long getuId() {
		return uId;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", dockerUri=" + Arrays.toString(dockerUri)
				+ ", uId=" + uId + ", gId=" + gId + ", loginFailedCount=" + loginFailedCount + ", lastLoginTime="
				+ lastLoginTime + ", isSentEmail=" + isSentEmail + ", phone=" + phone + ", userName=" + userName
				+ ", qAuthToken=" + qAuthToken + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate
				+ ", lastLogin=" + lastLogin + ", role=" + role + ", customer=" + customer + ", password=" + password
				+ ", status=" + status + "]";
	}

	public void setuId(Long uId) {
		this.uId = uId;
	}

	public Long getgId() {
		return gId;
	}

	public void setgId(Long gId) {
		this.gId = gId;
	}

	public boolean isSentEmail() {
		return isSentEmail;
	}

	public void setSentEmail(boolean isSentEmail) {
		this.isSentEmail = isSentEmail;
	}

	public String getPasswordKey() {
		return passwordKey;
	}

	public void setPasswordKey(String passwordKey) {
		this.passwordKey = passwordKey;
	}
}
