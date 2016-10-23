package com.nvidia.cosmos.cloud.services.cluster.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.URL;

import com.nvidia.cosmos.cloud.common.ServicesConstants;
import com.nvidia.cosmos.cloud.common.model.AbstractModel;
import com.nvidia.cosmos.cloud.services.customer.model.Customer;
import com.nvidia.cosmos.cloud.services.node.model.Node;

/**
 * @author pbatta
 *
 */
@Entity
@Table(name = ServicesConstants.CLUSTER_TABLE_NAME)
public class Cluster extends AbstractModel {

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
	@Size(min = 4, max = 32)
	@Column(name = "NAME", nullable = false)
	private String name;

	/**
	 * 
	 */
	
	@Column(name = "IP_ADDRESS")
	private String ipAddress;

	/**
	 * 
	 */
	@Column(name = "NFS_DETAILS")
	private String nfsDetails;

	/**
	 * 
	 */
	@Size(max = 2000, message = "Serial number should be maxiumum 2000")
	@Column(name = "SERIAL_NUMBER",unique=true)
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
	@ManyToOne
	@JoinColumn(name = "CUSTOMER_ID")
	private Customer customer;
	/**
	 * 
	 */
	@OneToMany
	@Cascade(CascadeType.DELETE)
	@JoinColumn(name = "CLUSTER_ID")
	private Set<Node> nodes;

	@Column(name = "UPGRADE_ON_BOOT", nullable = true)
	private String upgradeOnBoot;


	/**
	 * 
	 */
	public Cluster() {

	}

	/**
	 * 
	 * @param name
	 * @param ipAddress
	 * @param nfsDetails
	 * @param serialNumber
	 * @param customer
	 */
	public Cluster(String name, String ipAddress, String nfsDetails, String serialNumber, Customer customer) {
		super();
		this.name = name;
		this.ipAddress = ipAddress;
		this.customer = customer;
		this.nfsDetails = nfsDetails;
		this.serialNumber = serialNumber;
		this.createdDate = new Date();
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
	 * @return the nfsDetails
	 */
	public String getNfsDetails() {
		return nfsDetails;
	}

	/**
	 * @param nfsDetails
	 *            the nfsDetails to set
	 */
	public void setNfsDetails(String nfsDetails) {
		this.nfsDetails = nfsDetails;
	}

	/**
	 * @return the ipAddress
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * @param ipAddress
	 *            the ipAddress to set
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
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
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
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
	 * @return the serialNumber
	 */
	public String getSerialNumber() {
		return serialNumber;
	}

	/**
	 * @param serialNumber
	 *            the serialNumber to set
	 */
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	/**
	 * @return the nodes
	 */
	public Set<Node> getNodes() {
		return nodes;
	}

	/**
	 * @param nodes
	 *            the nodes to set
	 */
	public void setNodes(Set<Node> nodes) {
		this.nodes = nodes;
	}

	public String getUpgradeOnBoot() {
		return upgradeOnBoot;
	}

	public void setUpgradeOnBoot(String upgradeOnBoot) {
		this.upgradeOnBoot = upgradeOnBoot;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */

	@Override
	public String toString() {
		return "Cluster [id=" + id + ", name=" + name + ", ipAddress=" + ipAddress + ", nfsDetails=" + nfsDetails
				+ ", serialNumber=" + serialNumber + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate
				+ ", customer=" + customer + ", nodes=" + nodes + ", upgradeOnBoot=" + upgradeOnBoot + "]";
	}

}
