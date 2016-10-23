package com.nvidia.cosmos.cloud.services.appliance.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.nvidia.cosmos.cloud.common.ServicesConstants;
import com.nvidia.cosmos.cloud.common.model.AbstractModel;
import com.nvidia.cosmos.cloud.services.customer.model.Customer;
 
/**
 * @author pbatta
 *
 */
@Entity
@Table(name=ServicesConstants.APPLIANCE_TABLE_NAME)
public class Appliance extends AbstractModel{
	
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
    @NotEmpty(message="Service Key should not be empty")
    @Column(name = "SERVICE_KEY", nullable = false)
    private String serviceKey;
    
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
    @ManyToOne
    @JoinColumn(name="CUSTOMER_ID")
    private Customer customer;
    
    /**
     * 
     */
    public Appliance(){
    	
    }
	/**
	 * @param id
	 * @param name
	 * @param email
	 * @param phone
	 */
	public Appliance(String name, String serviceKey, Customer customer) {
		super();
		this.name = name;
		this.serviceKey = serviceKey;
		this.createdDate = new Date();
		this.customer = customer;
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
	 * @return the serviceKey
	 */
	public String getServiceKey() {
		return serviceKey;
	}
	/**
	 * @param serviceKey the serviceKey to set
	 */
	public void setServiceKey(String serviceKey) {
		this.serviceKey = serviceKey;
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
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}
	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Appliance [id=" + id + ", name=" + name + ", serviceKey=" + serviceKey + ", createdDate=" + createdDate
				+ ", updatedDate=" + updatedDate + ", customer=" + customer + "]";
	}
	
		
}
