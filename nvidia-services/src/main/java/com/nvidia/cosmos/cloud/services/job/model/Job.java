package com.nvidia.cosmos.cloud.services.job.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.nvidia.cosmos.cloud.common.ServicesConstants;
import com.nvidia.cosmos.cloud.common.model.AbstractModel;
import com.nvidia.cosmos.cloud.services.customer.model.Customer;
import com.nvidia.cosmos.cloud.services.node.model.Node;
import com.nvidia.cosmos.cloud.services.user.model.User;

@Entity
@Table(name = ServicesConstants.JOB_TABLE_NAME)
public class Job extends AbstractModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * 
	 */
	@Column(name = "NAME", nullable = false)
	private String name;

	/**
	 * 
	 */
	@Column(name = "REQUEST_JSON", length = 65535)
	private String requestJson;

	/**
	 * 
	 */
	@Column(name = "RESPONSE_JSON", length = 65535, nullable = true)
	private String responseJson;

	/**
	 * 
	 */
	@Column(name = "UPDATED_DATE", nullable = true)
	private Date updatedDate;

	/**
	 * 
	 */
	@Column(name = "CREATED_DATE", nullable = false)
	private Date createdDate;

	/**
	 * 
	 */
	@Column(name = "STATUS", nullable = true)
	private String status;

	/**
	 * 
	 */
	@Column(name = "SERVICE_TYPE", nullable = false)
	private String serviceType;

	/**
	 * 
	 */
	@Column(name = "IS_DELETED", nullable = true)
	private String isDeleted;

	/**
	 * 
	 */
	@Column(name = "TIME_STARTED", nullable = true)
	private Date timeStarted;

	/**
	 * 
	 */
	@Column(name = "SERVICE_HELTH", nullable = true)
	private String serviceHelth;

	/**
	 * 
	 */
	@Column(name = "UNIQUE_ID", nullable = true)
	private String uniqueId;

	/**
	 * 
	 */
	@Column(name = "CONTAINER_NAME", nullable = true,length = 65535)
	private String dockerContainerName;

	/**
	 * 
	 */
	@Column(name = "IP_ADDRESS", nullable = true)
	private String ipAddress;

	/**
	 * 
	 */
	@Column(name = "NFS_VOLUME", nullable = true,length = 65535)
	private String nfsVolume;

	/**
	 * 
	 */
	@Column(name = "LOCAL_VOLUME", nullable = true,length = 65535)
	private String localVolume;
	/**
	 * 
	 */
	@Column(name = "SCHEDULER_PARAMETERS", nullable = true,length = 65535)
	private String schedulerParameters;
	/**
	 * 
	 */
	@Column(name = "APPLICATION_PARAMETRS", nullable = true,length = 1000)
	private String applicationParametrs;
	/**
	 * 
	 */
	@Column(name = "CONTAINER_PORTS", nullable = true,length = 2000)
	private String containerPorts;

	/**
	 * 
	 */
	@Column(name = "HOST_PORTS", nullable = true,length = 1000)
	private String hostPorts;
	/**
	 * 
	 */
	@Column(name = "FORCE_PULL_IMAGE", nullable = true)
	private Boolean forcePullImage;

	/**
	 * 
	 */
	@Column(name = "OWNER_NAME", nullable = true)
	private String ownerName;

	/**
	 * 
	 */
	@Column(name = "IMAGE", nullable = true)
	private String image;

	/**
	 * 
	 */
	@Column(name = "PORTS", nullable = true)
	private String ports;

	/**
	 * 
	 */
	@Column(name = "SERIAL_NUMBER", nullable = true)
	private String serialNumber;

	/**
	 * 
	 */
	@Column(name = "MESOS_SLAVE", nullable = true, length = 65535)
	private String mesosSlaveName;

	/**
	 * 
	 */
	@Column(name = "HOST", nullable = true)
	private String host;
	/**
	 * 
	 */
	@Column(name = "MESSAGE", nullable = true,length = 65535)
	private String message;
	/**
	 * 
	 */
	@Column(name = "SLAVE_ID", nullable = true)
	private String slaveId;
	/**
	 * 
	 */
	@Column(name = "STATE", nullable = true)
	private String state;
	/**
	 * 
	 */
	@Column(name = "TASK_ID", nullable = true)
	private String taskId;
	/**
	 * 
	 */
	@Column(name = "VERSION", nullable = true)
	private String version;
	/**
	 * 
	 */
	@Column(name = "TIME_STAMP", nullable = true)
	private String timestamp;
	
	
	/**
	 * 
	 */
	@ManyToOne(targetEntity = Customer.class)
	@JoinColumn(name = "CUSTOMER_ID")
	private Customer customer;
	

	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * 
	 */
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "USER_ID")
	private User user;

	/**
	 * 
	 */
	@ManyToOne(targetEntity = Node.class)
	@JoinColumn(name = "NODE_ID")
	private Node node;

	public Job() {
	}
	
	public Job(Long id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
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
	 * @return the requestJson
	 */
	public String getRequestJson() {
		return requestJson;
	}

	/**
	 * @param requestJson
	 *            the requestJson to set
	 */
	public void setRequestJson(String requestJson) {
		this.requestJson = requestJson;
	}

	/**
	 * @return the responseJson
	 */
	public String getResponseJson() {
		return responseJson;
	}

	/**
	 * @param responseJson
	 *            the responseJson to set
	 */
	public void setResponseJson(String responseJson) {
		this.responseJson = responseJson;
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
	 * @return the serviceType
	 */
	public String getServiceType() {
		return serviceType;
	}

	/**
	 * @param serviceType
	 *            the serviceType to set
	 */
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	/**
	 * @return the isDeleted
	 */
	public String getIsDeleted() {
		return isDeleted;
	}

	/**
	 * @param isDeleted
	 *            the isDeleted to set
	 */
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	/**
	 * @return the timeStarted
	 */
	public Date getTimeStarted() {
		return timeStarted;
	}

	/**
	 * @param timeStarted
	 *            the timeStarted to set
	 */
	public void setTimeStarted(Date timeStarted) {
		this.timeStarted = timeStarted;
	}

	/**
	 * @return the serviceHelth
	 */
	public String getServiceHelth() {
		return serviceHelth;
	}

	/**
	 * @param serviceHelth
	 *            the serviceHelth to set
	 */
	public void setServiceHelth(String serviceHelth) {
		this.serviceHelth = serviceHelth;
	}

	/**
	 * @return the uniqueId
	 */
	public String getUniqueId() {
		return uniqueId;
	}

	/**
	 * @param uniqueId
	 *            the uniqueId to set
	 */
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	/**
	 * @return the dockerContainerName
	 */
	public String getDockerContainerName() {
		return dockerContainerName;
	}

	/**
	 * @param dockerContainerName
	 *            the dockerContainerName to set
	 */
	public void setDockerContainerName(String dockerContainerName) {
		this.dockerContainerName = dockerContainerName;
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
	 * @return the node
	 */
	public Node getNode() {
		return node;
	}

	/**
	 * @param node
	 *            the node to set
	 */
	public void setNode(Node node) {
		this.node = node;
	}

	/**
	 * @return the nfsVolume
	 */
	public String getNfsVolume() {
		return nfsVolume;
	}

	/**
	 * @param nfsVolume
	 *            the nfsVolume to set
	 */
	public void setNfsVolume(String nfsVolume) {
		this.nfsVolume = nfsVolume;
	}

	/**
	 * @return the localVolume
	 */
	public String getLocalVolume() {
		return localVolume;
	}

	/**
	 * @param localVolume
	 *            the localVolume to set
	 */
	public void setLocalVolume(String localVolume) {
		this.localVolume = localVolume;
	}

	/**
	 * @return the schedulerParameters
	 */
	public String getSchedulerParameters() {
		return schedulerParameters;
	}

	/**
	 * @param schedulerParameters
	 *            the schedulerParameters to set
	 */
	public void setSchedulerParameters(String schedulerParameters) {
		this.schedulerParameters = schedulerParameters;
	}

	/**
	 * @return the applicationParametrs
	 */
	public String getApplicationParametrs() {
		return applicationParametrs;
	}

	/**
	 * @param applicationParametrs
	 *            the applicationParametrs to set
	 */
	public void setApplicationParametrs(String applicationParametrs) {
		this.applicationParametrs = applicationParametrs;
	}

	/**
	 * @return the containerPorts
	 */
	public String getContainerPorts() {
		return containerPorts;
	}

	/**
	 * @param containerPorts
	 *            the containerPorts to set
	 */
	public void setContainerPorts(String containerPorts) {
		this.containerPorts = containerPorts;
	}

	/**
	 * @return the forcePullImage
	 */
	public Boolean getForcePullImage() {
		return forcePullImage;
	}

	/**
	 * @param forcePullImage
	 *            the forcePullImage to set
	 */
	public void setForcePullImage(Boolean forcePullImage) {
		this.forcePullImage = forcePullImage;
	}

	/**
	 * @return the ownerName
	 */
	public String getOwnerName() {
		return ownerName;
	}

	/**
	 * @param ownerName
	 *            the ownerName to set
	 */
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * @return the ports
	 */
	public String getPorts() {
		return ports;
	}

	/**
	 * @param ports
	 *            the ports to set
	 */
	public void setPorts(String ports) {
		this.ports = ports;
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
	 * @return the mesosSlaveName
	 */
	public String getMesosSlaveName() {
		return mesosSlaveName;
	}

	/**
	 * @param mesosSlaveName
	 *            the mesosSlaveName to set
	 */
	public void setMesosSlaveName(String mesosSlaveName) {
		this.mesosSlaveName = mesosSlaveName;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the slaveId
	 */
	public String getSlaveId() {
		return slaveId;
	}

	/**
	 * @param slaveId
	 *            the slaveId to set
	 */
	public void setSlaveId(String slaveId) {
		this.slaveId = slaveId;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the taskId
	 */
	public String getTaskId() {
		return taskId;
	}

	/**
	 * @param taskId
	 *            the taskId to set
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp
	 *            the timestamp to set
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host
	 *            the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}

	public String getHostPorts() {
		return hostPorts;
	}

	public void setHostPorts(String hostPorts) {
		this.hostPorts = hostPorts;
	}

}
