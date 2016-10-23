package com.nvidia.cosmos.cloud.services.userauth.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nvidia.cosmos.cloud.common.ServicesConstants;
import com.nvidia.cosmos.cloud.common.model.AbstractModel;
 
/**
 * @author pbatta
 *
 */
@Entity
@Table(name=ServicesConstants.USERAUTH_TABLE_NAME)
public class UserAuth extends AbstractModel{
	
	/**
	 * 
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 /**
     * 
     */
    @Column(name = "AUTH_TOKEN", nullable = true)
    private String authToken;
    /**
     * 
     */
    @Column(name = "NAME", nullable = true)
    private String name;
    /**
     * 
     */
    @Column(name = "EMAIL", nullable = true)
    private String email;
    /**
     * 
     */
    @Column(name = "EXT_TOKEN", nullable = true)
    private String extToken;
    
    
   /* public String getGrafanaToken() {
		return grafanaToken;
	}
	public void setGrafanaToken(String grafanaToken) {
		this.grafanaToken = grafanaToken;
	}
	@Column(name = "GRAFANA_TOKEN", nullable = true)
    private String grafanaToken;*/
    /**
     * 
     */
    @Column(name = "ROLE_NAME", nullable = true)
    private String roleName;
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
	/**
     * 
     */
	
	@Column(name = "AUTH_DETAILS", nullable=true,length = 100000)
	private byte[] authenticationWithToken;
	
	
    
	public byte[] getAuthenticationWithToken() {
		return authenticationWithToken;
	}
	public void setAuthenticationWithToken(byte[] authenticationWithToken) {
		this.authenticationWithToken = authenticationWithToken;
	}
	public UserAuth(){
    	
    }
	/**as
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the authKey
	 */
	public String getAuthToken() {
		return authToken;
	}
	/**
	 * @param authKey the authKey to set
	 */
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
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
	 * @return the extToken
	 */
	public String getExtToken() {
		return extToken;
	}
	/**
	 * @param extToken the extToken to set
	 */
	public void setExtToken(String extToken) {
		this.extToken = extToken;
	}
	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}
	/**
	 * @param createdDate the createdDate to set
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
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
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
	 * @param authToken
	 * @param name
	 * @param email
	 */
	public UserAuth(String authToken, String name, String email, String roleName) {
		super();
		this.authToken = authToken;
		this.name = name;
		this.email = email;
		this.roleName = roleName;
		this.createdDate = new Date();
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserAuth [id=" + id + ", authToken=" + authToken + ", name=" + name + ", extToken=" + extToken
				+ ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + "]";
	}
	
}
