package com.nvidia.cosmos.cloud.services.entitlement.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
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
@Table(name=ServicesConstants.ENTITLEMENT_TABLE_NAME, uniqueConstraints=@UniqueConstraint( columnNames = {"ENTITLE_KEY", "HW_SERIAL_NUMBER"}))
public class Entitlement extends AbstractModel{
	
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
    @Column(name = "CUSTOMER_NAME", nullable = false)
    private String customerName;
    
    /**
     * 
     */
    @NotEmpty(message="Key should not be empty")
    @Column(name = "ENTITLE_KEY", nullable = false, unique = true)
    private String key;
    /**
     * 
     */
    @NotEmpty(message="Hard ware serial number should not be empty")
    @Column(name = "HW_SERIAL_NUMBER", nullable = false, unique = true)
    private String serialNumber;
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
    //@NotEmpty(message="InfluxdbUrl should not be empty")
    @Column(name = "INFLUX_DB_URL", nullable = true)
    private String influxdbUrl;
    
    @Column(name = "DATA_BASE_NAME", nullable = true)
    private String databaseName;
    
    public String getDatabaseName() {
		return databaseName;
	}


	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}


	/**
     * 
     */
   // @NotEmpty(message="InfluxdbUser should not be empty")
    @Column(name = "INFLUX_DB_USER", nullable = true)
    private String influxdbUser;
    /**
     * 
     */
   // @NotEmpty(message="InfluxdbPassword should not be empty")
    @Column(name = "INFLUX_DB_PASSWORD", nullable = true)
    private String influxdbPassword;
	
	/**
	 * 
	 */
	@ManyToOne
    @JoinColumn(name="CUSTOMER_ID")
	private Customer customer;
    /**
     * 
     */
    public Entitlement(){
    	
    }
	
	
	/**
	 * @param customerName
	 * @param key
	 */
	public Entitlement(String customerName, String key, String serialNumber) {
		super();
		this.customerName = customerName;
		this.key = key;
		this.createdDate = new Date();
		this.serialNumber = serialNumber;
	}

	/**
	 * @return the influxdbUrl
	 */
	 public String getInfluxdbUrl() {
	    	return influxdbUrl;
	    }
	 /**
		 * @param influxdbUrl the influxdbUrl to set
		 */
	    public void setInfluxdbUrl(String influxdbUrl) {
	    	this.influxdbUrl = influxdbUrl;
	    }
	    /**
		 * @return the influxdbUser
		 */
	    public String getInfluxdbUser() {
	    	return influxdbUser;
	    }
	    /**
		 * @param influxdbUser the influxdbUser to set
		 */
	    public void setInfluxdbUser(String influxdbUser) {
	    	this.influxdbUser = influxdbUser;
	    }
	    /**
		 * @return the influxdbPassword
		 */
	    public String getInfluxdbPassword() {
	    	return influxdbPassword;
	    }
	    /**
		 * @param influxdbPassword the influxdbPassword to set
		 */
	    public void setInfluxdbPassword(String influxdbPassword) {
	    	this.influxdbPassword = influxdbPassword;
	    }

	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}


	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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
	 * @return the serialNumber
	 */
	public String getSerialNumber() {
		return serialNumber;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	
	@Override
	public String toString() {
		return "Entitlement [id=" + id + ", customerName=" + customerName
				+ ", key=" + key + ", serialNumber=" + serialNumber
				+ ", createdDate=" + createdDate + ", updatedDate="
				+ updatedDate + ", influxdbUrl=" + influxdbUrl
				+ ", influxdbUser=" + influxdbUser + ", influxdbPassword="
				+ influxdbPassword + "]";
	}


	/**
	 * @param serialNumber the serialNumber to set
	 */
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	
	/**
	 * @param name
	 * @param email
	 * @param phoneNumber
	 * @param createdDate
	 * @return
	 
	public static Builder getBuilder(String name, String email, String phoneNumber, Date createdDate) {
		return new Builder(name, email, phoneNumber, createdDate);
	}
	/**
	 * @author pbatta
	 *
	 
	public static class Builder {

		private Customer built;

		public Builder(String name, String email, String phoneNumber, Date createdDate) {
			built = new Customer();
			built.setName(name);
			built.setEmail(email);
			built.setCreatedDate(createdDate);
			built.setPhone(phoneNumber);
		}

		public Customer build() {
			return built;
		}

		public Builder name(String name) {
			built.name = name;
			return this;
		}
		public Builder email(String email) {
			built.email = email;
			return this;
		}
		public Builder phoneNumber(String phoneNumber) {
			built.phone = phoneNumber;
			return this;
		}
		
	}*/
     
}
