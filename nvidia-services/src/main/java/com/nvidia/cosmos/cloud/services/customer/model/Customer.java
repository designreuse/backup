package com.nvidia.cosmos.cloud.services.customer.model;

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
import com.nvidia.cosmos.cloud.services.appliance.model.Appliance;
import com.nvidia.cosmos.cloud.services.cluster.model.Cluster;
import com.nvidia.cosmos.cloud.services.entitlement.model.Entitlement;
import com.nvidia.cosmos.cloud.services.token.model.Token;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
 
/**
 * @author pbatta
 *
 */
@Entity
@Table(name=ServicesConstants.CUSTOMER_TABLE_NAME)
public class Customer extends AbstractModel{
	
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
    @Column(name = "NAME", nullable = false,unique=true)
    private String name;
    
    
    /**
     * 
     */
    @NotEmpty(message="Email should not be empty")
    @Column(name = "EMAIL", nullable = false)
    private String email;
    
    /**
     * 
     */
   // @NotEmpty(message="Phone should not be empty")
    //@Size(min=10, max=14, message="Phone should be minimum 10 and maximum 14")
    @Column(name = "PHONE", nullable = true)
    private String phone;
    
    /**
     * 
     */
    @Column(name = "G_AUTH_TOKEN", nullable = true, unique = true)
    private String grafanaAuthToken;
    
    @Column(name = "G_ORG_USER_ID", nullable = true, unique = true)
    private String grafanaOrgUserId;
    
    
    @Column(name = "G_ORG_ID", nullable = true, unique = true)
    private String grafanaOrgId;
    
    /**
     * 
     */
    @Column(name = "CLIENT_ID", nullable = true)
    private String clientId;
    
    /**
     * 
     */
    @Column(name = "EULA_ACCEPTED", nullable = true)
    private String eulaAccepted;
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
	
		@OneToMany
		@Cascade(CascadeType.DELETE)
		@JoinColumn(name="CUSTOMER_ID")
		private Set<User> users;
		
		@OneToMany
		@Cascade(CascadeType.DELETE)
	    @JoinColumn(name="CUSTOMER_ID")
		private Set<Entitlement> entitlements;
		
		@OneToMany
		@Cascade(CascadeType.DELETE)
	    @JoinColumn(name="CUSTOMER_ID")
		private Set<Appliance> appliances;
		
		@OneToMany
		@Cascade(CascadeType.DELETE)
	    @JoinColumn(name="CUSTOMER_ID")
		private Set<Cluster> clusters;
		
		@OneToMany
		@Cascade(CascadeType.DELETE)
	    @JoinColumn(name="CUSTOMER_ID")
		private Set<Token> tokens;
		
    /**
     * 
     */
    public Customer(){
    	
    }
    
	/**
	 * @param id
	 * @param name
	 * @param email
	 * @param phone
	 */
	public Customer(String name, String email, String phone) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.createdDate = new Date();
	}
	

	/**
	 * @param id
	 * @param name
	 * @param email
	 * 
	 */
	public Customer(String name, String email) {
		super();
		this.name = name;
		this.email = email;
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

	public String getEulaAccepted() {
		return eulaAccepted;
	}
	public void setEulaAccepted(String eulaAccepted) {
		this.eulaAccepted = eulaAccepted;
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
	
	
	    /**
		 * @return the grafanaOrgUserId
		 */
	    public String getGrafanaOrgUserId() {
	    	return grafanaOrgUserId;
	    }
	    /**
		 * @param grafanaOrgUserId the grafanaOrgUserId to set
		 */
	    public void setGrafanaOrgUserId(String grafanaOrgUserId) {
	    	this.grafanaOrgUserId = grafanaOrgUserId;
	    }
	    /**
		 * @return the grafanaAuthToken
		 */
	    public String getGrafanaAuthToken() {
	    	return grafanaAuthToken;
	    }
	    /**
		 * @param grafanaAuthToken the grafanaAuthToken to set
		 */
	    public void setGrafanaAuthToken(String grafanaAuthToken) {
	    	this.grafanaAuthToken = grafanaAuthToken;
	    }
	    /**
		 * @return the grafanaOrgId
		 */
	    public String getGrafanaOrgId() {
	    	return grafanaOrgId;
	    }
	    /**
		 * @param grafanaOrgId the grafanaOrgId to set
		 */
	    public void setGrafanaOrgId(String grafanaOrgId) {
	    	this.grafanaOrgId = grafanaOrgId;
	    }
	    
	    /* (non-Javadoc)
	     * @see java.lang.Object#toString()
	     */
		@Override
		public String toString() {
			return "Customer [id=" + id + ", name=" + name + ", email=" + email
					+ ", phone=" + phone + ", grafanaAuthToken="
					+ grafanaAuthToken + ", grafanaOrgUserId="
					+ grafanaOrgUserId + ", grafanaOrgId=" + grafanaOrgId
					+ ", clientId=" + clientId + ", eulaAccepted="
					+ eulaAccepted + ", createdDate=" + createdDate
					+ ", updatedDate=" + updatedDate + ", users=" + users
					+ ", entitlements=" + entitlements + ", appliances="
					+ appliances + ", clusters=" + clusters + ", tokens="
					+ tokens + "]";
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
