package com.nvidia.cosmos.cloud.services.role.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.nvidia.cosmos.cloud.common.ServicesConstants;
import com.nvidia.cosmos.cloud.common.model.AbstractModel;
import com.nvidia.cosmos.cloud.services.user.model.User;
 
/**
 * @author pbatta
 *
 */
@Entity
@Table(name=ServicesConstants.ROLE_TABLE_NAME)
public class Role extends AbstractModel{
	
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
    @Size(min=3, max=50, message="Name should be minimum 3 and maxiumum 50")
    @Column(name = "NAME", nullable = false)
    private String name;
    
    /**
     * 
     */
    @NotEmpty(message="Description should not be empty")
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;
    
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
	
	@OneToMany
    @JoinColumn(name="ROLE_ID")
	private Set<User> users;
    /**
     * 
     */
    public Role(){
    	
    }
	/**
	 * @param id
	 * @param name
	 * @param email
	 * @param phone
	 */
	public Role(String name, String description) {
		super();
		this.name = name;
		this.description = description;
		this.createdDate = new Date();
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * @return the users
	 */
	public Set<User> getUsers() {
		return users;
	}
	/**
	 * @param users the users to set
	 */
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Role [id=" + this.getId() + ", name=" + name + ", description=" + description +"]";
	}
	
     
}
